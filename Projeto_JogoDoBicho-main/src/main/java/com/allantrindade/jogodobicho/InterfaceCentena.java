package com.allantrindade.jogodobicho;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.allantrindade.jogodobicho.Apostas.*;
import com.allantrindade.jogodobicho.Jogo.*;
import com.allantrindade.jogodobicho.Padrões.*;

public class InterfaceCentena {
    
    private Jogador novoJogador;
    protected boolean resultado;
    protected boolean verificador;
    
    public InterfaceCentena(Jogador novoJogador) {
     this.novoJogador = novoJogador;  
    }
        
    
    public void IniciaInterfaceC(){

        Scanner sc = new Scanner(System.in);
        JogoDoBicho jogo = new JogoDoBicho();
        
        System.out.println("Informe a centena que deseja apostar (000 a 999): ");
        String centena = sc.nextLine().strip();
                
        System.out.println("Deseja apostar no 1º prêmio ou em todos os prêmios? (C - 1º prêmio / T - Todos os prêmios)");
        String modal2 = sc.nextLine().toUpperCase();

        System.out.println("Apostou na centena "+(centena) +", que corresponde ao animal "+jogo.getAnimal(centena) + ".");
                
        System.out.println("Qual o valor da aposta?");
        double vlr = sc.nextDouble();

        ApostaCentena jogada = new ApostaCentena(jogo.getAnimal(centena), modal2, centena, vlr);
        System.out.println("\n----------------------");
        System.out.println("Hora dos resultados!");

        // Sorteio 
        List<String> sorteados = new ArrayList<>();
        List<String> centenasSorteadas = new ArrayList<>();
        sorteados = jogo.sortearMilhares();
        for (String milhar : sorteados){
            String centenaSorteada = milhar.substring(1, 4);
            centenasSorteadas.add(centenaSorteada);
            }
        // Lista de animais usados no print
        List<Animal> animalSorteado = new ArrayList<>();
        for (String centenas : centenasSorteadas){
            animalSorteado.add(jogo.getAnimal(centenas));
          }
        // Utilização do Iterator para verificar se há prêmios repetidos
        while (true){
            BichoIterator iterator = new BichoIterator(animalSorteado, sorteados.size());
            if (iterator.temRepetidos()){
                sorteados.clear();
                animalSorteado.clear();
                centenasSorteadas.clear();
                sorteados.addAll(jogo.sortearMilhares());

                for (String milhar : sorteados){
                    String centenaSorteada = milhar.substring(1, 4);
                    centenasSorteadas.add(centenaSorteada);
                }

                animalSorteado = new ArrayList<>();
                for (String centenas : centenasSorteadas){
                    animalSorteado.add(jogo.getAnimal(centenas));
                }
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

        // Verificação da centena apostada com os milhares sorteados
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
