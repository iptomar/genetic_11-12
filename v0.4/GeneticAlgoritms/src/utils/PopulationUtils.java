package utils;

import genetics.Chromosome;
import genetics.Gene;
import genetics.Individual;
import genetics.Population;
import genetics.algorithms.K100;
import genetics.algorithms.K50;
import genetics.algorithms.KnapSack;
import genetics.algorithms.TSP;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.TreeSet;

/**
 *
 * @author Chorinca-Notebook
 */
public class PopulationUtils {

    public static Population getHallOfFame(Population population, int sizeHallOfFame) {
        // Nova população que vai guardar os Hall Of Fame
        final Population __newPopulation =
                new Population(
                sizeHallOfFame,
                population.getSizeGenome(),
                population.getSizeGenotype(),
                population.getSizeAllelo(),
                population.getTypePopulation(),
                false);
        // Ordenar População pelo fitness de forma descendente
        PopulationUtils.orderPopulation(population);
        // Devolve o numero de individuos que foram pedidos
        PopulationUtils._createHallOfFame(__newPopulation, sizeHallOfFame, population.getPopulation());
        // Devolve a população com os Hall Of Fame (melhores individuos)
        return __newPopulation;
    }

    /**
     * Cria uma população com os individuos que foram passados como parametro em
     * que o numero de individuos é determinado pelo sizeHallOfFame
     * @param newPopulation
     * @param sizeHallOfFame
     * @param individuals 
     */
    private static void _createHallOfFame(Population newPopulation, int sizeHallOfFame, AbstractCollection<Individual> individuals) {
        int __indexIndividual = 0;
        // Só para o ciclo quando obter todos os individuos pedidos atraves do sizeHallOfFame
        // ou quando deixar de haver individuos dentro do array que ainda não tenham sido
        // adicionados à nova população
        while ((__indexIndividual < sizeHallOfFame)
                && (__indexIndividual < individuals.toArray().length)) {
            newPopulation.addIndividual((Individual) individuals.toArray()[__indexIndividual]);
            __indexIndividual++;
        }
    }

    /**
     * Método que ordena o array de individuos da poipulação pelo seu fitness 
     */
    public static void orderPopulation(Population population) {
        if (population.getTypePopulation() instanceof TSP) {
            // Ordenar População pelo fitness de forma ascendente, já que o problema é o TSP
            Collections.sort(population.getPopulation(), new ComparatorIndividualTSP());
        } else {
            // Ordenar População pelo fitness (isso esta definido no ComparatorIndividual) de forma 
            // descendente
            Collections.sort(population.getPopulation(), new ComparatorIndividualFitness());
        }
    }

    /**
     * Método que devolve os parametros de peso máximo da mochila, soma dos pesos de todos os componentes do
     * problema e o peso dos items do melhor individuo da população.
     * @param population - População a ser analisada
     * @return String com a informação do melhor individuo e dos problemas
     */
    public static String getKnapSackLastParameters(Population population) {
        //Verifica se a população é do tipo KnapSack e não é nula nem vazia
        if (!population.getPopulation().isEmpty() && population != null && (population.getTypePopulation() instanceof KnapSack || population.getTypePopulation() instanceof K50 || population.getTypePopulation() instanceof K100)) {
            //Ordena a população pelo melhor fitness
            Collections.sort(population.getPopulation(), new ComparatorIndividualFitness());
            String info = "" + ((KnapSack) population.getIndividual(0)).getMaxWeight();
            info += ":" + ((KnapSack) population.getIndividual(0))._calculateIndividualWeight();
            info += ":" + ((KnapSack) population.getIndividual(0)).calculateTotalItemsWeight();
            return info;
        } else {
            //Se a população não for correcta, devolve null
            return null;
        }
    }

    /**
     * Método que devolve o número de individúos que tem o fitness máximo na população
     * @return - Número de individuos na população que tem o valor de fitness máximo
     */
    public static int getNumberIndividualsWithBestFitness(Population population) {
        double __bestFitness;
        int __numberOfIndividualsWithBestFitness = 0;
        // Pede o melhor individuo atraves do metodo getHallOfFame com tamanho 1
        // depois escolhe o primeiro individuo e devolve o seu fitness
        __bestFitness = PopulationUtils.getBestFitness(population);
        // Corre a população toda à procura de individuos que tenham
        // o melhor fitness
        for (Individual __individuals : population) {
            if (__bestFitness == __individuals.fitness()) {
                __numberOfIndividualsWithBestFitness++;
            }
        }
        // Devolve a quantidade de individuos encontrados
        return __numberOfIndividualsWithBestFitness;
    }

    /***
     * Método que vai buscar individuos individuos aleatoriamente á população.
     * O número de individuos é passado por parâmetro, e tem a opção de retirar o individuo
     * da população ou mantê-lo.
     * @param numberIndividual
     * @param removeIndividualFromPopulation
     * @return 
     */
    public static ArrayList<Individual> getArrayIndividualsRandom(Population population, int numberIndividual, boolean removeIndividualFromPopulation) {
        final ArrayList<Individual> __newArrayIndividual = new ArrayList<Individual>(numberIndividual);
        int __countIndividual = 0;
        int __indexIndividual;
        int __numberIndividualToReturn = numberIndividual;

        // Escolher de forma aleatoria varios individuos        
        while (__countIndividual < __numberIndividualToReturn) {
            if ((population.getPopulation().size() - 1) > 0) {
                __indexIndividual = Population.RANDOM_GENERATOR.nextInt(population.getPopulation().size() - 1);
                __newArrayIndividual.add(population.getPopulation().get(__indexIndividual).clone());
                if (removeIndividualFromPopulation) {
                    population.getPopulation().remove(population.getPopulation().get(__indexIndividual));
                }
            } else {
                Collections.sort(__newArrayIndividual, new ComparatorIndividualFitness());
                __newArrayIndividual.add(__newArrayIndividual.get(0).clone());
            }
            __countIndividual++;
        }
        return __newArrayIndividual;
    }

    /***
     * Devolve o melhor fitness que existe na população
     * @param population
     * @return 
     */
    public static double getBestFitness(Population population) {
        final int NUMBER_INDIVIDUALS_TO_GET_FROM_HALL_OF_FAME = 1;
        final int FIRST_INDIVIDUAL = 0;
        // Pede o melhor individuo atraves do metodo getHallOfFame com tamanho 1
        // depois escolhe o primeiro individuo e devolve o seu fitness
        return PopulationUtils.getHallOfFame(population, NUMBER_INDIVIDUALS_TO_GET_FROM_HALL_OF_FAME).getIndividual(FIRST_INDIVIDUAL).fitness();
    }

    /**
     * Método que devolve o fitness total da população (soma do fitness de todos os individuos da população)
     * @return (double) - Fitness total da população
     */
    public static double getFitnessTotal(Population population) {
        double __totalFitness = 0;
        for (Individual __individual : population) {
            __totalFitness += __individual.fitness();
        }
        return __totalFitness;
    }

    public static double totalFitnessAcumulation(Population population, double[][] costMatrix) {
        double totalFitness = 0;
        for (Individual individuo : population) {
            // incrementa o total fitness
            totalFitness += calculateFitness(individuo, costMatrix);
        }
        return totalFitness;
    }

    public static double calculateFitness(Individual individual, double[][] costMatrix) {
        // starting point
        double fitness = 0;
        for (Chromosome chromosome : individual) {
            for (Gene<Integer[]> gene : chromosome) {
                for (int __indexAlleloValue = 1; __indexAlleloValue < gene.getAllele().length; __indexAlleloValue++) {
                    fitness += costMatrix[gene.getAllele()[__indexAlleloValue - 1]][gene.getAllele()[__indexAlleloValue % gene.getAllele().length]];
                }
            }
        }
        return fitness;
    }

    /**
     * *ª********************************************
     * ******** AINDA NÃO FUNCIONA COM O TSP ********
     * **********************************************
     */
    /***
     * Devolve um Hall of Fame com os melhores invididuos, a quantidade define no sizeHallFame,
     * mas esses individuos não se repetem, são todos individuos unicos na população
     * @param population
     * @param sizeHallOfFame
     * @return 
     */
    public static Population getHallOfFameWithoutDuplicateIndividuals(Population population, int sizeHallOfFame) {
        // Nova população que vai guardar os Hall Of Fame
        final Population __newPopulation =
                new Population(
                sizeHallOfFame,
                population.getSizeGenome(),
                population.getSizeGenotype(),
                population.getSizeAllelo(),
                population.getTypePopulation(),
                false);

        TreeSet<Individual> __population = new TreeSet<Individual>(new ComparatorIndividual());

        // Ordenar População pelo fitness de forma descendente
        //PopulationUtils.orderPopulation(population);

        // Devolve o numero de individuos que foram pedidos
        for (Individual __individualHallOfFame : population.getPopulation()) {
            __population.add(__individualHallOfFame);
        }

        PopulationUtils._createHallOfFame(__newPopulation, sizeHallOfFame, __population);

        // Devolve a população com os Hall Of Fame (melhores individuos)
        return __newPopulation;
    }

    public static Collection<Individual> getUniqueIndividuals(Population population, int fitness) {
        //Ordenação
        TreeSet<Individual> __population = new TreeSet<Individual>(new ComparatorIndividual());

        // Ordenar População pelo fitness de forma descendente
        //PopulationUtils.orderPopulation(population);

        // Devolve o numero de individuos que foram pedidos
        ArrayList<Individual> top = new ArrayList<Individual>();
        for (Individual __individualHallOfFame : population.getPopulation()) {
            if (__individualHallOfFame.fitness() == fitness) {//?maior ou 
                __population.add(__individualHallOfFame);
            }
        }
        // Devolve a população com os Hall Of Fame (melhores individuos)
        return __population;
    }
}
