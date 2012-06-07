package operators.recombinations;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Ruben Felix <Ruben.Felix@gmail.com>
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({operators.recombinations.CrossoverTest.class, operators.recombinations.RecombinationTest.class, operators.recombinations.UniformCrossoverTest.class})
public class RecombinationsSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
