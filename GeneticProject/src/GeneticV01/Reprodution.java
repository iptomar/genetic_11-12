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
 * Classe que irá tratar da reprodução da população
 * @author Ruben Felix <Ruben.Felix@gmail.com>
 */
public class Reprodution {
    /**
     * Variavel que irá receber a população a ser reproduzida
     */
    private ArrayList<Individual> populationBeforeRepr;
    /**
     * Variavel que irá conter a população de filhos resultante da reprodução da população
     * dos pais
     */
    private ArrayList<Individual> populationAfterRepr;
    
    /**
     * Construtor por defeito da classe, que irá receber a população pai para se 
     * reproduzir
     * @param population (ArrayList<Individual>) - População pai para ser reproduzida
     */
    public Reprodution(ArrayList<Individual> population){
        populationBeforeRepr = population;
        populationAfterRepr = new ArrayList<Individual>(populationBeforeRepr.size());
    }   
    
    //FALTA IMPLEMENTAÇÃO
    public ArrayList<Individual> execute(){
        int numGenes = populationBeforeRepr.get(0).getChromosome(0).getNumberGenes();
        int trim = numGenes/2;
        
        return populationAfterRepr;
    }
}
