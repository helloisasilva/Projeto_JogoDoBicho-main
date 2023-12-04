package com.allantrindade.jogodobicho.Padrões;
import com.allantrindade.jogodobicho.Apostas.*;
import java.util.*;




public class VerificadorApostaVisitor implements ApostaVisitor{
    @Override
    public boolean visit(ApostaGrupo jogada, List<String> sorteados){
        if (jogada.getModalidade().equals("C")){
            if (jogada.getAnimalApostado().getGrupo().equals(sorteados.get(0))) return true;
            return false;
        }
        else {
            for (String grupo : sorteados){
                if (jogada.getAnimalApostado().getGrupo().equals(grupo)){
                    return true;
                }
            }
            return false;
        }
    }
    
    @Override
    public boolean visit(ApostaDezena jogada, List<String> sorteados){
        if (jogada.getModalidade().equals("C")){
            if (sorteados.get(0).endsWith(jogada.getDezena())) return true;
            return false;
        }
        else {

            for (String numero : sorteados){
                if (numero.endsWith(jogada.getDezena())) {
                    return true;
                }
            }
            return false;
        }
    }
    
    @Override
    public boolean visit(ApostaCentena jogada, List<String> sorteados){
        if (jogada.getModalidade().equals("C")){
            if (sorteados.get(0).endsWith(jogada.getCentena())) return true;
            return false;
        }

        else {
            for (String numero : sorteados){
                if (numero.endsWith(jogada.getCentena())) {
                    return true;
                }
            }
            return false;
        }
    }
    
    @Override
    public boolean visit(ApostaMilhar jogada, List<String> sorteados){
       if (jogada.getModalidade().equals("C")){
            if (sorteados.get(0).equals(jogada.getMilhar())) return true;
            return false;
        }

       else {
           for (String numero : sorteados){
               if (numero.equals(jogada.getMilhar())) {
                   return true;
                }
            }
            return false;
        }
    }

    @Override
    public boolean visit(DuqueGrupo jogada, List<String> sorteados){
        for (String apostado : jogada.getGruposApostados()){
            if (!sorteados.contains(apostado)) return false; // Se qualquer número não estiver na lista sorteada, retorna false.
        }
        return true; // Retorno true caso os dois números apostados se encontram na lista.
    }

    @Override
    public boolean visit(DuqueDezena jogada, List<String> sorteados){
        for (String dezena : jogada.getDezenasApostadas()) {
            boolean dezenaEncontrada = false;
            
            for (String numeroSorteado : sorteados) {
                String dezenasSorteadas = numeroSorteado.substring(1);
                if (dezenasSorteadas.equals(dezena)) {
                    dezenaEncontrada = true;
                    break;
                }
            }
            if (!dezenaEncontrada) {
                return false; // Se a dezena apostada não for encontrada nos números sorteados, retorna falso
            }
        }
        
        return true; // Se todas as dezenas estiverem nos números sorteados, retorna verdadeiro
    }

    @Override
    public boolean visit(TernoGrupo jogada, List<String> sorteados){
        for (String apostado : jogada.getGruposApostados()){
            if (!sorteados.contains(apostado)) return false;
        }
        return true;
    }

    @Override
    public boolean visit(TernoDezena jogada, List<String> sorteados){
        for (String dezena : jogada.getDezenasApostadas()) {
            boolean dezenaEncontrada = false;
            
            for (String numeroSorteado : sorteados) {
                String dezenasSorteadas = numeroSorteado.substring(1);
                if (dezenasSorteadas.equals(dezena)) {
                    dezenaEncontrada = true;
                    break;
                }
            }
            if (!dezenaEncontrada) {
                return false; // Se a dezena apostada não for encontrada nos números sorteados, retorna falso
            }
        }
        
        return true; // Se todas as dezenas estiverem nos números sorteados, retorna verdadeiro
    }
}
