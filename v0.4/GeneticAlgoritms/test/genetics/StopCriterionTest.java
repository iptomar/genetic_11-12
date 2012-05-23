/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genetics;

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
public class StopCriterionTest {
    
    public StopCriterionTest() {
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
     * Test of getNumberIteractions method, of class StopCriterion.
     */
    @Test
    public void testGetNumberIteractions() {
        System.out.println("getNumberIteractions");
        StopCriterion instance = null;
        int expResult = 0;
        int result = instance.getNumberIteractions();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNumberIteractions method, of class StopCriterion.
     */
    @Test
    public void testSetNumberIteractions() {
        System.out.println("setNumberIteractions");
        int numberIteractions = 0;
        StopCriterion instance = null;
        instance.setNumberIteractions(numberIteractions);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGoodFiteness method, of class StopCriterion.
     */
    @Test
    public void testGetGoodFiteness() {
        System.out.println("getGoodFiteness");
        StopCriterion instance = null;
        Double expResult = null;
        Double result = instance.getGoodFiteness();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGoodFiteness method, of class StopCriterion.
     */
    @Test
    public void testSetGoodFiteness() {
        System.out.println("setGoodFiteness");
        Double goodFiteness = null;
        StopCriterion instance = null;
        instance.setGoodFiteness(goodFiteness);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
