package genetics;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Helio
 */
public class GeneTest {
    
    public GeneTest() {
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
     * Test para verificar se os genes correspondem aos introduzidos
     */
    @Test
    public void testGetSetAllele() {
        //define se uma array de boolean
        Boolean[] alleloTeste = {false, true, true, false};
        //passa se por parametros
        Gene<Boolean[]> gene = new Gene<Boolean[]>(alleloTeste);
        
        //verifica se os valores são os mesmo, senao o teste não passa
        for (int i = 0; i < alleloTeste.length; i++) {
            if(!alleloTeste[i]==gene.getAllele()[i]){
                fail("O allelo que foi especificado não é o mesmo que está definido de momento.");
            }
        }
    }

}