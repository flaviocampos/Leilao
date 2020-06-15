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
        double valorLance = lance.getValor();
        if (maiorLance > valorLance){
            return;
        }
        lances.add(lance);
        if (lances.size() == 1){
            maiorLance = valorLance;
            menorlance = valorLance;
            return;
        }
        Collections.sort(lances);
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

        int quantidadeMaximaLance = lances.size();
        if (quantidadeMaximaLance > 3){
            quantidadeMaximaLance = 3;
        }
        return lances.subList(0, quantidadeMaximaLance);
    }


    public int quantidadeDeLances() {
        return  lances.size();
    }
}
