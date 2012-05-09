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
public class IndividualTest extends Individual {
    
    public IndividualTest() {
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
     * Test of fitness method, of class Individual.
     */
    @Test
    public void testFitness() {
        System.out.println("fitness");
        Individual instance = new IndividualImpl();
        int expResult = 0;
        int result = instance.fitness();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of inicializationAllelo method, of class Individual.
     */
    @Test
    public void testInicializationAllelo() {
        System.out.println("inicializationAllelo");
        Individual instance = new IndividualImpl();
        Boolean[] expResult = null;
        Boolean[] result = instance.inicializationAllelo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clone method, of class Individual.
     */
    @Test
    public void testClone() {
        System.out.println("clone");
        Individual instance = new IndividualImpl();
        Individual expResult = null;
        Individual result = instance.clone();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of inicializationGenome method, of class Individual.
     */
    @Test
    public void testInicializationGenome() {
        System.out.println("inicializationGenome");
        Individual instance = new IndividualImpl();
        instance.inicializationGenome();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getChromosome method, of class Individual.
     */
    @Test
    public void testGetChromosome() {
        System.out.println("getChromosome");
        int index = 0;
        Individual instance = new IndividualImpl();
        Chromosome expResult = null;
        Chromosome result = instance.getChromosome(index);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setChromosome method, of class Individual.
     */
    @Test
    public void testSetChromosome() {
        System.out.println("setChromosome");
        int index = 0;
        Chromosome cromosome = null;
        Individual instance = new IndividualImpl();
        instance.setChromosome(index, cromosome);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSizeGenotype method, of class Individual.
     */
    @Test
    public void testGetSizeGenotype() {
        System.out.println("getSizeGenotype");
        Individual instance = new IndividualImpl();
        int expResult = 0;
        int result = instance.getSizeGenotype();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSizeGenotype method, of class Individual.
     */
    @Test
    public void testSetSizeGenotype() {
        System.out.println("setSizeGenotype");
        int sizeGenotype = 0;
        Individual instance = new IndividualImpl();
        instance.setSizeGenotype(sizeGenotype);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSizeGenome method, of class Individual.
     */
    @Test
    public void testGetSizeGenome() {
        System.out.println("getSizeGenome");
        Individual instance = new IndividualImpl();
        int expResult = 0;
        int result = instance.getSizeGenome();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSizeGenome method, of class Individual.
     */
    @Test
    public void testSetSizeGenome() {
        System.out.println("setSizeGenome");
        int sizeGenome = 0;
        Individual instance = new IndividualImpl();
        instance.setSizeGenome(sizeGenome);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSizeAllelo method, of class Individual.
     */
    @Test
    public void testGetSizeAllelo() {
        System.out.println("getSizeAllelo");
        Individual instance = new IndividualImpl();
        int expResult = 0;
        int result = instance.getSizeAllelo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSizeAllelo method, of class Individual.
     */
    @Test
    public void testSetSizeAllelo() {
        System.out.println("setSizeAllelo");
        int sizeAllelo = 0;
        Individual instance = new IndividualImpl();
        instance.setSizeAllelo(sizeAllelo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGenome method, of class Individual.
     */
    @Test
    public void testGetGenome() {
        System.out.println("getGenome");
        Individual instance = new IndividualImpl();
        ArrayList expResult = null;
        ArrayList result = instance.getGenome();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAgeIndividual method, of class Individual.
     */
    @Test
    public void testGetAgeIndividual() {
        System.out.println("getAgeIndividual");
        Individual instance = new IndividualImpl();
        int expResult = 0;
        int result = instance.getAgeIndividual();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAgeIndividual method, of class Individual.
     */
    @Test
    public void testSetAgeIndividual() {
        System.out.println("setAgeIndividual");
        int ageIndividual = 0;
        Individual instance = new IndividualImpl();
        instance.setAgeIndividual(ageIndividual);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of incrementAge method, of class Individual.
     */
    @Test
    public void testIncrementAge() {
        System.out.println("incrementAge");
        Individual instance = new IndividualImpl();
        instance.incrementAge();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Individual.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Individual instance = new IndividualImpl();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iterator method, of class Individual.
     */
    @Test
    public void testIterator() {
        System.out.println("iterator");
        Individual instance = new IndividualImpl();
        Iterator expResult = null;
        Iterator result = instance.iterator();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Override
    public String getInfo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean setParameters(String parameters) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int fitness() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean[] inicializationAllelo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Individual clone() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public class IndividualImpl extends Individual {

        public int fitness() {
            return 0;
        }

        public Boolean[] inicializationAllelo() {
            return null;
        }

        public Individual clone() {
            return null;
        }

        @Override
        public String getInfo() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public boolean setParameters(String parameters) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
