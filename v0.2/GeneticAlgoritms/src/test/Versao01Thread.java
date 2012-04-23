/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import genetics.algorithms.OnesMax;
import genetics.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import operators.Operator;
import operators.mutation.Flipbit;
import operators.recombinations.Crossover;
import operators.selections.SUS;
import statistics.Statistics;
import utils.EventsSolver;
import utils.PopulationUtils;
import utils.exceptions.SolverException;

/**
 *
 * @author Chorinca-Notebook
 */
public class Versao01Thread extends Thread {

    public static void main(String[] args) {
        
        int __sizePopulation = 100; 
        int __sizeAllelo = 100;
        Individual __prototypeIndividual = new OnesMax();
        
        Population __population = new Population(
                __sizePopulation, 
                Population.DEFAULT_SIZE_GENOME, 
                Population.DEFAULT_SIZE_GENOTYPE, 
                __sizeAllelo, 
                __prototypeIndividual);
        
        
        /* ######################################## */
        /*                  THREADS                 */
        
        Thread[] __threads = new Thread[4];
        
        __threads[0] = new Versao01Thread(__population);        
        __threads[1] = new Versao01Thread(__population);
        __threads[2] = new Versao01Thread(__population);
        __threads[3] = new Versao01Thread(__population);

        __threads[0].start();
        __threads[1].start();        
        __threads[2].start();        
        __threads[3].start();        
    }
    
    // ######################################################################################
    
    private Population _population; 
    
    public Versao01Thread(Population population){
        _population = population;
    }
    
    @Override
    public void run() {
//        
//        // Parametros do Solver
//        
//        // Não esta preparado para multithread o StopCriterion, ou seja,
//        // neste exemplo abaixo o StopCriterion iam
//        // ser usados nas 4 threads ao mesmo tempo
//        int __iteractions = 500;
//        int __bestFitness = 100;
//        StopCriterion _stopCriterion = new StopCriterion(__iteractions, __bestFitness);
//        
//        // Operadores
//        // Não estão preparados para multithread os operadores, ou seja,
//        // neste exemplo abaixo os 4 objectos de operadores criados iam
//        // ser usados nas 4 threads ao mesmo tempo
//        ArrayList<Operator> _operators = new ArrayList<Operator>();     
//        _operators.add(new SUS(10));        
//        _operators.add(new Crossover());
//        _operators.add(new Flipbit(0.01));
//        _operators.add(new operators.replacements.Tournament(100, 2));
//        
//        // Instanciar solver
//        Solver _solver = new Solver(
//                _population, 
//                _stopCriterion, 
//                _operators, 
//                new EventsSolver() {
//
//            @Override
//            public void EventStartSolver() {
//                System.out.println("Solver Iniciou - " + Thread.currentThread().getName());
//            }
//
//            @Override
//            public void EventIteraction(int iteractionNumber, Population currentPopulation) {
//                // de 10 em 10 vai mostrar estatistica
////                if( (iteractionNumber % 10) == 0){
////                    System.out.println("--------------------------------------------------");
////                    System.out.println("Iteração:" + iteractionNumber);
////                    System.out.println("Variância: " + desvioPadrao.getVariancia().doubleValue());
////                    System.out.println("Média:" + desvioPadrao.getMedia().doubleValue());
////                    System.out.println("Desvio Padrão: " + desvioPadrao.getDesvioPadrao().doubleValue());
////                    System.out.println("--------------------------------------------------");
////                    System.out.println("");
////                }
//            }
//
//            @Override
//            public void EventFinishSolver(int totalIteracoes, Population lastPopulation) {
//                
//                Statistics __statistics = new Statistics(lastPopulation);
//                
//                StringBuilder __buffer = new StringBuilder();
//                
//                __buffer.append("\n");
//                __buffer.append("Solver Terminou - " + Thread.currentThread().getName());
//                __buffer.append("\n");
//                __buffer.append("--------------------------------------------------");
//                __buffer.append("\n");
//                __buffer.append("Total Iteração:" + totalIteracoes);
//                __buffer.append("\n");
//                __buffer.append("Variância: " + __statistics.getVariancia().doubleValue());
//                __buffer.append("\n");
//                __buffer.append("Média:" + __statistics.getMedia().doubleValue());
//                __buffer.append("\n");
//                __buffer.append("Desvio Padrão: " + __statistics.getDesvioPadrao().doubleValue());
//                __buffer.append("\n");
//                __buffer.append("--------------------------------------------------");
//                __buffer.append("\n");
//                __buffer.append("\n");
//                __buffer.append("Hall of Fame - Top 5");
//                __buffer.append("\n");
//                __buffer.append("--------------------------------------------------");
//                __buffer.append("\n");
//                __buffer.append(PopulationUtils.getHallOfFame(lastPopulation, 5));
//                __buffer.append("\n");
//                
//                System.out.println(__buffer);
//            }
//        });
//        
//        try {
//            // Correr o solver
//            _solver.run();
//        } catch (SolverException ex) {
//            Logger.getLogger(Versao01.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
}
