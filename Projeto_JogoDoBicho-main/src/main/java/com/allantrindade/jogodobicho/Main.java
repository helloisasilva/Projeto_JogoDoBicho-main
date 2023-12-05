package com.allantrindade.jogodobicho;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.allantrindade.jogodobicho.Apostas.*;
import com.allantrindade.jogodobicho.Jogo.*;
import com.allantrindade.jogodobicho.Padrões.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Bem vindo ao jogo do bicho!\nInsira seu nome: ");
        String nome = sc.nextLine();
        Jogador novoJogador = new Jogador(nome);
        
        while (true) {
            System.out.println("O que deseja fazer, "+ novoJogador.getNome() + "?");
            System.out.println("1 - Apostar\n2 - Ver regras do jogo\n3 - Mostrar histórico de apostas\n4 - Mostrar ganhos\n5 - Mostrar investimento total\n6 - Sair");
            String op = sc.nextLine();
            
            switch (op) {
                case "1":
                    System.out.println("Sua aposta será em qual modalidade?\n(G - Grupo, D - Dezena, C - Centena, M - Milhar,\nDG - Duque de grupo, TG - Terno de grupo,\n" +
                    "DD - Duque de Dezena, TD - Terno de Dezena): ");
                    String modal1 = sc.nextLine().toUpperCase();
            
                    if (modal1.equals("G")){
                        InterfaceGrupo intergrupo = new InterfaceGrupo(novoJogador);
                        intergrupo.IniciaInterfaceG();

                         if (intergrupo.getVerificador()){
                             break;
                         }                
                    }      
                    else if (modal1.equals("D")){
                        InterfaceDezena interdezena = new InterfaceDezena(novoJogador);
                        interdezena.IniciaInterfaceD();
                        if (interdezena.getVerificador()){
                            break;
                        }
                    }
                    else if (modal1.equals("C")) {
                        InterfaceCentena intercentena = new InterfaceCentena(novoJogador);
                        intercentena.IniciaInterfaceC();
                        if (intercentena.getVerificador()){
                            break;
                        }   
                    }
                    else if (modal1.equals("M")){
                        InterfaceMilhar intermilhar = new InterfaceMilhar(novoJogador);
                        intermilhar.IniciaInterfaceM();
                        if (intermilhar.getVerificador()){
                            break;       
                        }
                    }
                    else if (modal1.equals("DG")){
                        InterfaceDuqueGrupo interduquegrupo = new InterfaceDuqueGrupo(novoJogador);
                        interduquegrupo.IniciaInterfaceDG();
                        if (interduquegrupo.getVerificador()){
                            break;
                        }
                    }
                
            else if (modal1.equals("TG")){
                        InterfaceTernoGrupo interternogrupo = new InterfaceTernoGrupo(novoJogador);
                        interternogrupo.IniciaInterfaceTG();
                        if (interternogrupo.getVerificador()){
                            break;  
                
                
            }
            }
              
/*
            else if (modal1.equals("DD")){
                System.out.println("Informe a primeira dezena que deseja apostar: ");
                String dezena1 = sc.nextLine().strip();
                
                System.out.println("Agora a segunda: ");
                String dezena2 = sc.nextLine().toUpperCase();

                System.out.println("Apostou nos animais: "+ jogo.getAnimal(dezena1) + " e " + jogo.getAnimal(dezena2));
                
                System.out.println("Qual o valor da aposta?");
                double vlr = sc.nextDouble();

                List<Animal> apostados = new ArrayList<>();
                apostados.add(jogo.getAnimal(dezena1));
                apostados.add(jogo.getAnimal(dezena2));

                DuqueDezena jogada = new DuqueDezena(apostados, vlr);
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
                for (int i = 0; i < 5; i++){
                    System.out.println(sorteados.get(i) + " (" + animalSorteado.get(i).getNome() + ")");
                }

                // Verificação da dezena apostada com os milhares sorteados
                if (resultado){
                    double valorObtido = vlr * jogada.multiplicador();
                    novoJogador.incrementarGanho(valorObtido);
                    novoJogador.incrementarApostas(jogada);

                    System.out.println("Parabéns! Você ganhou R$"+ valorObtido + " com esta aposta.");
                    break;
                }
                else {
                    novoJogador.incrementarPerda(vlr);
                    novoJogador.incrementarApostas(jogada);
                    System.out.println("Infelizmente você perdeu...\nMais sorte na próxima vez!");
                    break;
                }
            }
            else if (modal1.equals("TD")){
                System.out.println("Informe a primeira dezena que deseja apostar (00 a 99): ");
                String dezena1 = sc.nextLine().strip();
                
                System.out.println("A segunda dezena (00 a 99): ");
                String dezena2 = sc.nextLine().toUpperCase();

                System.out.println("Agora a terceira dezena (00 a 99): ");
                String dezena3 = sc.nextLine().toUpperCase();

                System.out.println("Apostou nos animais: "+ jogo.getAnimal(dezena1) + ", " + jogo.getAnimal(dezena2) + " e " + jogo.getAnimal(dezena3));
                
                System.out.println("Qual o valor da aposta?");
                double vlr = sc.nextDouble();

                List<Animal> apostados = new ArrayList<>();
                apostados.add(jogo.getAnimal(dezena1));
                apostados.add(jogo.getAnimal(dezena2));
                apostados.add(jogo.getAnimal(dezena3));

                TernoDezena jogada = new TernoDezena(apostados, vlr);
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
                for (int i = 0; i < 5; i++){
                    System.out.println(sorteados.get(i) + " (" + animalSorteado.get(i).getNome() + ")");
                }

                // Verificação da dezena apostada com os milhares sorteados
                if (resultado){
                    double valorObtido = vlr * jogada.multiplicador();
                    novoJogador.incrementarGanho(valorObtido);
                    novoJogador.incrementarApostas(jogada);

                    System.out.println("Parabéns! Você ganhou R$"+ valorObtido + " com esta aposta.");
                    break;
                }
                else {
                    novoJogador.incrementarPerda(vlr);
                    novoJogador.incrementarApostas(jogada);
                    System.out.println("Infelizmente você perdeu...\nMais sorte na próxima vez!");
                    break;
                }
            }
*/
            case "2":
                System.out.println("Quais regras você deseja consultar:\n1-Regras Gerais\n2-Regras Aposta\n3-Tipos de Apostas e Prêmios");
                String opc=sc.nextLine();
                
                switch(opc){
                    case "1":
                        Regras.regrasGerais();
                        break;
                    case "2":
                       Regras.regrasApostas();
                       break;
                    case "3":
                       Regras.ApostasPremios(); 
                       break;
                }
                break;
            case "3":
                novoJogador.mostrarApostas();
                break;
            case "4":
                novoJogador.mostrarGanhos();
                break;
            case "5":
                novoJogador.mostrarPerdas();
                break;
            default:
                System.out.println("Opção inválida.");
                break;
            } 
            
            if (op.equals("6")){
                System.out.println("Até mais, "+ novoJogador.getNome() + "!");
                break;
            }
            sc = new Scanner(System.in);
            
        }    
    }
}
