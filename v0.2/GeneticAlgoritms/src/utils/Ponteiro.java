/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import genetics.Individual;
import genetics.Population;
import operators.Genetic;

/**
 *
 * @author PedroIsi
 */
public class Ponteiro {
    
    //define um ponto aleatorio entre o e o total de fitness da população
    public static double pontoAleatorio(int totalFitnessPopulacao){
        return Genetic.RANDOM_GENERATOR.nextDouble() * (double)totalFitnessPopulacao;
    }
    
    //devolve individuo para onde o ponto aponta
    public static Individual devolveIndividuoParaOndeOPontoAponta(double ponteiro, Population population){
        int totalFitness = 0;
        
        //corre a população para cada individuo
        for (Individual individuo : population) {
            //incrementa o total fitness
            totalFitness += individuo.fiteness();
            
            //escolhe o individuo onde o ponteiro aponta 
            if(ponteiro <= (double)totalFitness) {
                //clone para criar um novo individuo e não ser individuo da pop original
                return individuo.clone();
            }
        }
        
        return null;
    }
    
}
