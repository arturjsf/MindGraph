/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxgraphs;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafxgraphs.modelo.Jogador;

/**
 *
 * @author brunomnsilva
 */
public class AppMindGraphsFX extends Application {

    @Override
    public void start(Stage primaryStage) {

        //PAINEL
        BorderPane root1 = new BorderPane();
        BorderPane root2 = new BorderPane();
        
        //JANELAS
        Scene introJanela = new Scene(root1, 800, 600);
        Scene menuJanela = new Scene(root2, 800, 600);

        //horizontal box inicial
        HBox boxNomeJogador = new HBox();
        boxNomeJogador.setAlignment(Pos.CENTER);
        boxNomeJogador.getChildren().add(new Text("Bem vindo ao jogo Mind Graphs! "));

        //text field com o nome do jogador
        TextField textNomeJogador = new TextField();
        textNomeJogador.setText("Introduza o nome do jogador");
        textNomeJogador.setPrefWidth(250);

         //Botao para criar o mini jogo
        Button btnCriarJogo = new Button("OK");
        btnCriarJogo.setOnAction(new EventHandler<ActionEvent>() {
    @Override public void handle(ActionEvent e) {
        Jogador jogador1 = new Jogador(textNomeJogador.getText());
        System.out.println(jogador1.toString());
        primaryStage.setScene(menuJanela);
        
    }
});

        //vamos adicionar as coisas á hbox
        boxNomeJogador.getChildren().addAll(textNomeJogador, btnCriarJogo);

        //vamos adicionar a hbox ao border pane
        root1.setCenter(boxNomeJogador);

        
        
        
        
        
        
        
        
        
        
        
        //CSS
        root1.getStylesheets().addAll(this.getClass().getResource("/javafxgraphs/ui/resources/style.css").toExternalForm());
        root1.setId("pane");


        //propriedades da janela
        primaryStage.setTitle("Mind Graph!");
        primaryStage.setScene(introJanela);
        primaryStage.centerOnScreen();
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
