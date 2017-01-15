/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxgraphs;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import static javafxgraphs.AppMindGraphsFX.painel;
import javafxgraphs.modelo.Jogador;
import javafxgraphs.modelo.Ligacao;
import javafxgraphs.modelo.Local;
import javafxgraphs.modelo.MiniJogo;
import javafxgraphs.modelo.TipoJogo;
import javafxgraphs.tad.iVertex;
import javafxgraphs.ui.GraphDraw;

/**
 *
 * @author Artur Ferreira
 */
public class TimeTrial {

    static int NIVEL_MAX = 20;

    /**
     * MENU TT
     *
     * @param primaryStage
     * @param jogador
     * @return
     */
    public static Scene menuTimeTrial(Stage primaryStage, Jogador jogador) {

        BorderPane rootTimeTrial = new BorderPane();
        Scene janelaTimeTrial = new Scene(rootTimeTrial, 1000, 600);
        System.out.println("menu TIME TRIAL");

        Text textEscolherOpcao = new Text("Time Trial");
        textEscolherOpcao.setFill(Color.GREEN);
        textEscolherOpcao.setFont(Font.font("Verdana", FontWeight.BOLD, 60));

        int btnSize = 150;

        Button botaoNovoJogo = new Button("Start");
        botaoNovoJogo.setMaxWidth(btnSize);
        botaoNovoJogo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                primaryStage.setScene(criarJogoTimeTrial(primaryStage, 1, jogador));
            }
        });

        Button btnRecords = new Button("Recordes");
        btnRecords.setMaxWidth(btnSize);
        btnRecords.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Scene scene = Recordes.recordesTimeTrial(primaryStage, jogador);
                primaryStage.setScene(scene);
                primaryStage.show();
            }
        });
        Button botaoRegras = new Button("Regras");
        botaoRegras.setMaxWidth(btnSize);
        botaoRegras.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Scene scene = Regras.menuRegrasTT(primaryStage, jogador);
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
        rootTimeTrial.getStylesheets().addAll(AppMindGraphsFX.class.getResource("/javafxgraphs/ui/resources/style.css").toExternalForm());
        rootTimeTrial.setId(painel);

        return janelaTimeTrial;
    }

    /**
     * Desenha a janela Jogo TT
     *
     * @param primaryStage
     * @param nivel
     * @param jogador
     * @return
     */
    public static Scene criarJogoTimeTrial(Stage primaryStage, int nivel, Jogador jogador) {

        MiniJogo jogoTT = new MiniJogo(TipoJogo.TIMETRIAL, jogador, nivel);

        Integer seconds = jogoTT.getSegundos();
        count(seconds);

        BorderPane rootJogoTT = new BorderPane();
        Scene janelaJogoTT = new Scene(rootJogoTT, 1000, 600);
        System.out.println("menu JOGO TT: " + nivel);

        //para apresentar o nome do jogador
        Text nomeJogador = new Text(jogoTT.getJogador().getNome());
        nomeJogador.setFill(Color.BLACK);
        nomeJogador.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

        //para apresentar o nivel
        Text nivelJogo = new Text(jogoTT.getNivel() + "/" + NIVEL_MAX);
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
        Text dificuldadeJogo = new Text(jogoTT.getDificuldade() + "");
        dificuldadeJogo.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        if (dificuldadeJogo.getText().equals("FACIL")) {
            dificuldadeJogo.setFill(Color.GREEN);
        } else if (dificuldadeJogo.getText().equals("MEDIO")) {
            dificuldadeJogo.setFill(Color.YELLOW);
        } else {
            dificuldadeJogo.setFill(Color.RED);
        }
        rootJogoTT.setRight(dificuldadeJogo);

        Local[] arrayLocaisTemp = jogoTT.randomVertices2();

        String vIN = arrayLocaisTemp[0].getId();
        String vOUT = arrayLocaisTemp[1].getId();

        //botao Generate
        Button btnGenerate = new Button();
        btnGenerate.setText("Generate");
        btnGenerate.setMaxWidth(150);
        btnGenerate.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                
                System.out.println("entrou no generate");
                fecharContdown();
                primaryStage.setScene(criarJogoTimeTrial(primaryStage, nivel, jogador));
                

            }
        });

        //para apresentar o tipo de Solucao
        Text tipoSolucao = new Text(jogoTT.getTipoSolucao() + "");
        tipoSolucao.setFill(Color.BLACK);
        tipoSolucao.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

        //para apresentar o vertice de entrada (Fazer um random para devolver um vertice)
        Text textOrigem = new Text(vIN + "");
        textOrigem.setFill(Color.YELLOW);
        textOrigem.setFont(Font.font("Verdana", FontWeight.BOLD, 30));

        //para apresentar o vertice de saida (Faer um random para devolver um vertice diferente do outro)
        Text textDestino = new Text(vOUT + "");
        textDestino.setFill(Color.YELLOW);
        textDestino.setFont(Font.font("Verdana", FontWeight.BOLD, 30));

        //text field com a solucao
        TextField textSolucao = new TextField();
        textSolucao.setPromptText("Introduza a solucao");
        textSolucao.setAlignment(Pos.CENTER);
        textSolucao.setFocusTraversable(false);
        textSolucao.setMaxWidth(210);
        textSolucao.getText();

        //Botao para criar o mini jogo
        Button btnCalcularSolucao = new Button("Calcular");
        btnCalcularSolucao.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String strSolucao = textSolucao.getText();
                fecharContdown();
                calcularSolucao(primaryStage, jogador, nivel, strSolucao, vIN, vOUT, jogoTT);
            }

        });

        //botao Voltar
        Button btnVoltar = new Button();
        btnVoltar.setText("Voltar");
        btnVoltar.setMaxWidth(150);
        btnVoltar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (nivel > 1) {
                    jogador.escreverFicheiroRecordes("TT");
                    fecharContdown();
                    primaryStage.setScene(menuTimeTrial(primaryStage, jogador));
                } else {
                    fecharContdown();
                    primaryStage.setScene(menuTimeTrial(primaryStage, jogador));
                }
            }
        });

        //HBOX RODAPE
        HBox boxRodape = new HBox();
        boxRodape.setSpacing(20);
        boxRodape.setAlignment(Pos.CENTER);
        boxRodape.getChildren().addAll(btnGenerate, tipoSolucao, textOrigem, textDestino, textSolucao, btnCalcularSolucao, btnVoltar);
        rootJogoTT.setBottom(boxRodape);

        //CSS
        rootJogoTT.getStylesheets().addAll(AppMindGraphsFX.class.getResource("/javafxgraphs/ui/resources/style.css").toExternalForm());
        rootJogoTT.setId(painel);

        return janelaJogoTT;
    }

    public static void fecharContdown() {
        
        System.out.println("entrou no fechar ct");

        Stage stage1;

        stage1 = count(0);

        stage1.close();

    }

    public static void calcularSolucao(Stage primaryStage, Jogador jogador, int nivel, String solucaoUtilizador, String vIN, String vOUT, MiniJogo jogoTT) {

        iVertex<Local> verticeIN = jogoTT.findVertice(vIN);
        iVertex<Local> verticeOUT = jogoTT.findVertice(vOUT);

        //devolve uma string com o caminho consoante a estrategia
        int solucaoINT = jogoTT.getGrafoAdaptee().
                calcularSolucao(verticeIN, verticeOUT, jogoTT.getEstrategiaSolucao());

        String solucaoSTR = jogoTT.getGrafoAdaptee().
                dijkstra(verticeIN, verticeOUT, jogoTT.getEstrategiaSolucao());

        Stage stagePOPUP = new Stage();
        BorderPane rootPOPUP = new BorderPane();
        Scene janelaPOPUP = new Scene(rootPOPUP, 300, 200);
        System.out.println("menuPOPUP");

        Text txtCabecalho = new Text();
        txtCabecalho.setFont(Font.font("Verdana", FontWeight.BOLD, 30));

        if (verificaSolucao(solucaoINT, solucaoUtilizador)) {
            txtCabecalho.setText("CORRETO");
            txtCabecalho.setFill(Color.GREEN);

            if (nivel == NIVEL_MAX) {
                txtCabecalho.setText("VENCEDOR!");
                txtCabecalho.setFill(Color.GREEN);
            }
        } else {
            txtCabecalho.setText("ERRADO");
            txtCabecalho.setFill(Color.RED);
        }

        //para apresentar a solucao
        Text txtSolucaoINT = new Text("Solucao: " + solucaoINT);
        txtSolucaoINT.setFill(Color.BLACK);
        txtSolucaoINT.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

        //para apresentar o nivel
        Text txtSolucaoSTR = new Text("Caminho: " + solucaoSTR);
        txtSolucaoSTR.setFill(Color.GREEN);
        txtSolucaoSTR.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

        Button btnCalcularSolucao = new Button();
        btnCalcularSolucao.setMaxWidth(150);

        if (verificaSolucao(solucaoINT, solucaoUtilizador)) {
            btnCalcularSolucao.setText("Next");
            if (nivel == NIVEL_MAX) {
                btnCalcularSolucao.setText("Voltar");
            }
        } else {
            btnCalcularSolucao.setText("Game Over!");
        }

        btnCalcularSolucao.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                int f = nivel;

                if (verificaSolucao(solucaoINT, solucaoUtilizador)) {
                    jogador.setPontuacao(f);
                    f++;
                    primaryStage.setScene(criarJogoTimeTrial(primaryStage, f, jogador));

                    if (nivel == NIVEL_MAX) {
                        jogador.setPontuacao(nivel);
                        jogador.escreverFicheiroRecordes("TT");
                        primaryStage.setScene(menuTimeTrial(primaryStage, jogador));

                    }
                    stagePOPUP.close();
                } else {
                    if (f > 1) {
                        jogador.setPontuacao(f - 1);
                        jogador.escreverFicheiroRecordes("TT");
                    }
                    primaryStage.setScene(menuTimeTrial(primaryStage, jogador));
                    stagePOPUP.close();

                }

            }
        });

        //VBOX DE BOTOES
        VBox boxPOPUP = new VBox();
        boxPOPUP.setSpacing(10);
        boxPOPUP.setAlignment(Pos.CENTER);

        if (verificaSolucao(solucaoINT, solucaoUtilizador)) {
            boxPOPUP.getChildren().addAll(txtCabecalho, txtSolucaoINT, txtSolucaoSTR, btnCalcularSolucao);
        } else {
            boxPOPUP.getChildren().addAll(txtCabecalho, btnCalcularSolucao);
        }

        boxPOPUP.setStyle("-fx-background-color: #808080;");
        rootPOPUP.setCenter(boxPOPUP);

        //css
        rootPOPUP.getStylesheets().addAll(AppMindGraphsFX.class.getResource("/javafxgraphs/ui/resources/style.css").toExternalForm());

        //propriedades da nova stage
        stagePOPUP.setScene(janelaPOPUP);
        stagePOPUP.centerOnScreen();
        stagePOPUP.setResizable(false);
        stagePOPUP.show();

    }

    public static boolean verificaSolucao(int solucaoINT, String solucaoUtilizador) {

        return solucaoINT == Integer.parseInt(solucaoUtilizador);

    }

    static Integer seconds = 0;
    static Label label = null;

   
    

    public static Stage count(int temp) {
        
     
        int tempo = temp;
        BorderPane root = new BorderPane();
        Stage stage = new Stage();
        
        label = new Label();
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        
        VBox layout = new VBox();
        layout.getChildren().add(label);
        layout.setAlignment(Pos.CENTER);
//        root.getChildren().add(layout);
        root.setCenter(layout);
        doTime(stage, tempo);

        stage.setScene(new Scene(root, 60, 60));
        stage.setTitle("Tempo");

        stage.setAlwaysOnTop(true);
        stage.setX(1100.0);
        stage.setY(120.0);
        stage.initStyle(StageStyle.TRANSPARENT);

        stage.show();

        return stage;
    }

//
    static void doTime(Stage stage, int tempo) {
        seconds = tempo;
        Timeline time = new Timeline();
        time.setCycleCount(Timeline.INDEFINITE);
        if (time != null) {
            time.stop();
        }
        KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                seconds--;
                label.setText(seconds.toString());
                System.out.println(seconds);
                if (seconds <= 0) {
                    stage.close();
                    time.stop();
                }
            }
        });
        time.getKeyFrames().add(frame);
        time.playFromStart();
    }

}
