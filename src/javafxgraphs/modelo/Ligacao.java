/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxgraphs.modelo;

import javafxgraphs.ui.DrawableGraphElement;

/**
 * Classe Ligacao. É a nossa aresta
 * Tudo o que é necessário na classe Ligacao. nome, distancia, custo e tipo
 * @author Artur Ferreira
 */
public class Ligacao implements DrawableGraphElement {

    public String nomeLigacao;
    public int distancia;
    public int custo;
    public TipoLigacao tipo;

    /**
     * Variaveis obrigatorias pelo Drawable
     */
    private String id;
    boolean selected;

    /**
     * Construtor. Uma Ligacao contem um nome, custo, distancia e tipo
     *
     * @param tipo tipoLigacao
     * @param nomeLigacao nome
     * @param distancia distancia
     * @param custo custo
     */
    public Ligacao(TipoLigacao tipo, String nomeLigacao, int distancia, int custo) {
        this.tipo = tipo;
        this.nomeLigacao = nomeLigacao;
        this.distancia = distancia;
        this.custo = custo;
    }

    /**
     *
     * @return tipo
     */
    public TipoLigacao getTipo() {
        return tipo;
    }

    /**
     *
     * @return nome
     */
    public String getNomeLigacao() {
        return nomeLigacao;
    }

    /**
     *
     * @return distancia
     */
    public int getDistancia() {
        return distancia;
    }

    /**
     *
     * @return custo
     */
    public int getCusto() {
        return custo;
    }

    /**
     *
     * @return toString da Ligacao
     */
    @Override
    public String toString() {
        return tipo + "{"
                + nomeLigacao + ", "
                + distancia + "Km, "
                + custo + "€}";
    }

    /**
     * Metodo do Draw
     *
     * @return id
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * Metodo do Draw
     *
     * @return selected
     */
    @Override
    public boolean isSelected() {
        return selected;
    }

}
