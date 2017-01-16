/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxgraphs;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.animation.TranslateTransitionBuilder;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import static javafxgraphs.AppMindGraphsFX.painel;
import javafxgraphs.modelo.Jogador;

/**
 * CLASSE REGRAS
 * @author Artur Ferreira
 */
public class Regras{
    
    /**
     * 
     * @param primaryStage stage inicial
     * @param jogador jogador
     * @return Devolve uma cena com um texto de regras sobre o tipo de jogo Time Trial
     */
    public static Scene menuRegrasTT(Stage primaryStage, Jogador jogador) {
        
        BorderPane rootRegras = new BorderPane();
        Scene janelaRegras = new Scene(rootRegras, 1000, 600);
        
        //para apresentar o nome do jogador
        Text nomeJogador = new Text(jogador.getNome());
        nomeJogador.setFill(Color.BLACK);
        nomeJogador.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
        rootRegras.setTop(nomeJogador);
        
        VBox vb = new VBox();
        vb.setSpacing(80);
        vb.setAlignment(Pos.CENTER);
        
                //texto Regras
        DropShadow ds = new DropShadow();
        Text textoRegras = new Text();
        textoRegras.setEffect(ds);
        textoRegras.setCache(true);
        textoRegras.setX(20);
        textoRegras.setY(10);
        
                
        //Texto das regras
        textoRegras.setText(jogador.lerFicheiroRegras("TT"));
        textoRegras.setFill(Color.BLACK);
        textoRegras.setFont(Font.font(null, FontWeight.BOLD, 20));
        textoRegras.setTextAlignment(TextAlignment.CENTER);
        
        
                //animacao textoNomes
        TranslateTransition translateTransition = TranslateTransitionBuilder.create().node(textoRegras).fromY(500).toY(20).duration(new Duration(8000)).interpolator(Interpolator.LINEAR).build();
       

        
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
        
        
        vb.getChildren().addAll(textoRegras, btn1);
        rootRegras.setCenter(vb);
        translateTransition.play();        
        return janelaRegras;
    }

    
    /**
     * 
     * @param primaryStage stage inicial
     * @param jogador jogador
     * @return Devolve uma cena com um texto de regras sobre o tipo de jogo Arcade
     */
    public static Scene menuRegrasArcade(Stage primaryStage, Jogador jogador) {
        
        
        BorderPane rootRegras = new BorderPane();
        Scene janelaRegras = new Scene(rootRegras, 1000, 600);
        
        //para apresentar o nome do jogador
        Text nomeJogador = new Text(jogador.getNome());
        nomeJogador.setFill(Color.BLACK);
        nomeJogador.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        rootRegras.setTop(nomeJogador);
        
        VBox vb = new VBox();
        vb.setSpacing(80);
        vb.setAlignment(Pos.CENTER);

            //texto Regras
        DropShadow ds = new DropShadow();
        Text textoRegras = new Text();
        textoRegras.setEffect(ds);
        textoRegras.setCache(true);
        textoRegras.setX(20);
        textoRegras.setY(10);
        
                
        //Texto das regras
        textoRegras.setText(jogador.lerFicheiroRegras("Arcade"));
        textoRegras.setFill(Color.BLACK);
        textoRegras.setFont(Font.font(null, FontWeight.BOLD, 20));
        textoRegras.setTextAlignment(TextAlignment.CENTER);
        
        
                //animacao textoNomes
        TranslateTransition translateTransition = TranslateTransitionBuilder.create().node(textoRegras).fromY(500).toY(20).duration(new Duration(8000)).interpolator(Interpolator.LINEAR).build();
       

                
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
        

        
        vb.getChildren().addAll(textoRegras, btn1);
        rootRegras.setCenter(vb);
        translateTransition.play();         
        return janelaRegras;
        
        
    }
    
}
