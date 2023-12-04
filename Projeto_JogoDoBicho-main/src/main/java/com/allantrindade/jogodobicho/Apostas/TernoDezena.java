package com.allantrindade.jogodobicho.Apostas;
import java.util.*;
import com.allantrindade.jogodobicho.Jogo.Animal;
import com.allantrindade.jogodobicho.Padrões.ApostaVisitor;

public class TernoDezena extends Aposta{
    public TernoDezena(List<Animal> animais, double vlr){
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
        double valorMultiplicado = getValor() * 3000;
        return valorMultiplicado;
    }
}
