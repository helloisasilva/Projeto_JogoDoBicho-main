package com.allantrindade.jogodobicho.Apostas;
import java.util.List;
import com.allantrindade.jogodobicho.Jogo.Animal;
import com.allantrindade.jogodobicho.Padrões.ApostaVisitor;

public class ApostaCentena extends Aposta{
    public ApostaCentena(Animal anim, String modal, String ctn, double vlr){
        setAnimalApostado(anim);
        setModalidade(modal);
        setCentena(ctn);
        setValor(vlr);
    }

    @Override
    public  boolean accept(ApostaVisitor visitor, List<String> sorteados){
        boolean result = visitor.visit(this, sorteados);
        return result;
    }

    @Override
    public double multiplicador(){
        if (getModalidade().equals("C")){
            double valorMultiplicado = getValor() * 600;
            return valorMultiplicado;
        }
        else {
            double valorMultiplicado = getValor() * (600/5);
            return valorMultiplicado;
        }
    }
}
