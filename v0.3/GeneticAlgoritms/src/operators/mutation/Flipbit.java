package operators.mutation;

import genetics.Chromosome;
import genetics.Gene;
import genetics.Individual;
import genetics.Population;
import operators.Genetic;
/**
 *
 * @author Chorinca-Notebook
 */
public class Flipbit extends Mutation {

    public Flipbit() {
        this(Mutation.PROBABILITY_BY_DEFAULT);
    }
    
    public Flipbit(double probability) {
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
                for (Gene<Boolean[]> __genes : __chromosomes) {
                    __genes.setAllele(this._doFlipBit(__genes.getAllele()));
                }
            }
            
            __newPopulation.addIndividual(__individuals);
        }

        return __newPopulation;
    }
    
    private Boolean[] _doFlipBit(Boolean[] allelo){
        Boolean[] __newAllelo = new Boolean[allelo.length];
        
        for (int __indexAlleloValue = 0; __indexAlleloValue < __newAllelo.length; __indexAlleloValue++) {
            if(Genetic.RANDOM_GENERATOR.nextDouble() < super.probability) {
                __newAllelo[__indexAlleloValue] = !allelo[__indexAlleloValue];
            }else{
                __newAllelo[__indexAlleloValue] = allelo[__indexAlleloValue];
            }
        }
        
        return __newAllelo;
    } 
    
    //*********************************************************************************
    //*****************************Métodos para Reflection*****************************
    //*********************************************************************************    
    
    @Override
    public String getInfo() {
        String s = "!!!";

        return s;
    }

    
    @Override
    public boolean setParameters(String parameters) {
        int dimensaoPop = Integer.parseInt(parameters.split(" ")[0]);
        int tamanhoTorneio= Integer.parseInt(parameters.split(" ")[1]);
        
        try{           
           return true;
       }catch(Exception ex){
           //parametos por defeito
           return false;
       }
    }    
    //*********************************************************************************
    //*********************************************************************************
    //********************************************************************************* 
}
