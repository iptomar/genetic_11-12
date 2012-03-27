package GeneticV01;

import java.util.ArrayList;
import java.util.Random;

/* -------------------------------------------------------------------------
 * -------------------------------------------------------------------------
 *  I n s t i t u t o   P o l i t e c n i c o   d e   T o m a r
 *   E s c o l a   S u p e r i o r   d e   T e c n o l o g i a
 *
 * @author Ruben Felix <Ruben.Felix@gmail.com>
 * -------------------------------------------------------------------------
 * Número de Aluno: 13691 
 * E-mail: Ruben.Felix@gmail.com
 * -------------------------------------------------------------------------
 * -------------------------------------------------------------------------
 */
public class Population {
     protected static final Random randomGenerator = new Random();

    private ArrayList<Individual> population;
    
    private int numberIndividuals;
    private int numberGenes;
    private int numberChromosomes;
    private Object typeIndividual;
    
    public Population(int numberIndividuals, int numberChromosomes, int numberGenes, Object typeIndividual){
        this.numberIndividuals = numberIndividuals;
        this.numberChromosomes = numberChromosomes;
        this.numberGenes = numberGenes;
        this.typeIndividual = typeIndividual;
        population = new ArrayList<Individual>(numberIndividuals);
    }
    
    private void inicializationPopulation(){
        for (int i = 0; i < this.numberIndividuals; i++) {
            if(this.typeIndividual instanceof onesMax) this.population.add((onesMax)this.typeIndividual);
        }
    }
   
    public Individual getIndividual(int index) {
        return population.get(index);
    }

    public void setIndividual(int index, Individual individual) {
        population.add(index, individual);
    }
    
    @Override
    public String toString(){
        StringBuilder value = new StringBuilder();
        for (Individual individual : population) {
            for (Object chromosome : individual) {
                for (Gene gene : (Chromosome)chromosome) {
                    value.append(gene.toString());
                }
            }
        }     
        return value.toString();
    }      
}
