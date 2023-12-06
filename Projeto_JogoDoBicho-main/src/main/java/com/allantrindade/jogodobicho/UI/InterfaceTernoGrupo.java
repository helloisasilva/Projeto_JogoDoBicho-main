package com.allantrindade.jogodobicho.UI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.allantrindade.jogodobicho.Apostas.*;
import com.allantrindade.jogodobicho.Jogo.*;
import com.allantrindade.jogodobicho.Padrões.*;

public class InterfaceTernoGrupo {
    
    private Jogador novoJogador;
    protected boolean resultado;
    protected boolean verificador;
    
    public InterfaceTernoGrupo(Jogador novoJogador) {
     this.novoJogador = novoJogador;  
    }
        
    
    public void IniciaInterfaceTG(){

        Scanner sc = new Scanner(System.in);
        JogoDoBicho jogo = new JogoDoBicho();
        
        System.out.println("Informe o primeiro grupo que deseja apostar. (1 a 25)");
        int grp1 = sc.nextInt();
        sc.nextLine();

        System.out.println("Informe o segundo grupo. (1 a 25)");
        int grp2 = sc.nextInt();
        sc.nextLine();

        System.out.println("Agora informe o terceiro grupo. (1 a 25)");
        int grp3 = sc.nextInt();
        sc.nextLine();

        List<String> animaisApostados = new ArrayList<>();
        animaisApostados.add("" + grp1);
        animaisApostados.add("" + grp2);
        animaisApostados.add("" + grp3);

        System.out.println("Apostou nos animais: " + jogo.getAnimal(grp1 - 1).getNome() + ", " + jogo.getAnimal(grp2 - 1).getNome() + " e " + jogo.getAnimal(grp3 - 1).getNome());

        System.out.println("Qual valor da aposta? R$");
        double vlr = sc.nextDouble();

        TernoGrupo jogada = new TernoGrupo(animaisApostados, vlr);
        System.out.println("\n----------------------");
        System.out.println("Hora dos resultados!");

        // Sorteio
        List<Animal> sorteados = new ArrayList<>();
        sorteados = jogo.sortearAnimais();

        // Utilização do Iterator para verificar se há prêmios repetidos
        while (true){
            BichoIterator iterator = new BichoIterator(sorteados, sorteados.size());
            if (iterator.temRepetidos()){
                sorteados.clear();
                sorteados.addAll(jogo.sortearAnimais());
            }

            else break;
        }

        // Separação para comparação entre o animal apostado e o animal sorteado
        List<String> gruposSorteados = new ArrayList<>();

        for (Animal animais : sorteados){
            gruposSorteados.add(animais.getGrupo());
        }

        // Utilização do Visitor para comparação
        ApostaVisitor visitor = new VerificadorApostaVisitor();
        boolean resultado = jogada.accept(visitor, gruposSorteados);

        // Print dos animais sorteados e seus números
        System.out.println("Os animais sorteados foram:");
        System.out.println("----------------------");
        for (Animal animal : sorteados){
            System.out.println("-> "+animal.getNome() + " " + animal.getGrupo() + " " + animal.getNumeros());
        }
        // Verificação dos grupos sorteados com o grupo apostado
        if (resultado){
            double valorObtido = vlr * jogada.multiplicador();
            novoJogador.incrementarGanho(valorObtido);
            novoJogador.incrementarApostas(jogada);

            System.out.println("\nParabéns! Você ganhou R$"+ valorObtido + " com esta aposta.");
            this.verificador = true;
            sc.close();
        }
        else {
            novoJogador.incrementarPerda(vlr);
            novoJogador.incrementarApostas(jogada);
            System.out.println("\nInfelizmente você perdeu...\nMais sorte na próxima vez!");
            this.verificador = true;
            sc.close();
        }
    }
                
    public boolean getVerificador(){
        return this.verificador;
    }
    
    public boolean getResultado(){
        return this.resultado;
    }
}
