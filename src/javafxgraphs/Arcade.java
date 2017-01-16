/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxgraphs;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static javafxgraphs.AppMindGraphsFX.painel;
import javafxgraphs.modelo.Jogador;
import javafxgraphs.modelo.Ligacao;
import javafxgraphs.modelo.Local;
import javafxgraphs.modelo.MiniJogo;
import javafxgraphs.modelo.TipoJogo;
import javafxgraphs.tad.iVertex;
import javafxgraphs.ui.GraphDraw;

/**
 * Classe onde são gerados os packs de jogos Arcade. Nesta classe são criados os
 * menus e submenus Arcade
 *
 * @author Artur Ferreira
 */
public class Arcade {

    /**
     * MENU ARCADE
     *
     * @param primaryStage Stage inicial
     * @param jogador jogador
     * @return Retorna uma cena com o subMenu Arcade. Contem 4 botoes (Start,
     * recordes, regras e voltar)
     */
    public static Scene menuArcade(Stage primaryStage, Jogador jogador) {

        BorderPane rootArcade = new BorderPane();
        Scene janelaArcade = new Scene(rootArcade, 1000, 600);

        Text textEscolherOpcao = new Text("Arcade");
        textEscolherOpcao.setFill(Color.GREEN);
        textEscolherOpcao.setFont(Font.font("Verdana", FontWeight.BOLD, 60));
        
        Reflection r = new Reflection();
        r.setFraction(0.7f);
        textEscolherOpcao.setEffect(r);

        int btnSize = 150;

        Button botaoNovoJogo = new Button("Start");
        botaoNovoJogo.setMaxWidth(btnSize);
        botaoNovoJogo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                primaryStage.setScene(menuPackArcade(primaryStage, jogador, 1, gerarPackMiniJogosArcade(jogador)));
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
                Scene scene = Regras.menuRegrasArcade(primaryStage, jogador);
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
        Text nomeJogador = new Text("Jogador\n" + jogador.getNome());
        nomeJogador.setFill(Color.BLACK);
        nomeJogador.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        rootArcade.setTop(nomeJogador);

        //CSS
        rootArcade.getStylesheets().addAll(AppMindGraphsFX.class.getResource("/javafxgraphs/ui/resources/style.css").toExternalForm());
        rootArcade.setId(painel);

        return janelaArcade;
    }

    /**
     *
     *
     * @param primaryStage stage inicial
     * @param jogador jogador
     * @param packMiniJogosArcade recebe um arrayList do tipo MiniJogo. A cada
     * botao é atribuido um Minijogo consoante a dificuldade.
     * @return Devolve uma TilePane com varios jogos pre definidos
     */
    public static Scene menuPackArcade(Stage primaryStage, Jogador jogador, int nBotoes, ArrayList<MiniJogo> packMiniJogosArcade) {

        BorderPane rootArcade = new BorderPane();
        Scene janelaArcade = new Scene(rootArcade, 1000, 600);

        //para apresentar o nome do jogador
        Text nomeJogador = new Text("Jogador\n" + jogador.getNome());
        nomeJogador.setFill(Color.BLACK);
        nomeJogador.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

        int somaEstrelas = somaEstrelas(packMiniJogosArcade);
        jogador.setPontuacao(somaEstrelas);

        Text nivelEstrelas = new Text("Estrelas\n" + somaEstrelas + "/60");
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
        Reflection r = new Reflection();
        r.setFraction(0.7f);
        textEscolherOpcao.setEffect(r);

        TilePane tilePane = new TilePane(Orientation.HORIZONTAL, 10, 10);
        tilePane.setMaxWidth(500);

        Button[] arrayBotoes = new Button[packMiniJogosArcade.size()];

        for (int i = 1; i < packMiniJogosArcade.size(); i++) {
            arrayBotoes[i] = new Button(Integer.toString(i));
            arrayBotoes[i].setPrefSize(80, 80);
            arrayBotoes[i].setDisable(true);
            int f = i;
            arrayBotoes[i].setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    MiniJogo jogoArcade;
                    jogoArcade = packMiniJogosArcade.get(f);
                    primaryStage.setScene(criarJogoArcade(primaryStage, f, jogador, jogoArcade, packMiniJogosArcade));
                }
            });
            
            for (int j = 0; j <= nBotoes; j++) {
                arrayBotoes[j].setDisable(false);
            }
            
            
            tilePane.getChildren().add(arrayBotoes[i]);
        }

        tilePane.setAlignment(Pos.CENTER);

        //botao Voltar
        Button btnVoltar = new Button();
        btnVoltar.setAlignment(Pos.CENTER);
        btnVoltar.setText("Voltar");
        btnVoltar.setMaxWidth(250);
        btnVoltar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if (somaEstrelas != 0) {
                    jogador.escreverFicheiroRecordes("Arcade");
                    primaryStage.setScene(menuArcade(primaryStage, jogador));
                } else {
                    primaryStage.setScene(menuArcade(primaryStage, jogador));
                }
            }
        });

        //VBOX
        VBox vBoxCentro = new VBox(textEscolherOpcao, tilePane, btnVoltar);
        vBoxCentro.setSpacing(10);
        vBoxCentro.setAlignment(Pos.CENTER);
        rootArcade.setCenter(vBoxCentro);

        //CSS
        rootArcade.getStylesheets().addAll(AppMindGraphsFX.class.getResource("/javafxgraphs/ui/resources/style.css").toExternalForm());
        rootArcade.setId(painel);

        return janelaArcade;
    }

    /**
     *
     * @param primaryStage stage inicial
     * @param nivel nivel do MiniJogo gerado
     * @param jogador jogador
     * @param jogoArcade MiniJogo gerado
     * @param packMiniJogosArcade ArrayList de MiniJogos
     * @return Este metodo cria a cena do MiniJogo.
     */
    public static Scene criarJogoArcade(Stage primaryStage, int nivel, Jogador jogador,
            MiniJogo jogoArcade, ArrayList<MiniJogo> packMiniJogosArcade) {

        BorderPane rootJogoArcade = new BorderPane();
        Scene janelaJogoArcade = new Scene(rootJogoArcade, 1000, 600);

        //para apresentar o nome do jogador
        Text nomeJogador = new Text("Jogador\n" + jogoArcade.getJogador().getNome());
        nomeJogador.setFill(Color.BLACK);
        nomeJogador.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

        //para apresentar o nivel
        Text nivelJogo = new Text("Nivel\n" + jogoArcade.getNivel() + "/20");
        nivelJogo.setFill(Color.YELLOW);
        nivelJogo.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

        //para apresentar a dificuldade
        Text dificuldadeJogo = new Text("Dificuldade\n" + jogoArcade.getDificuldade() + "");
        dificuldadeJogo.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        if (dificuldadeJogo.getText().equals("Dificuldade\nFACIL")) {
            dificuldadeJogo.setFill(Color.GREEN);
        } else if (dificuldadeJogo.getText().equals("Dificuldade\nMEDIO")) {
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

        //desenho do grafo
        GraphDraw<Local, Ligacao> drawMiniJogo = new GraphDraw(jogoArcade.getGrafoAdaptee());
        rootJogoArcade.setCenter(drawMiniJogo);

        //para apresentar o tipo de Solucao
        Text tipoSolucao = new Text(jogoArcade.getTipoSolucao() + "");
        tipoSolucao.setFill(Color.BLACK);
        tipoSolucao.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

        //Gera 2 vertices origem e destino
        Local[] arrayLocaisTemp = jogoArcade.randomVertices2();

        String vIN = arrayLocaisTemp[0].getId();
        String vOUT = arrayLocaisTemp[1].getId();

        //para apresentar o vertice de origem
        Text vOrigem = new Text(vIN + "");
        vOrigem.setFill(Color.YELLOW);
        vOrigem.setFont(Font.font("Verdana", FontWeight.BOLD, 30));

        //para apresentar o vertice de destino
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
        btnCalcularSolucao.setDisable(true);
        AppMindGraphsFX.verificaTextField(textSolucao, btnCalcularSolucao);
        btnCalcularSolucao.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) throws NumberFormatException{
                try {
                    String strSolucao = textSolucao.getText();
                    calcularSolucao(primaryStage, jogador, nivel, strSolucao, vIN, vOUT, jogoArcade, packMiniJogosArcade);
                } catch (NumberFormatException ex) {
                    System.out.println("Erro de entrada: " + ex.getMessage());
                }
            }
        });

        //botao Voltar
        Button btnVoltar = new Button();
        btnVoltar.setText("Voltar");
        btnVoltar.setMaxWidth(150);
        btnVoltar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setScene(menuPackArcade(primaryStage, jogador, nivel, packMiniJogosArcade));
            }
        });

        //HBOX RODAPE
        HBox boxRodape = new HBox();
        boxRodape.setSpacing(20);
        boxRodape.setAlignment(Pos.CENTER);
        boxRodape.getChildren().addAll(tipoSolucao, vOrigem, vDestino, textSolucao, btnCalcularSolucao, btnVoltar);
        rootJogoArcade.setBottom(boxRodape);

        //CSS
        rootJogoArcade.getStylesheets().addAll(AppMindGraphsFX.class.getResource("/javafxgraphs/ui/resources/style.css").toExternalForm());
        rootJogoArcade.setId(painel);

        return janelaJogoArcade;
    }

    /**
     *
     * @param primaryStage stage inicial
     * @param jogador jogador
     * @param nivel nivel do minijogo gerado
     * @param solucaoUtilizador solucao recebida pelo utilizador
     * @param vIN vertice de entrada
     * @param vOUT vertice de destino
     * @param jogoArcade minijogo gerado
     * @param packMiniJogosArcade pack de miniJogos gerados
     *
     * Este metodo cria uma Stage popUP. Atribui estrelas consoante o nivel de
     * acerto do utilizador
     */
    public static void calcularSolucao(Stage primaryStage, Jogador jogador, int nivel, String solucaoUtilizador,
            String vIN, String vOUT, MiniJogo jogoArcade, ArrayList<MiniJogo> packMiniJogosArcade) {

        //Tenho de converter para iVertex<Local> para enviar para o metodo calcularSolucao e dijkstra
        iVertex<Local> verticeIN = jogoArcade.findVertice(vIN);
        iVertex<Local> verticeOUT = jogoArcade.findVertice(vOUT);

        //devolve um int com a solucao consoante a estrategia
        int solucaoINT = jogoArcade.getGrafoAdaptee().
                calcularSolucao(verticeIN, verticeOUT, jogoArcade.getEstrategiaSolucao());

        //devolve uma string com o caminho consoante a estrategia
        String solucaoSTR = jogoArcade.getGrafoAdaptee().
                dijkstra(verticeIN, verticeOUT, jogoArcade.getEstrategiaSolucao());

        //Numero de estrelas consoante o nivel de acerto
        int nEstrelas = atribuirEstrelas(solucaoINT, solucaoUtilizador);

        Stage stagePOPUP = new Stage();
        BorderPane rootPOPUP = new BorderPane();
        Scene janelaPOPUP = new Scene(rootPOPUP, 300, 200);

        Text txtCabecalho = new Text();
        txtCabecalho.setFont(Font.font("Verdana", FontWeight.BOLD, 30));

        if (verificaSolucao(solucaoINT, solucaoUtilizador)) {

        } else {
            txtCabecalho.setText("ERRADO");
            txtCabecalho.setFill(Color.RED);
        }

        Text txtEstrelas = new Text();
        txtEstrelas.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        txtEstrelas.setFill(Color.YELLOW);

        switch (nEstrelas) {
            case 3:
                txtEstrelas.setText("* * *");
                txtCabecalho.setText("PERFEITO");
                txtCabecalho.setFill(Color.GREEN);
                break;
            case 2:
                txtEstrelas.setText("* *");
                txtCabecalho.setText("QUASE LA");
                txtCabecalho.setFill(Color.YELLOWGREEN);
                break;
            case 1:
                txtEstrelas.setText("*");
                txtCabecalho.setText("BAH");
                txtCabecalho.setFill(Color.YELLOW);
                break;
            default:
                txtEstrelas.setText(" ");
                txtCabecalho.setText("ERRADO");
                txtCabecalho.setFill(Color.RED);
                break;
        }

        //para apresentar a solucao
        Text txtSolucaoINT = new Text("Solucao: " + solucaoINT);
        txtSolucaoINT.setFill(Color.BLACK);
        txtSolucaoINT.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

        //para apresentar o caminho
        Text txtSolucaoSTR = new Text("Caminho: " + solucaoSTR);
        txtSolucaoSTR.setFill(Color.GREEN);
        txtSolucaoSTR.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

        Button btnCalcularSolucao = new Button();
        btnCalcularSolucao.setMaxWidth(150);
        btnCalcularSolucao.setText("Voltar");

        btnCalcularSolucao.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int f = nivel;

                if (verificaSolucao(solucaoINT, solucaoUtilizador)) {
                    packMiniJogosArcade.get(f).setEstrelas(nEstrelas);
                    f++;
                    primaryStage.setScene(menuPackArcade(primaryStage, jogador, f, packMiniJogosArcade));
                    stagePOPUP.close();
                } else {
                    primaryStage.setScene(menuPackArcade(primaryStage, jogador, f, packMiniJogosArcade));
                    stagePOPUP.close();
                }

            }
        });

        //VBOX 
        VBox boxPOPUP = new VBox();
        boxPOPUP.setSpacing(10);
        boxPOPUP.setAlignment(Pos.CENTER);

        if (nEstrelas == 3) {
            boxPOPUP.getChildren().addAll(txtCabecalho, txtEstrelas, txtSolucaoINT, txtSolucaoSTR, btnCalcularSolucao);
        } else {
            boxPOPUP.getChildren().addAll(txtCabecalho, txtEstrelas, btnCalcularSolucao);
        }
        boxPOPUP.setStyle("-fx-background-color: #808080;");
        rootPOPUP.setCenter(boxPOPUP);

        //css
        rootPOPUP.getStylesheets().addAll(AppMindGraphsFX.class.getResource("/javafxgraphs/ui/resources/style.css").toExternalForm());

        //propriedades da nova stage
        stagePOPUP.setScene(janelaPOPUP);
        stagePOPUP.centerOnScreen();
        stagePOPUP.setResizable(false);
        stagePOPUP.initStyle(StageStyle.TRANSPARENT);
        stagePOPUP.show();

    }

    /**
     *
     *
     * @param solucaoINT solucao correta
     * @param solucaoUtilizador solucao do utilizador
     * @return Verifica se esta correto e atribui estrelas 3,2,1 ou 0 estrelas
     * consoante o nivel de acerto
     */
    public static int atribuirEstrelas(int solucaoINT, String solucaoUtilizador) {

        int intSolucaoUtilizador = Integer.parseInt(solucaoUtilizador);

        if (solucaoINT == intSolucaoUtilizador) {
            return 3;
        } else if ((intSolucaoUtilizador > (solucaoINT - (solucaoINT * 0.25))) && (intSolucaoUtilizador < (solucaoINT * 1.25))) {
            return 2;
        } else if ((intSolucaoUtilizador > (solucaoINT - (solucaoINT * 0.50))) && (intSolucaoUtilizador < (solucaoINT * 1.50))) {
            return 1;
        } else {
            return 0;
        }

    }

    /**
     *
     * @param solucaoINT solucao correta
     * @param solucaoUtilizador solucao do utilizador
     * @return Devolve um booleano true se a solucao do utilizador estiver
     * correta
     */
    public static boolean verificaSolucao(int solucaoINT, String solucaoUtilizador) {

        return ((atribuirEstrelas(solucaoINT, solucaoUtilizador) <= 3) || (atribuirEstrelas(solucaoINT, solucaoUtilizador) > 0));
    }

    /**
     *
     * @param jogador jogador
     * @return Gera uma ArrayList de Minijogos
     */
    public static ArrayList<MiniJogo> gerarPackMiniJogosArcade(Jogador jogador) {

        ArrayList<MiniJogo> packMiniJogos = new ArrayList<>();

        for (int i = 0; i <= 20; i++) {
            packMiniJogos.add(new MiniJogo(TipoJogo.ARCADE, jogador, i));
        }

        return packMiniJogos;
    }

    /**
     * Recebe um pack de miniJogosArcade e devolve a soma das estrelas
     *
     * @param packMiniJogos ArrayList de miniJogos
     * @return soma das estrelas de todos os minijogos
     */
    public static int somaEstrelas(ArrayList<MiniJogo> packMiniJogos) {

        int somaEstrelas = 0;
        for (int i = 0; i < packMiniJogos.size(); i++) {
            somaEstrelas += packMiniJogos.get(i).getEstrelas();
        }

        return somaEstrelas;
    }

}
