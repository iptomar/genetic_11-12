package GeneticV01;

import java.util.ArrayList;

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

/**
 * Classe Individual, referente a cada individuo de uma determinada população.
 * Cada individuo é definido e diferenciado pelos seus cromossomas.
 * @author Ruben Felix <Ruben.Felix@gmail.com>
 */
public abstract class Individual {
    /**
     * ArrayList de cromossomas que especifica um determinado individuo.
     */
    private ArrayList<Chromosome> genome;
    /**
     * Variavel DEFAULT_SIZE que indica que, por defeito, um individuo
     * terá 1 cromossoma na sua definição genética.
     */
    private static final int DEFAULT_SIZE_GENOME = 1;
    /**
     * Variavél que receberá um número especifico de cromossoma que cada individuo
     * terá, caso não se pretenda o valor por defeito.
     */
    private int numberChromosomes = 0;

    
    public Individual() {
        this(DEFAULT_SIZE_GENOME);
    }

    public Individual(int numberChromosomes) {
        this.numberChromosomes = numberChromosomes;
        genome = new ArrayList<Chromosome>(numberChromosomes);
        this.inicializationChromosomes();
    }

    private void inicializationChromosomes() {
        for (int i = 0; i < this.numberChromosomes; i++) {
            this.genome.set(i, new Chromosome());
        }
    }

    public Chromosome getChromosome(int index) {
        return genome.get(index);
    }

    public void setChromosome(int index, Chromosome cromosome) {
        genome.add(index, cromosome);
    }

    public abstract int fiteness();
}
