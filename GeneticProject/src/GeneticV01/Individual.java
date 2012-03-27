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
public abstract class Individual {
    /**
     * ArrayList de cromossomas que especifica um determinado individuo.
     */
    private ArrayList <Chromossome> genome; 
   
    private int size;
    
    public abstract void fitness();
    
    /**
     * Método que retorna um determinado cromossoma do individuo no qual o index é passado
     * por parametro
     * @param index (int) - Index do cromossoma a ser retirado do ArrayList de cromossomas
     * @return (Chromossome) - Cromossoma do individuo
     */
    public Chromossome getCromossoma(int index){
        if(genome.isEmpty()) return null;
        else return genome.get(index);
    }
    
    /**
     * Método que permite a inserção de um novo cromossoma no individuo
     * @param cromossoma (Chromossome) - Cromossoma a ser adicionado ao individuo
     */
    public void setCromossoma(Chromossome cromossoma){
        genome.add(cromossoma);
    }
    
    /**
     * Método que devolve o tamanho do geoma do individuo
     * @return size (int) - Tamanho do genoma do individuo
     */
    public int sizeCromossoma(){
        return size;
    }
}
