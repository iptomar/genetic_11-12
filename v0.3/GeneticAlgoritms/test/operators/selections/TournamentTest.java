package operators.selections;

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
public class TournamentTest {
    
    public TournamentTest() {
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
     * Test of execute method, of class Tournament.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        Population population = null;
        Tournament instance = new Tournament();
        Population expResult = null;
        Population result = instance.execute(population);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isRemoveIndividualFromPopulation method, of class Tournament.
     */
    @Test
    public void testIsRemoveIndividualFromPopulation() {
        System.out.println("isRemoveIndividualFromPopulation");
        Tournament instance = new Tournament();
        boolean expResult = false;
        boolean result = instance.isRemoveIndividualFromPopulation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRemoveIndividualFromPopulation method, of class Tournament.
     */
    @Test
    public void testSetRemoveIndividualFromPopulation() {
        System.out.println("setRemoveIndividualFromPopulation");
        boolean removeIndividualFromPopulation = false;
        Tournament instance = new Tournament();
        instance.setRemoveIndividualFromPopulation(removeIndividualFromPopulation);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
