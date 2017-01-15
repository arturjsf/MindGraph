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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
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
public class Arcade{
    
    /**
     * MENU ARCADE
     *
     * @param primaryStage
     * @param jogador
     * @return
     */
    public static Scene menuArcade(Stage primaryStage, Jogador jogador) {
        
        
        BorderPane rootArcade = new BorderPane();
        Scene janelaArcade = new Scene(rootArcade, 1000, 600);
        System.out.println("menu ARCADE");
        
        Text textEscolherOpcao = new Text("Arcade");
        textEscolherOpcao.setFill(Color.GREEN);
        textEscolherOpcao.setFont(Font.font("Verdana", FontWeight.BOLD, 60));
        
        int btnSize = 150;
        
        Button botaoNovoJogo = new Button("Start");
        botaoNovoJogo.setMaxWidth(btnSize);
        botaoNovoJogo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                             
                primaryStage.setScene(menuPackArcade(primaryStage, jogador, gerarPackMiniJogosArcade(jogador)));
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
        Text nomeJogador = new Text(jogador.getNome());
        nomeJogador.setFill(Color.BLACK);
        nomeJogador.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        rootArcade.setTop(nomeJogador);
        
        //CSS
        rootArcade.getStylesheets().addAll(AppMindGraphsFX.class.getResource("/javafxgraphs/ui/resources/style.css").toExternalForm());     
        rootArcade.setId(painel);
        
        return janelaArcade;
    }

    
    
    /**
     * Cria uma GRID com varios jogos pre definidos
     *
     * @param primaryStage
     * @param jogador
     * @param nivel
     * @param packMiniJogosArcade
     * @param nEstrelas
     * @return
     */
    public static Scene menuPackArcade(Stage primaryStage, Jogador jogador, ArrayList<MiniJogo> packMiniJogosArcade) {
        
        
        
        BorderPane rootArcade = new BorderPane();
        Scene janelaArcade = new Scene(rootArcade, 1000, 600);
        System.out.println("Pack Arcade");
        
        
        //para apresentar o nome do jogador
        Text nomeJogador = new Text(jogador.getNome());
        nomeJogador.setFill(Color.BLACK);
        nomeJogador.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        
        
        int somaEstrelas = somaEstrelas(packMiniJogosArcade);
        jogador.setPontuacao(somaEstrelas);
        
        Text nivelEstrelas = new Text(somaEstrelas+"/60");
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
        
       // int NIVEL_MAX = 21;
        
        Button[] arrayBotoes = new Button[packMiniJogosArcade.size()];
        
        
        
        for (int i = 1; i < packMiniJogosArcade.size(); i++) {
            arrayBotoes[i] = new Button(Integer.toString(i));
            arrayBotoes[i].setPrefSize(80, 80);
            int f = i;
            arrayBotoes[i].setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    MiniJogo jogoArcade;
                    jogoArcade = packMiniJogosArcade.get(f);
                    primaryStage.setScene(criarJogoArcade(primaryStage, f, jogador, jogoArcade, packMiniJogosArcade));
                }
            });
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
                jogador.escreverFicheiroRecordes("Arcade");
                primaryStage.setScene(menuArcade(primaryStage, jogador));
            }
        });
        
        //VBOX DE BOTOES DE ESCOLHA DE TEMA
        VBox vBoxCentro = new VBox(textEscolherOpcao, tilePane, btnVoltar);
        vBoxCentro.setSpacing(10);
        vBoxCentro.setAlignment(Pos.CENTER);
        rootArcade.setCenter(vBoxCentro);
        
        //CSS
        rootArcade.getStylesheets().addAll(AppMindGraphsFX.class.getResource("/javafxgraphs/ui/resources/style.css").toExternalForm());     
        rootArcade.setId(painel);
        
        return janelaArcade;
    }
    
    
    
    
    
    
    
    
    public static Scene criarJogoArcade(Stage primaryStage, int nivel, Jogador jogador, 
            MiniJogo jogoArcade, ArrayList<MiniJogo> packMiniJogosArcade) {
        
 
    
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
        if (dificuldadeJogo.getText().equals("FACIL")) {
            dificuldadeJogo.setFill(Color.GREEN);
        } else if (dificuldadeJogo.getText().equals("MEDIO")) {
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
                String strSolucao = textSolucao.getText();
                calcularSolucao(primaryStage, jogador, nivel, strSolucao, vIN, vOUT, jogoArcade, packMiniJogosArcade);
            }
        });
        
        //botao Voltar
        Button btnVoltar = new Button();
        btnVoltar.setText("Voltar");
        btnVoltar.setMaxWidth(150);
        btnVoltar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setScene(menuPackArcade(primaryStage, jogador, packMiniJogosArcade));
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
    
    
    public static void calcularSolucao(Stage primaryStage, Jogador jogador, int nivel, String solucaoUtilizador, 
            String vIN, String vOUT, MiniJogo jogoArcade, ArrayList<MiniJogo> packMiniJogosArcade) {

        
        iVertex<Local> verticeIN = jogoArcade.findVertice(vIN);
        iVertex<Local> verticeOUT = jogoArcade.findVertice(vOUT);
    
        //devolve uma string com o caminho consoante a estrategia
        int solucaoINT = jogoArcade.getGrafoAdaptee().
                calcularSolucao(verticeIN, verticeOUT, jogoArcade.getEstrategiaSolucao());

        String solucaoSTR = jogoArcade.getGrafoAdaptee().
                dijkstra(verticeIN, verticeOUT, jogoArcade.getEstrategiaSolucao());
        
        int nEstrelas = atribuirEstrelas(solucaoINT, solucaoUtilizador);

        Stage stagePOPUP = new Stage();
        BorderPane rootPOPUP = new BorderPane();
        Scene janelaPOPUP = new Scene(rootPOPUP, 300, 200);
        System.out.println("menuPOPUP");

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

        //para apresentar o nivel
        Text txtSolucaoSTR = new Text("Caminho: " + solucaoSTR);
        txtSolucaoSTR.setFill(Color.GREEN);
        txtSolucaoSTR.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

        
        
        Button btnCalcularSolucao = new Button();
        btnCalcularSolucao.setMaxWidth(150);
        btnCalcularSolucao.setText("Voltar");
       
        
        
        btnCalcularSolucao.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                int f = nivel;

                if (verificaSolucao(solucaoINT, solucaoUtilizador)) {
                    //f++;
                    packMiniJogosArcade.get(f).setEstrelas(nEstrelas);
                    primaryStage.setScene(menuPackArcade(primaryStage, jogador, packMiniJogosArcade));
                    stagePOPUP.close();
                } else {
                    primaryStage.setScene(menuPackArcade(primaryStage, jogador, packMiniJogosArcade));
                    stagePOPUP.close();
                }

            }
        });

        //VBOX 
        VBox boxPOPUP = new VBox();
        boxPOPUP.setSpacing(10);
        boxPOPUP.setAlignment(Pos.CENTER);
        boxPOPUP.getChildren().addAll(txtCabecalho, txtEstrelas, txtSolucaoINT, txtSolucaoSTR, btnCalcularSolucao);
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
    
    /**
     * Verifica se esta correto e atribui estrelas
     * @param solucaoINT
     * @param solucaoUtilizador
     * @return 
     */
    public static int atribuirEstrelas(int solucaoINT, String solucaoUtilizador) {
      
        int intSolucaoUtilizador = Integer.parseInt(solucaoUtilizador);
               
        if (solucaoINT == intSolucaoUtilizador) {
            return 3;
        } else if((intSolucaoUtilizador > (solucaoINT-(solucaoINT*0.25))) || (intSolucaoUtilizador < (solucaoINT*1.25))){
            return 2;
        }else if((intSolucaoUtilizador > (solucaoINT-(solucaoINT*0.50))) || (intSolucaoUtilizador < (solucaoINT*1.50))){
            return 1;
        }else{
            return 0;
        }

    }
    
    
    /**
     * Verifica se acertou ou nao
     * @param solucaoINT
     * @param solucaoUtilizador
     * @return 
     */
    public static boolean verificaSolucao(int solucaoINT, String solucaoUtilizador){
        
        return ((atribuirEstrelas(solucaoINT, solucaoUtilizador) <= 3)|| (atribuirEstrelas(solucaoINT, solucaoUtilizador) > 0));
    }
        
        
        
        /**
     * Gera uma ArrayList de Minijogos
     * @param jogador
     * @return 
     */
    public static ArrayList<MiniJogo> gerarPackMiniJogosArcade(Jogador jogador){
        
        ArrayList<MiniJogo> packMiniJogos = new ArrayList<> ();
        
        for (int i = 0; i <= 20; i++) {
            packMiniJogos.add(new MiniJogo(TipoJogo.ARCADE, jogador, i));
        }
        
                
        return packMiniJogos;
    }
    
    
    /**
     * Recebe um pack de miniJogosArcade e devolve a soma das estrelas
     * @param packMiniJogos
     * @return 
     */
    public static int somaEstrelas(ArrayList<MiniJogo> packMiniJogos){
        
        int somaEstrelas = 0;
        for (int i = 0; i < packMiniJogos.size(); i++) {
            somaEstrelas += packMiniJogos.get(i).getEstrelas();
        }
        
        return somaEstrelas;
    }
    
    }
  

    

