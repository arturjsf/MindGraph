/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxgraphs.modelo;

import javafxgraphs.tad.iEdge;

/**
 * Interface Solucao. Todas as classes deste tipo terão de implementar o metodo calcularValorMinimo
 * @author Artur Ferreira
 */
public interface iEstrategiaSolucao<E, V> {

    
    /**
     * Dependendo da estrategia de calculo, devolve um inteiro com o valor a calcular.
     * @param edge aresta
     * @return int
     */
    public int calcularValorMinimo(iEdge<E, V> edge);

}
