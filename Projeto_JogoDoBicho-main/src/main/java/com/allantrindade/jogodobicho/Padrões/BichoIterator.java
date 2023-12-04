package com.allantrindade.jogodobicho.Padrões;
import java.util.*;

import com.allantrindade.jogodobicho.Jogo.Animal;

public class BichoIterator implements Iterator<Animal>{
    private List<Animal> animais;
    private int qtddAnimais;
    private Random rd;

    public BichoIterator(List<Animal> animais, int qtdd){
        this.animais = animais;
        qtddAnimais = qtdd;
        rd = new Random();
    }

    public boolean temRepetidos(){
        Set<Animal> set = new HashSet<>(animais);
        return set.size() < animais.size();
    }

    public Animal sortearAnimal(){
        int indiceSorteado = rd.nextInt(qtddAnimais);
        return animais.get(indiceSorteado);
    }

    public boolean contemAnimal(Animal animal){
        for (Animal a : animais){
            if (a.getNome().equals(animal.getNome())){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasNext(){
        return animais.size() < qtddAnimais && !temRepetidos();
    }

    @Override
    public Animal next() {
        while (hasNext()) {
            Animal novoAnimal = sortearAnimal();
            if (!contemAnimal(novoAnimal)){
                animais.add(novoAnimal);
            }
        }
        return animais.get(animais.size() - 1);
    }
}
