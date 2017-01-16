/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxgraphs;

import javafx.animation.Interpolator;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.animation.TranslateTransitionBuilder;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
 * CLASSE RECORDES
 * @author Artur Ferreira
 */
public class Recordes{
    
    /**
     * RECORDES TT
     * 
     * @param primaryStage stage Inicial
     * @param jogador jogador
     * @return  Este metodo devolve uma "cena" com os recordes do tipo de jogo Time Trial
     */
    public static Scene recordesTimeTrial(Stage primaryStage, Jogador jogador) {
        
        
        BorderPane rootRecordes = new BorderPane();
        Scene janelaRecordes = new Scene(rootRecordes, 1000, 600);

        
        //para apresentar o nome do jogador
        Text nomeJogador = new Text("Jogador\n"+jogador.getNome());
        nomeJogador.setFill(Color.BLACK);
        nomeJogador.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        rootRecordes.setTop(nomeJogador);
          
        //texto Nomes
        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0f);
        ds.setColor(Color.color(0.4f, 0.4f, 0.4f));
        Text textoRecordes = new Text();
        textoRecordes.setEffect(ds);
        textoRecordes.setCache(true);
        textoRecordes.setX(20);
        textoRecordes.setY(10);
             
        //meter o texto dos recordes aki
        textoRecordes.setText(jogador.lerFicheiroRecordes("TT"));
        textoRecordes.setFill(Color.CYAN);
        textoRecordes.setFont(Font.font(null, FontWeight.BOLD, 30));
        textoRecordes.setTextAlignment(TextAlignment.CENTER);
        
        
        
        VBox vb = new VBox();
        vb.setPadding(new Insets(0, 0, 100, 0));
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
        
        
        //animacao textoNomes
        TranslateTransition translateTransition = TranslateTransitionBuilder.create().node(textoRecordes).fromY(500).toY(-500).duration(new Duration(8000)).interpolator(Interpolator.LINEAR).cycleCount(Timeline.INDEFINITE).build();
       

        rootRecordes.getStylesheets().addAll(AppMindGraphsFX.class.getResource("/javafxgraphs/ui/resources/style.css").toExternalForm());     
        rootRecordes.setId(painel);
        
        vb.getChildren().addAll(btn1);
        rootRecordes.setCenter(textoRecordes);
        rootRecordes.setBottom(vb);
        translateTransition.play();
        return janelaRecordes;
    }

    /**
     * RECORDES ARCADE
     * 
     * @param primaryStage stage Inicial
     * @param jogador jogador
     * @return Este metodo devolve uma "cena" com os recordes do tipo de jogo Time Trial
     */
    public static Scene recordesArcade(Stage primaryStage, Jogador jogador) {
        
        
        BorderPane rootRecordes = new BorderPane();
        Scene janelaRecordes = new Scene(rootRecordes, 1000, 600);
              
        //para apresentar o nome do jogador
        Text nomeJogador = new Text("Jogador\n"+jogador.getNome());
        nomeJogador.setFill(Color.BLACK);
        nomeJogador.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        rootRecordes.setTop(nomeJogador);
        
        
   
        //texto Nomes
        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0f);
        ds.setColor(Color.color(0.4f, 0.4f, 0.4f));
        Text textoRecordes = new Text();
        textoRecordes.setEffect(ds);
        textoRecordes.setCache(true);
        textoRecordes.setX(20);
        textoRecordes.setY(10);
        
        
        //meter o texto dos recordes aki
        textoRecordes.setText(jogador.lerFicheiroRecordes("Arcade"));
        textoRecordes.setFill(Color.CYAN);
        textoRecordes.setFont(Font.font(null, FontWeight.BOLD, 30));
        textoRecordes.setTextAlignment(TextAlignment.CENTER);
        
        
        
        
        VBox vb = new VBox();
        vb.setPadding(new Insets(0, 0, 100, 0));
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
        
        
        //animacao textoNomes
        TranslateTransition translateTransition = TranslateTransitionBuilder.create().node(textoRecordes).fromY(500).toY(-500).duration(new Duration(8000)).interpolator(Interpolator.LINEAR).cycleCount(Timeline.INDEFINITE).build();
        

        rootRecordes.getStylesheets().addAll(AppMindGraphsFX.class.getResource("/javafxgraphs/ui/resources/style.css").toExternalForm());     
        rootRecordes.setId(painel);
        
        vb.getChildren().addAll(btn1);
        rootRecordes.setCenter(textoRecordes);
        rootRecordes.setBottom(vb);
        translateTransition.play();
        
        return janelaRecordes;
    }
    
}
