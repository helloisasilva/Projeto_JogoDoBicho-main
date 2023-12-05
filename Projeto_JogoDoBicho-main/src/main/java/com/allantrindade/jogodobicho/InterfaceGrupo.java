package com.allantrindade.jogodobicho;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.allantrindade.jogodobicho.Apostas.*;
import com.allantrindade.jogodobicho.Jogo.*;
import com.allantrindade.jogodobicho.Padrões.*;

public class InterfaceGrupo {
    
    private Jogador novoJogador;
    protected boolean resultado;
    protected boolean verificador;

    
    public InterfaceGrupo(Jogador novoJogador) {
     this.novoJogador = novoJogador;  
    }
        
    
    public void IniciaInterfaceG(){
        
        Scanner sc = new Scanner(System.in);
        JogoDoBicho jogo = new JogoDoBicho();

        System.out.println("Em qual grupo deseja apostar? (1 a 25)");
        int grp = sc.nextInt();
        sc.nextLine();

        System.out.println("Deseja apostar no 1º prêmio ou em todos os prêmios? (C - 1º prêmio / T - Todos os prêmios)");
        String modal2 = sc.nextLine().toUpperCase();

        System.out.println("Apostou em "+ jogo.getAnimal(grp - 1) +".");
        String grpString = Integer.toString(grp);

        System.out.println("Qual valor da aposta? R$");
        double vlr = sc.nextDouble();

        ApostaGrupo jogada = new ApostaGrupo(jogo.getAnimal(grp - 1), modal2, grpString, vlr);
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
        this.resultado = jogada.accept(visitor, gruposSorteados); 

        // Print dos animais sorteados e seus números
        System.out.println("Os animais sorteados foram:");
        for (Animal animal : sorteados){
            System.out.println(animal.getNome() + " " + animal.getGrupo() + " " + animal.getNumeros());                
          }

        // Verificação dos grupos sorteados com o grupo apostado
        
        if (this.resultado){
            double valorObtido = vlr * jogada.multiplicador();
            novoJogador.incrementarGanho(valorObtido);
            novoJogador.incrementarApostas(jogada);

            System.out.println("Parabéns! Você ganhou R$"+ valorObtido + " com esta aposta.");
            this.verificador = true;
        } else {
                novoJogador.incrementarPerda(vlr);
                novoJogador.incrementarApostas(jogada);
                System.out.println("Infelizmente você perdeu...\nMais sorte na próxima vez!");
                this.verificador = true;
        }
    }

    

    public boolean getVerificador(){
        return this.verificador;
    }
    
    public boolean getResultado(){
        return this.resultado;
    }

}
