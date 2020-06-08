package br.com.alura.leilao.model;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leilao implements Serializable {

    private final String descricao;
    private final List<Lance> lances;
    private double maiorLance = Double.NEGATIVE_INFINITY;
    private double menorlance = Double.POSITIVE_INFINITY;

    public Leilao(String descricao) {
        this.descricao = descricao;
        this.lances = new ArrayList<>();
    }

    public void propoe(Lance lance) {
        lances.add(lance);
        Collections.sort(lances);
        double valorLance = lance.getValor();
        obterMaiorLance(valorLance);
        obterMenorLance(valorLance);
    }

    private void obterMenorLance(double valorLance) {
        if (valorLance < menorlance){
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


        return lances.subList(0, lances.size());
    }


}
