/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxgraphs.modelo;

import javafxgraphs.modelo.DificuldadeDIFICIL;
import javafxgraphs.modelo.DificuldadeFACIL;
import javafxgraphs.modelo.DificuldadeMEDIO;
import javafxgraphs.modelo.Jogador;
import javafxgraphs.modelo.MiniJogo;

/**
 *
 * @author Artur Ferreira
 */
public class Arcade {

    
    int nivel = 0;
    Jogador jogador;
    MiniJogo jogo;
    
    public Arcade(Jogador jogador, int nivel){
        
        this.jogador = jogador;
        
        if(nivel <= 8){
              jogo = new MiniJogo(new DificuldadeFACIL());
        } else if (nivel >= 9 && nivel <= 16) {
            jogo = new MiniJogo(new DificuldadeMEDIO());
        } else {
            jogo = new MiniJogo(new DificuldadeDIFICIL());
        }
    }

    public int getNivel() {
        return nivel;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public MiniJogo getJogo() {
        return jogo;
    }
    
    
    
}
