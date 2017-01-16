/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxgraphs.modelo;

import javafxgraphs.ui.DrawableGraphElement;

/**
 * Classe Local. É o nosso vertice
 * É igual ao dummytype
 * @author Artur Ferreira
 *
 */

public class Local implements DrawableGraphElement {

    public String id;
    boolean selected;

    /**
     * Um local só tem um id e um boolean
     *
     * @param id idLocal
     */
    public Local(String id) {
        this.id = id;
        this.selected = false;
    }

    /**
     * 
     * @return id do Local
     */
    @Override
    public String getId() {
        return this.id;
    }

    /**
     * Alterar o nome do local
     * @param id id a alterar
     */
    public void setId(String id) {
        this.id = id;
    }
    
    
    /**
     * 
     * @return boolean
     */
    @Override
    public boolean isSelected() {
        return this.selected;
    }

    /**
     * 
     */
    public void toggleSelect() {
        this.selected = !this.selected;
    }

    /**
     * 
     * @return id do Local
     */
    @Override
    public String toString() {
        return String.format(this.id);
    }

}
