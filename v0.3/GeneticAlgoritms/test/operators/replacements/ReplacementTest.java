/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package operators.replacements;

import genetics.Population;
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
public class ReplacementTest {
    
    public ReplacementTest() {
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
     * Test of execute method, of class Replacement.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        Population parents = null;
        Population sons = null;
        Replacement instance = new ReplacementImpl();
        Population expResult = null;
        Population result = instance.execute(parents, sons);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class ReplacementImpl extends Replacement {

        public Population execute(Population parents, Population sons) {
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
