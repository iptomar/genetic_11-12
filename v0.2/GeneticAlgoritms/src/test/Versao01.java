/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import genetics.OnesMax;
import genetics.Population;
import genetics.Solver;
import genetics.StopCriterion;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import operators.Operator;
import operators.mutation.Flipbit;
import operators.recombinations.Crossover;
import operators.selections.SUS;
import statistics.DesvioPadrao;
import utils.EventsSolver;
import utils.PopulationUtils;
import utils.exceptions.SolverException;

/**
 *
 * @author Aurélien Mota
 */
public class Versao01 {
    
    public static void main(String[] args) {
        
        // Parametros do Solver
        
        // Operadores
        ArrayList<Operator> __operators = new ArrayList<Operator>();     
        __operators.add(new SUS(10));        
        __operators.add(new Crossover());
        __operators.add(new Flipbit(0.01));
        __operators.add(new operators.replacements.Tournament(100, 2));
        
        int __sizePopulation = 100; 
        int __sizeAllelo = 100;
        Class __prototypeIndividual = OnesMax.class;
        
        int __iteractions = 10000;
        int __bestFitness = 99;
        StopCriterion __stopCriterion = new StopCriterion(__iteractions, __bestFitness);
        
        // Instanciar solver
        Solver solver = new Solver(
                __sizePopulation, 
                __sizeAllelo, 
                __prototypeIndividual, 
                __stopCriterion, 
                __operators, 
                new EventsSolver() {

            @Override
            public void EventStartSolver() {
                System.out.println("Solver Iniciou");
            }

            @Override
            public void EventIteraction(int iteractionNumber, Population currentPopulation, DesvioPadrao desvioPadrao) {
                // de 10 em 10 vai mostrar estatistica
                if( (iteractionNumber % 10) == 0){
                    System.out.println("--------------------------------------------------");
                    System.out.println("Iteração:" + iteractionNumber);
                    System.out.println("Variância: " + desvioPadrao.getVariancia().doubleValue());
                    System.out.println("Média:" + desvioPadrao.getMedia().doubleValue());
                    System.out.println("Desvio Padrão: " + desvioPadrao.getDesvioPadrao().doubleValue());
                    System.out.println("--------------------------------------------------");
                    System.out.println("");
                }
            }

            @Override
            public void EventFinishSolver(int totalIteracoes, Population lastPopulation, DesvioPadrao desvioPadrao) {
                System.out.println("Solver Terminou");
                System.out.println("--------------------------------------------------");
                System.out.println("Total Iteração:" + totalIteracoes);
                System.out.println("Variância: " + desvioPadrao.getVariancia().doubleValue());
                System.out.println("Média:" + desvioPadrao.getMedia().doubleValue());
                System.out.println("Desvio Padrão: " + desvioPadrao.getDesvioPadrao().doubleValue());
                System.out.println("--------------------------------------------------");
                System.out.println("");
                
                System.out.println("Hall of Fame - Top 5");
                System.out.println("--------------------------------------------------");
                System.out.println(PopulationUtils.getHallOfFame(lastPopulation, 5));
            }
        });
        
        
        try {
            // Correr o solver
            solver.run();
        } catch (SolverException ex) {
            Logger.getLogger(Versao01.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
