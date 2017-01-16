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
 * classe MAIN
 * @author Artur e Sergio
 */
public class AppMindGraphsFX extends Application{

    
    Jogador jogador = null;    
    static String painel = "";
    
    /**
     * Metodo que cria uma Stage que será a stage principal. Todas as cenas são montadas nesta stage.
     * Para começar é pedido o nome do Jogador.
     * @param primaryStage stage inicial
     */
    @Override
    public void start(Stage primaryStage) {

        BorderPane root = new BorderPane();
        Scene intro = new Scene(root, 1000, 600);

        //horizontal box inicial
        VBox boxNomeJogador = new VBox();
        boxNomeJogador.setAlignment(Pos.CENTER);
        Text textBemVindo = new Text("Bem vindo ao jogo MIND GRAPHS! ");
        textBemVindo.setFill(Color.GREEN);
        textBemVindo.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        Reflection r = new Reflection();
        r.setFraction(0.7f);
        textBemVindo.setEffect(r);
        
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
        btnCriarJogo.setMaxWidth(150);
        btnCriarJogo.setDisable(true);
        verificaTextField(textNomeJogador, btnCriarJogo);
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
        primaryStage.setTitle("Mind Graphs");
        primaryStage.setScene(intro);
        primaryStage.centerOnScreen();
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * MENU PRINCIPAL
     * Recebe a stage principal e o jogador criado anteriormente
     * @param primaryStage stage inicial
     * @param jogador jogador
     * @return Este metodo retorna a cena do menu principal. 
     * É uma cena com 4 botoes(TT, Arcade, temas, sair)
     */
    public static Scene menuPrincipal(Stage primaryStage, Jogador jogador) {

        BorderPane rootMenu = new BorderPane();
        Scene janelaMenu = new Scene(rootMenu, 1000, 600);

        //titulo
        Text textoTitulo = new Text();
        textoTitulo.setCache(true);
        textoTitulo.setText("Mind Graphs");
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
                System.exit(0);
            }
        });

        //VBOX DE TITULO+BOTOES DO MENU
        VBox boxBotoes = new VBox(textoTitulo, btnTimeTrial, btnArcade, btnConfiguracao, btnSair);
        boxBotoes.setSpacing(10);
        boxBotoes.setAlignment(Pos.CENTER);
        rootMenu.setCenter(boxBotoes);

        //para apresentar o nome do jogador
        Text nomeJogador = new Text("Jogador "+jogador.getNome());

        nomeJogador.setFill(Color.BLACK);
        nomeJogador.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        rootMenu.setTop(nomeJogador);

        //CSS
        rootMenu.getStylesheets().addAll(AppMindGraphsFX.class.getResource("/javafxgraphs/ui/resources/style.css").toExternalForm());
        rootMenu.setId(painel);

        return janelaMenu;
    }

    /**
     * Recebe um txtField e um botao. Desbloqueia o botao se o txt for diferente de vazio.
     * @param campoTexto campo de texto a ser verificado
     * @param btnOK botao a ser desbloqueado
     */
    public static void verificaTextField(TextField campoTexto, Button btnOK) {
        campoTexto.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                if (t1.equals("")) {
                    btnOK.setDisable(true);
                } else {
                    btnOK.setDisable(false);
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
