/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxgraphs.tad;

/**
 *
 * @author PM-Uninova
 * @param <V>
 * @param <E>
 */
public interface iGraph<V, E> {

    /**
     * Returns the number of vertices of the graph
     * @return vertex count
     */
    public int numVertices();

    /**
     * Returns the number of edges of the graph     *
     * @return edge count
     */
    public int numEdges();

    /**
     * Returns the vertices of the graph as an iterable collection.
     * @return vertex iterable collection
     */
    public Iterable<iVertex<V>> vertices();

    /**
     * Returns the edges of the graph as an iterable collection     *
     * @return edge iterable collection
     */
    public Iterable<iEdge<E, V>> edges();

    /**
     * Replaces the element of a given vertex with a new element and returns the
     * old element
     *
     * @param v the vertex
     * @param o new element to store in vertex
     * @return old element stored in vertex
     * @throws InvalidVertexException if vertex does not exist
     */
    public V replace(iVertex<V> v, V o) throws InvalidVertexException;

    /**
     * Replaces the element of a given edge with a new element and returns the
     * old element.
     *
     * @param e e
     * @param o o
     * @return old element stored in edge
     * @throws InvalidEdgeException if edge does not exist
     */
    public E replace(iEdge<E, V> e, E o) throws InvalidEdgeException;

    /**
     * Returns the edges incident on a vertex as an iterable collection
     *
     * @param v the vertex
     * @return incident edge iterable collection
     * @throws InvalidEdgeException
     */
    public Iterable<iEdge<E, V>> incidentEdges(iVertex<V> v)
            throws InvalidEdgeException;

    /**
     * Returns opposite the opposite vertex.
     * @param v the vertex
     * @param e the edge
     * @return opposite vertex
     * @throws InvalidVertexException if vertex does not exits
     * @throws InvalidEdgeException if edge does not exist
     */
    public iVertex<V> opposite(iVertex<V> v, iEdge<E, V> e)
            throws InvalidVertexException, InvalidEdgeException;

    /**
     * Tests whether two vertices are adjacent, i.e., connected by edge.
     *
     * @param u first vertex
     * @param v second vertex
     * @return existence of an edge
     * @throws InvalidVertexException if one the the vertices is invalid
     */
    public boolean areAdjacent(iVertex<V> u, iVertex<V> v)
            throws InvalidVertexException;

    /**
     * Inserts and return a new vertex with a given element.
     *
     * @param e the element to store at the vertex
     * @return the newly created vertex
     */
    public iVertex<V> insertVertex(V e);

    /**
     * Inserts and return a new edge with a given element between two vertices.
     * Whether it accepts more than one edge is up to the concrete implementation.
     *
     * @param u first vertex
     * @param v second vertex
     * @param o element to store at edge
     * @return the newly created edge
     * @throws InvalidVertexException if one of the vertices is invalid
     */
    public iEdge<E, V> insertEdge(iVertex<V> u, iVertex<V> v, E o)
            throws InvalidVertexException;

    /**
     * Inserts an edge with an element between two stored elements (at vertices). Finding the
     * corresponding vertices is up to the equality between stored objects.
     * @param elem1 first element
     * @param elem2 second element
     * @param o element to store at edge
     * @return the newly created edge
     * @throws InvalidVertexException if vertices elements are not found
     */
    public iEdge<E, V> insertEdge(V elem1, V elem2, E o)
            throws InvalidVertexException;

    /**
     * Removes a vertex and all its incident edges and returns the element
     * stored at the removed vertex
     *
     * @param v the vertex
     * @return stored element at removed vertex
     * @throws InvalidVertexException if vertex does not exist
     */
    public V removeVertex(iVertex<V> v) throws InvalidVertexException;

    /**
     * Removes an edge and return its element.
     *
     * @param e the edge
     * @return stored element at removed edge
     * @throws InvalidEdgeException if edge does not exist
     */
    public E removeEdge(iEdge<E, V> e) throws InvalidEdgeException;

}
