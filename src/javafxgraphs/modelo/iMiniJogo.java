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

    /**
     * para calcular a solucao otima o metodo recebe o verticein e out
     *
     * @param verticeIN verticein
     * @param verticeOUT verticeout
     * @return int com a solucao
     */
    public int calcularSolucao(iVertex<Local> verticeIN, iVertex<Local> verticeOUT);

    /**
     *
     *
     *
     * @return devolve uma String com o caminho desde o verticeIN até ao
     * verticeOUT. dependendo da solucao escolhida
     */
    public String mostrarCaminho();

}
