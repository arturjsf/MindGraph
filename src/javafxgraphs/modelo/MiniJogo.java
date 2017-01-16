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
 * É a classe principal. É tudo criado a partir daqui.
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

    int estrelas;

    /**
     * Construtor do MiniJogo
     *
     * @param modo modo de jogo
     * @param jogador jogador
     * @param nivel Cria um miniJogo random consoante o modo de Jogo
     */
    public MiniJogo(TipoJogo modo, Jogador jogador, int nivel) {

        this.jogador = jogador;

        switch (modo) {

            case ARCADE:
                MiniJogoArcade(jogador, nivel, estrelas);
                break;
            case TIMETRIAL:
                MiniJogoTT(jogador, nivel, segundos);
                break;
            default:
                System.out.println("sem modo");
        }
    }

    /**
     * construtor do tipo de jogo ARCADE
     *
     * @param jogador jogador
     * @param nivel nivel do minijogo(0-20)
     * @param estrelas um minijogo arcade contem estrelas (0-3)
     */
    public void MiniJogoArcade(Jogador jogador, int nivel, int estrelas) {

        this.jogador = jogador;
        this.estrelas = estrelas;

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
     * Construtor do tipo de jogo TIMETRIAL
     *
     * @param jogador jogador
     * @param nivel nivel do minijogo(0-20)
     * @param segundos cada minijogo Time trial contem segundos(120-60)
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
     * Recebe um enumerado e indica a sua estrategia (FACIL, MEDIO, DIFICIL)
     *
     * @param dificuldade enumerado
     * @return estrategia da dificuldade
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

    /**
     * Recebe um enumerado e indica a sua solucao (CUSTO, DISTANCIA, MOVIMENTOS)
     *
     * @param tipoSolucao TipoSolucao
     * @return estrategia de solucao
     */
    @Override
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
     * Recebe uma estrategia de dificuldade e de solucao e cria o grafo
     *
     * @param estrategiaDificuldade estrategiaDificuldade
     * @param estrategiaSolucao estrategiaSolucao
     */
    @Override
    public void criarMiniJogo(iEstrategiaDificuldade estrategiaDificuldade, iEstrategiaSolucao estrategiaSolucao) {

        grafoAdaptee = new MyGraph<>();
        this.estrategiaDificuldade = estrategiaDificuldade;
        this.estrategiaSolucao = estrategiaSolucao;
        criarGrafo(estrategiaDificuldade);
    }

    /**
     * Este metodo cria o grafo consoante o nivel de dificuldade
     *
     * @param nivel Recebe nivel de dificuldade
     */
    @Override
    public void criarGrafo(iEstrategiaDificuldade nivel) {

        //gera e cria uma lista de vertices random consoante o estrategiaDificuldade
        gerarLocal(nivel.randomVertices());

        //gera e cria uma ligacao e atribui de maneira aleatoria os vertices in e out
        gerarLigacao(nivel);
        
        serializeLocais("fileListaLocais.txt");
        serializeLigacoes("fileListaLigacoes.txt");

    }

    /**
     * Faz um random do ENUM TipoSolucao
     *
     * @return tipo de solucao
     */
    @Override
    public TipoSolucao randomSolucao() {

        TipoSolucao[] rdSolucoes = TipoSolucao.values();
        Random rdn = new Random();

        TipoSolucao tipoAux;
        tipoAux = rdSolucoes[rdn.nextInt(rdSolucoes.length)];

        return tipoAux;
    }

    /**
     * Gera uma ligacao random, consoante o nivel, enquanto houver numArestas a
     * serem criadas. Para criar uma Ligacao e necessário 2 Locais
     *
     * @param nivel nivel de dificuldade
     */
    public void gerarLigacao2(iEstrategiaDificuldade nivel) {

        for (int i = 0; i < nivel.randomArestas(); i++) {

            //Gera 2 vertices diferentes in e out
            Local[] listaLocais = randomVertices2();

            Local origemTemp = listaLocais[0];
            Local destinoTemp = listaLocais[1];

            Ligacao ligacaoTemp = new Ligacao(nivel.randomTipo(), origemTemp.getId() + destinoTemp.getId(), nivel.randomDistancia(), nivel.randomCusto());

            grafoAdaptee.insertEdge(origemTemp, destinoTemp, ligacaoTemp);
        }
    }

    /**
     * Gera uma ligacao random, consoante o nivel, enquanto houver listaVertices
     * Este metodo é uma versão melhorada do metodo acima. Todos os vertices
     * estão conectados
     *
     * @param nivel nivel de dificuldade
     */
    public void gerarLigacao(iEstrategiaDificuldade nivel) {

        for (iVertex<Local> listaVertices : grafoAdaptee.vertices()) {
            Local temp = listaVertices.element();
            Local[] listaLocais = randomVertices2();
            if (!temp.equals(listaLocais[0])) {
                listaLocais[1] = temp;
            }

            Ligacao ligacaoTemp = new Ligacao(nivel.randomTipo(), listaLocais[0].getId() + listaLocais[1].getId(), nivel.randomDistancia(), nivel.randomCusto());
            grafoAdaptee.insertEdge(listaLocais[0], listaLocais[1], ligacaoTemp);

        }
    }

    /**
     * Recebe uma lista de vertices e percorre a devolvendo um local random a
     * ser inserido
     *
     * @return Local random
     */
    public Local randomVertices() {

        //copia para uma arraylist e adiciona cada elemento da listaVertices
        ArrayList<iVertex<Local>> locaisAux = new ArrayList<>();
        for (iVertex<Local> listaVertices : grafoAdaptee.vertices()) {
            locaisAux.add(listaVertices);
        }

        Collections.shuffle(locaisAux);

        Local localAux = locaisAux.get(0).element();

        return localAux;
    }

    /**
     *
     * @return Devolve um array de Locais de 2 posicoes random. A posicao 0 irá
     * servir para o verticeIN e a posicao 1 para o verticeOUT. São sempre
     * vertices diferentes
     */
    public Local[] randomVertices2() {

        //copia para uma arraylist e adiciona cada elemento da listaVertices
        ArrayList<iVertex<Local>> locaisAux = new ArrayList<>();
        for (iVertex<Local> listaVertices : grafoAdaptee.vertices()) {
            locaisAux.add(listaVertices);
        }

        Collections.shuffle(locaisAux);

        Local localAux[] = new Local[2];

        localAux[0] = locaisAux.get(0).element();
        localAux[1] = locaisAux.get(1).element();

        return localAux;
    }

    /**
     * Gera um Local através do numero de vertices recebido
     *
     * @param numVertices Recebe um numVertices e atribui uma letra a cada Local
     *
     */
    public void gerarLocal(int numVertices) {

        CharSequence alphabet = "ABCDEFGHIJKLMNOPQRSTUVYWXZ";

        /**
         * cria os Locais e atribui uma letra do alfabeto a cada numVertice
         */
        for (int i = 0; i < numVertices; i++) {
            Local localTemp = new Local(alphabet.charAt(i) + "");
            adicionarLocal(localTemp);
        }
    }

    /**
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
     * @param id id a procurar na listaVertices
     * @return Se encontrar o vertice atraves do id devolve-o
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
     *
     * *********************GETS E SETS********************
     */
    /**
     * @return Devolve um grafo
     */
    public MyGraph<Local, Ligacao> getGrafoAdaptee() {
        return grafoAdaptee;
    }

    /**
     *
     * @return jogador
     */
    public Jogador getJogador() {
        return jogador;
    }

    /**
     *
     * @param jogador jogador a alterar
     */
    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    /**
     *
     * @return estrategia de dificuldade
     */
    public iEstrategiaDificuldade getEstrategiaDificuldade() {
        return estrategiaDificuldade;
    }

    /**
     *
     * @param estrategiaDificuldade estrategiaDificuldade
     */
    public void setEstrategiaDificuldade(iEstrategiaDificuldade estrategiaDificuldade) {
        this.estrategiaDificuldade = estrategiaDificuldade;
    }

    /**
     *
     * @return segundos do minijogo
     */
    public int getSegundos() {
        return segundos;
    }

    /**
     *
     * @param segundos segundos a alterar
     */
    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }

    /**
     *
     * @return nivel do minijogo
     */
    public int getNivel() {
        return nivel;
    }

    /**
     *
     * @param nivel nivel a alterar
     */
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    /**
     *
     * @return modo de jogo
     */
    public TipoJogo getTipoJogo() {
        return tipoJogo;
    }

    /**
     *
     * @param tipoJogo tipo de jogo a alterar
     */
    public void setTipoJogo(TipoJogo tipoJogo) {
        this.tipoJogo = tipoJogo;
    }

    /**
     *
     * @return dificuldade do minijogo
     */
    public Dificuldade getDificuldade() {
        return dificuldade;
    }

    /**
     *
     * @param dificuldade dificuldade a alterar
     */
    public void setDificuldade(Dificuldade dificuldade) {
        this.dificuldade = dificuldade;
    }

    /**
     *
     * @return estrategia solucao
     */
    public iEstrategiaSolucao getEstrategiaSolucao() {
        return estrategiaSolucao;
    }

    /**
     *
     * @param estrategiaSolucao estrategia solucao a alterar
     */
    public void setEstrategiaSolucao(iEstrategiaSolucao estrategiaSolucao) {
        this.estrategiaSolucao = estrategiaSolucao;
    }

    /**
     *
     * @return tipo solucao
     */
    public TipoSolucao getTipoSolucao() {
        return tipoSolucao;
    }

    /**
     *
     * @param tipoSolucao tipo de solucao a alterar
     */
    public void setTipoSolucao(TipoSolucao tipoSolucao) {
        this.tipoSolucao = tipoSolucao;
    }

    /**
     *
     * @return numero de estrelas
     */
    public int getEstrelas() {
        return estrelas;
    }

    /**
     *
     * @param estrelas numero de estrelas a alterar
     */
    public void setEstrelas(int estrelas) {
        this.estrelas = estrelas;
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

}
