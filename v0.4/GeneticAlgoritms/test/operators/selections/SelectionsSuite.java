/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package operators.selections;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Pedro
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({operators.selections.TournamentTest.class, operators.selections.SUSTest.class, operators.selections.RouletteTest.class})
public class SelectionsSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
}
