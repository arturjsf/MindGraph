/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxgraphs.tad;

/**
 *
 * @author patricia.macedo
 */
public interface iVertex<V> {
    public V element()throws InvalidEdgeException;

    public int getDistance();
}
