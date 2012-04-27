package operators.replacements;

import genetics.Population;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author André
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
        System.out.println("-- Teste ao operador Tournament --");
        /**
         * População de pais, com 100 individuos e com o alelo a 10.
         */
        Population parents = new Population(100, 1, 1, 10, new genetics.algorithms.OnesMax(), true);
        /**
         * População de filhos, com 100 individuos e com o alelo a 10.
         */
        Population sons = new Population(100, 1, 1, 10, new genetics.algorithms.OnesMax(), true);
        /**
         * Criação do torneio de 100 elementos 2 a 2
         */
        Tournament instance = new Tournament(100,2);
        /**
         * Execução do torneio
         */
        Population result = instance.execute(parents, sons);
        
        /**
         * Compara se o o size da population do result é 100, que é o numero de individos que sai no torneio
         */
        assertEquals(result.getPopulation().size(), 100);
        
        /**
         * Ordena os resultados obtidos no torneio
         */
        utils.PopulationUtils.orderPopulation(result);
        utils.PopulationUtils.orderPopulation(parents);
        utils.PopulationUtils.orderPopulation(sons);
        /**
         * Condição para saber se um individuo da população resultante do torneio pertence à população
         */
        if(result.getPopulation().get(0).fitness() != parents.getPopulation().get(0).fitness() && result.getPopulation().get(0).fitness() != sons.getPopulation().get(0).fitness()){
            fail("Um individuo da população resultante do torneio não se encontra na população pai nem na população filho.");
        }
        /**
         * Imprime o torneio, ordenado pelo individuo com melhor fitness
         */
        System.out.println(" " + result);
        /**
         * Mostra qual o valor de fitness do melhor inviduo
         * Não é possivel saber se o individuo com melhor fitness pertence 
         * aos pais ou aos filhos devia ao facto da existencia do método clone na classe Tournament
         */
        System.out.println("Melhor individuo: " + result.getPopulation().get(0).fitness());  
    }
}