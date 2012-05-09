/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genetics;

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
@Suite.SuiteClasses({genetics.SolverKnapSackExampleTest.class, genetics.GeneTest.class, genetics.algorithms.AlgorithmsSuite.class, genetics.SolverTest.class, genetics.StopCriterionTest.class, genetics.PopulationTest.class, genetics.ChromosomeTest.class})
public class GeneticsSuite {

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
