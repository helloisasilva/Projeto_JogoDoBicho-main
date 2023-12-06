package com.allantrindade.jogodobicho.UI;

import java.util.Scanner;
import com.allantrindade.jogodobicho.Jogo.*;


public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("\n*******************************************\nBem vindo ao jogo do bicho!\n*******************************************\nInsira seu nome: ");
        String nome = sc.nextLine();
        Jogador novoJogador = new Jogador(nome);
        
        while (true) {
            System.out.println("\nO que deseja fazer, "+ novoJogador.getNome() + "?");
            System.out.println("1 - Apostar\n2 - Ver regras do jogo\n3 - Mostrar histórico de apostas\n4 - Mostrar ganhos\n5 - Mostrar investimento total\n6 - Sair");
            String op = sc.nextLine();
            
            switch (op) {
                case "1":
                    System.out.println("\nSua aposta será em qual modalidade?\n(G - Grupo, D - Dezena, C - Centena, M - Milhar,\nDG - Duque de grupo, TG - Terno de grupo,\n" +
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

                    else if (modal1.equals("DD")){
                        InterfaceDuqueDezena interduquedezena = new InterfaceDuqueDezena(novoJogador);
                        interduquedezena.IniciaInterfaceDD();
                        if (interduquedezena.getVerificador()){
                            break;
                        }
                    }
                
                    else if (modal1.equals("TD")){
                        InterfaceTernoDezena interternodezena = new InterfaceTernoDezena(novoJogador);
                        interternodezena.IniciaInterfaceTD();
                        if (interternodezena.getVerificador()){
                            break;
                        }
                    }
                    
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
            case "6":
                System.out.println("Até mais, "+ novoJogador.getNome() + "!");
                sc.close();
                break;
            default:
                System.out.println("Opção inválida.");
                break;
            } 
            
            if (op.equals("6")){
                break;
            }
            sc = new Scanner(System.in);
            
        }    
    }
}
