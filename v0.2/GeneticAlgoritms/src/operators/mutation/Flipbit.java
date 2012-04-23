/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package operators.mutation;

import genetics.Chromosome;
import genetics.Gene;
import genetics.Individual;
import genetics.Population;
import operators.Genetic;

/**
 * Operador Flipbit que escolhe um bit de forma aleatoria e calcula a probabilidade
 * desse bit ser trocado para o estado oposto
 * @author Chorinca-Notebook
 */
public class Flipbit extends Mutation {

    /**
     * Construtor por defeito onde passa a probabilidade de mutação
     * por defeito
     */
    public Flipbit() {
        this(Mutation.PROBABILITY_BY_DEFAULT);
    }
    
    /**
     * Construtor do Flipbit
     * @param probability Probabilidade de mutação
     */
    public Flipbit(double probability) {
        super.probability = probability;
    }
    
    /**
     * Executa o operador Flipbit
     * @param population População que vai ser mutada
     * @return Devolve população mutada
     */
    @Override
    public Population execute(Population population) {
        // nova população a ser criada com os individuos mutados ou não
        final Population __newPopulation = 
                new Population(
                    population.getSizePopulation(), 
                    population.getSizeGenome(), 
                    population.getSizeGenotype(),
                    population.getSizeAllelo(),
                    population.getTypePopulation(), 
                    false);

        // Percorre o individuo ate ao gene
        for (Individual __individuals : population) {
            for (Object __chromosomes : __individuals) {
                for (Gene<Boolean[]> __genes : (Chromosome)__chromosomes) {
                    // aplica a mutação ao gene
                    __genes.setAllele(this._doFlipBit(__genes.getAllele()));
                }
            }
            
            // adiciona o individuo à nova população, tendo sido, ou não, mutado
            __newPopulation.addIndividual(__individuals);
        }

        return __newPopulation;
    }
    
    /**
     * Percorre dos os allelos e calcula a probabilidade de esse allelo ver o seu
     * estado ser trocado
     * @param allelo Passa o allelo do gene
     * @return Devolve o gene ja mutado
     */
    private Boolean[] _doFlipBit(Boolean[] allelo){
        Boolean[] __newAllelo = new Boolean[allelo.length];
        
        // Percorre todos os allelos contidos no gene
        for (int __indexAlleloValue = 0; __indexAlleloValue < __newAllelo.length; __indexAlleloValue++) {
            // calcula a probabilidade de ser mutado
            if(Genetic.RANDOM_GENERATOR.nextDouble() < super.probability) {
                // muta o allelo
                __newAllelo[__indexAlleloValue] = !allelo[__indexAlleloValue];
            }else{
                // não muta o allelo
                __newAllelo[__indexAlleloValue] = allelo[__indexAlleloValue];
            }
        }
        
        // devolve o gene mutado
        return __newAllelo;
    } 
}
