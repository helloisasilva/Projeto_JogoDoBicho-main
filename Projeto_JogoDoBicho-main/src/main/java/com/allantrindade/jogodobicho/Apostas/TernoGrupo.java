package com.allantrindade.jogodobicho.Apostas;
import java.util.List;

import com.allantrindade.jogodobicho.Padrões.ApostaVisitor;

public class TernoGrupo extends Aposta{
    public TernoGrupo(List<String> animais, double vlr){
        for (int i = 0; i < animais.size(); i++){
            setGruposApostados(animais.get(i));
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
        double valorMultiplicado = getValor() * 130;
        return valorMultiplicado;
    }
}
