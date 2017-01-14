/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxgraphs;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import static javafxgraphs.AppMindGraphsFX.painel;
import javafxgraphs.modelo.Jogador;

/**
 *
 * @author Artur Ferreira
 */
public class Regras{
    
    /**
     * 
     * @param jogador
     * @return 
     */
    public static Scene menuRegrasTT(Stage primaryStage, Jogador jogador) {
        
        BorderPane rootRegras = new BorderPane();
        Scene janelaRegras = new Scene(rootRegras, 1000, 600);
        
        System.out.println("menu REGRAS TT");
        
        //para apresentar o nome do jogador
        Text nomeJogador = new Text(jogador.getNome());
        nomeJogador.setFill(Color.BLACK);
        nomeJogador.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        rootRegras.setTop(nomeJogador);
        
        VBox vb = new VBox();
        vb.setSpacing(80);
        vb.setAlignment(Pos.CENTER);
        
        //botao Voltar
        Button btn1 = new Button();
        btn1.setText("Voltar");
        btn1.setMaxWidth(250);
        btn1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Scene scene = TimeTrial.menuTimeTrial(primaryStage, jogador);
                primaryStage.setScene(scene);
                primaryStage.show();
            }
        });
        
        rootRegras.getStylesheets().addAll(AppMindGraphsFX.class.getResource("/javafxgraphs/ui/resources/style.css").toExternalForm());     
        rootRegras.setId(painel);
        
        vb.getChildren().addAll(btn1);
        rootRegras.setCenter(vb);
        
        return janelaRegras;
    }

    
    /**
     * 
     * @param jogador
     * @return 
     */
    public static Scene menuRegrasArcade(Stage primaryStage, Jogador jogador) {
        
        
        BorderPane rootRegras = new BorderPane();
        Scene janelaRegras = new Scene(rootRegras, 1000, 600);
        System.out.println("menu REGRAS Arcade");
        
        
        //para apresentar o nome do jogador
        Text nomeJogador = new Text(jogador.getNome());
        nomeJogador.setFill(Color.BLACK);
        nomeJogador.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        rootRegras.setTop(nomeJogador);
        
        VBox vb = new VBox();
        vb.setSpacing(80);
        vb.setAlignment(Pos.CENTER);
        
        //botao Voltar
        Button btn1 = new Button();
        btn1.setText("Voltar");
        btn1.setMaxWidth(250);
        btn1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Scene scene = Arcade.menuArcade(primaryStage, jogador);
                primaryStage.setScene(scene);
                primaryStage.show();
            }
        });
        
        rootRegras.getStylesheets().addAll(AppMindGraphsFX.class.getResource("/javafxgraphs/ui/resources/style.css").toExternalForm());     
        rootRegras.setId(painel);
        
        vb.getChildren().addAll(btn1);
        rootRegras.setCenter(vb);
        
        
        return janelaRegras;
        
        
    }
    
}
