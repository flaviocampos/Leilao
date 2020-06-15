package br.com.alura.leilao.model;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leilao implements Serializable {

    private final String descricao;
    private final List<Lance> lances;
    private double maiorLance = 0.0;
    private double menorlance = 0.0;

    public Leilao(String descricao) {
        this.descricao = descricao;
        this.lances = new ArrayList<>();
    }

    public void propoe(Lance lance) {
        if (lanceNaoValido(lance)) return;
        double valorLance = lance.getValor();
        lances.add(lance);
        if (defineMaiorEMenorLanceParaOPrimeiroLance(valorLance)) return;
        Collections.sort(lances);
        obterMaiorLance(valorLance);
        obterMenorLance(valorLance);
    }

    private boolean defineMaiorEMenorLanceParaOPrimeiroLance(double valorLance) {
        if (lances.size() == 1) {
            maiorLance = valorLance;
            menorlance = valorLance;
            return true;
        }
        return false;
    }

    private boolean lanceNaoValido(Lance lance) {
        double valorLance = lance.getValor();
        if (lanceMaiorQueUltimoLance(valorLance)) return true;
        if (!lances.isEmpty()) {
            Usuario usuarioNovo = lance.getUsuario();
            if (usuarioForMesmoDoUltimo(usuarioNovo)) return true;
            if (usuarioDeuCincoLances(usuarioNovo)) return true;
        }
        return false;
    }

    private boolean usuarioDeuCincoLances(Usuario usuarioNovo) {
        int quantidadeDeLancesDoUsuario = 0;
        for (Lance l : lances) {
            Usuario usuario = l.getUsuario();
            if (usuario.equals(usuarioNovo)) {
                quantidadeDeLancesDoUsuario++;
                if (quantidadeDeLancesDoUsuario == 5) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean usuarioForMesmoDoUltimo(Usuario usuarioNovo) {
        Usuario ultimoUsuario = lances.get(0).getUsuario();
        if (usuarioNovo.equals(ultimoUsuario)) {
            return true;
        }
        return false;
    }

    private boolean lanceMaiorQueUltimoLance(double valorLance) {
        if (maiorLance > valorLance) {
            return true;
        }
        return false;
    }

    private void obterMenorLance(double valorLance) {
        if (valorLance < menorlance) {
            menorlance = valorLance;
        }
    }

    private void obterMaiorLance(double valorLance) {
        if (valorLance > maiorLance) {
            maiorLance = valorLance;
        }
    }

    public String getDescricao() {
        return descricao;
    }

    public double getMaiorLance() {
        return maiorLance;
    }

    public double getMenorlance() {
        return menorlance;
    }

    public List<Lance> tresMaioresLances() {

        int quantidadeMaximaLance = lances.size();
        if (quantidadeMaximaLance > 3) {
            quantidadeMaximaLance = 3;
        }
        return lances.subList(0, quantidadeMaximaLance);
    }


    public int quantidadeDeLances() {
        return lances.size();
    }
}
