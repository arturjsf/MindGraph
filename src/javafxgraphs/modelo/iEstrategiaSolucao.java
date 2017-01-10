/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxgraphs.modelo;

import javafxgraphs.tad.iEdge;

/**
 *
 * @author Artur Ferreira
 */
public interface iEstrategiaSolucao<E, V> {

    /**
     * vai calcular a solucao conforme a estrategia. Devolve em inteiro:
     *
     * @param edge aresta
     * @return int
     */

    public int calcularSolucao(iEdge<E, V> edge);

}
