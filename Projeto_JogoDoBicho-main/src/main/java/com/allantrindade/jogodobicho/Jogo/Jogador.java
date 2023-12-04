package com.allantrindade.jogodobicho.Jogo;

import java.util.ArrayList;
import java.util.List;

import com.allantrindade.jogodobicho.Apostas.Aposta;
import com.allantrindade.jogodobicho.Apostas.ApostaCentena;
import com.allantrindade.jogodobicho.Apostas.ApostaDezena;
import com.allantrindade.jogodobicho.Apostas.ApostaGrupo;
import com.allantrindade.jogodobicho.Apostas.ApostaMilhar;
import com.allantrindade.jogodobicho.Apostas.DuqueDezena;
import com.allantrindade.jogodobicho.Apostas.DuqueGrupo;
import com.allantrindade.jogodobicho.Apostas.TernoDezena;
import com.allantrindade.jogodobicho.Apostas.TernoGrupo;


public class Jogador {
    private String nome;
    private ArrayList<Aposta> apostas;
    private double ganhos = 0;
    private double perdas = 0;

    public Jogador(String name){
        nome = name;
        apostas = new ArrayList<>();
    }

    public Jogador() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getNome() {
        return nome;
    }

    public void incrementarGanho(double ganho){
        this.ganhos += ganho;
    }

    public void incrementarPerda(double perda){
        this.perdas += perda;
    }
    public void incrementarApostas(Aposta aposta){
        apostas.add(aposta);
    }
    public void mostrarGanhos(){
        System.out.println("Você já ganhou "+ ganhos + " jogando o jogo do bicho!");
    }

    public void mostrarPerdas(){
        System.out.println("Você já investiu "+ perdas + " no jogo do bicho!");
    }

    public void mostrarApostas(){
        System.out.println("------- Aqui estão suas apostas: -------");
        for (Aposta aposta : apostas){
            if (aposta instanceof ApostaGrupo){
                ApostaGrupo apostaGrupo = (ApostaGrupo) aposta;
                System.out.println("Tipo de aposta: Grupo");
                System.out.println("Animal apostado: " + apostaGrupo.getAnimalApostado().getNome());
                System.out.println("Modalidade: " + apostaGrupo.getModalidade());
                System.out.println("Valor apostado: " + apostaGrupo.getValor());
                System.out.println("----------------------------");
            }
            else if (aposta instanceof ApostaDezena){
                ApostaDezena apostaDezena = (ApostaDezena) aposta;
                System.out.println("Tipo de aposta: Dezena");
                System.out.println("Dezena apostada: " + apostaDezena.getDezena());
                System.out.println("Modalidade: " + apostaDezena.getModalidade());
                System.out.println("Valor apostado: " + apostaDezena.getValor());
                System.out.println("----------------------------");
            }
            else if (aposta instanceof ApostaCentena){
                ApostaCentena apostaCentena = (ApostaCentena) aposta;
                System.out.println("Tipo de aposta: Centena");
                System.out.println("Centena apostada: " + apostaCentena.getCentena());
                System.out.println("Modalidade: " + apostaCentena.getModalidade());
                System.out.println("Valor apostado: " + apostaCentena.getValor());
                System.out.println("----------------------------");
            }
            else if (aposta instanceof ApostaMilhar){
                ApostaMilhar apostaMilhar = (ApostaMilhar) aposta;
                System.out.println("Tipo de aposta: Milhar");
                System.out.println("Milhar apostado: " + apostaMilhar.getMilhar());
                System.out.println("Modalidade: " + apostaMilhar.getModalidade());
                System.out.println("Valor apostado: " + apostaMilhar.getValor());
                System.out.println("----------------------------");
            }
            else if (aposta instanceof DuqueGrupo){
                DuqueGrupo duqueGrupo = (DuqueGrupo) aposta;
                List<String> gruposApostados = new ArrayList<>();
                gruposApostados.addAll(duqueGrupo.getGruposApostados());

                System.out.println("Tipo de aposta: Duque de grupo");
                System.out.println("Grupos apostados: " + gruposApostados);
                System.out.println("Valor apostado: " + duqueGrupo.getValor());
                System.out.println("----------------------------");
            }
            else if (aposta instanceof TernoGrupo){
                TernoGrupo ternoGrupo = (TernoGrupo) aposta;
                List<String> gruposApostados = new ArrayList<>();
                gruposApostados.addAll(ternoGrupo.getGruposApostados());

                System.out.println("Tipo de aposta: Duque de grupo");
                System.out.println("Grupos apostados: " + gruposApostados);
                System.out.println("Valor apostado: " + ternoGrupo.getValor());
                System.out.println("----------------------------");
            }
            else if (aposta instanceof DuqueDezena){
                DuqueDezena duqueDezena = (DuqueDezena) aposta;
                List<String> dezenasApostadas = new ArrayList<>();
                dezenasApostadas.addAll(duqueDezena.getDezenasApostadas());

                System.out.println("Tipo de aposta: Duque de grupo");
                System.out.println("Grupos apostados: " + dezenasApostadas);
                System.out.println("Valor apostado: " + duqueDezena.getValor());
                System.out.println("----------------------------");
            }
            else if (aposta instanceof TernoDezena){
                TernoDezena ternoDezena = (TernoDezena) aposta;
                List<String> dezenasApostadas = new ArrayList<>();
                dezenasApostadas.addAll(ternoDezena.getDezenasApostadas());

                System.out.println("Tipo de aposta: Duque de grupo");
                System.out.println("Grupos apostados: " + dezenasApostadas);
                System.out.println("Valor apostado: " + ternoDezena.getValor());
                System.out.println("----------------------------");
            }
        }
        
    }

}