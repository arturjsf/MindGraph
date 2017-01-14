/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxgraphs.modelo;

import java.util.Random;

/**
 * Estrategia de dificuldade NIVEL3
 * @author Artur Ferreira
 */
public class DificuldadeDIFICIL implements iEstrategiaDificuldade{
    
     Random xRandom = new Random();
     
      int minVertices;
        int maxVertices;

    /**
     * 
     * @return numero de vertices a ser gerado no nivel1
     * É um valor random entre 2 e 5
     * Para haver uma ligação é necessário pelo menos 2 vertices
     */
    @Override
    public int randomVertices() {

        minVertices = 9;
        maxVertices = 12;

        int numVertices = xRandom.nextInt((maxVertices - minVertices) + 1) + minVertices;

        return numVertices;
    }
    
    //a distancia é um random entre 10  e 100 no nivel 1
    
    //o custo é um random entre 1 e 10 no nivel 1
    @Override
    public int randomCusto(){
        
   
        int minCusto = 100;
        int maxCusto = 1000;

        int nCusto = xRandom.nextInt((maxCusto - minCusto) + 1) + minCusto;

        return nCusto;
        
    }
    
   
    @Override
     public int randomDistancia(){
         int minDistancia = 100;
        int maxdistancia = 1000;

        int nDistancia = xRandom.nextInt((maxdistancia - minDistancia) + 1) + minDistancia;

        return nDistancia;
        
    }
    
    @Override
    public TipoLigacao randomTipo(){
        
       // return TipoLigacao.valueOf("T" + (xRandom.nextInt(6)+1));
       return TipoLigacao.T3;
    }
    
    
     @Override
     public int randomArestas(){
         
        int minArestas = maxVertices;
        int maxArestas = maxVertices+(maxVertices/2);

        int numArestas = xRandom.nextInt((maxArestas - minArestas) + 1) + minArestas;

        return numArestas;
    }
    
    
}
