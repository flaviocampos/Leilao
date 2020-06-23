package br.com.alura.leilao.model;

import android.util.Log;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import br.com.alura.leilao.builder.LeilaoBuilder;
import br.com.alura.leilao.exception.LanceMenorQueUltimoLanceException;
import br.com.alura.leilao.exception.LanceSeguidoDoMesmoUsuarioException;
import br.com.alura.leilao.exception.UsuarioJaDeuCincoLancesException;

import static org.junit.Assert.*;

public class LeilaoTest {

    public static final double DELTA = 0.0001;

    private final Leilao LEILAO = new Leilao("MacBook Air 13 inc 8GB - SSD 128");
    private final Usuario FLAVIO = new Usuario("Flavio");
    private final Usuario LUISA = new Usuario("Luísa");
    private final Usuario SIMONE = new Usuario("Simone");

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
    public void obterTresMaioresLancesQuandoPropostosTresLances() {
        //Executar
        LEILAO.propoe(new Lance(FLAVIO, 180.0));
        LEILAO.propoe(new Lance(LUISA, 300.0));
        LEILAO.propoe(new Lance(SIMONE, 800.0));

        List<Lance> treisMaioresLances = LEILAO.tresMaioresLances();
        //Testar
        assertEquals(3, treisMaioresLances.size());
        assertEquals(800, treisMaioresLances.get(0).getValor(), DELTA);
        assertEquals(300, treisMaioresLances.get(1).getValor(), DELTA);
        assertEquals(180, treisMaioresLances.get(2).getValor(), DELTA);
    }

    @Test
    public void obterTresMaioresLances_QuandoNaoHaLances() {
        List<Lance> tresMaioresLances = LEILAO.tresMaioresLances();
        assertEquals(0, tresMaioresLances.size(), DELTA);
    }

    @Test
    public void obterTresMaioresLances_QuandoRecebeApenasUM() {
        LEILAO.propoe(new Lance(FLAVIO, 300.0));

        List<Lance> tresMaioresLances = LEILAO.tresMaioresLances();

        assertEquals(1, tresMaioresLances.size(), DELTA);
        assertEquals(300.0, tresMaioresLances.get(0).getValor(), DELTA);
    }

    @Test
    public void obterTresMaioresLances_QuandoRecebeApenasDois() {
        LEILAO.propoe(new Lance(FLAVIO, 300.0));
        LEILAO.propoe(new Lance(LUISA, 400));

        List<Lance> tresMaioresLances = LEILAO.tresMaioresLances();

        assertEquals(2, tresMaioresLances.size(), DELTA);
        assertEquals(400.0, tresMaioresLances.get(0).getValor(), DELTA);
        assertEquals(300.0, tresMaioresLances.get(1).getValor(), DELTA);
    }

    @Test
    public void obterTresMaioresLances_QuandoRecebeMaisTresLances() {
        LEILAO.propoe(new Lance(LUISA, 100));
        LEILAO.propoe(new Lance(FLAVIO, 200.0));
        LEILAO.propoe(new Lance(new Usuario("Pedro"), 300.0));
        LEILAO.propoe(new Lance(LUISA, 400));

        List<Lance> tresMaioresLances = LEILAO.tresMaioresLances();

        assertEquals(3, tresMaioresLances.size(), DELTA);
        assertEquals(200.0, tresMaioresLances.get(2).getValor(), DELTA);
        assertEquals(300.0, tresMaioresLances.get(1).getValor(), DELTA);
        assertEquals(400.0, tresMaioresLances.get(0).getValor(), DELTA);
    }

    @Test
    public void deveDevolverValoresZeroParaMaiorLanceQuandoNaoTiverLances() {
        final double maiorLance = LEILAO.getMaiorLance();
        assertEquals(0.0, maiorLance, DELTA);
    }

    @Test
    public void deveDevolverValoresZeroParamenorLanceQuandoNaoTiverLances() {
        final double menorlance = LEILAO.getMenorlance();
        assertEquals(0.0, menorlance, DELTA);
    }

    @Test(expected = LanceMenorQueUltimoLanceException.class)
    public void naoDeveAdicionarLanceQuandoForMenorQueOMaiorLance() {
        LEILAO.propoe(new Lance(FLAVIO, 500.0));
        LEILAO.propoe(new Lance(LUISA, 400.0));
    }

    @Test(expected = LanceSeguidoDoMesmoUsuarioException.class)
    public void naoDeveAdicionarLanceQuandoForMesmoUsuarioDoUltimoLance() {
        LEILAO.propoe(new Lance(FLAVIO, 500));
        LEILAO.propoe(new Lance(new Usuario("Flavio"), 600));

    }

    @Test(expected = UsuarioJaDeuCincoLancesException.class)
    public void naoDeveAcionarLanceQuandoUsuarioDerCincoLances() {
        /*Leilao console = new LeilaoBuilder("Console")
                .lance(FLAVIO, 100.0)
                .lance(LUISA, 200)
                .build();*/


        LEILAO.propoe(new Lance(FLAVIO, 100.0));
        LEILAO.propoe(new Lance(LUISA, 150.0));
        LEILAO.propoe(new Lance(FLAVIO, 300.0));
        LEILAO.propoe(new Lance(LUISA, 350.0));
        LEILAO.propoe(new Lance(FLAVIO, 400.0));
        LEILAO.propoe(new Lance(LUISA, 450.0));
        LEILAO.propoe(new Lance(FLAVIO, 500.0));
        LEILAO.propoe(new Lance(LUISA, 550.0));
        LEILAO.propoe(new Lance(FLAVIO, 600.0));
        LEILAO.propoe(new Lance(LUISA, 650.0));
        LEILAO.propoe(new Lance(FLAVIO, 700.0));


    }
}