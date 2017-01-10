/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxgraphs.modelo;

import java.util.Random;

/**
 * Estrategia de dificuldade NIVEL1. Cada dificuldade tem a sua maneira de criar o jogo
 *
 * @author Artur Ferreira
 */
public class DificuldadeFACIL implements iEstrategiaDificuldade {

    Random xRandom = new Random();

    /**
     *
     * @return numero de vertices a ser gerado no nivel1 É um valor random entre
     * 2 e 5. Para haver uma ligação é necessário pelo menos 2 vertices
     */
    @Override
    public int randomVertices() {

        int minVertices = 2;
        int maxVertices = 5;

        int numVertices = xRandom.nextInt((maxVertices - minVertices) + 1) + minVertices;

        return numVertices;
    }

    /**
     *
     * @return O custo é um random entre 10 e 100 no nivel 1
     */
    @Override
    public int randomCusto() {

        int minCusto = 1;
        int maxCusto = 10;

        int nCusto = xRandom.nextInt((maxCusto - minCusto) + 1) + minCusto;

        return nCusto;

    }

    /**
     *
     * @return A distancia é um random entre 1 e 8 no nivel 1
     */
    @Override
    public int randomDistancia() {

        int minDistancia = 1;
        int maxdistancia = 100;

        int nDistancia = xRandom.nextInt((maxdistancia - minDistancia) + 1) + minDistancia;

        return nDistancia;

    }

    /**
     *
     *
     * @return Devolve um tipo consoante o nivel. Neste caso T1
     */
    @Override
    public TipoLigacao randomTipo() {

        //  return TipoLigacao.valueOf("T" + (xRandom.nextInt(2) + 1));
        return TipoLigacao.T1;
    }

    /**
     * O numero de arestas a criar tambem é random conforme a dificuldade Para
     * haver um grafo tem de haver pelo menos 1 aresta
     *
     * @return numArestas
     */
    @Override
    public int randomArestas() {
        int minArestas = 2;
        int maxArestas = 8;

        int numArestas = xRandom.nextInt((maxArestas - minArestas) + 1) + minArestas;

        return numArestas;
    }

}
