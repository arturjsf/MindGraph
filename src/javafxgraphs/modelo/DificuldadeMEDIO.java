/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxgraphs.modelo;

import java.util.Random;

/**
 * Estrategia de dificuldade MEDIO
 * @author Artur Ferreira
 */
public class DificuldadeMEDIO implements iEstrategiaDificuldade {

    
    Random xRandom = new Random();
    
      int minVertices;
      int maxVertices;
      
      
      
    /**
     * @return A distancia é um valor random entre 10 e 100
     */
    @Override
    public int randomVertices() {

        minVertices = 6;
        maxVertices = 8;

        int numVertices = xRandom.nextInt((maxVertices - minVertices) + 1) + minVertices;

        return numVertices;
    }

    /**
     * 
     * @return A distancia é um valor random entre 10 e 100 
     */
    @Override
    public int randomCusto() {

        int minCusto = 10;
        int maxCusto = 100;

        int nCusto = xRandom.nextInt((maxCusto - minCusto) + 1) + minCusto;

        return nCusto;

    }

    /**
     * 
     * @return A distancia é um valor random entre 10 e 100
     */
    @Override
    public int randomDistancia() {
        int minDistancia = 10;
        int maxdistancia = 100;

        int nDistancia = xRandom.nextInt((maxdistancia - minDistancia) + 1) + minDistancia;

        return nDistancia;

    }

    /**
     * 
     * @return tipo de ligacao T2
     */
    @Override
    public TipoLigacao randomTipo() {
       return TipoLigacao.T2;
    }
    
    /**
     * 
     * @return um valor de arestas a serem geradas
     */
    @Override
     public int randomArestas(){
         
        int minArestas = maxVertices;
        int maxArestas = maxVertices+(maxVertices/2);

        int numArestas = xRandom.nextInt((maxArestas - minArestas) + 1) + minArestas;

        return numArestas;
    }

}
