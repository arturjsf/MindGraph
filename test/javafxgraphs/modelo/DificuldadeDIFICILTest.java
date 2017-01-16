/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxgraphs.modelo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author smachado
 */
public class DificuldadeDIFICILTest {

    DificuldadeDIFICIL teste;

    public DificuldadeDIFICILTest() {
    }

    @Before
    public void setUp() {
        teste = new DificuldadeDIFICIL();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of randomVertices method, of class DificuldadeDIFICIL.
     */
    @Test
    public void testRandomVertices() {
        assertEquals(true, teste.randomVertices() <= 12 && teste.randomVertices() >= 9);
    }

    /**
     * Test of randomCusto method, of class DificuldadeDIFICIL.
     */
    @Test
    public void testRandomCusto() {
        assertEquals(true, teste.randomCusto() <= 1000 && teste.randomCusto() >= 100);

    }

    /**
     * Test of randomDistancia method, of class DificuldadeDIFICIL.
     */
    @Test
    public void testRandomDistancia() {
        assertEquals(true, teste.randomDistancia() <= 10000 && teste.randomDistancia() >= 1000);

    }

    /**
     * Test of randomTipo method, of class DificuldadeDIFICIL.
     */
    @Test
    public void testRandomTipo() {
    }

    /**
     * Test of randomArestas method, of class DificuldadeDIFICIL.
     */
    @Test
    public void testRandomArestas() {
        assertEquals(true, teste.randomArestas() <= 25 && teste.randomArestas() >= 15);

    }

}
