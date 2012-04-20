/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import genetics.Individual;
import genetics.Population;
import java.util.ArrayList;
import java.util.Collections;

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
        for (Individual __individualHallOfFame : population.getPopulation().subList(0, sizeHallOfFame)) {
            __newPopulation.addIndividual(__individualHallOfFame);
        }

        // Devolve a população com os Hall Of Fame (melhores individuos)
        return __newPopulation;
    }
    
    /**
     * Método que ordena o array de individuos da poipulação pelo seu fitness 
     */
    public static void orderPopulation(Population population) {
        // Ordenar População pelo fitness (isso esta definido no ComparatorIndividual) de forma 
        // descendente
        Collections.sort(population.getPopulation(), new ComparatorIndividual());
    }
    
    /**
     * Método que devolve o número de individúos que tem o fitness máximo na população
     * @return - Número de individuos na população que tem o valor de fitness máximo
     */
    public static int getNumberIndividualsWithBestFitness(Population population){
        int __bestFitness = 0;
        int __numberOfIndividualsWithBestFitness = 0;
        
        // Pede o melhor individuo atraves do metodo getHallOfFame com tamanho 1
        // depois escolhe o primeiro individuo e devolve o seu fitness
        __bestFitness = PopulationUtils.getBestFitness(population);
        
        // Corre a população toda à procura de individuos que tenham
        // o melhor fitness
        for (Individual __individuals : population) {
            if(__bestFitness == __individuals.fiteness()) __numberOfIndividualsWithBestFitness++;
        }
        
        // Devolve a quantidade de individuos encontrados
        return __numberOfIndividualsWithBestFitness;
    }
    
    /***
     * 
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

            __indexIndividual = Population.RANDOM_GENERATOR.nextInt(population.getSizePopulation() - 1);
            __newArrayIndividual.add(population.getPopulation().get(__indexIndividual).clone());

            if (removeIndividualFromPopulation) {
                population.getPopulation().remove(population.getPopulation().get(__indexIndividual));
            }

            __countIndividual++;
        }

        return __newArrayIndividual;
    }
    
    public static int getBestFitness(Population population) {
        final int NUMBER_INDIVIDUALS_TO_GET_FROM_HALL_OF_FAME = 1;        
        final int FIRST_INDIVIDUAL = 0;
        
        // Pede o melhor individuo atraves do metodo getHallOfFame com tamanho 1
        // depois escolhe o primeiro individuo e devolve o seu fitness
        return PopulationUtils.getHallOfFame(population, NUMBER_INDIVIDUALS_TO_GET_FROM_HALL_OF_FAME).getIndividual(FIRST_INDIVIDUAL).fiteness();
    }
    
     /**
     * Método que devolve o fitness total da população (soma do fitness de todos os individuos da população)
     * @return - Fitness total da população
     */
    public static int getFitnessTotal(Population population) {
        int __totalFitness = 0;
        
        for (Individual __individual : population) {
            __totalFitness += __individual.fiteness();
        }
        
        return __totalFitness;
    }
}
