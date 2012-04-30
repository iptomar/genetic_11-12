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
public class ChromosomeTest {
    
    public ChromosomeTest() {
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
     * Test of getGene method, of class Chromosome.
     */
    @Test
    public void testGetGene() {
        System.out.println("getGene");
        int index = 0;
        Chromosome instance = null;
        Gene expResult = null;
        Gene result = instance.getGene(index);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGene method, of class Chromosome.
     */
    @Test
    public void testSetGene() {
        System.out.println("setGene");
        Gene gene = null;
        Chromosome instance = null;
        instance.setGene(gene);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGenotype method, of class Chromosome.
     */
    @Test
    public void testGetGenotype() {
        System.out.println("getGenotype");
        Chromosome instance = null;
        ArrayList expResult = null;
        ArrayList result = instance.getGenotype();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGenotype method, of class Chromosome.
     */
    @Test
    public void testSetGenotype() {
        System.out.println("setGenotype");
        ArrayList<Gene> genotype = null;
        Chromosome instance = null;
        instance.setGenotype(genotype);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIndividual method, of class Chromosome.
     */
    @Test
    public void testGetIndividual() {
        System.out.println("getIndividual");
        Chromosome instance = null;
        Individual expResult = null;
        Individual result = instance.getIndividual();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIndividual method, of class Chromosome.
     */
    @Test
    public void testSetIndividual() {
        System.out.println("setIndividual");
        Individual individual = null;
        Chromosome instance = null;
        instance.setIndividual(individual);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Chromosome.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Chromosome instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iterator method, of class Chromosome.
     */
    @Test
    public void testIterator() {
        System.out.println("iterator");
        Chromosome instance = null;
        Iterator expResult = null;
        Iterator result = instance.iterator();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
