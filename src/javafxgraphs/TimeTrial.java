/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxgraphs;

import javafxgraphs.modelo.DificuldadeDIFICIL;
import javafxgraphs.modelo.DificuldadeFACIL;
import javafxgraphs.modelo.DificuldadeMEDIO;
import javafxgraphs.modelo.Jogador;
import javafxgraphs.modelo.MiniJogo;

/**
 *
 * @author Artur Ferreira
 */
public class TimeTrial {

    int seconds;
    int nivel = 0;
    Jogador jogador;
    MiniJogo jogo;

    //para criar um timetrial novo precisamos do nome do jogador e a partir daqui é tudo criado
    public TimeTrial(Jogador jogador) {

        this.jogador = jogador;

        nivel++;

        if (nivel <= 5) {
            setSeconds(120);
            jogo = new MiniJogo(new DificuldadeFACIL());
        } else if (nivel >= 6 && nivel <= 15) {
            setSeconds(100);
            jogo = new MiniJogo(new DificuldadeMEDIO());
        } else {
            setSeconds(60);
            jogo = new MiniJogo(new DificuldadeDIFICIL());
        }

    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getSeconds() {
        return seconds;
    }

    public int getNivel() {
        return nivel;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public MiniJogo getMiniJogo() {
        return jogo;
    }

}
