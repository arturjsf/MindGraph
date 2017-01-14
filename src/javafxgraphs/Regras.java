/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxgraphs;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafxgraphs.modelo.Jogador;

/**
 *
 * @author Artur Ferreira
 */
public class Regras{
    
    public static Scene menuRegrasTT(Jogador jogador) {
        BorderPane rootRegras = new BorderPane();
        Scene janelaRegras = new Scene(rootRegras, 1000, 600);
        System.out.println("menu REGRAS TT");
        //para apresentar o nome do jogador
        Text nomeJogador = new Text(jogador.getNome());
        nomeJogador.setFill(Color.BLACK);
        nomeJogador.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        rootRegras.setTop(nomeJogador);
        return janelaRegras;
    }

    public static Scene menuRegrasArcade(Jogador jogador) {
        BorderPane rootRegras = new BorderPane();
        Scene janelaRegras = new Scene(rootRegras, 1000, 600);
        System.out.println("menu REGRAS Arcade");
        //para apresentar o nome do jogador
        Text nomeJogador = new Text(jogador.getNome());
        nomeJogador.setFill(Color.BLACK);
        nomeJogador.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        rootRegras.setTop(nomeJogador);
        return janelaRegras;
    }
    
}
