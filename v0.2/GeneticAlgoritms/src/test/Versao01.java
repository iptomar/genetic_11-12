/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import genetics.OnesMax;
import genetics.Population;
import genetics.Solver;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import operators.Operator;
import operators.mutation.Flipbit;
import operators.recombinations.Crossover2;
import operators.selections.SUS;
import statistics.DesvioPadrao;
import utils.EventsSolver;
import utils.exceptions.SolverException;

/**
 *
 * @author Aurélien Mota
 */
public class Versao01 {
    
//    public static void main(String[] args) {
// 
//        // Operadores
//        ArrayList<Operator> operators = new ArrayList<Operator>();     
//        operators.add(new SUS(10));        
//        operators.add(new Crossover2());
//        operators.add(new Flipbit(0.01));
//        operators.add(new operators.replacements.Tournament(100, 2));
//        
//        // Instanciar solver
//        Solver solver = new Solver(100, 100, new OnesMax(), 10000, 99, operators, new EventsSolver() {
//
//            @Override
//            public void EventStartSolver() {
//                System.out.println("Solver Iniciou");
//            }
//
//            @Override
//            public void EventIteraction(int iteractionNumber, Population currentPopulation, DesvioPadrao desvioPadrao) {
//                // de 10 em 10 vai mostrar estatistica
//                if( (iteractionNumber % 10) == 0){
//                    System.out.println("--------------------------------------------------");
//                    System.out.println("Iteração:" + iteractionNumber);
//                    System.out.println("Variância: " + desvioPadrao.getVariancia().doubleValue());
//                    System.out.println("Média:" + desvioPadrao.getMedia().doubleValue());
//                    System.out.println("Desvio Padrão: " + desvioPadrao.getDesvioPadrao().doubleValue());
//                    System.out.println("--------------------------------------------------");
//                    System.out.println("");
//                }
//            }
//
//            @Override
//            public void EventFinishSolver(int totalIteracoes, Population lastPopulation, DesvioPadrao desvioPadrao) {
//                System.out.println("Solver Terminou");
//                System.out.println("--------------------------------------------------");
//                System.out.println("Total Iteração:" + totalIteracoes);
//                System.out.println("Variância: " + desvioPadrao.getVariancia().doubleValue());
//                System.out.println("Média:" + desvioPadrao.getMedia().doubleValue());
//                System.out.println("Desvio Padrão: " + desvioPadrao.getDesvioPadrao().doubleValue());
//                System.out.println("--------------------------------------------------");
//                System.out.println("");
//                
//                System.out.println("Hall of Fame");
//                System.out.println("--------------------------------------------------");
//                System.out.println(lastPopulation.getHallOfFame(5));
//            }
//        });
//        
//        
//        try {
//            // Correr o solver
//            solver.run();
//        } catch (SolverException ex) {
//            Logger.getLogger(Versao01.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
}
