/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genetics;

import java.util.ArrayList;
import java.util.Iterator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ruben Felix <Ruben.Felix@gmail.com>
 */
public class PopulationTest {
    
    public PopulationTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getIndividual method, of class Population.
     */
    @Test
    public void testGetIndividual() {
        System.out.println("getIndividual");
        int index = 0;
        Population instance = null;
        Individual expResult = null;
        Individual result = instance.getIndividual(index);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIndividual method, of class Population.
     */
    @Test
    public void testSetIndividual() {
        System.out.println("setIndividual");
        int index = 0;
        Individual individual = null;
        Population instance = null;
        instance.setIndividual(index, individual);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSizePopulation method, of class Population.
     */
    @Test
    public void testGetSizePopulation() {
        System.out.println("getSizePopulation");
        Population instance = null;
        int expResult = 0;
        int result = instance.getSizePopulation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSizePopulation method, of class Population.
     */
    @Test
    public void testSetSizePopulation() {
        System.out.println("setSizePopulation");
        int sizePopulation = 0;
        Population instance = null;
        instance.setSizePopulation(sizePopulation);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTypePopulation method, of class Population.
     */
    @Test
    public void testGetTypePopulation() {
        System.out.println("getTypePopulation");
        Population instance = null;
        Individual expResult = null;
        Individual result = instance.getTypePopulation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTypePopulation method, of class Population.
     */
    @Test
    public void testSetTypePopulation() {
        System.out.println("setTypePopulation");
        Individual prototypeIndividual = null;
        Population instance = null;
        instance.setTypePopulation(prototypeIndividual);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addIndividual method, of class Population.
     */
    @Test
    public void testAddIndividual() {
        System.out.println("addIndividual");
        Individual individual = null;
        Population instance = null;
        instance.addIndividual(individual);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPopulation method, of class Population.
     */
    @Test
    public void testGetPopulation() {
        System.out.println("getPopulation");
        Population instance = null;
        ArrayList expResult = null;
        ArrayList result = instance.getPopulation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPopulation method, of class Population.
     */
    @Test
    public void testSetPopulation() {
        System.out.println("setPopulation");
        ArrayList<Individual> population = null;
        Population instance = null;
        instance.setPopulation(population);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iterator method, of class Population.
     */
    @Test
    public void testIterator() {
        System.out.println("iterator");
        Population instance = null;
        Iterator expResult = null;
        Iterator result = instance.iterator();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSizeAllelo method, of class Population.
     */
    @Test
    public void testGetSizeAllelo() {
        System.out.println("getSizeAllelo");
        Population instance = null;
        int expResult = 0;
        int result = instance.getSizeAllelo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSizeAllelo method, of class Population.
     */
    @Test
    public void testSetSizeAllelo() {
        System.out.println("setSizeAllelo");
        int sizeAllelo = 0;
        Population instance = null;
        instance.setSizeAllelo(sizeAllelo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Population.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Population instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSizeGenotype method, of class Population.
     */
    @Test
    public void testGetSizeGenotype() {
        System.out.println("getSizeGenotype");
        Population instance = null;
        int expResult = 0;
        int result = instance.getSizeGenotype();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSizeGenotype method, of class Population.
     */
    @Test
    public void testSetSizeGenotype() {
        System.out.println("setSizeGenotype");
        int sizeGenotype = 0;
        Population instance = null;
        instance.setSizeGenotype(sizeGenotype);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSizeGenome method, of class Population.
     */
    @Test
    public void testGetSizeGenome() {
        System.out.println("getSizeGenome");
        Population instance = null;
        int expResult = 0;
        int result = instance.getSizeGenome();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSizeGenome method, of class Population.
     */
    @Test
    public void testSetSizeGenome() {
        System.out.println("setSizeGenome");
        int sizeGenome = 0;
        Population instance = null;
        instance.setSizeGenome(sizeGenome);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clone method, of class Population.
     */
    @Test
    public void testClone() {
        System.out.println("clone");
        Population instance = null;
        Population expResult = null;
        Population result = instance.clone();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
