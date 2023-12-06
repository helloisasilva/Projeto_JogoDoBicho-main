package com.allantrindade.jogodobicho.Apostas;
import java.util.List;
import com.allantrindade.jogodobicho.Jogo.Animal;
import com.allantrindade.jogodobicho.Padrões.ApostaVisitor;

public class ApostaGrupo extends Aposta {

    public ApostaGrupo(Animal anim, String modal, String grp, double valor) {
        setAnimalApostado(anim);
        setModalidade(modal);
        setGrupo(grp);
        setValor(valor);
    }

    @Override
    public boolean accept(ApostaVisitor visitor, List<String> sorteados){
        boolean result = visitor.visit(this, sorteados);
        return result;
    }
   
    @Override
    public double multiplicador() {
        if (getModalidade().equals("C")){
            double valorMultiplicado = getValor() * 18;
            return valorMultiplicado;
        }
        else {
            double valorMultiplicado = getValor() * 3.6;
            return valorMultiplicado;
        }
    
    }
    
}