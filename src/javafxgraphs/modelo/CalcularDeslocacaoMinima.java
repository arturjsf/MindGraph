/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxgraphs.modelo;

import javafxgraphs.tad.iEdge;

/**
 * Estrategia CalcularDeslocacaoMinima
 * @author Artur Ferreira
 */
public class CalcularDeslocacaoMinima implements iEstrategiaSolucao<Ligacao, Local>{

    /**
     * (not implemented)
     * @param edge aresta
     * @return 1-iteracao
     */
     @Override
    public int calcularSolucao(iEdge<Ligacao, Local> edge){
        return 1;
    }
}
