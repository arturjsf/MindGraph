/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxgraphs.modelo;

/**
 *
 * @author Artur Ferreira
 */
public interface iEstrategiaDificuldade {
    
    public int randomVertices();
    public int randomCusto();
    public int randomDistancia();
    public TipoLigacao randomTipo();
    public int randomArestas();
    
}
