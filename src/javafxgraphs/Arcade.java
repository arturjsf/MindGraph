/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxgraphs;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
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
import javafxgraphs.modelo.TipoJogo;
import javafxgraphs.ui.GraphDraw;

/**
 *
 * @author Artur Ferreira
 */
public class Arcade{
    
    /**
     * MENU ARCADE
     *
     * @param primaryStage
     * @return
     */
    public static Scene menuArcade(Stage primaryStage, Jogador jogador) {
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
                menuPackArcade(primaryStage, jogador);
            }
        });
        Button btnRecords = new Button("Recordes");
        btnRecords.setMaxWidth(btnSize);
        btnRecords.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Scene scene = Recordes.recordesArcade(primaryStage, jogador);
                primaryStage.setScene(scene);
                primaryStage.show();
            }
        });
        Button botaoRegras = new Button("Regras");
        botaoRegras.setMaxWidth(btnSize);
        botaoRegras.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Scene scene = Regras.menuRegrasArcade(jogador);
                primaryStage.setScene(scene);
                primaryStage.show();
            }
        });
        Button botaoVoltar = new Button("Voltar");
        botaoVoltar.setMaxWidth(250);
        botaoVoltar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Scene scene = AppMindGraphsFX.menuPrincipal(primaryStage, jogador);
                primaryStage.setScene(scene);
                primaryStage.show();
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

    /**
     * Cria uma GRID com varios jogos pre definidos
     *
     * @param primaryStage
     * @return
     */
    public static Scene menuPackArcade(Stage primaryStage, Jogador jogador) {
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
                    criarJogoArcade(primaryStage, f, jogador);
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
        btn1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                menuArcade(primaryStage, jogador);
            }
        });
        //VBOX DE BOTOES DE ESCOLHA DE TEMA
        VBox vBoxCentro = new VBox(textEscolherOpcao, tilePane, btn1);
        vBoxCentro.setSpacing(10);
        vBoxCentro.setAlignment(Pos.CENTER);
        rootArcade.setCenter(vBoxCentro);
        //CSS
        rootArcade.getStylesheets().addAll(appMindGraphsFX.getClass().getResource("/javafxgraphs/ui/resources/style.css").toExternalForm());
        rootArcade.setId("pane");
        return janelaArcade;
    }
    
    
    
    
    
    
    public static Scene criarJogoArcade(Stage primaryStage, int nivel, Jogador jogador) {
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
        Text dificuldadeJogo = new Text(jogoArcade.getDificuldade() + "");
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
        Text tipoSolucao = new Text(jogoArcade.getTipoSolucao() + "");
        tipoSolucao.setFill(Color.BLACK);
        tipoSolucao.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        Local[] arrayLocaisTemp = jogoArcade.randomVertices2();
        String vIN = arrayLocaisTemp[0].getId();
        String vOUT = arrayLocaisTemp[1].getId();
        //para apresentar o vertice de entrada (Fazer um random para devolver um vertice)
        Text vOrigem = new Text(vIN + "");
        vOrigem.setFill(Color.YELLOW);
        vOrigem.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        //para apresentar o vertice de saida (Faer um random para devolver um vertice diferente do outro)
        Text vDestino = new Text(vOUT + "");
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
                System.out.println("calcular solucao e ir para o menu POPUP CORRETO+botao voltar menuARcade se tiver certa. " + "Se tiver errado ir para o menu POPUP Game over+botao voltar menuArcade");
            }
        });
        //botao Voltar
        Button btnVoltar = new Button();
        btnVoltar.setText("Voltar");
        btnVoltar.setMaxWidth(150);
        btnVoltar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                menuPackArcade(primaryStage, jogador);
            }
        });
        //HBOX RODAPE
        HBox boxRodape = new HBox();
        boxRodape.setSpacing(20);
        boxRodape.setAlignment(Pos.CENTER);
        boxRodape.getChildren().addAll(tipoSolucao, vOrigem, vDestino, textSolucao, btnCalcularSolucao, btnVoltar);
        rootJogoArcade.setBottom(boxRodape);
        //CSS
        rootJogoArcade.getStylesheets().addAll(appMindGraphsFX.getClass().getResource("/javafxgraphs/ui/resources/style.css").toExternalForm());
        rootJogoArcade.setId("pane");
        return janelaJogoArcade;
    }

    
}
