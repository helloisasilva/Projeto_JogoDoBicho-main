package com.allantrindade.jogodobicho.Jogo;

public class Regras {

    public static void regasGerais() {
            char caractere = '-';
            int quantidade = 150;
            String resultado = String.valueOf(caractere).repeat(quantidade);
            System.out.println(resultado);
            System.out.println("O Jogo do Bicho é um jogo de apostas onde você seleciona uma das opções de apostas e informa ao bicheiro qual deseja apostar.");
            System.out.println("O bicheiro leva sua aposta para o sorteio e aguarda o resultado, o qual tem 5 categorias, sendo que cada uma tem um número de 4 dígitos*.");
            System.out.println("Após o sorteio é apurado o resultado do Jogo do Bicho e a premiação é distribuída de acordo com as apostas dos vencedores.");
            System.out.println("Para apostar, você precisa escolher entre:");
            System.out.println("Animal(grupo)          Duque(dois animais)");
            System.out.println("Dezena                 Terno(três animais)");
            System.out.println("Centena");
            System.out.println("Milhar");
            System.out.println("\n*Para a aposta de grupo, o resultado dos sorteios aparecerá apenas os animais, não os milhares sorteados.\n");
            System.out.println(resultado);
    }
    public static void regrasApostas(){
        char caractere = '-';
        int quantidade = 150;
        String resultado = String.valueOf(caractere).repeat(quantidade);
        System.out.println(resultado);
        System.out.println("\nHá 2 tipos diferentes de apostas:");
        System.out.println("\n1- Apostas em animais. A aposta mais simples em um só animal chama-se “grupo”, ");
        System.out.println("em dois animais ao mesmo tempo “duque”, em três animais “terno”, etc.");
        System.out.println("\n2-Apostas em números (ou avançadas). Estes números aparecem junto aos animais no sorteio e ");
        System.out.println("você deve acertar as duas últimas cifras (“dezena”), as três últimas (“centena”) ou o número inteiro (“milhar”).");
        System.out.println("\nObservação: realmente cada animal representa uma aposta em 4 dezenas ao mesmo tempo. ");
        System.out.println("Por exemplo, o avestruz é uma aposta nos números 01, 02, 03 e 04; ");
        System.out.println("a águia no 05, 06, 07 e 08; e assim até a vaca que ");
        System.out.println("representa os números 97, 98, 99 e 00.");
        System.out.println("Cada tipo de aposta tem prêmios diferentes.\n");   
        System.out.println(resultado);
    }
    public static void ApostasPremios(){
        char caractere = '-';
        int quantidade = 150;
        String resultado = String.valueOf(caractere).repeat(quantidade);  
        System.out.println(resultado);
        System.out.println("\nVeja na Tabela a seguir os tipos de apostas e suas premiações:\n");
         // Dados da tabela 
        String[][] dados = {
            //dados dos tipos de aposta
            
            //1 linha                      //2linha     //3linha
            {"Grupo(Aposta em um animal)","1ª posição", "18 * ValorApostado"},
            {"                        ","demais posições", "18/5 * ValorApostado"},
            
            {"Dezena(Aposta em uma dezena)", "1ª posição", "60 * ValorApostado"},
            {"                         ","demais posições", "60/5 * ValorApostado"},
            
            {"Centena(Aposta em uma centena","1ª posição","600 * ValorApostado"},
            {"                           ","demais posições", "600/5 * ValorApostado"},
            
            {"Milhar(Aposta em um milhar)","1ª posição","4000 * ValorApostado"},
            {"                        ","demais posições", "4000/5 * ValorApostado"},
            
            {"Duque de Grupo(Aposta em dois grupos de animais)","Qualquer posição","18.5 * ValorApostado"},
            
            {"Terno de Grupo(Aposta em tres grupos de animais)","Qualquer posição","130 * ValorApostado"},

            
            {"Duque de Dezena(Aposta em duas dezenas)","Qualquer posição","300 * ValorApostado"},
            
            {"Terno de Dezena(Aposta em tres dezenas)", "Qualquer posição", "3000 * ValorApostado"},
   
   
        };
   // Imprimir cabeçalho
   System.out.println(resultado); 
        System.out.printf("%-55s%-30s%s%n", "Tipo de aposta", "Sorteio", "Prêmio");
    System.out.println(resultado);

   for (String[] linha : dados) {
        System.out.printf("%-55s%-30s%s%n", linha[0], linha[1], linha[2]);
        }
   System.out.println(resultado);
    }

 }
