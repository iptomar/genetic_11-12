/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import genetics.Population;
import operators.recombinations.UniformCrossover;

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
 * ERRO NA ORDENAÇÃO DAS POPULAÇÕES FILHOS
 * @author Ruben Felix <Ruben.Felix@gmail.com>
 */
public class Error {
    public static void main(String[] args) {
        Population parents = new Population(10, 1, 1, 10, new genetics.algorithms.OnesMax());
        Population sons;
        UniformCrossover crossover = new UniformCrossover();
        
        //Erro por resolver: Ordena a população parents sem qualquer problema
        //Já na população filho, erro:
        //Exception in thread "main" java.lang.ClassCastException: [Z cannot be cast to [Ljava.lang.Boolean;
        utils.PopulationUtils.orderPopulation(parents);
        sons = crossover.execute(parents);
        utils.PopulationUtils.orderPopulation(sons);
        System.out.println("dsfgdfg");
    }
}
