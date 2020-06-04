package br.com.alura.leilao.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class LeilaoTest {

    @Test
    public void getDescricao() {
        //Criar Cenario Teste
        final Leilao console = new Leilao("Console");
        //Executar açāo esperada
        final String descricaoDevolvida = console.getDescricao();
        //Testar resultado esperado
        assertEquals("Console", descricaoDevolvida);
    }

    @Test
    public void getMaiorlance(){
        // Cenario
        final Leilao console = new Leilao("Console");
        // Executar
        console.propoe(new Lance(new Usuario("Flavio"),200.0));
        final double maiorLanceDevolvido = console.getMaiorLance();
        // Testar
        assertEquals(200.0,maiorLanceDevolvido,0.0001);
    }
}