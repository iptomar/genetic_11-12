/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package operators.replacements;

import genetics.Population;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pedro
 */
public class TournamentTest {
    
    public TournamentTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of execute method, of class Tournament.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        Population parents = null;
        Population sons = null;
        Tournament instance = new Tournament();
        Population expResult = null;
        Population result = instance.execute(parents, sons);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
