package GeneticV01;

import java.util.ArrayList;
import java.util.Iterator;

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
public class Chromosome implements Iterable<Gene>{
    /**
     * ArrayList de genes que será a definição de um cromossoma
     */
    private ArrayList<Gene> genotype;
    private static final int DEFAULT_SIZE_CHROMOSOME = 10;
    private int numberGenes;

    public Chromosome() {
        this(DEFAULT_SIZE_CHROMOSOME);
    }

    public Chromosome(int sizeChromosome) {
        this.genotype = new ArrayList<Gene>(sizeChromosome);
    }
    
    
    public Gene getGene(int index){
        return genotype.get(index);
    }
    
    public void setGene(int index, Gene gene){
        genotype.add(index, gene);
    }

    @Override
    public Iterator<Gene> iterator() {
        return this.genotype.iterator();
    }
}
