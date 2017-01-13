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
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafxgraphs.modelo.Jogador;
import javafxgraphs.modelo.Ligacao;
import javafxgraphs.modelo.Local;
import javafxgraphs.modelo.MiniJogo;
import javafxgraphs.modelo.TipoJogo;
import javafxgraphs.tad.iVertex;
import javafxgraphs.ui.GraphDraw;

/**
 *
 * @author Artur e Sergio
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
        btnCriarJogo.setDisable(true);
        validaIntroducaoDeNome(textNomeJogador, btnCriarJogo);
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

        /**
         * Titulo
         */
        Text textoTitulo = new Text();
        //textoTitulo.setX(100.0f);
        // textoTitulo.setY(20.0f);
        textoTitulo.setCache(true);
        textoTitulo.setText("Mind Graph");
        textoTitulo.setFill(Color.GREEN);
        textoTitulo.setFont(Font.font(null, FontWeight.BOLD, 70));
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

        Button btnConfiguracao = new Button("Temas");
        btnConfiguracao.setMaxWidth(btnSize);
        btnConfiguracao.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                primaryStage.setScene(menuTemas(primaryStage));
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
        rootMenu.getStylesheets().addAll(this.getClass().getResource("/javafxgraphs/ui/resources/style.css").toExternalForm());
        rootMenu.setId("pane");

        // primaryStage.setScene(janelaMenu);
        return janelaMenu;
    }

    /**
     * MENU TEMAS
     *
     * @param primaryStage
     * @return
     */
    public Scene menuTemas(Stage primaryStage) {

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

    /**
     * MENU TT
     *
     * @param primaryStage
     * @return
     */
    public Scene menuTimeTrial(Stage primaryStage) {

        BorderPane rootTimeTrial = new BorderPane();
        Scene janelaTimeTrial = new Scene(rootTimeTrial, 1000, 600);
        System.out.println("menu TIME TRIAL");

        Text textEscolherOpcao = new Text("Time Trial");
        textEscolherOpcao.setFill(Color.GREEN);
        textEscolherOpcao.setFont(Font.font("Verdana", FontWeight.BOLD, 40));

        int btnSize = 150;
        Button botaoNovoJogo = new Button("Start");
        botaoNovoJogo.setMaxWidth(btnSize);
        botaoNovoJogo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                primaryStage.setScene(criarJogoTimeTrial(primaryStage));
            }
        });

        Button btnRecords = new Button("Recordes");
        btnRecords.setMaxWidth(btnSize);
        btnRecords.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                primaryStage.setScene(recordesTimeTrial(primaryStage));
            }
        });

        Button botaoRegras = new Button("Regras");
        botaoRegras.setMaxWidth(btnSize);
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
        VBox boxBotoesTrial = new VBox(textEscolherOpcao, botaoNovoJogo, btnRecords, botaoRegras, botaoVoltar);
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

    /**
     * MENU ARCADE
     *
     * @param primaryStage
     * @return
     */
    public Scene menuArcade(Stage primaryStage) {

        BorderPane rootArcade = new BorderPane();
        Scene janelaArcade = new Scene(rootArcade, 1000, 600);
        System.out.println("menu ARCADE");

        Text textEscolherOpcao = new Text("Arcade");
        textEscolherOpcao.setFill(Color.GREEN);
        textEscolherOpcao.setFont(Font.font("Verdana", FontWeight.BOLD, 40));

        int btnSize = 150;
        Button botaoNovoJogo = new Button("Start");
        botaoNovoJogo.setMaxWidth(btnSize);
        botaoNovoJogo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                primaryStage.setScene(menuPackArcade(primaryStage));
            }
        });

        Button btnRecords = new Button("Recordes");
        btnRecords.setMaxWidth(btnSize);
        btnRecords.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                primaryStage.setScene(recordesArcade(primaryStage));
            }
        });

        Button botaoRegras = new Button("Regras");
        botaoRegras.setMaxWidth(btnSize);
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
        VBox boxBotoesArcade = new VBox(textEscolherOpcao, botaoNovoJogo, btnRecords, botaoRegras, botaoVoltar);
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

    public Scene menuRegrasTT() {

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

    public Scene menuRegrasArcade() {

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

    /**
     * RECORDES TT
     *
     * @param primaryStage
     * @return
     */
    public Scene recordesTimeTrial(Stage primaryStage) {

        BorderPane rootRecordes = new BorderPane();
        Scene janelaRecordes = new Scene(rootRecordes, 1000, 600);
        System.out.println("menu RECORDES TT");

        
        //para apresentar o nome do jogador
        Text nomeJogador = new Text(jogador.getNome());
        nomeJogador.setFill(Color.BLACK);
        nomeJogador.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        rootRecordes.setTop(nomeJogador);
        
        VBox vb = new VBox();
        vb.setSpacing(80);
        vb.setAlignment(Pos.CENTER);

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

        //botao Voltar
        Button btn1 = new Button();
        btn1.setText("Voltar");
        btn1.setMaxWidth(250);
        btn1.setOnMouseClicked(
                new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                primaryStage.setScene(menuTimeTrial(primaryStage));
            }
        });

        //animacao textoNomes
        TranslateTransition translateTransition = TranslateTransitionBuilder.create().node(textoRecordes).fromY(500).toY(-500).duration(new Duration(8000)).interpolator(Interpolator.LINEAR).cycleCount(Timeline.INDEFINITE).build();

        rootRecordes.getStylesheets().addAll(this.getClass().getResource("/javafxgraphs/ui/resources/style.css").toExternalForm());
        rootRecordes.setId("pane");

        vb.getChildren().addAll(textoRecordes, btn1);
        rootRecordes.setCenter(vb);

        translateTransition.play();

        return janelaRecordes;

    }

    /**
     * RECORDES ARCADE
     *
     * @param primaryStage
     * @return
     */
    public Scene recordesArcade(Stage primaryStage) {

        
        BorderPane rootRecordes = new BorderPane();
        Scene janelaRecordes = new Scene(rootRecordes, 1000, 600);
        System.out.println("menu RECORDES Arcade");

   
        //para apresentar o nome do jogador
        Text nomeJogador = new Text(jogador.getNome());
        nomeJogador.setFill(Color.BLACK);
        nomeJogador.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        rootRecordes.setTop(nomeJogador);
        
        VBox vb = new VBox();
        vb.setSpacing(80);
        vb.setAlignment(Pos.CENTER);

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

        
        //botao Voltar
        Button btn1 = new Button();
        btn1.setText("Voltar");
        btn1.setMaxWidth(250);
        btn1.setOnMouseClicked(
                new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                primaryStage.setScene(menuArcade(primaryStage));
            }
        });

        //animacao textoNomes
        TranslateTransition translateTransition = TranslateTransitionBuilder.create().node(textoRecordes).fromY(500).toY(-500).duration(new Duration(8000)).interpolator(Interpolator.LINEAR).cycleCount(Timeline.INDEFINITE).build();

        rootRecordes.getStylesheets().addAll(this.getClass().getResource("/javafxgraphs/ui/resources/style.css").toExternalForm());
        rootRecordes.setId("pane");

        vb.getChildren().addAll(textoRecordes, btn1);
        rootRecordes.setCenter(vb);

        translateTransition.play();

        return janelaRecordes;

    }

    //este sim realmente vai desenhar o grafo e criar o jogo trial
    public Scene criarJogoTimeTrial(Stage primaryStage) {

        
        int NIVEL_MAX = 20;
        
        MiniJogo jogoTT = new MiniJogo(TipoJogo.TIMETRIAL, jogador, 5); 
        
        
        
                
        BorderPane rootJogoTT = new BorderPane();
        Scene janelaJogoTT = new Scene(rootJogoTT, 1000, 600);
        System.out.println("menu JOGO TT");

        //para apresentar o nome do jogador
        Text nomeJogador = new Text(jogoTT.getJogador().getNome());
        nomeJogador.setFill(Color.BLACK);
        nomeJogador.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

        //para apresentar o nivel
        Text nivelJogo = new Text(jogoTT.getNivel() + "/20");
        nivelJogo.setFill(Color.YELLOW);
        nivelJogo.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

        //Para apresentar o timer
        Text timer = new Text(jogoTT.getSegundos() + "");
        timer.setFill(Color.GREEN);
        timer.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

        //HBOX CABECALHO
        HBox boxCabecalho = new HBox();
        boxCabecalho.setSpacing(350);
        boxCabecalho.setAlignment(Pos.CENTER);
        boxCabecalho.getChildren().addAll(nomeJogador, nivelJogo, timer);
        rootJogoTT.setTop(boxCabecalho);


        
        GraphDraw<Local, Ligacao> drawMiniJogo = new GraphDraw(jogoTT.getGrafoAdaptee());
        rootJogoTT.setCenter(drawMiniJogo);

        //para apresentar a dificuldade
        Text dificuldadeJogo = new Text(jogoTT.getDificuldade()+ "");
        dificuldadeJogo.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

        if (dificuldadeJogo.equals("FACIL")) {
            dificuldadeJogo.setFill(Color.GREEN);
        } else if (dificuldadeJogo.equals("MEDIO")) {
            dificuldadeJogo.setFill(Color.YELLOW);
        } else {
            dificuldadeJogo.setFill(Color.RED);
        }

        rootJogoTT.setRight(dificuldadeJogo);

        //para apresentar o tipo de Solucao
        Text tipoSolucao = new Text(jogoTT.getTipoSolucao()+"");
        tipoSolucao.setFill(Color.BLACK);
        tipoSolucao.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        
        
        Local arrayLocaisTemp[] = jogoTT.randomVertices2();
        
        String vIN = arrayLocaisTemp[0].getId();
        String vOUT = arrayLocaisTemp[1].getId();
        
        iVertex<Local> verticeIN = jogoTT.findVertice(vIN);
        iVertex<Local> verticeOUT = jogoTT.findVertice(vOUT);
        
       // verticeIN.element().setId(vIN+"");
       // verticeOUT.element().setId(vOUT+"");
       
       

        //para apresentar o vertice de entrada (Fazer um random para devolver um vertice)
        Text textOrigem = new Text(vIN+"");
        textOrigem.setFill(Color.YELLOW);
        textOrigem.setFont(Font.font("Verdana", FontWeight.BOLD, 30));

        //para apresentar o vertice de saida (Faer um random para devolver um vertice diferente do outro)
        Text textDestino = new Text(vOUT+"");
        textDestino.setFill(Color.YELLOW);
        textDestino.setFont(Font.font("Verdana", FontWeight.BOLD, 30));

        //text field com a solucao
        TextField textSolucao = new TextField();
        textSolucao.setPromptText("Introduza a solucao");
        textSolucao.setAlignment(Pos.CENTER);
        textSolucao.setFocusTraversable(false);
        textSolucao.getText();
        textSolucao.setMaxWidth(210);

        
        
        //devolve uma string com o caminho consoante a estrategia
        System.out.println(jogoTT.getGrafoAdaptee().calcularSolucao(verticeIN, verticeOUT, jogoTT.getEstrategiaSolucao()));
        System.out.println(jogoTT.getGrafoAdaptee().dijkstra(verticeIN, verticeOUT, jogoTT.getEstrategiaSolucao()));
        
        
        //depois temos de calcular o caminho através desta string
        
//Botao para criar o mini jogo
        Button btnCalcularSolucao = new Button("Calcular");
        btnCalcularSolucao.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("calcular solucao e ir para o menu POPUP NEXTGAME se tiver certa. "
                        + "Se tiver errado ir para o menu POPUP Game over");
            }
        });
        
         //botao Voltar
        Button btnVoltar = new Button();
        btnVoltar.setText("Voltar");
        btnVoltar.setMaxWidth(150);
        btnVoltar.setOnMouseClicked(
                new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                primaryStage.setScene(menuTimeTrial(primaryStage));
            }
        });

        //HBOX RODAPE
        HBox boxRodape = new HBox();
        boxRodape.setSpacing(20);
        boxRodape.setAlignment(Pos.CENTER);
        boxRodape.getChildren().addAll(tipoSolucao, textOrigem, textDestino, textSolucao, btnCalcularSolucao, btnVoltar);
        rootJogoTT.setBottom(boxRodape);

        //CSS
        rootJogoTT.getStylesheets().addAll(this.getClass().getResource("/javafxgraphs/ui/resources/style.css").toExternalForm());
        rootJogoTT.setId("pane");

        return janelaJogoTT;
    }
    
    
    public Scene criarJogoArcade(Stage primaryStage, int nivel){
        
         int NIVEL_MAX = 20;
        
        MiniJogo jogoArcade = new MiniJogo(TipoJogo.ARCADE, jogador, nivel); 
        
          
        BorderPane rootJogoArcade = new BorderPane();
        Scene janelaJogoArcade = new Scene(rootJogoArcade, 1000, 600);
        System.out.println("menu JOGO ARCADE");

        //para apresentar o nome do jogador
        Text nomeJogador = new Text(jogoArcade.getJogador().getNome());
        nomeJogador.setFill(Color.BLACK);
        nomeJogador.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

        //para apresentar o nivel
        Text nivelJogo = new Text(jogoArcade.getNivel() + "/20");
        nivelJogo.setFill(Color.YELLOW);
        nivelJogo.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        
        
         //para apresentar a dificuldade
        Text dificuldadeJogo = new Text(jogoArcade.getDificuldade()+ "");
        dificuldadeJogo.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

        if (dificuldadeJogo.equals("FACIL")) {
            dificuldadeJogo.setFill(Color.GREEN);
        } else if (dificuldadeJogo.equals("MEDIO")) {
            dificuldadeJogo.setFill(Color.YELLOW);
        } else {
            dificuldadeJogo.setFill(Color.RED);
        }
        
        
        //HBOX CABECALHO
        HBox boxCabecalho = new HBox();
        boxCabecalho.setSpacing(350);
        boxCabecalho.setAlignment(Pos.CENTER);
        boxCabecalho.getChildren().addAll(nomeJogador, nivelJogo, dificuldadeJogo);
        rootJogoArcade.setTop(boxCabecalho);


        
        GraphDraw<Local, Ligacao> drawMiniJogo = new GraphDraw(jogoArcade.getGrafoAdaptee());
        rootJogoArcade.setCenter(drawMiniJogo);
        
        
        
        
        //para apresentar o tipo de Solucao
        Text tipoSolucao = new Text(jogoArcade.getTipoSolucao()+"");
        tipoSolucao.setFill(Color.BLACK);
        tipoSolucao.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        
        
        Local arrayLocaisTemp[] = jogoArcade.randomVertices2();
        
        String vIN = arrayLocaisTemp[0].getId();
        String vOUT = arrayLocaisTemp[1].getId();

        //para apresentar o vertice de entrada (Fazer um random para devolver um vertice)
        Text vOrigem = new Text(vIN+"");
        vOrigem.setFill(Color.YELLOW);
        vOrigem.setFont(Font.font("Verdana", FontWeight.BOLD, 30));

        //para apresentar o vertice de saida (Faer um random para devolver um vertice diferente do outro)
        Text vDestino = new Text(vOUT+"");
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
                System.out.println("calcular solucao e ir para o menu POPUP CORRETO+botao voltar menuARcade se tiver certa. "
                        + "Se tiver errado ir para o menu POPUP Game over+botao voltar menuArcade");
            }
        });
        
         //botao Voltar
        Button btnVoltar = new Button();
        btnVoltar.setText("Voltar");
        btnVoltar.setMaxWidth(150);
        btnVoltar.setOnMouseClicked(
                new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                primaryStage.setScene(menuPackArcade(primaryStage));
            }
        });

        //HBOX RODAPE
        HBox boxRodape = new HBox();
        boxRodape.setSpacing(20);
        boxRodape.setAlignment(Pos.CENTER);
        boxRodape.getChildren().addAll(tipoSolucao, vOrigem, vDestino, textSolucao, btnCalcularSolucao, btnVoltar);
        rootJogoArcade.setBottom(boxRodape);

        //CSS
        rootJogoArcade.getStylesheets().addAll(this.getClass().getResource("/javafxgraphs/ui/resources/style.css").toExternalForm());
        rootJogoArcade.setId("pane");
        
    
        return janelaJogoArcade;
    }

    /**
     * Cria uma GRID com varios jogos pre definidos
     *
     * @param primaryStage
     * @return
     */
    
    public Scene menuPackArcade(Stage primaryStage) {

        
        BorderPane rootArcade = new BorderPane();
        Scene janelaArcade = new Scene(rootArcade, 1000, 600);
        System.out.println("Pack Arcade");

        
        //para apresentar o nome do jogador
        Text nomeJogador = new Text(jogador.getNome());
        nomeJogador.setFill(Color.BLACK);
        nomeJogador.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

        
        //para apresentar o nivel
        Text nivelEstrelas = new Text("6/48");
        nivelEstrelas.setFill(Color.YELLOW);
        nivelEstrelas.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

        //Para apresentar o timer
        Text timer = new Text("");
        timer.setFill(Color.GREEN);
        timer.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

        //HBOX CABECALHO
        HBox boxCabecalho = new HBox();
        boxCabecalho.setSpacing(350);
        boxCabecalho.setAlignment(Pos.CENTER);
        boxCabecalho.getChildren().addAll(nomeJogador, nivelEstrelas, timer);
        rootArcade.setTop(boxCabecalho);

        Text textEscolherOpcao = new Text("Pack 1 - Arcade");
        textEscolherOpcao.setFill(Color.GREEN);
        textEscolherOpcao.setFont(Font.font("Verdana", FontWeight.BOLD, 40));




        
        
        TilePane tilePane = new TilePane(Orientation.HORIZONTAL, 10, 10);
        tilePane.setMaxWidth(500);

        int NIVEL_MAX = 21;
        Button[] arrayBotoes = new Button[NIVEL_MAX];

        for (int i = 1; i < NIVEL_MAX; i++) {
            arrayBotoes[i] = new Button(Integer.toString(i));
            arrayBotoes[i].setPrefSize(80, 80);
            int f = i;         
            arrayBotoes[i].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
               
                primaryStage.setScene(criarJogoArcade(primaryStage, f));
            }
        });
            tilePane.getChildren().add(arrayBotoes[i]);
        }

        tilePane.setAlignment(Pos.CENTER);

        //botao Voltar
        Button btn1 = new Button();

        btn1.setAlignment(Pos.CENTER);
        btn1.setText("Voltar");
        btn1.setMaxWidth(250);
        btn1.setOnMouseClicked(
                new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event
            ) {
                primaryStage.setScene(menuArcade(primaryStage));
            }
        }
        );

        //VBOX DE BOTOES DE ESCOLHA DE TEMA
        VBox vBoxCentro = new VBox(textEscolherOpcao, tilePane, btn1);
        vBoxCentro.setSpacing(10);
        vBoxCentro.setAlignment(Pos.CENTER);
        rootArcade.setCenter(vBoxCentro);


        //CSS
        rootArcade.getStylesheets().addAll(this.getClass().getResource("/javafxgraphs/ui/resources/style.css").toExternalForm());
        rootArcade.setId("pane");

        return janelaArcade;

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
