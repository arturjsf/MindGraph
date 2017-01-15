/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxgraphs.modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Artur Ferreira
 */
public class Jogador {

    public String nome;
    public int pontuacao;

    public Jogador(String nome) {
        this.nome = nome;
        this.pontuacao = 0;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public void resetPontuacao() {
        pontuacao = 0;
    }

    @Override
    public String toString() {
        return nome + " - " + pontuacao;
    }

    
    //Metodo que escreve no ficheiro o nome e a pontuação do jogador
    public void escreverFicheiroRecordes(String tipoJogo) {

        PrintWriter fEscrever;

        try {

            if (tipoJogo.equals("TT")) {
                fEscrever = new PrintWriter(new FileWriter("recordesTT.txt", true));
            } else {
                fEscrever = new PrintWriter(new FileWriter("recordesArcade.txt", true));
            }

            fEscrever.println(getNome() + "        " + getPontuacao());
            fEscrever.close();
        } catch (IOException ex) {
            System.out.println("erro");
        }
    }

    //Metodo para ler do ficheiro o nome e os pontos do jogador 
    public String lerFicheiroRecordes(String tipoJogo) {

        String str = "";
        String linhas = "";

        BufferedReader fLeitura;

        try {
            if (tipoJogo.equals("TT")) {
                fLeitura = new BufferedReader(new FileReader("recordesTT.txt"));
            }else{
                fLeitura = new BufferedReader(new FileReader("recordesArcade.txt"));
            }
                
                while (fLeitura.ready()) {
                    linhas = fLeitura.readLine();
                    str += "\n" + linhas;
                }
                fLeitura.close();
            }catch (IOException e) {
            System.out.println("Impossivel ler ficheiro!");

        }
            return str;
        }
    
    
        //Metodo para ler do ficheiro o nome e os pontos do jogador 
    public String lerFicheiroRegras(String tipoJogo) {

        String str = "";
        String linhas = "";

        BufferedReader fLeitura;

        try {
            if (tipoJogo.equals("TT")) {
                fLeitura = new BufferedReader(new FileReader("regrasTT.txt"));
            }else{
                fLeitura = new BufferedReader(new FileReader("regrasArcade.txt"));
            }
                
                while (fLeitura.ready()) {
                    linhas = fLeitura.readLine();
                    str += "\n" + linhas;
                }
                fLeitura.close();
            }catch (IOException e) {
            System.out.println("Impossivel ler ficheiro!");

        }
            return str;
        }

    }
