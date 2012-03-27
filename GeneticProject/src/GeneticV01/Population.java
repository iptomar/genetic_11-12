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
public class Population {
    /**
     * ArrayList de individuos que formará a população
     */
    private ArrayList<Individual> pop;
    
    /**
     * Variavelque guarda o tamanho da população
     */
    private int size;
    
    /**
     * Método que retorna um determinado individuo da população
     * @param index (index) - Index da pessoa pretendida da ArrayList que contem a população
     * @return (Individual) - Individuo especificado pelo index pretendido na ArrayList da população
     */
    public Individual getPerson(int index){
        if(pop.isEmpty()) return null;
        else return pop.get(index);
    }
    
    /**
     * Método que permite a adição de uma nova pessoa na população
     * @param person (Individual) - Individuo a ser adicionado à população
     */
    public void setPerson(Individual person){
        pop.add(person);
    }
    
    /**
     * Método que devolve o tmanho da população
     * @return (int) - Tamanho da população
     */
    public int size(){
        return size;
    }
    
    //FALTA A DEFINIÇÃO DO MÉTODO: NÃO HÁ DOCUMENTAÇÃO!!!
    /**
     * 
     * @param size (int) - Tamanho da população a ser criada ao acaso
     */
    public void createRandom(int size){
        
    }
            
}
