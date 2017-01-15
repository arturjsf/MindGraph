/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxgraphs.modelo;

import javafxgraphs.ui.DrawableGraphElement;

/**
 * Just to represent base information on the example.
 * 
 * @author brunomnsilva
 */
public class DummyType implements DrawableGraphElement {
    
    private final String id;
    boolean selected;

    public DummyType(String id) {
        this.id = id;
        this.selected = false;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public boolean isSelected() {
        return this.selected;
    }
    
    public void toggleSelect() {
        this.selected = !this.selected;
    }

    @Override
    public String toString() {
        return String.format("{%s}", this.id);
    }
    
    
    
}
