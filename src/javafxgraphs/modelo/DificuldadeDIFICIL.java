/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxgraphs.modelo;

import java.util.Random;

/**
 * Estrategia de dificuldade DIFICIL
 * @author Artur Ferreira
 */
public class DificuldadeDIFICIL implements iEstrategiaDificuldade{
    
     Random xRandom = new Random();
     
        int minVertices;
        int maxVertices;

        
    /**
     * 
     * @return numero de vertices a ser gerado no nivel DIFICIL
     * É um valor random entre 9 e 12
     */
    @Override
    public int randomVertices() {

        minVertices = 9;
        maxVertices = 12;

        int numVertices = xRandom.nextInt((maxVertices - minVertices) + 1) + minVertices;

        return numVertices;
    }
    
    
    /**
     * 
     * @return um custo random entre 100 e 1000
     */
    @Override
    public int randomCusto(){
        
   
        int minCusto = 100;
        int maxCusto = 1000;

        int nCusto = xRandom.nextInt((maxCusto - minCusto) + 1) + minCusto;

        return nCusto;
        
    }
    
   
    /**
     * 
     * @return uma distancia random entre 100 e 1000
     */
    @Override
     public int randomDistancia(){
         int minDistancia = 100;
        int maxdistancia = 1000;

        int nDistancia = xRandom.nextInt((maxdistancia - minDistancia) + 1) + minDistancia;

        return nDistancia;
        
    }
    
     /**
      * 
      * @return tipo de ligacao T3
      */
    @Override
    public TipoLigacao randomTipo(){
       return TipoLigacao.T3;
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
