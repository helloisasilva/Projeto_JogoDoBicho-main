package com.allantrindade.jogodobicho;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.allantrindade.jogodobicho.Apostas.*;
import com.allantrindade.jogodobicho.Jogo.*;
import com.allantrindade.jogodobicho.Padrões.*;

public class InterfaceDuqueDezena {
    
    private Jogador novoJogador;
    protected boolean resultado;
    protected boolean verificador;
    
    public InterfaceDuqueDezena(Jogador novoJogador) {
     this.novoJogador = novoJogador;  
    }
        
    public void IniciaInterfaceDD(){

        Scanner sc = new Scanner(System.in);
        JogoDoBicho jogo = new JogoDoBicho();
        
        System.out.println("Informe a primeira dezena que deseja apostar: ");
        String dezena1 = sc.nextLine().strip();

        System.out.println("Agora a segunda: ");
        String dezena2 = sc.nextLine().toUpperCase();

        System.out.println("Apostou nas dezenas "+((dezena1)+(" e "+dezena2))+", que correspondem aos animais: "+ jogo.getAnimal(dezena1) + " e " + jogo.getAnimal(dezena2));

        System.out.println("Qual o valor da aposta?");
        double vlr = sc.nextDouble();

        List<Animal> apostados = new ArrayList<>();
        apostados.add(jogo.getAnimal(dezena1));
        apostados.add(jogo.getAnimal(dezena2));

        DuqueDezena jogada = new DuqueDezena(apostados, vlr);
        System.out.println("\n----------------------");
        System.out.println("Hora dos resultados!");

        // Sorteio
        List<String> sorteados = new ArrayList<>();
        List<String> dezenaSorteados = new ArrayList<>();
        sorteados = jogo.sortearMilhares();
        for (String milhar : sorteados){
            String dezenaSorteada = milhar.substring(2, 4);
            dezenaSorteados.add(dezenaSorteada);
        }

        // Lista de animais usados no print
        List<Animal> animalSorteado = new ArrayList<>();
        for (String dezenas : dezenaSorteados){
            animalSorteado.add(jogo.getAnimal(dezenas));
        }

        // Utilização do Iterator para verificar se há prêmios repetidos
        while (true){
            BichoIterator iterator = new BichoIterator(animalSorteado, sorteados.size());
            if (iterator.temRepetidos()){
                sorteados.clear();
                animalSorteado.clear();
                dezenaSorteados.clear();
                sorteados.addAll(jogo.sortearMilhares());

                for (String milhar : sorteados){
                    String dezenaSorteada = milhar.substring(2, 4);
                    dezenaSorteados.add(dezenaSorteada);
                }

                animalSorteado = new ArrayList<>();
                for (String dezenas : dezenaSorteados){
                    animalSorteado.add(jogo.getAnimal(dezenas));
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

        // Verificação da dezena apostada com os milhares sorteados
        if (resultado){
            double valorObtido = vlr * jogada.multiplicador();
            novoJogador.incrementarGanho(valorObtido);
            novoJogador.incrementarPerda(vlr);
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
