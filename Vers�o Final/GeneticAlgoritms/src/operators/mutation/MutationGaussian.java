package operators.mutation;

import genetics.Chromosome;
import genetics.Individual;
import genetics.Population;
import genetics.algorithms.FunctionReal;
import operators.Genetic;

/**
 *
 * @author Chorinca-Notebook
 */
public class MutationGaussian extends Mutation {

    final double SIZE = 0.01;
    
    public MutationGaussian() {
        this(Mutation.PROBABILITY_BY_DEFAULT);
    }

    public MutationGaussian(double probability) {
        super.probability = probability;
    }

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

        for (Individual __individuals : population) {
            for (Chromosome __chromosomes : __individuals) {
                for (int __genesIndex = 0; __genesIndex < __chromosomes.getGenotype().size(); __genesIndex++) {
                    if (Genetic.RANDOM_GENERATOR.nextDouble() < super.probability) {
                        __chromosomes.getGenotype().get(__genesIndex).setAllele(
                                this._performMutation(
                                    (Double)__chromosomes.getGenotype().get(__genesIndex).getAllele(),
                                    ((FunctionReal)__individuals).getSizeDomains(__genesIndex)
                                ));
                    }
                }
            }

            __newPopulation.addIndividual(__individuals);
        }

        return __newPopulation;
    }

    private double _performMutation(double allelo, double domain) {
        double __newAllelo;
        double __mutation;
        
        //calulate mutation whith gaussian distribution
        __mutation = domain * SIZE * Genetic.RANDOM_GENERATOR.nextGaussian();
        __newAllelo = allelo + __mutation;

        return __newAllelo;
    }

    //*********************************************************************************
    //*****************************Métodos para Reflection*****************************
    //*********************************************************************************    
    @Override
    public String getInfo() {
        String s = "<p>Método usado para fazer a mutação de indivíduos a uma população.</p>"
                + "<p>Este método tem como parâmetro de entrada a probabilidade de mutação de um individuo.</p> "
                + "<p>A probabilidade é passado como valor real ou seja, um valor entre 0 e 1 em que, por exemplo,</p>"
                + "<p>o parametro 0.40 representa 40% de probabilidade de mutação do individuo.</p>";
        return s;
    }

    @Override
    public boolean setParameters(String parameters) {
        try {
            double probabil = Double.parseDouble(parameters.split(" ")[0]);
            this.probability = probabil;
            return true;
        } catch (Exception ex) {
            //Se algo correr mal, devolve false.
            return false;
        }
    }
    //*********************************************************************************
    //*********************************************************************************
    //********************************************************************************* 
}
