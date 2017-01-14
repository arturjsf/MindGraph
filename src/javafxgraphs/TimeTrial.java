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
import javafx.scene.input.MouseEvent;
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
import javafxgraphs.modelo.TipoJogo;
import javafxgraphs.tad.iVertex;
import javafxgraphs.ui.GraphDraw;

/**
 *
 * @author Artur Ferreira
 */
public class TimeTrial {

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
        textEscolherOpcao.setFont(Font.font("Verdana", FontWeight.BOLD, 40));
        
        
        int btnSize = 150;
          
        Button botaoNovoJogo = new Button("Start");
        botaoNovoJogo.setMaxWidth(btnSize);
        botaoNovoJogo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                
                primaryStage.setScene(criarJogoTimeTrial(primaryStage, 1, jogador));
                //gerarTimeTrial(primaryStage, 1, jogador);
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
        //rootTimeTrial.getStylesheets().addAll(this.getClass().getResource("/javafxgraphs/ui/resources/style.css").toExternalForm());
        rootTimeTrial.setId("pane");
        // primaryStage.setScene(janelaMenu);
        return janelaTimeTrial;
    }

    
    /**
     * Este metodo vai gerar logo os 20 niveis
     *
     * @param primaryStage
     * @param nivel
     * @param jogador
     */
//    public static void gerarTimeTrial(Stage primaryStage, int nivel, Jogador jogador) {
//        int NIVEL_MAX = 21;
//        for (int i = 0; i < NIVEL_MAX; i++) {
//            criarJogoTimeTrial(primaryStage, i, jogador);
//        }
//        System.out.println("VENCEDOR");
//    }

    
    /**
     * Desenha a janela Jogo TT
     * @param primaryStage
     * @param nivel
     * @param jogador
     * @return 
     */
    public static Scene criarJogoTimeTrial(Stage primaryStage, int nivel, Jogador jogador) {
        
        MiniJogo jogoTT = new MiniJogo(TipoJogo.TIMETRIAL, jogador, nivel);
        
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
        Text dificuldadeJogo = new Text(jogoTT.getDificuldade() + "");
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
        Text tipoSolucao = new Text(jogoTT.getTipoSolucao() + "");
        tipoSolucao.setFill(Color.BLACK);
        tipoSolucao.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        
        
        Local[] arrayLocaisTemp = jogoTT.randomVertices2();
        
        String vIN = arrayLocaisTemp[0].getId();
        String vOUT = arrayLocaisTemp[1].getId();
        
        iVertex<Local> verticeIN = jogoTT.findVertice(vIN);
        iVertex<Local> verticeOUT = jogoTT.findVertice(vOUT);
        
        
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
                System.out.println("calcular solucao e ir para o menu POPUP NEXTGAME se tiver certa. " + "Se tiver errado ir para o menu POPUP Game over");
            }
        });
        
        
        //botao Voltar
        Button btnVoltar = new Button();
        btnVoltar.setText("Voltar");
        btnVoltar.setMaxWidth(150);
        btnVoltar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setScene(menuTimeTrial(primaryStage, jogador));
            }
        });
        
        
        //HBOX RODAPE
        HBox boxRodape = new HBox();
        boxRodape.setSpacing(20);
        boxRodape.setAlignment(Pos.CENTER);
        boxRodape.getChildren().addAll(tipoSolucao, textOrigem, textDestino, textSolucao, btnCalcularSolucao, btnVoltar);
        rootJogoTT.setBottom(boxRodape);
        
        //CSS
        //rootJogoTT.getStylesheets().addAll(this.getClass().getResource("/javafxgraphs/ui/resources/style.css").toExternalForm());
        rootJogoTT.setId("pane");
        
        return janelaJogoTT;
    }

}
