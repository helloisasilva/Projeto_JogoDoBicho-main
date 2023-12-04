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
        JogoDoBicho jogo = new JogoDoBicho();

        System.out.println("Bem vindo ao jogo do bicho!\nInsira seu nome: ");
        String nome = sc.nextLine();
        Jogador novoJogador = new Jogador(nome);
        
        while (true) {
            System.out.println("O que deseja fazer, "+ novoJogador.getNome() + "?");
            System.out.println("1 - Apostar\n2 - Ver regras do jogo\n3 - Mostrar histórico de apostas\n4 - Mostrar ganhos\n5 - Mostrar investimento total\n6 - Sair");
            String op = sc.nextLine();
            
            switch (op) {
                case "1":{
                    System.out.println("Sua aposta será em qual modalidade?\n(G - Grupo, D - Dezena, C - Centena, M - Milhar,\nDG - Duque de grupo, TG - Terno de grupo,\n" +
                    "DD - Duque de Dezena, TD - Terno de Dezena): ");
            String modal1 = sc.nextLine().toUpperCase();
            
            if (modal1.equals("G")){
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
                boolean resultado = jogada.accept(visitor, gruposSorteados);

                // Print dos animais sorteados e seus números
                System.out.println("Os animais sorteados foram:");
                for (Animal animal : sorteados){
                    System.out.println(animal.getNome() + " " + animal.getGrupo() + " " + animal.getNumeros());                
                }

                // Verificação dos grupos sorteados com o grupo apostado
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

            else if (modal1.equals("D")){
                System.out.println("Informe a dezena que deseja apostar (00 a 99): ");
                String dezena = sc.nextLine().strip();
                
                System.out.println("Deseja apostar no 1º prêmio ou em todos os prêmios? (C - 1º prêmio / T - Todos os prêmios)");
                String modal2 = sc.nextLine().toUpperCase();

                System.out.println("Apostou em "+ jogo.getAnimal(dezena) + ".");
                
                System.out.println("Qual o valor da aposta?");
                double vlr = sc.nextDouble();

                ApostaDezena jogada = new ApostaDezena(jogo.getAnimal(dezena), modal2, dezena, vlr);
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

            else if (modal1.equals("C")) {
                System.out.println("Informe a centena que deseja apostar (000 a 999): ");
                String centena = sc.nextLine().strip();
                
                System.out.println("Deseja apostar no 1º prêmio ou em todos os prêmios? (C - 1º prêmio / T - Todos os prêmios)");
                String modal2 = sc.nextLine().toUpperCase();

                System.out.println("Apostou em "+ jogo.getAnimal(centena) + ".");
                
                System.out.println("Qual o valor da aposta?");
                double vlr = sc.nextDouble();

                ApostaCentena jogada = new ApostaCentena(jogo.getAnimal(centena), modal2, centena, vlr);
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
                for (int i = 0; i < 5; i++){
                    System.out.println(sorteados.get(i) + " (" + animalSorteado.get(i).getNome() + ")");
                }
                
                // Verificação da centena apostada com os milhares sorteados
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
            
            else if (modal1.equals("M")){
                System.out.println("Informe o milhar que deseja apostar (1000 a 9999): ");
                String milhar = sc.nextLine().strip();
                
                System.out.println("Deseja apostar no 1º prêmio ou em todos os prêmios? (C - 1º prêmio / T - Todos os prêmios)");
                String modal2 = sc.nextLine().toUpperCase();

                System.out.println("Apostou em "+ jogo.getAnimal(milhar) + ".");
                
                System.out.println("Qual o valor da aposta?");
                double vlr = sc.nextDouble();

                ApostaMilhar jogada = new ApostaMilhar(jogo.getAnimal(milhar), modal2, milhar, vlr);
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
                for (int i = 0; i < 5; i++){
                    System.out.println(sorteados.get(i) + " (" + animalSorteado.get(i).getNome() + ")");
                }

                // Verificação do milhar apostada com os milhares sorteados
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

            else if (modal1.equals("DG")){
                System.out.println("Informe o primeiro grupo que deseja apostar. (1 a 25)");
                int grp1 = sc.nextInt();
                sc.nextLine();
                
                System.out.println("Agora informe o segundo grupo. (1 a 25)");
                int grp2 = sc.nextInt();
                sc.nextLine();

                List<String> animaisApostados = new ArrayList<>();
                animaisApostados.add("" + grp1);
                animaisApostados.add("" + grp2);
                
                System.out.println("Apostou nos animais: " + jogo.getAnimal(grp1 - 1).getNome() + " e " + jogo.getAnimal(grp2 - 1).getNome() + ".");
                
                System.out.println("Qual valor da aposta? R$");
                double vlr = sc.nextDouble();

                DuqueGrupo jogada = new DuqueGrupo(animaisApostados, vlr);
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
                for (Animal animal : sorteados){
                    System.out.println(animal.getNome() + " " + animal.getGrupo() + " " + animal.getNumeros());
                }
                // Verificação dos grupos sorteados com o grupo apostado
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

            else if (modal1.equals("TG")){
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
                for (Animal animal : sorteados){
                    System.out.println(animal.getNome() + " " + animal.getGrupo() + " " + animal.getNumeros());
                }
                // Verificação dos grupos sorteados com o grupo apostado
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

                }
            case "2":
                System.out.println("Quais regras você deseja consultar:\n1-Regras Gerais\n2-Regras Aposta\n3-Tipos de Apostas e Prêmios");
                String opc=sc.nextLine();
                
                switch(opc){
                    case "1":
                        Regras.regasGerais();
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
            } 
            
            sc = new Scanner(System.in);
            
        }    
    }
}
