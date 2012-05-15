package utils;

import genetics.Individual;
import genetics.Population;
import operators.Genetic;
import utils.exceptions.PonteiroForaDoLimiteException;

/**
 * Classe Ponteiro que será um ponteiro para a população. Este ponteiro será calculado aleatóriamente e apontará
 * para um individuo da população passada por parametro.
 * @author PedroIsi
 */
public class Ponteiro {
    
    /**
     * Método que devolve um ponto aleatorio entre 0 e o total de fitness da população
     * @param totalFitnessPopulacao (int) - Total de fitness da população
     * @return (double) - Valor apontado ao acaso pelo método (entre 0 e o fitness total da população)
     */
    public static double pontoAleatorio(double totalFitnessPopulacao){
        return Genetic.RANDOM_GENERATOR.nextDouble() * (double)totalFitnessPopulacao;
    }
    
    //devolve individuo para onde o ponto aponta
    /**
     * Método que devolve o individuo para onde o ponteiro aponta
     * @param ponteiro (double) - Valor entre 0 e o fitness total da população, sendo que este será o valor que irá apontar para um individuo em especifico
     * @param population (Population) - População na qual será aplicado o ponteiro
     * @return (Individual) - Individuo para o qual o ponteiro apontou
     * @throws PonteiroForaDoLimiteException - Excepção caso o ponteiro aponte para "o vazio"
     */
    public static Individual devolveIndividuoParaOndeOPonteiroAponta(double ponteiro, Population population) throws PonteiroForaDoLimiteException{
        int totalFitness = 0;
        // verificar se o ponteiro é maior que o total de fitness da população ou
        // se é menor que zero; Se sim, dispara uma excepção do tipo PonteiroForaDoLimiteException
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
        //Caso não existam individuos na população recebida, devolve null
        return null;
    }
}
