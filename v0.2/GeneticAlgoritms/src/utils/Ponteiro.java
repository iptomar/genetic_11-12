/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import genetics.Individual;
import genetics.Population;
import operators.Genetic;
import utils.exceptions.PonteiroForaDoLimiteException;

/**
 *
 * @author PedroIsi
 */
public class Ponteiro {
    
    //define um ponto aleatorio entre zero e o total de fitness da população
    public static double pontoAleatorio(int totalFitnessPopulacao){
        return Genetic.RANDOM_GENERATOR.nextDouble() * (double)totalFitnessPopulacao;
    }
    
    //devolve individuo para onde o ponto aponta
    public static Individual devolveIndividuoParaOndeOPonteiroAponta(double ponteiro, Population population) throws PonteiroForaDoLimiteException{
        int totalFitness = 0;
        
        // verificar se o ponteiro é maior que o total de fitness da população ou
        // se é menor que zero, se sim dispar uma excepção do tipo PonteiroForaDoLimiteException
        if(ponteiro > PopulationUtils.getFitnessTotal(population) || ponteiro < 0) 
            throw new PonteiroForaDoLimiteException("Ponteiro dentro da class Ponteiro esta fora dos limites do total de fitness da população.");
        
        //corre a população para cada individuo
        for (Individual individuo : population) {
            //incrementa o total fitness
            totalFitness += individuo.fitness();
            
            //escolhe o individuo onde o ponteiro aponta 
            if(ponteiro <= (double)totalFitness) {
                //clone para criar um novo individuo e não ser individuo da pop original
                return individuo.clone();
            }
        }
        
        return null;
    }
    
}
