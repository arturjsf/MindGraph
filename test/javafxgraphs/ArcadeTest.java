/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxgraphs;

import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author smachado
 */
public class ArcadeTest {

    Arcade arcade;

    public ArcadeTest() {
    }

    @Before
    public void setUp() {
        arcade = new Arcade();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of menuArcade method, of class Arcade.
     */
    @Test
    public void testMenuArcade() {
    }

    /**
     * Test of menuPackArcade method, of class Arcade.
     */
    @Test
    public void testMenuPackArcade() {
    }

    /**
     * Test of criarJogoArcade method, of class Arcade.
     */
    @Test
    public void testCriarJogoArcade() {
    }

    /**
     * Test of calcularSolucao method, of class Arcade.
     */
    @Test
    public void testCalcularSolucao() {

    }

    /**
     * Test of atribuirEstrelas method, of class Arcade.
     */
    @Test
    public void testAtribuirEstrelas1() {

        assertEquals(1, Arcade.atribuirEstrelas(100, "125"));

    }

    @Test
    public void testAtribuirEstrelas2() {
        assertEquals(2, Arcade.atribuirEstrelas(100, "124"));
    }
    
        @Test
    public void testAtribuirEstrelas3() {
        assertEquals(3, Arcade.atribuirEstrelas(120, "120"));
    }

    /**
     * Test of verificaSolucao method, of class Arcade.
     */
    @Test
    public void testVerificaSolucao() {
    }

    /**
     * Test of gerarPackMiniJogosArcade method, of class Arcade.
     */
    @Test
    public void testGerarPackMiniJogosArcade() {
    }

    /**
     * Test of somaEstrelas method, of class Arcade.
     */
    @Test
    public void testSomaEstrelas() {
    }

}
