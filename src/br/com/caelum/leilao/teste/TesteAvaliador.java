package br.com.caelum.leilao.teste;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class TesteAvaliador {

	private Avaliador leiloeiro;
	private Usuario maria;
	private Usuario jose;
	private Usuario joao;

	@Before
	public void criaAvaliador() {
		this.leiloeiro = new Avaliador();
		joao = new Usuario("João");
		jose = new Usuario("José");
		maria = new Usuario("Maria");
	}
	
	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		Leilao leilao = new Leilao("RTX 2080 TI");
		
		leilao.propoe(new Lance(joao, 250.00));
		leilao.propoe(new Lance(jose, 350.00));
		leilao.propoe(new Lance(maria, 400.00));

		leiloeiro.avalia(leilao);
		
		double menorEsperado = 250;
		double maiorEsperado = 400;
		
		assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);
	}
	
	@Test
	public void deveEntenderLancesEmOrdemDecrescente() {
		
		Leilao leilao = new Leilao("RTX 2080 TI");
		
		leilao.propoe(new Lance(joao, 400.00));
		leilao.propoe(new Lance(maria, 300.00));
		leilao.propoe(new Lance(joao, 200.00));
		leilao.propoe(new Lance(maria, 100.00));

		leiloeiro.avalia(leilao);
		
		double menorEsperado = 100;
		double maiorEsperado = 400;
		
		assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);
	}
	
	@Test
	public void deveEntenderLancesEmOrdemAleatoria() {		
		Leilao leilao = new Leilao("RTX 2080 TI");
		
		leilao.propoe(new Lance(joao, 200.00));
		leilao.propoe(new Lance(maria, 450.00));
		leilao.propoe(new Lance(joao, 120.00));
		leilao.propoe(new Lance(maria, 700.00));
		leilao.propoe(new Lance(joao, 630.00));
		leilao.propoe(new Lance(maria, 230.00));
		
		leiloeiro.avalia(leilao);
		
		double menorEsperado = 120;
		double maiorEsperado = 700;
		
		assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);
	}
	
	@Test
	public void deveEntenderLeilaoComApenasUmLance() {
		Leilao leilao = new Leilao("RTX 2080 TI");
		
		leilao.propoe(new Lance(joao, 1000.00));
		
		leiloeiro.avalia(leilao);

		assertEquals(1000, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(1000, leiloeiro.getMenorLance(), 0.00001);
	}
	
	@Test
	public void deveEncontrarOsTresMaioresLances() {
		Leilao leilao = new CriadorDeLeilao().para("RTX 2080 TI")
												.lance(joao, 100.00)
												.lance(maria, 200.00)
												.lance(joao, 300.00)
												.lance(maria, 400.00)
												.constroi();
		
		leiloeiro.avalia(leilao);
		
		List<Lance> maiores = leiloeiro.getTresMaiores();
		
		assertEquals(3, maiores.size());
		assertEquals(400.00, maiores.get(0).getValor(), 0.00001);
		assertEquals(300.00, maiores.get(1).getValor(), 0.00001);
		assertEquals(200.00, maiores.get(2).getValor(), 0.00001);
	}
}
