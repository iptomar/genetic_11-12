/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package operators.recombinations;

import statistics.Statistics;
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
public class UniformCrossoverTest {
    /**
     * Populações para serem testadas com o uniforme crossover
     */
    private Population parents;
    private Population sons;
    
    
    public UniformCrossoverTest() {
    }
    
    /**
     * Método da classe onde se passa por parametro a população onde será aplcado o uniforme crossover
     */
    public void UniformCrossoverTest(Population parents){
        this.parents = parents;
    }
    
    public void UniformCrossoverTest(){
        this.parents = new Population(499, 1, 1, 10, new genetics.algorithms.OnesMax());
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
     * Test of execute method, of class UniformCrossover.
     */
    @Test
    public void testExecute() {
        System.out.println("-- Teste ao operador Uniform Crossover --");
        //Cria a população pai aleatoriamente
        UniformCrossoverTest();
        //Novo objecto Uniform Crossover
        UniformCrossover crossover = new UniformCrossover();
        //Ordena a população pai por fitness
        utils.PopulationUtils.orderPopulation(parents);
        //Executa o uniform crossover na população parents
        sons = crossover.execute(parents);
        //utils.PopulationUtils.orderPopulation(sons);
        System.out.println("Parents size: " + parents.getSizePopulation());
        System.out.println("Sons size: " + sons.getSizePopulation());
        //Verifica se a população recebida tem o mesmo número de individuos que a população que entrou para o uniform crossover
        assertEquals(parents.getSizePopulation(), sons.getSizePopulation());
        
        //Verifica se a população inicial é impar para fazer um teste adicional
        //Se for impar, ordenando a população saberemos que o ultimo elemento dessa população
        //estará obrigatoriamente presente na população filho já que o ultimo elemento não teve
        //ninguem para troca de informação genética, o que quer dizer que foi adicionado sem alterações à
        //população filho
        if(parents.getSizePopulation() % 2 != 0){
            System.out.println("Teste ao ultimo individuo de cada população (População impar)");
            System.out.println("Ultimo individuo parents: " + parents.getIndividual(parents.getSizePopulation()-1).toString());
            System.out.println("Ultimo individuo sons: " + sons.getIndividual(sons.getSizePopulation()-1).toString());
            assertEquals(parents.getIndividual(parents.getSizePopulation()-1), sons.getIndividual(sons.getSizePopulation()-1));
        }
        
        //Verifica se as medidas estatisticas da população filho e pai são diferentes
        //Caso sejam, houve efectivamente mutação genética
        //Caso contrário, há fortes possibilidades do uniform crossover ter falhado
        Statistics partnS = new Statistics(parents);
        Statistics sonsS = new Statistics(parents);
        if(partnS.getMediaFitnessPopulation() == sonsS.getMediaFitnessPopulation()){
            fail("As medias de fitness's das duas populações são iguais.");
        }
        else if(partnS.getDesvioPadraoPopulation() == sonsS.getDesvioPadraoPopulation()) {
            fail("Os devio padrão das duas populações são iguais.");
        }
        else if(partnS.getVarianciaPopulation() == sonsS.getVarianciaPopulation()) {
            fail("As variâncias das duas populações são iguais.");
        }
    }
}
