package com.allantrindade.jogodobicho.Apostas;

import java.util.ArrayList;
import java.util.List;

import com.allantrindade.jogodobicho.Jogo.Animal;
import com.allantrindade.jogodobicho.Padrões.ApostaVisitor;

public abstract class Aposta {
    private String modalidade;
    private Animal animalApostado;
    private String grupo;
    private List<String> gruposApostados = new ArrayList<>();
    private List<String> dezenasApostadas = new ArrayList<>();
    private String dezena;
    private String centena;
    private String milhar;
    private double valor;

    public Animal getAnimalApostado() {
        return animalApostado;
    }

    public void setAnimalApostado(Animal animalApostado) {
        this.animalApostado = animalApostado;
    }

    public String getGrupo() {
        return grupo;
    }

    public double getValor() {
        return valor;
    }

    public String getModalidade() {
        return modalidade;
    }

    public String getDezena() {
        return dezena;
    }

    public List<String> getDezenasApostadas() {
        return dezenasApostadas;
    }

    public List<String> getGruposApostados() {
        return gruposApostados;
    }
    
    public String getCentena() {
        return centena;
    }

    public String getMilhar() {
        return milhar;
    }

    public void setCentena(String centena) {
        this.centena = centena;
    }

    public void setGruposApostados(String grupos) {
        this.gruposApostados.add(grupo);
    }

    public void setDezenasApostadas(String dezenas) {
        this.dezenasApostadas.add(dezenas);
    }

    public void setMilhar(String milhar) {
        this.milhar = milhar;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade.toUpperCase();
    }
    
    public void setDezena(String dezena) {
        this.dezena = dezena;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    
    public abstract double multiplicador();
    public abstract boolean accept(ApostaVisitor visitor, List<String> sorteados);
}