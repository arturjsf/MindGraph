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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafxgraphs.modelo.Jogador;

/**
 *
 * @author Artur Ferreira
 */
public class Temas{
    
    
    /**
     * MENU TEMAS
     *
     * @param primaryStage
     * @param jogador
     * @return
     */
    
    public static Scene menuTemas(Stage primaryStage, Jogador jogador) {
        
        BorderPane rootTemas = new BorderPane();
        Scene janelaTemas = new Scene(rootTemas, 1000, 600);
        System.out.println("menu TEMAS");
        int btnSize = 150;
        
        // BOTOES DE ESCOLHA DE TEMA
        Text textEscolherTema = new Text("Escolha o tema");
        textEscolherTema.setFill(Color.GREEN);
        textEscolherTema.setFont(Font.font("Verdana", FontWeight.BOLD, 40));
        
        Button btnSurf = new Button("Surf");
        btnSurf.setMaxWidth(btnSize);
        btnSurf.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                
                rootTemas.getStylesheets().addAll(AppMindGraphsFX.class.getResource("/javafxgraphs/ui/resources/style.css").toExternalForm());
                AppMindGraphsFX.painel="surfPane";
                rootTemas.setId(AppMindGraphsFX.painel);
                
                Scene scene = AppMindGraphsFX.menuPrincipal(primaryStage, jogador);
                primaryStage.setScene(scene);
                primaryStage.show();
                
                //primaryStage.setScene(menuPrincipal(primaryStage));
            }
        });
        Button btnCycling = new Button("Cycling");
        btnCycling.setMaxWidth(btnSize);
        btnCycling.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                 rootTemas.getStylesheets().addAll(AppMindGraphsFX.class.getResource("/javafxgraphs/ui/resources/style.css").toExternalForm());
                 AppMindGraphsFX.painel="cyclingPane";
                 rootTemas.setId(AppMindGraphsFX.painel);
                
                Scene scene = AppMindGraphsFX.menuPrincipal(primaryStage, jogador);
                primaryStage.setScene(scene);
                primaryStage.show();
            }
        });
        Button btnRugby = new Button("Rugby");
        btnRugby.setMaxWidth(btnSize);
        btnRugby.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                rootTemas.getStylesheets().addAll(AppMindGraphsFX.class.getResource("/javafxgraphs/ui/resources/style.css").toExternalForm());
                AppMindGraphsFX.painel="rugbyPane";
                rootTemas.setId(AppMindGraphsFX.painel);
                
                Scene scene = AppMindGraphsFX.menuPrincipal(primaryStage, jogador);
                primaryStage.setScene(scene);
                primaryStage.show();
            }
        });
        Button btnFootball = new Button("Football");
        btnFootball.setMaxWidth(btnSize);
        btnFootball.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                rootTemas.getStylesheets().addAll(AppMindGraphsFX.class.getResource("/javafxgraphs/ui/resources/style.css").toExternalForm());
                AppMindGraphsFX.painel="footballPane";
                rootTemas.setId(AppMindGraphsFX.painel);
                
                Scene scene = AppMindGraphsFX.menuPrincipal(primaryStage, jogador);
                primaryStage.setScene(scene);
                primaryStage.show();
            }
        });
        Button btnVoltar = new Button("Voltar");
        btnVoltar.setMaxWidth(250);
        btnVoltar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("Voltar menu");
                
                Scene scene = AppMindGraphsFX.menuPrincipal(primaryStage, jogador);
                primaryStage.setScene(scene);
                primaryStage.show();
            }
        });
        
        
        //VBOX DE BOTOES DE ESCOLHA DE TEMA
        VBox boxBotoesTema = new VBox(textEscolherTema, btnSurf, btnFootball, btnRugby, btnCycling, btnVoltar);
        boxBotoesTema.setSpacing(10);
        boxBotoesTema.setAlignment(Pos.CENTER);
        rootTemas.setCenter(boxBotoesTema);
        
        
        //para apresentar o nome do jogador
        Text nomeJogador = new Text(jogador.getNome());
        nomeJogador.setFill(Color.BLACK);
        nomeJogador.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        rootTemas.setTop(nomeJogador);
        //CSS
        //rootTemas.getStylesheets().addAll(this.getClass().getResource("/javafxgraphs/ui/resources/style.css").toExternalForm());
        rootTemas.setId("pane");
        // primaryStage.setScene(janelaMenu);
        return janelaTemas;
    }
    
}
