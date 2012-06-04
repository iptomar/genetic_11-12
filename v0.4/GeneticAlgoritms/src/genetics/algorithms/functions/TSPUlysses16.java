/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genetics.algorithms.functions;

import genetics.Individual;
import genetics.Population;
import genetics.Solver;
import genetics.StopCriterion;
import genetics.algorithms.TSP;
import java.util.ArrayList;
import operators.Operator;
import operators.mutation.SwapGenes;
import operators.recombinations.PMX;
import operators.replacements.Truncation;
import operators.selections.SUS;
import utils.EventsSolver;
import utils.TSPProblem;
import utils.exceptions.SolverException;

/**
 *
 * @author Chorinca-Notebook
 */
public class TSPUlysses16 extends TSP {
    
    public static void main(String[] args) throws SolverException {
        ArrayList<Operator> operators = new ArrayList<Operator>(4);
        Solver solver;
        
        operators.add(new SUS(10));
        operators.add(new PMX(0.5));
        operators.add(new SwapGenes(0.05));
        operators.add(new Truncation());

        solver = new Solver(
                new Population(10, 1, 1, 16, 
                    new TSPUlysses16()),
                new StopCriterion(0.0, 1, 30, StopCriterion.TYPE_PROBLEM_MINIMIZATION), 
                operators, 
                new EventsSolver() {

            @Override
            public void EventStartSolver() {
            }

            @Override
            public void EventIteraction(int iteractionNumber, Population currentPopulation) {
                if((iteractionNumber%1000) == 0){
                    System.out.println("-------------");
                    System.out.println(currentPopulation.toString());
                    System.out.println("-------------");
                    System.out.println("");
                }
            }

            @Override
            public void EventFinishSolver(int totalIteracoes, Population lastPopulation) {
                System.out.println("");
                System.out.println("-------------");
                System.out.println(lastPopulation.toString());
                System.out.println("-------------");
                System.out.println("");
            }
        });
        
        solver.run();
    }
    
    public TSPUlysses16() {
        super();
        
        TSPProblem __tspProblem = new TSPProblem();
        __tspProblem.setFilename("C:\\tsp\\ulysses16.tsp");
        __tspProblem.read();
        
        this.costMatrix = __tspProblem.getCostMatrix();
    }
    
    public TSPUlysses16(TSPUlysses16 function) {
        super(function);        
    }
    
    @Override
    public Individual clone() {
        return new TSPUlysses16(this);
    }
    
}
