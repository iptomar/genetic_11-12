/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import genetics.Individual;
import genetics.OnesMax;

/**
 *
 * @author Chorinca-Notebook
 */
public class main {
    
    public static void main(String[] args) {
        boolean[] mask = {true, false, false, true};
        OnesMax parent1 = new OnesMax(); 
        OnesMax parent2 = new OnesMax();
        boolean[] allelop1 = {false, true, true, false};
        boolean[] allelop2 = {true, false, true, true};
       
                
                parent1.setSizeGenome(1);
                parent1.setSizeGenotype(1);
                parent1.setSizeAllelo(4);
                
                parent1.inicializationGenome();
                
                 parent2.setSizeGenome(1);
                parent2.setSizeGenotype(1);
                parent2.setSizeAllelo(4);
                
                parent2.inicializationGenome();
        
        parent1.getChromosome(0).getGene(0).setAllele(allelop1);
        parent2.getChromosome(0).getGene(0).setAllele(allelop2);
        
        Individual sonMask = parent1.clone();
        Individual daughterMask = parent2.clone();
        //Allelos de cada filho para que possa ser efectuada a troca
        boolean[] sonAllelo = (boolean[])sonMask.getChromosome(0).getGene(0).getAllele();
        boolean[] daughterAllelo = (boolean[])daughterMask.getChromosome(0).getGene(0).getAllele();
        //Percorre todas as posições do allelo
        for (int i = 0; i < mask.length; i++) {
            //faz a troca apenas se a máscara for igual a true
            if(mask[i]==true){
                boolean aux = sonAllelo[i];
                //Troca os booleans dos allelos do filho para a filha
                sonAllelo[i] = daughterAllelo[i];
                daughterAllelo[i] = aux;
            }
        }
        //Define os novos allelos para os filhos
        sonMask.getChromosome(0).getGene(0).setAllele(sonAllelo);
        daughterMask.getChromosome(0).getGene(0).setAllele(daughterAllelo);
        //Cria os novos objectos filhos
        Individual son1 = sonMask.clone();
        Individual son2 = daughterMask.clone();
        
        System.out.println("" + son1.toString());
        System.out.println("" + son2.toString());
    }
    
}
