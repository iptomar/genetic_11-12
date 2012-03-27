package GeneticV01;

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
public class onesMax extends Individual{
    static final int SIZE_VALUE_DEFAULT = 10;
    private int sizeValue = SIZE_VALUE_DEFAULT;
    
    public onesMax(int sizeValue){
        this.sizeValue = sizeValue;
    }
    
    @Override
    public int fiteness() {
        Integer numberOnes = new Integer(0);
        for (Chromosome chro : this) {
            for (Gene gene : chro) {
                if ((Boolean) gene.getValue()) {
                    numberOnes++;
                }
            }
        }
        return numberOnes;
    }
}
