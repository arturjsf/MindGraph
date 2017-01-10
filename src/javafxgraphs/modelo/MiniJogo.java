/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxgraphs.modelo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import static java.util.Collections.list;
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

    public iEstrategiaDificuldade nivel;

//    public iVertex<Local> verticeOrigem;
//    public iVertex<Local> verticeDestino;
    /**
     * Cosntrutor do MiniJogo
     *
     * @param nivel Cria um miniJogo random consoante o nivel
     */
    public MiniJogo(iEstrategiaDificuldade nivel) {
        grafoAdaptee = new MyGraph<>();
        this.nivel = nivel;
        criarGrafo(nivel);
    }

    /**
     * Este metodo cria o grafo.
     *
     * @param nivel Recebe a dificuldade e faz um random para o numero de
     * vertices e arestas
     */
    public void criarGrafo(iEstrategiaDificuldade nivel) {

        //gera e cria uma lista de vertices random consoante o nivel
        gerarLocal(nivel.randomVertices());

        //gera e cria uma ligacao e atribui de maneira aleatoria os vertices in e out
        gerarLigacao(nivel);

    }

    /**
     * gera uma ligacao random enquanto houver numArestas a serem criadas
     *
     * @param nivel Consoante o nivel a receber, cria uma Ligacao com valores
     * random
     */
    public void gerarLigacao(iEstrategiaDificuldade nivel) {

        for (int i = 0; i < nivel.randomArestas(); i++) {
            //Percorre a lista de Vertices criados e faz um random
            //  for (iVertex<Local> listaVertices : grafoAdaptee.vertices()) {
            //  só cria o destino se for diferente da origem
            //  Local origemTemp = new Local("" + randomVertices());
            //  Local destinoTemp = new Local("" + randomVertices());

            Local[] listaLocais = randomVertices2();

            Local origemTemp = listaLocais[0];
            Local destinoTemp = listaLocais[1];

            Ligacao ligacaoTemp = new Ligacao(nivel.randomTipo(), origemTemp.getId() + destinoTemp.getId(), nivel.randomDistancia(), nivel.randomCusto());

            //Só insere se a origem e destino forem diferentes
            // if (!origemTemp.toString().equals(destinoTemp.toString())) {
            grafoAdaptee.insertEdge(origemTemp, destinoTemp, ligacaoTemp);
           // System.out.println(ligacaoTemp.toString());
            //  }

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
     * @return Devolve uma estrategia nivel
     */
    public iEstrategiaDificuldade getNivel() {
        return nivel;
    }

//    public iVertex<Local> getVerticeOrigem() {
//        return verticeOrigem;
//    }
//
//    public iVertex<Local> getVerticeDestino() {
//        return verticeDestino;
//    }
    /**
     *
     *
     * @return Devolve um grafo
     */
    public MyGraph<Local, Ligacao> getGrafoAdaptee() {
        return grafoAdaptee;
    }

    /**
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
     * Metodo que calcula a solucao conforme o nivel. Recebe o vertice de
     * entrada e o vertice de saida (not implemented)
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
