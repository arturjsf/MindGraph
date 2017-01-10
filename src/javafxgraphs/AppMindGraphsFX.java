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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafxgraphs.modelo.Jogador;
import javafxgraphs.modelo.Ligacao;
import javafxgraphs.modelo.Local;
import javafxgraphs.modelo.MiniJogo;
import javafxgraphs.ui.GraphDraw;

/**
 *
 * @author brunomnsilva
 */
public class AppMindGraphsFX extends Application {

    //preciso de criar o jogador aki
    Jogador jogador = null;

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
        btnCriarJogo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                jogador = new Jogador(textNomeJogador.getText());
                primaryStage.setScene(menuPrincipal(primaryStage));
            }
        });

//vamos adicionar as coisas á hbox
        boxNomeJogador.getChildren().addAll(textNomeJogador, btnCriarJogo);
        boxNomeJogador.setSpacing(10);

//vamos adicionar a hbox ao border pane
        root.setCenter(boxNomeJogador);

        //CSS
        root.getStylesheets().addAll(this.getClass().getResource("/javafxgraphs/ui/resources/style.css").toExternalForm());
        root.setId("pane");

        //propriedades da janela
        //primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setTitle("Mind Graph!");
        primaryStage.setScene(intro);
        primaryStage.centerOnScreen();
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * MENU PRINCIPAL
     *
     * @return
     */
    public Scene menuPrincipal(Stage primaryStage) {

        BorderPane rootMenu = new BorderPane();
        Scene janelaMenu = new Scene(rootMenu, 1000, 600);

        //BOTÕES MENU
        int btnSize = 150;

        Button btnTimeTrial = new Button("Time Trial");
        btnTimeTrial.setMaxWidth(btnSize);
        btnTimeTrial.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                primaryStage.setScene(menuTimeTrial(primaryStage));
            }
        });

        Button btnArcade = new Button("Arcade");
        btnArcade.setMaxWidth(btnSize);
        btnArcade.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                primaryStage.setScene(menuArcade(primaryStage));
            }
        });

     

        Button btnConfiguracao = new Button("Escolha de tema");
        btnConfiguracao.setMaxWidth(btnSize);
        btnConfiguracao.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                primaryStage.setScene(menuTemas(primaryStage));
            }
        });

        Button btnSair = new Button("Sair");
        btnSair.setMaxWidth(btnSize);
        btnSair.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("Sair!");
                System.exit(0);
            }
        });

        //VBOX DE BOTOES DO MENU
        VBox boxBotoes = new VBox(btnTimeTrial, btnArcade, btnConfiguracao, btnSair);
        boxBotoes.setSpacing(10);
        boxBotoes.setAlignment(Pos.CENTER);
        rootMenu.setCenter(boxBotoes);

        //para apresentar o nome do jogador
        Text nomeJogador = new Text(jogador.getNome());

        nomeJogador.setFill(Color.BLACK);
        nomeJogador.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        rootMenu.setTop(nomeJogador);

        //CSS
        rootMenu.getStylesheets().addAll(this.getClass().getResource("/javafxgraphs/ui/resources/style.css").toExternalForm());
        rootMenu.setId("pane");

        // primaryStage.setScene(janelaMenu);
        return janelaMenu;
    }

//ESCOLHER TEMA
    public Scene menuTemas(Stage primaryStage) {

        BorderPane rootTemas = new BorderPane();
        Scene janelaTemas = new Scene(rootTemas, 1000, 600);
        System.out.println("menu TEMAS");

        int btnSize = 150;
        // BOTOES DE ESCOLHA DE TEMA
        Text textEscolherTema = new Text("Escolha o tema");
        textEscolherTema.setFill(Color.BLACK);
        textEscolherTema.setFont(Font.font("Verdana", FontWeight.BOLD, 16));

        Button btnSurf = new Button("Surf");
        btnSurf.setMaxWidth(btnSize);
        btnSurf.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                rootTemas.getStylesheets().addAll(this.getClass().getResource("/javafxgraphs/ui/resources/style.css").toExternalForm());
                rootTemas.setId("surfPane");
                primaryStage.setScene(menuPrincipal(primaryStage));
            }
        });

        Button btnCycling = new Button("Cycling");
        btnCycling.setMaxWidth(btnSize);
        btnCycling.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                rootTemas.getStylesheets().addAll(this.getClass().getResource("/javafxgraphs/ui/resources/style.css").toExternalForm());
                rootTemas.setId("cyclingPane");
                primaryStage.setScene(menuPrincipal(primaryStage));
            }
        });

        Button btnRugby = new Button("Rugby");
        btnRugby.setMaxWidth(btnSize);
        btnRugby.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                rootTemas.getStylesheets().addAll(this.getClass().getResource("/javafxgraphs/ui/resources/style.css").toExternalForm());
                rootTemas.setId("rugbyPane");
                primaryStage.setScene(menuPrincipal(primaryStage));
            }
        });

        Button btnFootball = new Button("Football");
        btnFootball.setMaxWidth(btnSize);
        btnFootball.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                rootTemas.getStylesheets().addAll(this.getClass().getResource("/javafxgraphs/ui/resources/style.css").toExternalForm());
                rootTemas.setId("footballPane");
                primaryStage.setScene(menuPrincipal(primaryStage));
            }
        });

        Button btnVoltar = new Button("Voltar");
        btnVoltar.setMaxWidth(250);
        btnVoltar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("Voltar menu");
                primaryStage.setScene(menuPrincipal(primaryStage));
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
        rootTemas.getStylesheets().addAll(this.getClass().getResource("/javafxgraphs/ui/resources/style.css").toExternalForm());
        rootTemas.setId("pane");

        // primaryStage.setScene(janelaMenu);
        return janelaTemas;
    }

    public Scene menuTimeTrial(Stage primaryStage) {

        BorderPane rootTimeTrial = new BorderPane();
        Scene janelaTimeTrial = new Scene(rootTimeTrial, 1000, 600);
        System.out.println("menu TIME TRIAL");

        Button botaoNovoJogo = new Button("Start");
        botaoNovoJogo.setMaxWidth(250);
        botaoNovoJogo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                primaryStage.setScene(criarJogoTimeTrial(primaryStage));
            }
        });

        Button btnRecords = new Button("Recordes TT");
        btnRecords.setMaxWidth(250);
        btnRecords.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                primaryStage.setScene(recordesTimeTrial());
            }
        });
        
        
        Button botaoRegras = new Button("Regras");
        botaoRegras.setMaxWidth(250);
        botaoRegras.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                primaryStage.setScene(menuRegrasTT());
            }
        });

        Button botaoVoltar = new Button("Voltar");
        botaoVoltar.setMaxWidth(250);
        botaoVoltar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                primaryStage.setScene(menuPrincipal(primaryStage));
            }
        });

        //VBOX DE BOTOES DE ESCOLHA DE TEMA
        VBox boxBotoesTrial = new VBox(botaoNovoJogo, btnRecords, botaoRegras, botaoVoltar);
        boxBotoesTrial.setSpacing(10);
        boxBotoesTrial.setAlignment(Pos.CENTER);
        rootTimeTrial.setCenter(boxBotoesTrial);

        //para apresentar o nome do jogador
        Text nomeJogador = new Text(jogador.getNome());
        nomeJogador.setFill(Color.BLACK);
        nomeJogador.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        rootTimeTrial.setTop(nomeJogador);

        //CSS
        rootTimeTrial.getStylesheets().addAll(this.getClass().getResource("/javafxgraphs/ui/resources/style.css").toExternalForm());
        rootTimeTrial.setId("pane");

        // primaryStage.setScene(janelaMenu);
        return janelaTimeTrial;
    }

    public Scene menuRegrasTT() {

        BorderPane rootRegras = new BorderPane();
        Scene janelaRegras = new Scene(rootRegras, 1000, 600);
        System.out.println("menu REGRAS TT");

        return janelaRegras;
    }
    
        public Scene menuRegrasArcade() {

        BorderPane rootRegras = new BorderPane();
        Scene janelaRegras = new Scene(rootRegras, 1000, 600);
        System.out.println("menu REGRAS Arcade");

        return janelaRegras;
    }

//CONSULTAR RECORDS 
    public Scene recordesTimeTrial() {

        BorderPane rootRecordes = new BorderPane();
        Scene janelaRecordes = new Scene(rootRecordes, 1000, 600);
        System.out.println("menu RECORDES TT");
        return janelaRecordes;

    }
    
       public Scene recordesArcade() {

        BorderPane rootRecordes = new BorderPane();
        Scene janelaRecordes = new Scene(rootRecordes, 1000, 600);
        System.out.println("menu RECORDES Arcade");
        return janelaRecordes;

    }

//MODO ARCADE
    public Scene menuArcade(Stage primaryStage) {

        BorderPane rootArcade = new BorderPane();
        Scene janelaArcade = new Scene(rootArcade, 1000, 600);
        System.out.println("menu ARCADE");
        
        
        Button botaoNovoJogo = new Button("Start");
        botaoNovoJogo.setMaxWidth(250);
        botaoNovoJogo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("criar uma grid com o pack de jogos");
            }
        });

        Button btnRecords = new Button("Recordes Arcade");
        btnRecords.setMaxWidth(250);
        btnRecords.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                primaryStage.setScene(recordesArcade());
            }
        });
        
        
        Button botaoRegras = new Button("Regras");
        botaoRegras.setMaxWidth(250);
        botaoRegras.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                primaryStage.setScene(menuRegrasArcade());
            }
        });

        Button botaoVoltar = new Button("Voltar");
        botaoVoltar.setMaxWidth(250);
        botaoVoltar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                primaryStage.setScene(menuPrincipal(primaryStage));
            }
        });

        //VBOX DE BOTOES DE ESCOLHA DE TEMA
        VBox boxBotoesArcade = new VBox(botaoNovoJogo, btnRecords, botaoRegras, botaoVoltar);
        boxBotoesArcade.setSpacing(10);
        boxBotoesArcade.setAlignment(Pos.CENTER);
        rootArcade.setCenter(boxBotoesArcade);

        
        //para apresentar o nome do jogador
        Text nomeJogador = new Text(jogador.getNome());
        nomeJogador.setFill(Color.BLACK);
        nomeJogador.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        rootArcade.setTop(nomeJogador);

        //CSS
        rootArcade.getStylesheets().addAll(this.getClass().getResource("/javafxgraphs/ui/resources/style.css").toExternalForm());
        rootArcade.setId("pane");

        
        
        return janelaArcade;

    }

    
    //este sim realmente vai desenhar o grafo e criar o jogo trial
    public Scene criarJogoTimeTrial(Stage primaryStage) {

        TimeTrial novoJogo = new TimeTrial(jogador);

        BorderPane rootJogoTT = new BorderPane();
        Scene janelaJogoTT = new Scene(rootJogoTT, 1000, 600);
        System.out.println("menu JOGO TT");

        //para apresentar o nome do jogador
        Text nomeJogador = new Text(jogador.getNome());
        nomeJogador.setFill(Color.BLACK);
        nomeJogador.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

        //para apresentar o nivel
        Text nivelJogo = new Text(novoJogo.getNivel() + "/20");
        nivelJogo.setFill(Color.YELLOW);
        nivelJogo.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

        //Para apresentar o timer
        Text timer = new Text(novoJogo.getSeconds()+"");
        timer.setFill(Color.GREEN);
        timer.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        
        
        //HBOX CABECALHO
        HBox boxCabecalho = new HBox();
        boxCabecalho.setSpacing(350);
        boxCabecalho.setAlignment(Pos.CENTER);
        boxCabecalho.getChildren().addAll(nomeJogador, nivelJogo, timer);
        rootJogoTT.setTop(boxCabecalho);

        //Para apresentar o grafo
        MiniJogo miniJogo = novoJogo.getMiniJogo();
        GraphDraw<Local, Ligacao> drawMiniJogo = new GraphDraw(miniJogo.getGrafoAdaptee());
        rootJogoTT.setCenter(drawMiniJogo);
        
        
        
        //para apresentar a dificuldade
        Text dificuldadeJogo = new Text(novoJogo.getMiniJogo().getNivel() + "");
        dificuldadeJogo.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

        if (dificuldadeJogo.equals("FACIL")) {
            dificuldadeJogo.setFill(Color.GREEN);
        } else if (dificuldadeJogo.equals("MEDIO")) {
            dificuldadeJogo.setFill(Color.YELLOW);
        } else {
            dificuldadeJogo.setFill(Color.RED);
        }

     //   rootJogoTT.setRight(dificuldadeJogo);

     
     
      //para apresentar o tipo de Solucao
        Text tipoSolucao = new Text(jogador.getNome());
        tipoSolucao.setFill(Color.BLACK);
        tipoSolucao.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
     
        
      //para apresentar o vertice de entrada (Fazer um random para devolver um vertice)
        Text vOrigem = new Text("IN");
        vOrigem.setFill(Color.YELLOW);
        vOrigem.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        
        
      //para apresentar o vertice de saida (Faer um random para devolver um vertice diferente do outro)
        Text vDestino = new Text("OUT");
        vDestino.setFill(Color.YELLOW);
        vDestino.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
     
        
        //text field com a solucao
        TextField textSolucao = new TextField();
        textSolucao.setPromptText("Introduza a solucao");
        textSolucao.setAlignment(Pos.CENTER);
        textSolucao.setFocusTraversable(false);
        textSolucao.getText();
        textSolucao.setMaxWidth(210);

//Botao para criar o mini jogo
        Button btnCalcularSolucao = new Button("Calcular");
        btnCalcularSolucao.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("calcular solucao e ir para o menu POPUP NEXTGAME se tiver certa. "
                        + "Se tiver errado ir para o menuPOP UP Game over");
            }
        });
     
     //HBOX RODAPE
        HBox boxRodape = new HBox();
        boxRodape.setSpacing(20);
        boxRodape.setAlignment(Pos.CENTER);
        boxRodape.getChildren().addAll(tipoSolucao, vOrigem, vDestino, textSolucao, btnCalcularSolucao);
        rootJogoTT.setBottom(boxRodape);

        //CSS
        rootJogoTT.getStylesheets().addAll(this.getClass().getResource("/javafxgraphs/ui/resources/style.css").toExternalForm());
        rootJogoTT.setId("pane");

        return janelaJogoTT;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
