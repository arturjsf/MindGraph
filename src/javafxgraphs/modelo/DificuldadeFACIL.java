/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxgraphs.modelo;

import java.util.Random;

/**
 * Estrategia de dificuldade FACIL
 *
 * @author Artur Ferreira
 */
public class DificuldadeFACIL implements iEstrategiaDificuldade {

    Random xRandom = new Random();
    
     int minVertices;
     int maxVertices;

     
    /**
     *
     * @return numero de vertices a ser gerado no nivel FACIL
     * É um valor random entre 2 e 5. 
     * Para haver uma ligação é necessário pelo menos 2 vertices
     */
    @Override
    public int randomVertices() {

        minVertices = 2;
        maxVertices = 5;

        int numVertices = xRandom.nextInt((maxVertices - minVertices) + 1) + minVertices;

        return numVertices;
    }

    /**
     *
     * @return O custo é um random entre 1 e 10
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
     * @return A distancia é um random entre 1 e 10
     */
    @Override
    public int randomDistancia() {

        int minDistancia = 1;
        int maxdistancia = 10;

        int nDistancia = xRandom.nextInt((maxdistancia - minDistancia) + 1) + minDistancia;

        return nDistancia;

    }

    /**
     *
     *
     * @return tipo de ligacao T1
     */
    @Override
    public TipoLigacao randomTipo() {
        return TipoLigacao.T1;
    }

    /**
     *
     * @return um valor de arestas a serem geradas
     */
    @Override
    public int randomArestas() {
        
        int minArestas = maxVertices;
        int maxArestas = maxVertices+(maxVertices/2);

        int numArestas = xRandom.nextInt((maxArestas - minArestas) + 1) + minArestas;

        return numArestas;
    }

}
