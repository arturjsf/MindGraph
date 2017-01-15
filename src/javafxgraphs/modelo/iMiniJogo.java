/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxgraphs.modelo;

import javafxgraphs.tad.iVertex;

/**
 *
 * @author Artur Ferreira
 */
public interface iMiniJogo {

   
public void criarMiniJogo(iEstrategiaDificuldade estrategiaDificuldade, iEstrategiaSolucao estrategiaSolucao);
public void criarGrafo(iEstrategiaDificuldade nivel);
public iEstrategiaSolucao escolherSolucao(TipoSolucao tipoSolucao);
public TipoSolucao randomSolucao();
   

}
