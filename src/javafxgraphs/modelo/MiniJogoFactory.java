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
public abstract class MiniJogoFactory{

    
    
    public MiniJogoFactory() {
        System.out.println("Fabrica geral de mini jogos");
    }

    
    public abstract iMiniJogo buildMiniJogo(String modo);
       
}
