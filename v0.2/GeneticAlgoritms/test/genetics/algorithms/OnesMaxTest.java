/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genetics.algorithms;

import genetics.Individual;
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
public class OnesMaxTest {
    
    public OnesMaxTest() {
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
     * Test of fitness method, of class OnesMax.
     */
    @Test
    public void testFitness() {
        System.out.println("fitness");
        OnesMax instance = new OnesMax();
        int expResult = 0;
        int result = instance.fitness();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of inicializationAllelo method, of class OnesMax.
     */
    @Test
    public void testInicializationAllelo() {
        System.out.println("inicializationAllelo");
        OnesMax instance = new OnesMax();
        Boolean[] expResult = null;
        Boolean[] result = instance.inicializationAllelo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clone method, of class OnesMax.
     */
    @Test
    public void testClone() {
        System.out.println("clone");
        OnesMax instance = new OnesMax();
        Individual expResult = null;
        Individual result = instance.clone();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
