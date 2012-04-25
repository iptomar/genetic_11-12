/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import genetics.Individual;
import genetics.Population;
import genetics.Solver;
import genetics.StopCriterion;
import genetics.algorithms.K50;
import genetics.algorithms.KnapSack;
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
 * @author Aurélien Mota
 */
public class Versao02 {
    
    public static void main(String[] args) {
        // Parametros do Solver
        
        // Operadores
        ArrayList<Operator> __operators = new ArrayList<Operator>();      
        __operators.add(new SUS(70));       
        __operators.add(new Crossover());
        __operators.add(new Flipbit(0.01));
        __operators.add(new operators.replacements.Tournament(100, 2));
        
        int __sizePopulation = 100; 
        int __sizeAllelo = 5; // Tem que se igual ao primeiro valor da data string do KnapSack, pois representam o mesmo valor
        
        // So que este 5 (tamanho allelo ou tamanho da tabela) é usado para fazer o parse dos dados
        Individual __prototypeIndividual = new KnapSack("5 15 4 2 1 10 2 12 2 1 4 1", KnapSack.ModeFunction.PENALTY, KnapSack.PENALTY_ORDER_QUADRATIC);
        
        int __iteractions = 1000;
        double __bestFitness = 1921.0;
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
            public void EventIteraction(int iteractionNumber, Population currentPopulation) {
                // de 10 em 10 vai mostrar estatistica
                if( (iteractionNumber % 10) == 0){
                    
                    Statistics __statistics = new Statistics(currentPopulation);
                    
                    System.out.println("--------------------------------------------------");
                    System.out.println("Iteração:" + iteractionNumber);
                    System.out.println("Variância: " + __statistics.getVarianciaPopulation().doubleValue());
                    System.out.println("Média:" + __statistics.getMediaFitnessPopulation().doubleValue());
                    System.out.println("Desvio Padrão: " + __statistics.getDesvioPadraoPopulation().doubleValue());
                    System.out.println("--------------------------------------------------");
                    System.out.println("");
                }
            }

            @Override
            public void EventFinishSolver(int totalIteracoes, Population lastPopulation) {
                
                Statistics __statistics = new Statistics(lastPopulation);
                
                System.out.println("Solver Terminou");
                System.out.println("--------------------------------------------------");
                System.out.println("Total Iteração:" + totalIteracoes);
                System.out.println("Variância: " + __statistics.getVarianciaPopulation().doubleValue());
                System.out.println("Média:" + __statistics.getMediaFitnessPopulation().doubleValue());
                System.out.println("Desvio Padrão: " + __statistics.getDesvioPadraoPopulation().doubleValue());
                System.out.println("--------------------------------------------------");
                System.out.println("");
                
                System.out.println("Hall of Fame - Top 5 - Unique");
                System.out.println("--------------------------------------------------");
                System.out.println(PopulationUtils.getHallOfFameWithoutDuplicateIndividuals(lastPopulation, 5));
                
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
