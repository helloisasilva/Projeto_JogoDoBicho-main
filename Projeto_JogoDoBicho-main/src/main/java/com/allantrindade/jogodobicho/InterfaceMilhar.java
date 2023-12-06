package com.allantrindade.jogodobicho;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.allantrindade.jogodobicho.Apostas.*;
import com.allantrindade.jogodobicho.Jogo.*;
import com.allantrindade.jogodobicho.Padrões.*;

public class InterfaceMilhar {
    
    private Jogador novoJogador;
    protected boolean resultado;
    protected boolean verificador;
    
    public InterfaceMilhar(Jogador novoJogador) {
     this.novoJogador = novoJogador;  
    }
        
    
    public void IniciaInterfaceM(){

        Scanner sc = new Scanner(System.in);
        JogoDoBicho jogo = new JogoDoBicho();
        System.out.println("Informe o milhar que deseja apostar (1000 a 9999): ");
        String milhar = sc.nextLine().strip();

        System.out.println("Deseja apostar no 1º prêmio ou em todos os prêmios? (C - 1º prêmio / T - Todos os prêmios)");
        String modal2 = sc.nextLine().toUpperCase();

        System.out.println("Apostou no milhar: "+(milhar)+", que corresponde ao animal "+ jogo.getAnimal(milhar) + ".");

        System.out.println("Qual o valor da aposta?");
        double vlr = sc.nextDouble();

        ApostaMilhar jogada = new ApostaMilhar(jogo.getAnimal(milhar), modal2, milhar, vlr);
        System.out.println("\n----------------------");
        System.out.println("Hora dos resultados!");
        

        // Sorteio 
        List<String> sorteados = new ArrayList<>();
        sorteados = jogo.sortearMilhares();

        // Lista de animais usados no print
        List<Animal> animalSorteado = new ArrayList<>();
        for (String milhares : sorteados){
            animalSorteado.add(jogo.getAnimal(milhares));
         }

        // Utilização do Iterator para verificar se há prêmios repetidos
        while (true){
            BichoIterator iterator = new BichoIterator(animalSorteado, sorteados.size());
            if (iterator.temRepetidos()){
                sorteados.clear();
                sorteados.addAll(jogo.sortearMilhares());
             }
            else break;
         }

        // Utilização do Visitor para comparação
        ApostaVisitor visitor = new VerificadorApostaVisitor();
        boolean resultado = jogada.accept(visitor, sorteados);

        // Print dos milhares sorteados e os animais que representam
        System.out.println("Os milhares sorteados foram:");
        System.out.println("----------------------");
        for (int i = 0; i < 5; i++){
            System.out.println("-> "+sorteados.get(i) + " (" + animalSorteado.get(i).getNome() + ")");
        }

        // Verificação do milhar apostada com os milhares sorteados
        if (resultado){
            double valorObtido = vlr * jogada.multiplicador();
            novoJogador.incrementarGanho(valorObtido);
            novoJogador.incrementarApostas(jogada);

            System.out.println("\nParabéns! Você ganhou R$"+ valorObtido + " com esta aposta.");
            this.verificador = true;
         }
        else {
            novoJogador.incrementarPerda(vlr);
            novoJogador.incrementarApostas(jogada);
            System.out.println("\nInfelizmente você perdeu...\nMais sorte na próxima vez!");
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
