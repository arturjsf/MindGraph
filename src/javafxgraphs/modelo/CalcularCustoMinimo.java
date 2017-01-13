/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxgraphs.modelo;

import javafxgraphs.tad.iEdge;

/**
 * Estrategia CalcularCustoMinimo
 * @author Artur Ferreira
 */
public class CalcularCustoMinimo implements iEstrategiaSolucao<Ligacao, Local> {

/**
 * Devolve o custo atraves do getCusto
 * @param edge aresta
 * @return getCusto
 */
    @Override
    public int calcularValorMinimo(iEdge<Ligacao, Local> edge){
        return edge.element().getCusto();
    }

}
