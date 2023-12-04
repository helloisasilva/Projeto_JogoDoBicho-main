package com.allantrindade.jogodobicho.Jogo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class JogoDoBicho {
    List<Animal> animais;
    List<String> grupos;
    List<List<String>> numeros;
    public JogoDoBicho() {
        animais = new ArrayList<>();
        grupos = new ArrayList<>();
        numeros = new ArrayList<>();
        // Adicionando animais com seus numeros correspondentes
        List<String> bichos = new ArrayList<>();
        bichos.addAll(Arrays.asList("avestruz", "águia", "burro", "borboleta", "cachorro", "cabra",
                "carneiro", "camelo", "cobra", "coelho", "cavalo", "elefante", "galo", "gato", "jacaré",
                "leão", "macaco", "porco", "pavão", "peru", "touro", "tigre", "urso", "veado", "vaca"));
        
        grupos.addAll(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13",
                                    "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25"));
        
        numeros.addAll(Arrays.asList(
        Arrays.asList("01","02","03","04"),Arrays.asList("05", "06","07","08"),Arrays.asList("09", "10","11","12"),Arrays.asList("13", "14","15","16"),Arrays.asList("17", "18","19","20"),
        Arrays.asList("21", "22","23","24"),Arrays.asList("25", "26","27","28"),Arrays.asList("29", "30","31","32"),Arrays.asList("33", "34","35","36"),Arrays.asList("37", "38","39","40"),
        Arrays.asList("41", "42","43","44"),Arrays.asList("45", "46","47","48"),Arrays.asList("49", "50","51","52"),Arrays.asList("53", "54","55","56"),Arrays.asList("57", "58","59","60"),
        Arrays.asList("61", "62","63","64"),Arrays.asList("65", "66","67","68"),Arrays.asList("69", "70","71","72"),Arrays.asList("73", "74","75","76"),Arrays.asList("77", "78","79","80"),
        Arrays.asList("81", "82","83","84"),Arrays.asList("85", "86","87","88"),Arrays.asList("89", "90","91","92"),Arrays.asList("93", "94","95","96"),Arrays.asList("97", "98","99","00")));
        
        for (int i = 0; i < bichos.size() ; i++) {
            Animal bicho = new Animal(bichos.get(i), grupos.get(i), numeros.get(i));
            animais.add(bicho);
        }
    }

    public List<Animal> getAnimais() {
        return animais;
    }

    public Animal getAnimal(int index){
        return animais.get(index);
    }

    public Animal getAnimal(String numero){
        if (numero.length() == 2){
            for (Animal animal : this.animais){
                List<String> grupos = new ArrayList<>();
                grupos = animal.getNumeros();
                if (grupos.contains(numero)) return animal;
            }
        }

        else if (numero.length() == 3){
            String sub = numero.substring(1, 3);
            for (Animal animal : this.animais){
                List<String> grupos = new ArrayList<>();
                grupos = animal.getNumeros();
                if (grupos.contains(sub)) return animal;
            }
        }

        else if (numero.length() == 4){
            String sub = numero.substring(2, 4);
            for (Animal animal : this.animais) {
                List<String> grupos = new ArrayList<>();
                grupos = animal.getNumeros();
                if (grupos.contains(sub)) return animal;
            }
        }

        return null;
    }

    public List<Animal> sortearAnimais() {
        
        List<Animal> sorteados = new ArrayList<>();
        Random rd = new Random();
        
        for (int i = 0; i < 5; i++){
            int indiceSorteado = rd.nextInt(animais.size());
            Animal sorteado = getAnimal(indiceSorteado);
            sorteados.add(sorteado);
        }
        return sorteados;
    }

    public List<String> sortearMilhares(){
        List<String> numerosSorteados = new ArrayList<>();
        Random rd = new Random();

        for (int i=0; i<5; i++){
            int numero = rd.nextInt(1000, 10000);
            String numString = Integer.toString(numero);
            numerosSorteados.add(numString);
        }

        return numerosSorteados;
    }

    /*
    Random rd = new Random();
        int indiceSorteado = rd.nextInt(animais.size());

        return animais.get(indiceSorteado);

    public Aposta realizarApostaDG(String jgdr, String modal, List<Integer> grps, double valor ){
      Aposta jogador = new Aposta(jgdr, modal, grps, valor);
      System.out.println("O jogador "+jgdr+" realizou a aposta na categoria "+modal+", nos grupos "+grps.toString()+", com o valor R$"+valor);
      return jogador;
    }

    public Aposta realizarApostaTG(String jgdr, String modal, List<Integer> grps, double valor){
      Aposta jogador = new Aposta(jgdr, modal, grps, valor);
      System.out.println("O jogador "+jgdr+" realizou a aposta na categoria "+modal+", nos grupos "+grps.toString()+", com o valor R$"+valor);
      return jogador;
    }
    */
}