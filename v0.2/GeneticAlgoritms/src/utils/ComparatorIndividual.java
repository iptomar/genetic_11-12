/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import genetics.Individual;
import java.util.Comparator;

/**
 *
 * @author Chorinca-Notebook
 */
public class ComparatorIndividual implements Comparator<Individual> {

    @Override
    public int compare(Individual o1, Individual o2) {
        int fitnessO1 = o1.fiteness();
        int fitnessO2 = o2.fiteness();
        
        if(fitnessO1 < fitnessO2)
            return 1;
        else if(fitnessO1 >  fitnessO2)
            return -1;
        else
            return 0;
        
    }
    
}
