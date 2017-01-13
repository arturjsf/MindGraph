/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxgraphs.modelo;

import javafxgraphs.tad.iEdge;

/**
 * Estrategia CalcularDistanciaMinima
 * @author Artur Ferreira
 */
public class CalcularDistanciaMinima implements iEstrategiaSolucao<Ligacao, Local> {

    /**
     * Devolve a distancia atraves do getDistancia
     *
     * @param edge aresta
     * @return getDistancia
     */
    @Override
    public int calcularValorMinimo(iEdge<Ligacao, Local> edge) {
        return edge.element().getDistancia();
    }
}
