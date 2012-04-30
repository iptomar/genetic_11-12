/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package operators.replacements;

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
@Suite.SuiteClasses({operators.replacements.TournamentTest.class, operators.replacements.TruncationTest.class, operators.replacements.ReplacementTest.class})
public class ReplacementsSuite {

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
