package operators.replacements;

import genetics.Individual;
import java.util.ArrayList;
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
public class TruncationTest {

    /**
     * Tamanho da população que sai do truncation
     */
    private int sizeTruncation;
    /**
     * População pai
     */
    private Population parents;
    /**
     * População filho
     */
    private Population sons;

    public TruncationTest() {
    }

    /**
     * Construtor da classe em que é passado por parametro o número de individuos a sair do operador truncation
     * A população filho e pai são inicializadas automaticamente com o número de individuos de cada uma a ser
     * igual ao tamanho que sairá do truncation.
     * @param sizeTruncation (int) - Tamanho da população que sai do operador truncation
     */
    public void TruncationTest(int sizeTruncation) {
        this.sizeTruncation = sizeTruncation;
        parents = new Population(sizeTruncation, 1, 1, 1, new genetics.algorithms.OnesMax());
        sons = new Population(sizeTruncation, 1, 1, 1, new genetics.algorithms.OnesMax());
    }

    /**
     * Contrutor da classe onde é passado por parametro a população pai e a população filho a serem
     * utilizadas no operador truncation, bem como o número de individuos que sairam do operador truncation
     * @param sizeTruncation (int) - Tamanho da população que sai do operador truncation
     * @param parents (Population) - População pai a ser aplicada o operador Truncation
     * @param sons (Population) - População filho a ser aplicada o operador Truncation
     */
    public void TruncationTest(int sizeTruncation, Population parents, Population sons) {
        this.parents = parents;
        this.sons = sons;
        this.sizeTruncation = sizeTruncation;
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
     * Test of execute method, of class Truncation.
     */
    @Test
    public void testExecute() {
        System.out.println("-- Teste ao operador Truncation --");
        //Teste com truncation a 10 e com as populações a ser inicializadas automaticamente
        this.TruncationTest(10);
        System.out.println("Parents population size: " + parents.getSizePopulation());
        System.out.println("Sons population size: " + sons.getSizePopulation());
        //Verifica se as populações tem o mesmo tamanho
        assertEquals(parents.getSizePopulation(), sons.getSizePopulation());
        //Novo operador Truncation que devolverá apenas 10 individuos
        Truncation instance = new Truncation(this.sizeTruncation);
        //Resultado do truncation
        Population result = instance.execute(parents, sons);
        //Verifica se foram apenas retirados os 10 individuos
        assertEquals(this.sizeTruncation, result.getSizePopulation());
        //Verifica se os individuos resultantes do operador se encontram nas populações pais ou filhos
        for (int i = 0; i < result.getSizePopulation(); i++) {
            if (!parents.getPopulation().contains(result.getIndividual(i)) && !sons.getPopulation().contains(result.getIndividual(i))) {
                fail("Os individuos resultantes do operador não estão na população pai nem na população filho.");
            }
        }

        //Ordena as populações por fitness
        utils.PopulationUtils.orderPopulation(sons);
        utils.PopulationUtils.orderPopulation(parents);
        utils.PopulationUtils.orderPopulation(result);

        //ArrayList de individuos 
        ArrayList<Individual> Individuos = new ArrayList<Individual>(sizeTruncation*2);
        for (int i = 0; i < this.sizeTruncation; i++) {
            Individuos.add(parents.getPopulation().get(i));
            Individuos.add(sons.getPopulation().get(i));
        }
        
        //Verifica se os individuos retirados do operador Truncation pertencem mesmo ao best-fitness das populações
        for (int i = 0; i < this.sizeTruncation; i++) {
            if(!Individuos.contains(result.getIndividual(i))){
                fail("Os individuos resultantes do operador não estão nos best fitness da população pai ou filho.");
            }
        }
    }
}
