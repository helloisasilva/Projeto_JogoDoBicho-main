package com.allantrindade.jogodobicho.Apostas;
import java.util.List;
import com.allantrindade.jogodobicho.Jogo.Animal;
import com.allantrindade.jogodobicho.Padrões.ApostaVisitor;

public class ApostaMilhar extends Aposta{
    public ApostaMilhar(Animal anim, String modal, String mil, double vlr){
        setAnimalApostado(anim);
        setModalidade(modal);
        setMilhar(mil);
        setValor(vlr);
    }
    
    @Override
    public boolean accept(ApostaVisitor visitor, List<String> sorteados){
        boolean result = visitor.visit(this, sorteados);
        return result;
    }

    @Override
    public double multiplicador(){
        if (getModalidade().equals("C")){
            double valorMultiplicado = getValor() * 4000;
            return valorMultiplicado;
        }
        else {
            double valorMultiplicado = getValor() * (4000/5);
            return valorMultiplicado;
        }
    }
}
