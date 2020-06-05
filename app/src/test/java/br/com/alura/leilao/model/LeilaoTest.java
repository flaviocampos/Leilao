package br.com.alura.leilao.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class LeilaoTest {

    //Criar Cenario Teste
    private final Leilao LEILAO = new Leilao("MacBook Air 13 inc 8GB - SSD 128");
    private final Usuario FLAVIO = new Usuario("Flavio Campos");
    private final Usuario LUISA = new Usuario("Luísa Nader");

    @Test
    public void obterDescricao() {

        //Executar açāo esperada
        final String descricaoDevolvida = LEILAO.getDescricao();
        //Testar resultado esperado
        assertEquals("MacBook Air 13 inc 8GB - SSD 128", descricaoDevolvida);
    }

    @Test
    public void obterMaiorlance() {
        // Executar
        LEILAO.propoe(new Lance(FLAVIO, 200.0));
        final double maiorLanceDevolvido = LEILAO.getMaiorLance();
        // Testar
        assertEquals(200.0, maiorLanceDevolvido, 0.0001);
    }

    @Test
    public void obterMaiorLancePropostasOrdemCrescente() {

        //Executar
        LEILAO.propoe(new Lance(FLAVIO, 100.15));
        LEILAO.propoe(new Lance(LUISA, 400.0));
        final double maiorLance = LEILAO.getMaiorLance();
        //Testar
        assertEquals(400.0, maiorLance, 0.0001);
    }

    @Test
    public void obterMaiorLancePropostasOrdemDecrescente() {

        //Executar
        LEILAO.propoe(new Lance(LUISA, 500.0));
        LEILAO.propoe(new Lance(FLAVIO, 100.0));
        final double maiorLance = LEILAO.getMaiorLance();
        //Testar
        assertEquals(500.0, maiorLance, 0.0001);
    }

    @Test
    public void obterMenorLance() {

        //Executar
        LEILAO.propoe(new Lance(FLAVIO, 100.0));
        final double menorlance = LEILAO.getMenorlance();
        //Testar
        assertEquals(100.0, menorlance, 0.0001);
    }

    @Test
    public void obterMenorLancePropostasOrdemCrescente() {

        //Executar
        LEILAO.propoe(new Lance(FLAVIO, 100.0));
        LEILAO.propoe(new Lance(LUISA, 200.0));
        final double menorlance = LEILAO.getMenorlance();
        //Testar
        assertEquals(100.0, menorlance, 0.0001);
    }

    @Test
    public void obterMenorlancePropostasOrdemDecrescente() {

        //Executar
        LEILAO.propoe(new Lance(FLAVIO, 200.0));
        LEILAO.propoe(new Lance(LUISA, 100.0));
        final double menorlance = LEILAO.getMenorlance();
        //Teste
        assertEquals(100.0, menorlance, 0.0001);
    }
}