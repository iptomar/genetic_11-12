/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import genetics.Individual;
import genetics.Population;
import java.util.AbstractCollection;
import java.util.ArrayList;
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
        // Ordenar População pelo fitness (isso esta definido no ComparatorIndividual) de forma 
        // descendente
        Collections.sort(population.getPopulation(), new ComparatorIndividualFitness());
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
        int __numberIndividualToReturn = numberIndividual;        // Escolher de forma aleatoria varios individuos                
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
     * @return (int) - Fitness total da população
     */
    public static int getFitnessTotal(Population population) {
        int __totalFitness = 0;

        for (Individual __individual : population) {
            __totalFitness += __individual.fitness();
        }

        return __totalFitness;
    }

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
}
