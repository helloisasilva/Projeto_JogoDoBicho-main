package com.allantrindade.jogodobicho.Jogo;

import java.util.ArrayList;
import java.util.List;
public class Animal {
    private String nome;
    private String grupo;
    private List<String> numeros = new ArrayList<>();

    public Animal(String name, String group, List<String> numeros){
        nome = name;
        grupo = group;
        for (String num : numeros){
            this.numeros.add(num);    
        }
    }
    
    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }
    
    public String getNome() {
        return nome;
    }
    
    public List<String> getNumeros() {
        return numeros;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return getNome() + " (" + getNumeros() + ")";
    }
}