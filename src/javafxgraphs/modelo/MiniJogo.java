/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxgraphs.modelo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javafxgraphs.tad.InvalidVertexException;
import javafxgraphs.tad.MyGraph;
import javafxgraphs.tad.iEdge;
import javafxgraphs.tad.iVertex;

/**
 * Baseado no LAB3 University Network. É a classe principal. É tudo criado a
 * partir daqui.
 *
 * @author Artur Ferreira
 */
public class MiniJogo implements iMiniJogo, Serializable {

    public MyGraph<Local, Ligacao> grafoAdaptee;

    public iEstrategiaDificuldade estrategiaDificuldade;
    public iEstrategiaSolucao estrategiaSolucao;

    /**
     * Enumerados
     */
    TipoJogo tipoJogo;
    TipoSolucao tipoSolucao;
    Dificuldade dificuldade;

    Jogador jogador;

    int segundos;
    int nivel;

    /**
     * Construtor do MiniJogo
     *
     * @param nivel Cria um miniJogo random consoante o estrategiaDificuldade
     */
    public MiniJogo(TipoJogo modo, Jogador jogador, int nivel) {

        this.jogador = jogador;

        switch (modo) {

            case ARCADE:
                MiniJogoArcade(jogador, nivel);
                break;
            case TIMETRIAL:
                MiniJogoTT(jogador, nivel, segundos);
                break;
            default:
                System.out.println("sem modo");

        }
    }

    /**
     * construtor ARCADE
     *
     * @param jogador
     * @param nivel
     * @param dificuldade
     */
    
    public void MiniJogoArcade(Jogador jogador, int nivel) {

        this.jogador = jogador;

        if (nivel <= 8) {
            this.nivel = nivel;
            criarMiniJogo(escolherDificuldade(Dificuldade.FACIL), escolherSolucao(randomSolucao()));
        } else if (nivel >= 9 && nivel <= 15) {
            this.nivel = nivel;
            criarMiniJogo(escolherDificuldade(Dificuldade.MEDIO), escolherSolucao(randomSolucao()));
        } else {
            this.nivel = nivel;
             criarMiniJogo(escolherDificuldade(Dificuldade.DIFICIL), escolherSolucao(randomSolucao()));
        }
    }

    /**
     * Construtor TIMETRIAL
     *
     * @param jogador
     * @param nivel
     * @param segundos
     * @param dificuldade
     */
    
    public void MiniJogoTT(Jogador jogador, int nivel, int segundos) {

        this.jogador = jogador;

        if (nivel <= 8) {
            setSegundos(120);
            this.nivel = nivel;
            criarMiniJogo(escolherDificuldade(Dificuldade.FACIL), escolherSolucao(randomSolucao()));
        } else if (nivel >= 9 && nivel <= 15) {
            setSegundos(100);
            this.nivel = nivel;
            criarMiniJogo(escolherDificuldade(Dificuldade.MEDIO), escolherSolucao(randomSolucao()));
        } else {
            setSegundos(60);
            this.nivel = nivel;
            criarMiniJogo(escolherDificuldade(Dificuldade.DIFICIL), escolherSolucao(randomSolucao()));
        }
    }

    /**
     * Básicamente iguala o que esta no enumerado á estrategia
     *
     * @param dificuldade
     */
    public iEstrategiaDificuldade escolherDificuldade(Dificuldade dificuldade) {

        switch (dificuldade) {

            case FACIL:
                this.dificuldade = dificuldade;
                return new DificuldadeFACIL();
            case MEDIO:
                this.dificuldade = dificuldade;
                return new DificuldadeMEDIO();
            case DIFICIL:
                this.dificuldade = dificuldade;
                return new DificuldadeDIFICIL();
            default:
                System.out.println("sem dificuldade");
                return null;

        }
    }

  

   
    public iEstrategiaSolucao escolherSolucao(TipoSolucao tipoSolucao) {
        
    switch (tipoSolucao) {

            case MIN_CUSTO:
                this.tipoSolucao = tipoSolucao;
                return new CalcularCustoMinimo();
            case MIN_DISTANCIA:
                this.tipoSolucao = tipoSolucao;
                return new CalcularDistanciaMinima();
            case MIN_MOVIMENTOS:
                this.tipoSolucao = tipoSolucao;
                return new CalcularDeslocacaoMinima();
            default:
                System.out.println("sem solucao");
                return null;

        
    }
    }
    
    
    /**
     * Faz um random do ENUM TipoSolucao
     * @return 
     */
    public TipoSolucao randomSolucao(){
        
        TipoSolucao[] rdSolucoes = TipoSolucao.values();
      
        Random rdn = new Random();
        
        TipoSolucao tipoAux;
        
        tipoAux = rdSolucoes[rdn.nextInt(rdSolucoes.length)];
        
        return tipoAux;
        
        
    }

    
    /**
     * Devolve um array de miniJogos a ser atribuido a cada botao no javaFX
     */
    public void gerarPackArcade() {

       
        for (int i = 0; i < 20; i++) {
            MiniJogoArcade(jogador, i);
        }
    }

    public void criarMiniJogo(iEstrategiaDificuldade estrategiaDificuldade, iEstrategiaSolucao estrategiaSolucao) {

        grafoAdaptee = new MyGraph<>();
        this.estrategiaDificuldade = estrategiaDificuldade;
        this.estrategiaSolucao = estrategiaSolucao;
        criarGrafo(estrategiaDificuldade);
    }

    /**
     * Construtor de MiniJogos. Consoante o modo de jogo
     *
     * @param modo
     * @return
     */
    /**
     * Este metodo cria o grafo.
     *
     * @param nivel Recebe a estrategiaDificuldade e faz um random para o numero
     * de vertices e arestas
     */
    public void criarGrafo(iEstrategiaDificuldade nivel) {

        //gera e cria uma lista de vertices random consoante o estrategiaDificuldade
        gerarLocal(nivel.randomVertices());

        //gera e cria uma ligacao e atribui de maneira aleatoria os vertices in e out
        gerarLigacao(nivel);

    }

    /**
     * gera uma ligacao random enquanto houver numArestas a serem criadas
     *
     * @param nivel Consoante o estrategiaDificuldade a receber, cria uma
     * Ligacao com valores random
     */
    public void gerarLigacao2(iEstrategiaDificuldade nivel) {

        for (int i = 0; i < nivel.randomArestas(); i++) {

            Local[] listaLocais = randomVertices2();

            Local origemTemp = listaLocais[0];
            Local destinoTemp = listaLocais[1];

            Ligacao ligacaoTemp = new Ligacao(nivel.randomTipo(), origemTemp.getId() + destinoTemp.getId(), nivel.randomDistancia(), nivel.randomCusto());

    
            grafoAdaptee.insertEdge(origemTemp, destinoTemp, ligacaoTemp);
          

        }
    }
    
    
    
    
    public void gerarLigacao(iEstrategiaDificuldade nivel) {

        //SIZE da listaVertices
        int nVertices = grafoAdaptee.numVertices();
        Ligacao ligacaoTemp;
        
        int nArestas = nivel.randomArestas();
        
        //System.out.println(""+nArestas);

        for (iVertex<Local> listaVertices : grafoAdaptee.vertices()) {
            Local temp = listaVertices.element();
            Local[] listaLocais = randomVertices2();
            if (!temp.equals(listaLocais[0])) {
                listaLocais[1] = temp;
            }
            ligacaoTemp = new Ligacao(nivel.randomTipo(), listaLocais[0].getId() + listaLocais[1].getId(), nivel.randomDistancia(), nivel.randomCusto());
            grafoAdaptee.insertEdge(listaLocais[0], listaLocais[1], ligacaoTemp);

//            for (int i = 0; i < nArestas-nVertices; i++) {
//                
//  
//                Local origemTemp = listaLocais[0];
//                Local destinoTemp = listaLocais[1];
//
//                ligacaoTemp = new Ligacao(nivel.randomTipo(), origemTemp.getId() + destinoTemp.getId(), nivel.randomDistancia(), nivel.randomCusto());
//                grafoAdaptee.insertEdge(origemTemp, destinoTemp, ligacaoTemp);
//            }

        }
    }

    /**
     * Recebe uma lista de vertices e percorre a devolvendo um local random a
     * ser inserido
     *
     * @return Local random
     */
    
    public Local randomVertices() {

        //fazer um random de uma lista de vertices e devolve o
        //copia para uma arraylist e adiciona cada elemento da listaVertices
        ArrayList<iVertex<Local>> locaisAux = new ArrayList<>();
        for (iVertex<Local> listaVertices : grafoAdaptee.vertices()) {
            locaisAux.add(listaVertices);
            // listaVertices.element().getId();
        }

        Collections.shuffle(locaisAux);

        Local localAux = locaisAux.get(0).element();

        return localAux;
    }

    /**
     *
     * @return Devolve um array de Locais de 2 posicoes random. A posicao 0 irá
     * servir para o verticeIN e a posicao 1 para o verticeOUT
     */
    public Local[] randomVertices2() {

        //fazer um random de uma lista de vertices e devolve o
        //copia para uma arraylist e adiciona cada elemento da listaVertices
        ArrayList<iVertex<Local>> locaisAux = new ArrayList<>();
        for (iVertex<Local> listaVertices : grafoAdaptee.vertices()) {
            locaisAux.add(listaVertices);
            // listaVertices.element().getId();
        }

        Collections.shuffle(locaisAux);

        Local localAux[] = new Local[2];

        localAux[0] = locaisAux.get(0).element();
        localAux[1] = locaisAux.get(1).element();

        return localAux;
    }

    /**
     * gera um Local através do numero de vertices recebido
     *
     * @param numVertices Recebe um numVertices e atribui uma letra a cada Local
     *
     */
    public void gerarLocal(int numVertices) {

        CharSequence alphabet = "ABCDEFGHIJKLM";

        /**
         * cria os Locais e atribui uma letra do alfabeto a cada numVertice
         */
        for (int i = 0; i < numVertices; i++) {
            //         Local localTemp = new Local("V" + (i + 1));
            Local localTemp = new Local(alphabet.charAt(i) + "");
            adicionarLocal(localTemp);
            //System.out.println(localTemp.getId());

            // return localTemp;
        }
    }

    /**
     * Baseado no LAB3 University.
     *
     *
     * @param local Recebe um local e adiciona o á lista de Vertices
     */
    public void adicionarLocal(Local local) throws LocalException {
        if (findVertice(local.getId()) != null) {
            throw new LocalException("Local já existe na rede");
        }

        try {
            grafoAdaptee.insertVertex(local);
        } catch (InvalidVertexException e) {
            throw new LocalException("Erro Local");
        }

    }

    /**
     *
     *
     * @return Devolve a lista completa de Locais em forma de String concatenada
     */
    public String listaLocaisToString() {
        String str = "{";
        for (iVertex<Local> listaVertices : grafoAdaptee.vertices()) {
            str += listaVertices.element().getId() + "";

        }
        str += "}";

        return str;
    }

    /**
     *
     * @return Devolve a lista completa de Ligacoes em forma de String
     * concatenada
     */
    public String listaLigacoesToString() {
        String str = "";

        for (iEdge<Ligacao, Local> listaArestas : grafoAdaptee.edges()) {
            str += listaArestas.element().getNomeLigacao() + "-";

        }
        return str;
    }

    /**
     * É tipo um getID do Local
     *
     * @param local local a procurar
     * @return String do local encontrado
     */
    public String localToString(iVertex<Local> local) {
        return local.element().getId();
    }

    /**
     * Baseado no LAB3
     *
     * @param id id a procurar na listaVertices
     * @return Se encontrar o vertice atraves do id devolve o
     */
    public iVertex<Local> findVertice(String id) {
        for (iVertex<Local> vertice : grafoAdaptee.vertices()) {
            if (vertice.element().getId().equals(id)) {
                return vertice;
            }
        }

        return null;
    }

    /**
     * (Not implemented)
     *
     * @return devolve o caminho percorrido em String. ex: A-C-D-F
     */
    // @Override
    public String mostrarCaminho() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     *
     *
     *
     *
     * *********************GETS E SETS********************
     */
    /**
     * @return Devolve um grafo
     */
    public MyGraph<Local, Ligacao> getGrafoAdaptee() {
        return grafoAdaptee;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public iEstrategiaDificuldade getEstrategiaDificuldade() {
        return estrategiaDificuldade;
    }

    public void setEstrategiaDificuldade(iEstrategiaDificuldade estrategiaDificuldade) {
        this.estrategiaDificuldade = estrategiaDificuldade;
    }

    public int getSegundos() {
        return segundos;
    }

    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public TipoJogo getTipoJogo() {
        return tipoJogo;
    }

    public void setTipoJogo(TipoJogo tipoJogo) {
        this.tipoJogo = tipoJogo;
    }

    public Dificuldade getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(Dificuldade dificuldade) {
        this.dificuldade = dificuldade;
    }

    public iEstrategiaSolucao getEstrategiaSolucao() {
        return estrategiaSolucao;
    }

    public void setEstrategiaSolucao(iEstrategiaSolucao estrategiaSolucao) {
        this.estrategiaSolucao = estrategiaSolucao;
    }

    public TipoSolucao getTipoSolucao() {
        return tipoSolucao;
    }

    public void setTipoSolucao(TipoSolucao tipoSolucao) {
        this.tipoSolucao = tipoSolucao;
    }

    
    
    
    
    /**
     *
     * Metodo Serialize Locais para o ficheiro dos Locais
     *
     * @param filename Recebe um ficheiro e insere a lista de Locais
     */
    public void serializeLocais(String filename) {
        try {
            FileOutputStream fout = new FileOutputStream(filename);
            ObjectOutputStream stream = new ObjectOutputStream(fout);
            stream.writeObject(listaLocaisToString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void deserializeLocais(String filename) {
//        try {
//            FileInputStream fin = new FileInputStream(filename);
//            ObjectInputStream stream = new ObjectInputStream(fin);
//            this.list = (ArrayList) stream.readObject();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
    /**
     * Metodo Serialize Ligacoes para o ficheiro Ligacoes
     *
     * @param filename Recebe um ficheiro e insere a lista de Ligacoes
     */
    public void serializeLigacoes(String filename) {
        try {
            FileOutputStream fout = new FileOutputStream(filename);
            ObjectOutputStream stream = new ObjectOutputStream(fout);
            stream.writeObject(listaLigacoesToString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo que calcula a solucao conforme o estrategiaDificuldade. Recebe o
     * vertice de entrada e o vertice de saida (not implemented)
     *
     * @param verticeIN verticein
     * @param verticeOUT verticeout
     * @return solucao
     */
    @Override
    public int calcularSolucao(iVertex<Local> verticeIN, iVertex<Local> verticeOUT) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
