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
import utils.MochilaOld;

/**
 *
 * @author Ruben Felix <Ruben.Felix@gmail.com>
 */
public class KnapSackOldTest {
    
    public KnapSackOldTest() {
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
     * Test of inicializationAllelo method, of class KnapSackOld.
     */
    @Test
    public void testInicializationAllelo() {
        System.out.println("inicializationAllelo");
        KnapSackOld instance = new KnapSackOld();
        Boolean[] expResult = null;
        Boolean[] result = instance.inicializationAllelo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clone method, of class KnapSackOld.
     */
    @Test
    public void testClone() {
        System.out.println("clone");
        KnapSackOld instance = new KnapSackOld();
        Individual expResult = null;
        Individual result = instance.clone();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMocha method, of class KnapSackOld.
     */
    @Test
    public void testGetMocha() {
        System.out.println("getMocha");
        KnapSackOld instance = new KnapSackOld();
        MochilaOld expResult = null;
        MochilaOld result = instance.getMocha();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMocha method, of class KnapSackOld.
     */
    @Test
    public void testSetMocha() {
        System.out.println("setMocha");
        MochilaOld mocha = null;
        KnapSackOld instance = new KnapSackOld();
        instance.setMocha(mocha);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class KnapSackOld.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        KnapSackOld instance = new KnapSackOld();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fitness method, of class KnapSackOld.
     */
    @Test
    public void testFitness() {
        System.out.println("fitness");
        KnapSackOld instance = new KnapSackOld();
        int expResult = 0;
        int result = instance.fitness();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
