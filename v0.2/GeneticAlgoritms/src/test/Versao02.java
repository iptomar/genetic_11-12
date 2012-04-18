/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import genetics.KnapSack;
import genetics.Population;
import genetics.Solver;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import operators.Operator;
import operators.mutation.Flipbit;
import operators.recombinations.Crossover2;
import operators.selections.SUS;
import utils.EventsSolver;
import utils.exceptions.SolverException;

/**
 *
 * @author Aurélien Mota
 */
public class Versao02 {
    
    public static void main(String[] args) {
 
        // Operadores
        ArrayList<Operator> operators = new ArrayList<Operator>();     
        operators.add(new SUS(10));        
        operators.add(new Crossover2());
        operators.add(new Flipbit(0.01));
        operators.add(new operators.replacements.Tournament(100, 2));
        
        // Instanciar solver
        Solver solver = new Solver(100, 30, new KnapSack(), 1000, 90, operators, new EventsSolver() {

            @Override
            public void EventStartSolver() {
                //throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void EventIteraction(int iteractionNumber, Population currentPopulation) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void EventFinishSolver(Population lastPopulation) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        
        
        try {
            // Correr o solver
            solver.run();
        } catch (SolverException ex) {
            Logger.getLogger(Versao02.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
