/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genetics.algorithms;

import genetics.Individual;
import genetics.algorithms.KnapSack.ModeFunction;
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
public class KnapSackTest {
    
    public KnapSackTest() {
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
     * Test of fitness method, of class KnapSack.
     */
    @Test
    public void testFitness() {
        System.out.println("fitness");
        KnapSack instance = new KnapSack();
        int expResult = 0;
        //int result = instance.fitness();
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of inicializationAllelo method, of class KnapSack.
     */
    @Test
    public void testInicializationAllelo() {
        System.out.println("inicializationAllelo");
        KnapSack instance = new KnapSack();
        Boolean[] expResult = null;
        Boolean[] result = instance.inicializationAllelo(0);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clone method, of class KnapSack.
     */
    @Test
    public void testClone() {
        System.out.println("clone");
        KnapSack instance = new KnapSack();
        Individual expResult = null;
        Individual result = instance.clone();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMaxWeight method, of class KnapSack.
     */
    @Test
    public void testGetMaxWeight() {
        System.out.println("getMaxWeight");
        KnapSack instance = new KnapSack();
        int expResult = 0;
        int result = instance.getMaxWeight();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMaxWeight method, of class KnapSack.
     */
    @Test
    public void testSetMaxWeight() {
        System.out.println("setMaxWeight");
        int maxWeight = 0;
        KnapSack instance = new KnapSack();
        instance.setMaxWeight(maxWeight);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getModeFunction method, of class KnapSack.
     */
    @Test
    public void testGetModeFunction() {
        System.out.println("getModeFunction");
        KnapSack instance = new KnapSack();
        ModeFunction expResult = null;
        ModeFunction result = instance.getModeFunction();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setModeFunction method, of class KnapSack.
     */
    @Test
    public void testSetModeFunction() {
        System.out.println("setModeFunction");
        ModeFunction modeFunction = null;
        KnapSack instance = new KnapSack();
        instance.setModeFunction(modeFunction);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTable method, of class KnapSack.
     */
    @Test
    public void testGetTable() {
        System.out.println("getTable");
        KnapSack instance = new KnapSack();
        int[][] expResult = null;
        int[][] result = instance.getTable();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTable method, of class KnapSack.
     */
    @Test
    public void testSetTable() {
        System.out.println("setTable");
        int[][] table = null;
        KnapSack instance = new KnapSack();
        instance.setTable(table);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPenaltyOrder method, of class KnapSack.
     */
    @Test
    public void testGetPenaltyOrder() {
        System.out.println("getPenaltyOrder");
        KnapSack instance = new KnapSack();
        int expResult = 0;
        int result = instance.getPenaltyOrder();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPenaltyOrder method, of class KnapSack.
     */
    @Test
    public void testSetPenaltyOrder() {
        System.out.println("setPenaltyOrder");
        int penaltyOrder = 0;
        KnapSack instance = new KnapSack();
        instance.setPenaltyOrder(penaltyOrder);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of _parseStringData method, of class KnapSack.
     */
    @Test
    public void test_parseStringData() {
        System.out.println("_parseStringData");
        String data = "";
        KnapSack instance = new KnapSack();
        instance._parseStringData(data);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
