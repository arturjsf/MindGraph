/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxgraphs.tad;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author patricia.macedo
 */
public class MyGraph<V, E> implements iGraph<V, E> {

    private int nEdges;
    private HashMap<V, iVertex<V>> listVertices;

    public MyGraph() {
        this.nEdges = 0;
        listVertices = new HashMap();
    }

    private MyVertex checkVertex(iVertex<V> p) throws InvalidVertexException {
        if (p == null || !this.listVertices.values().contains(p)) {
            throw new InvalidVertexException("WRONG vertex");
        }
        try {
            return (MyVertex) p;
        } catch (ClassCastException e) {
            throw new InvalidVertexException("WRONG vertex");
        }
    }

    private MyEdge checkEdge(iEdge<E, V> ed) throws InvalidEdgeException {
        if (ed == null || !existEdge(ed)) {
            throw new InvalidEdgeException("WRONG edge");
        }
        try {
            return (MyEdge) ed;
        } catch (ClassCastException e) {
            throw new InvalidEdgeException("WRONG edge");
        }
    }

    @Override
    public int numVertices() {
        return listVertices.size();

    }

    @Override
    public int numEdges() {
        return nEdges;
    }

    @Override
    public Iterable<iVertex<V>> vertices() {
        return listVertices.values();

    }

    @Override
    public Iterable<iEdge<E, V>> edges() {
        return setofEdges();
    }

    private Set<iEdge<E, V>> setofEdges() {
        Set<iEdge<E, V>> edges = new HashSet();
        for (iVertex<V> vertex : vertices()) {

            edges.addAll(checkVertex(vertex).listaEdges);
        }
        return edges;
    }

    @Override
    public V replace(iVertex<V> p, V elem) throws InvalidVertexException {
        MyVertex vertex = checkVertex(p);
        V elem1 = vertex.element();
        vertex.elem = elem;
        return elem1;
    }

    @Override
    public E replace(iEdge<E, V> ed, E elem) throws InvalidEdgeException {
        MyEdge edge = checkEdge(ed);
        E elem1 = edge.element();
        edge.elem = elem;
        return elem1;
    }

    private boolean existEdge(iEdge e) {
        return setofEdges().contains(e);
    }

    @Override
    public Iterable<iEdge<E, V>> incidentEdges(iVertex<V> v) throws InvalidEdgeException {
           return checkVertex(v).listaEdges;
    }

    
    @Override
    public iVertex<V> opposite(iVertex<V> v, iEdge<E, V> e) throws InvalidVertexException, InvalidEdgeException {
        iVertex<V>[] vertices = checkEdge(e).vertices();
        if (vertices[0] == v) {
            return vertices[1];
        }
        if (vertices[1] == v) {
            return vertices[0];
        }
        throw new InvalidVertexException("Invalid vertex");
    }

    @Override
    public boolean areAdjacent(iVertex<V> u, iVertex<V> v) throws InvalidVertexException {
        for (iEdge<E, V> e : edges()) {
            MyEdge myedge = checkEdge(e);
            if (myedge.vertexIn == u && myedge.vertexOut == v
                    || myedge.vertexOut == u && myedge.vertexIn == v) {
                return true;
            }
        }
        return false;
    }

    @Override
    public iVertex<V> insertVertex(V elem) throws InvalidVertexException {
        if (listVertices.containsKey(elem)) {
            throw new InvalidVertexException(elem + "already exists ");
        }
        MyVertex vertex = new MyVertex(elem);
        listVertices.put(elem, vertex);
        return vertex;

    }

    @Override
    public iEdge<E, V> insertEdge(iVertex<V> u, iVertex<V> v, E elem) throws InvalidVertexException {
             
        MyEdge edge = new MyEdge(elem, u, v);
        // coloca-lo nos vertices.
        checkVertex(u).listaEdges.add(edge);
        checkVertex(v).listaEdges.add(edge);
        return edge;
    }

    @Override
    public iEdge<E, V> insertEdge(V elem1, V elem2, E o) throws InvalidVertexException {
        if ((!listVertices.containsKey(elem1)) || (!listVertices.containsKey(elem2))) {
            throw new InvalidVertexException("Invalid Vertex");
        }

        iVertex<V> v1 = listVertices.get(elem1);
        iVertex<V> v2 = listVertices.get(elem2);
        MyEdge e = new MyEdge(o, v1, v2);
        checkVertex(v1).listaEdges.add(e);
        checkVertex(v2).listaEdges.add(e);
        nEdges++;
        return e;
    }

    @Override
    public V removeVertex(iVertex<V> v) throws InvalidVertexException {
        MyVertex vertex = checkVertex(v);
        if (!vertex.listaEdges.isEmpty()) {
            throw new InvalidVertexException(" vertex has incident edges");
        }
        listVertices.remove(v);
        return v.element();

    }

    @Override
    public E removeEdge(iEdge<E, V> e) throws InvalidEdgeException {
     
        iVertex<V>[] vertices = checkEdge(e).vertices();
        checkVertex(vertices[0]).listaEdges.remove(e);
        checkVertex(vertices[1]).listaEdges.remove(e);
        nEdges--;
        return e.element();

    }

    
    
    private class MyVertex implements iVertex<V> {

        private V elem;
        private List<iEdge<E, V>> listaEdges;

        public MyVertex(V elem) {
            this.elem = elem;
            this.listaEdges = new ArrayList<>();
        }

        @Override
        public V element() throws InvalidVertexException {
            if (elem == null) {
                throw new InvalidVertexException("vertex null");
            }
            return elem;
        }

    }

    private class MyEdge implements iEdge<E, V> {

        private E elem;
        private iVertex<V> vertexIn, vertexOut;

        public MyEdge(E elem, iVertex<V> vertexIn, iVertex<V> vertexOut) {
            this.elem = elem;
            this.vertexIn = vertexIn;
            this.vertexOut = vertexOut;
        }

        @Override
        public E element() throws InvalidEdgeException {
            if (elem == null) {
                throw new InvalidEdgeException("edge null");
            }
            return elem;
        }

        @Override
        public iVertex<V>[] vertices() {
            iVertex[] vertices= new iVertex[2];
            vertices[0]=vertexIn;
            vertices[1]=vertexOut;
            return vertices;      
        }

    }

}
