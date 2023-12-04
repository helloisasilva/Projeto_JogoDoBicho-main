package com.allantrindade.jogodobicho.Padrões;

import java.util.List;
import com.allantrindade.jogodobicho.Apostas.*;

public interface ApostaVisitor {
    boolean visit(ApostaGrupo jogada, List<String> sorteados);
    boolean visit(ApostaDezena jogada, List<String> sorteados);
    boolean visit(ApostaCentena jogada, List<String> sorteados);
    boolean visit(ApostaMilhar jogada, List<String> sorteados);
    boolean visit(DuqueGrupo jogada, List<String> sorteados);
    boolean visit(TernoGrupo jogada, List<String> sorteados);
    boolean visit(DuqueDezena jogada, List<String> sorteados);
    boolean visit(TernoDezena jogada, List<String> sorteados);
}
