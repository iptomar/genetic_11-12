package utils;

import genetics.Individual;
import java.util.Comparator;

/**
 *
 * @author Chorinca-Notebook
 */
public class ComparatorIndividualFitness implements Comparator<Individual> {

    @Override
    public int compare(Individual o1, Individual o2) {
        double fitnessO1 = o1.fitness();
        double fitnessO2 = o2.fitness();
        
        if(fitnessO1 < fitnessO2)
            return 1;
        else if(fitnessO1 >  fitnessO2)
            return -1;
        else
            return 0;
        
    }
    
}
