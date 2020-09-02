package br.com.caelum.leilao.teste;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class TesteLeilao {

	@Test
	public void deveReceberUmLance() {
		Leilao leilao = new Leilao("Galaxy S20");
		assertEquals(0, leilao.getLances().size());
		
		leilao.propoe(new Lance(new Usuario("Ruberval"), 2000));
		assertEquals(1, leilao.getLances().size());
		assertEquals(2000, leilao.getLances().get(0).getValor(), 0.00001);
	}
	
	@Test
	public void deveReceberVariosLances() {
		Leilao leilao = new Leilao("Galaxy S20");
		leilao.propoe(new Lance(new Usuario("Ruberval"), 2000));
		leilao.propoe(new Lance(new Usuario("Lucimara"), 3000));
		
		assertEquals(2, leilao.getLances().size());
		assertEquals(2000, leilao.getLances().get(0).getValor(), 0.00001);
		assertEquals(3000, leilao.getLances().get(1).getValor(), 0.00001);
	}
	
	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
		Leilao leilao = new Leilao("Galaxy S20");
		Usuario ruberval = new Usuario("Ruberval");
		
		leilao.propoe(new Lance(ruberval, 2000));
		leilao.propoe(new Lance(ruberval, 3000));
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(2000, leilao.getLances().get(0).getValor(), 0.00001);

	}
	
	@Test
	public void naoDeveAceitarMaisDoQue5LancesDeUmMesmoUsuario() {
		Leilao leilao = new Leilao("Galaxy S20");
		Usuario ruberval = new Usuario("Ruberval");
		Usuario lucimara = new Usuario("Lucimara");
		
		leilao.propoe(new Lance(ruberval, 2000));
		leilao.propoe(new Lance(lucimara, 3000));
		
		leilao.propoe(new Lance(ruberval, 4000));
		leilao.propoe(new Lance(lucimara, 5000));
		
		leilao.propoe(new Lance(ruberval, 6000));
		leilao.propoe(new Lance(lucimara, 7000));
		
		leilao.propoe(new Lance(ruberval, 8000));
		leilao.propoe(new Lance(lucimara, 9000));
		
		leilao.propoe(new Lance(ruberval, 10000));
		leilao.propoe(new Lance(lucimara, 11000));
		
		// Deve ignorar este
		leilao.propoe(new Lance(ruberval, 12000));
		
		assertEquals(10, leilao.getLances().size());
		assertEquals(11000, leilao.getLances().get(leilao.getLances().size() - 1).getValor(), 0.00001);
	}
}
