package com.allantrindade.jogodobicho.Apostas;

import java.util.List;

import com.allantrindade.jogodobicho.Jogo.Animal;
import com.allantrindade.jogodobicho.Padrões.ApostaVisitor;

public class DuqueDezena extends Aposta{
    public DuqueDezena(List<Animal> animais, double vlr){
        for (int i = 0; i < animais.size(); i++){
            setDezenasApostadas((animais.get(i).getGrupo()));
        }
        
        setValor(vlr);
    }

    @Override
    public boolean accept(ApostaVisitor visitor, List<String> sorteados){
        boolean result = visitor.visit(this, sorteados);
        return result;
    }

    @Override
    public double multiplicador(){
        double valorMultiplicado = getValor() * 300;
        return valorMultiplicado;
    }
}
