package utils;

import genetics.Individual;
import java.util.Comparator;

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
 * Comparador para devolver qual a cidade com melhor fitness - As cidades diferenciam-se do resto dos algoritmos
 * já que as mesmas tem o fitness minimizado. Uma cidade com menor fitness é melhor que uma cidade com melhor fitness.
 * @author Ruben Felix <Ruben.Felix@gmail.com>
 */
public class ComparatorIndividualTSP implements Comparator<Individual> {

    @Override
    public int compare(Individual o1, Individual o2) {
        double fitnessO1 = o1.fitness();
        double fitnessO2 = o2.fitness();

        if(fitnessO1 < fitnessO2)
            return -1;
        else if(fitnessO1 >  fitnessO2)
            return 1;
        else
            return 0;
    }
}
