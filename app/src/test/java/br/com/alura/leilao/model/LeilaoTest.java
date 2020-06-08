package br.com.alura.leilao.model;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class LeilaoTest {

    public static final double DELTA = 0.0001;

    private final Leilao LEILAO = new Leilao("MacBook Air 13 inc 8GB - SSD 128");
    private final Usuario FLAVIO = new Usuario("Flavio Campos");
    private final Usuario LUISA = new Usuario("Luísa Nader");
    private final Usuario SIMONE = new Usuario("Simone Nader");


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
        assertEquals(200.0, maiorLanceDevolvido, DELTA);
    }

    @Test
    public void obterMaiorLancePropostasOrdemCrescente() {

        //Executar
        LEILAO.propoe(new Lance(FLAVIO, 100.15));
        LEILAO.propoe(new Lance(LUISA, 400.0));
        final double maiorLance = LEILAO.getMaiorLance();
        //Testar
        assertEquals(400.0, maiorLance, DELTA);
    }

    @Test
    public void obterMaiorLancePropostasOrdemDecrescente() {

        //Executar
        LEILAO.propoe(new Lance(LUISA, 500.0));
        LEILAO.propoe(new Lance(FLAVIO, 100.0));
        final double maiorLance = LEILAO.getMaiorLance();
        //Testar
        assertEquals(500.0, maiorLance, DELTA);
    }

    @Test
    public void obterMenorLance() {

        //Executar
        LEILAO.propoe(new Lance(FLAVIO, 100.0));
        final double menorlance = LEILAO.getMenorlance();
        //Testar
        assertEquals(100.0, menorlance, DELTA);
    }

    @Test
    public void obterMenorLancePropostasOrdemCrescente() {

        //Executar
        LEILAO.propoe(new Lance(FLAVIO, 100.0));
        LEILAO.propoe(new Lance(LUISA, 200.0));
        final double menorlance = LEILAO.getMenorlance();
        //Testar
        assertEquals(100.0, menorlance, DELTA);
    }

    @Test
    public void obterMenorlancePropostasOrdemDecrescente() {

        //Executar
        LEILAO.propoe(new Lance(FLAVIO, 200.0));
        LEILAO.propoe(new Lance(LUISA, 100.0));
        final double menorlance = LEILAO.getMenorlance();
        //Teste
        assertEquals(100.0, menorlance, DELTA);
    }

    @Test
    public void obterTresMaioresLancesQuandoPropostosTresLances() {
        //Executar
        LEILAO.propoe(new Lance(FLAVIO, 180.0));
        LEILAO.propoe(new Lance(LUISA, 300.0));
        LEILAO.propoe(new Lance(SIMONE, 800.0));

        List<Lance> treisMaioresLances = LEILAO.tresMaioresLances();
        //Testar
        assertEquals(3,treisMaioresLances.size());
        assertEquals(800, treisMaioresLances.get(0).getValor(), DELTA);
        assertEquals(300, treisMaioresLances.get(1).getValor(), DELTA);
        assertEquals(180, treisMaioresLances.get(2).getValor(), DELTA);
    }
}