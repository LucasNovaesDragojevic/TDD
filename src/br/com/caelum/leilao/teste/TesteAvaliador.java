package br.com.caelum.leilao.teste;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class TesteAvaliador {

	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		Usuario joao = new Usuario("João");
		Usuario jose = new Usuario("José");
		Usuario maria = new Usuario("Maria");
		
		Leilao leilao = new Leilao("RTX 2080 TI");
		
		leilao.propoe(new Lance(joao, 250.00));
		leilao.propoe(new Lance(jose, 350.00));
		leilao.propoe(new Lance(maria, 400.00));

		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		double menorEsperado = 250;
		double maiorEsperado = 400;
		
		Assert.assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);
		Assert.assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);
	}
}
