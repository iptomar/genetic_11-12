/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genetics.algorithms;

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
@Suite.SuiteClasses({genetics.algorithms.K50Test.class, genetics.algorithms.KnapSackOldTest.class, genetics.algorithms.KnapSackTest.class, genetics.algorithms.K5Test.class, genetics.algorithms.OnesMaxTest.class, genetics.algorithms.K100Test.class})
public class AlgorithmsSuite {

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
