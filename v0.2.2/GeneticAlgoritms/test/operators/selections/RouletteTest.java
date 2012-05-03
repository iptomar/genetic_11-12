/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package operators.selections;

import utils.PopulationUtils;
import genetics.algorithms.OnesMax;
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
public class RouletteTest {
    
    
    private Population p;
    private Roulette r;
    int sizePopulation = 10;
    int sizeGenome = 1;
    int sizeGenotype = 1;
    int sizeAllelo = 10;
    int DimSelectedPopulation = 5;
    int testeAleatorio = 100;
    
    public RouletteTest() {
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
     * Test of execute method, of class Roulette.
     */
    @Test
    public void testExecute() {
        
//******************    Vê se o ponto aleatório está ente 0 e 1 ->  (0-maxFitness)/maxFitness  *********************************
       
        //população gerada
        p = new Population(sizePopulation, sizeGenome, sizeGenotype, sizeAllelo, new OnesMax());
        //SUS com a dimensão a ser seleccionada
        r = new Roulette(DimSelectedPopulation);
        
         Population popSaida;
        //executa o Roleta com a população que foi gerada
        popSaida = r.execute(p);
        //soma total de fitness de todos os individuos da população
        int maxFitness = PopulationUtils.getFitnessTotal(p);
        //variavel com o valor que é gerado aleatoriamente no SUS
        double pontoAleatorio = r.getPonteiro();
        
        
        
        assertTrue( 0<= pontoAleatorio && pontoAleatorio <= PopulationUtils.getFitnessTotal(r.getPopIn()));
     
//*************** Vê se o numero de individuos da população de saida é igual ao numero seleccionado ***************
        
        assertTrue( popSaida.getSizePopulation() == DimSelectedPopulation);
        
//*********************Vê se individuos da popOUT fazem parte da popIN***********************************************
        Population pi = new Population(sizePopulation, sizeGenome, sizeGenotype, sizeAllelo, new OnesMax());
        Population pf = r.execute(pi);


//tpf-testepopfinal
        //percorrer a pop de saida, e ver se cada individuo está contido na pop de entrada
        int tpf = pf.getSizePopulation();
        for (int j = 0; j < tpf; j++) {
            for (int i = 0; i < pi.getSizePopulation(); i++) {
                if (pf.getIndividual(0).fitness() == pi.getIndividual(i).fitness() ) {
                    pf.getPopulation().remove(0);
                    pf.DecSize();
                    break;
                }
            }
        }

        
        boolean t = false;
        if (pf.getSizePopulation() == 0) {
            t = true;
        }
        
        assertTrue(t);
        
        
    }
}
