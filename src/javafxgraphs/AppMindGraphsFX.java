/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxgraphs;


import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafxgraphs.modelo.Jogador;

/**
 *
 * @author Artur e Sergio
 */
public class AppMindGraphsFX extends Application{

    //preciso de criar o jogador aki e vou ter de o enviar sempre dentro dos metodos
    Jogador jogador = null;
    
    static String painel = "";
    
    @Override
    public void start(Stage primaryStage) {

        BorderPane root = new BorderPane();
        Scene intro = new Scene(root, 1000, 600);

        //horizontal box inicial
        VBox boxNomeJogador = new VBox();
        boxNomeJogador.setAlignment(Pos.CENTER);
        Text textBemVindo = new Text("Bem vindo ao jogo MIND GRAPHS! ");
        textBemVindo.setFill(Color.LIGHTSALMON);
        textBemVindo.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        boxNomeJogador.getChildren().add(textBemVindo);

//text field com o nome do jogador
        TextField textNomeJogador = new TextField();
        textNomeJogador.setPromptText("Introduza o seu nome");
        textNomeJogador.setAlignment(Pos.CENTER);
        textNomeJogador.setFocusTraversable(false);
        textNomeJogador.getText();
        textNomeJogador.setMaxWidth(210);

//Botao para criar o mini jogo
        Button btnCriarJogo = new Button("OK");
        btnCriarJogo.setDisable(true);
        validaIntroducaoDeNome(textNomeJogador, btnCriarJogo);
        btnCriarJogo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                jogador = new Jogador(textNomeJogador.getText());
                primaryStage.setScene(menuPrincipal(primaryStage, jogador));
            }
        });

//vamos adicionar as coisas á hbox
        boxNomeJogador.getChildren().addAll(textNomeJogador, btnCriarJogo);
        boxNomeJogador.setSpacing(10);

//vamos adicionar a hbox ao border pane
        root.setCenter(boxNomeJogador);

        //CSS
        root.getStylesheets().addAll(this.getClass().getResource("/javafxgraphs/ui/resources/style.css").toExternalForm());
        painel="pane";
        root.setId(painel);

        //propriedades da janela
        //primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setTitle("Mind Graph!");
        primaryStage.setScene(intro);
        primaryStage.centerOnScreen();
        primaryStage.setResizable(false);
        primaryStage.show();
    }

   /**
    * 
    * @param primaryStage
    * @param jogador
    * @return 
    */
    public static Scene menuPrincipal(Stage primaryStage, Jogador jogador) {

        BorderPane rootMenu = new BorderPane();
        Scene janelaMenu = new Scene(rootMenu, 1000, 600);

        /**
         * Titulo
         */
        Text textoTitulo = new Text();
        //textoTitulo.setX(100.0f);
        // textoTitulo.setY(20.0f);
        textoTitulo.setCache(true);
        textoTitulo.setText("Mind Graph");
        textoTitulo.setFill(Color.GREEN);
        textoTitulo.setFont(Font.font(null, FontWeight.BOLD, 90));
        textoTitulo.setTextAlignment(TextAlignment.CENTER);

        Reflection r = new Reflection();
        r.setFraction(0.7f);

        textoTitulo.setEffect(r);
        textoTitulo.setTranslateY(20);

        //BOTÕES MENU
        int btnSize = 150;

        Button btnTimeTrial = new Button("Time Trial");
        btnTimeTrial.setMaxWidth(btnSize);
        btnTimeTrial.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Scene scene = TimeTrial.menuTimeTrial(primaryStage, jogador);
                primaryStage.setScene(scene);
                primaryStage.show();
            }
        });

        Button btnArcade = new Button("Arcade");
        btnArcade.setMaxWidth(btnSize);
        btnArcade.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Scene scene = Arcade.menuArcade(primaryStage, jogador);
                primaryStage.setScene(scene);
                primaryStage.show();
            }
        });

        Button btnConfiguracao = new Button("Temas");
        btnConfiguracao.setMaxWidth(btnSize);
        btnConfiguracao.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Scene scene = Temas.menuTemas(primaryStage, jogador);
                primaryStage.setScene(scene);
                primaryStage.show();
            }
        });

        Button btnSair = new Button("Sair");
        btnSair.setMaxWidth(250);
        btnSair.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("Sair!");
                System.exit(0);
            }
        });

        //VBOX DE TITULO+BOTOES DO MENU
        VBox boxBotoes = new VBox(textoTitulo, btnTimeTrial, btnArcade, btnConfiguracao, btnSair);
        boxBotoes.setSpacing(10);
        boxBotoes.setAlignment(Pos.CENTER);
        rootMenu.setCenter(boxBotoes);

        //para apresentar o nome do jogador
        Text nomeJogador = new Text(jogador.getNome());

        nomeJogador.setFill(Color.BLACK);
        nomeJogador.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        rootMenu.setTop(nomeJogador);

        //CSS
        rootMenu.getStylesheets().addAll(AppMindGraphsFX.class.getResource("/javafxgraphs/ui/resources/style.css").toExternalForm());
        rootMenu.setId(painel);

        // primaryStage.setScene(janelaMenu);
        return janelaMenu;
    }





    
    

//VALIDA SE FOI INTRODUZIDO UM NOME ANTES DE ATIVAR O BOTÃO DE OK
    private void validaIntroducaoDeNome(TextField textNomeJogador, Button btnCriarJogo) {
        textNomeJogador.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                //System.out.println(t+"====="+t1);
                if (t1.equals("")) {
                    btnCriarJogo.setDisable(true);
                } else {
                    btnCriarJogo.setDisable(false);
                }
            }
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
