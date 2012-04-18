package test;

import genetics.KnapSack;
import utils.Mochila;

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
public class KnapSackTest {
    public static void main(String[] args) {
        //---------- TESTE NÚMERO 1 -----------
        
        //Definição dos arrays de peso e de valor
        int[] peso = {10,9,5,3};
        int[] valor = {5,6,3,10};
        //Problema da mochila
        Mochila mochila = new Mochila(25,peso,valor);
        //Mochila sem penalização, o que quer dizer que os individuos, caso ultrapassem o peso máximo, terão que ser reparados
        mochila.setPenalizacao(false);
        //Individuo do tipo KnapSack
        KnapSack Indiv1 = new KnapSack(mochila);
        //Size do alelo para 4 apenas
        Indiv1.setSizeAllelo(4);
        //Inicializa o genoma do individuo
        Indiv1.inicializationGenome();
        //Array de booleans para fazer o fitness do individuo
        boolean[] fitness = {true,true,true,true};
        
        Indiv1.getChromosome(0).getGene(0).setAllele(fitness);
        System.out.println("Fitness do Individuo 1: " + Indiv1.fiteness());
        
        
        //---------- TESTE NÚMERO 2 -----------
        
        //Definição dos arrays de peso e de valor
        int[] peso2 = {5,10,3,2,4,20,1,24};
        int[] valor2 = {10,4,3,25,10,15,7,2};
        //Problema da mochila
        Mochila mochila2 = new Mochila(25,peso2,valor2);
        //Mochila sem penalização, o que quer dizer que os individuos, caso ultrapassem o peso máximo, terão que ser reparados
        mochila2.setPenalizacao(false);
        //Individuo do tipo KnapSack
        KnapSack Indiv2 = new KnapSack(mochila2);
        //Size do alelo para 4 apenas
        Indiv2.setSizeAllelo(8);
        //Inicializa o genoma do individuo
        Indiv2.inicializationGenome();
        //Array de booleans para fazer o fitness do individuo
        boolean[] fitness2 = {false,false,false,false,false,true,false,true};
        
        Indiv2.getChromosome(0).getGene(0).setAllele(fitness2);
        System.out.println("Fitness do Individuo 2: " + Indiv2.fiteness());
        
        
    }
}
