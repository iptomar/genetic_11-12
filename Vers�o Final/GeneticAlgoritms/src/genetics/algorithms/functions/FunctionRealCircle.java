/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genetics.algorithms.functions;

import genetics.Individual;
import genetics.Population;
import genetics.Solver;
import genetics.StopCriterion;
import genetics.algorithms.FunctionReal;
import java.util.ArrayList;
import operators.Operator;
import operators.mutation.MutationGaussian;
import operators.recombinations.CrossoverAX;
import operators.replacements.Truncation;
import operators.selections.SUS;
import utils.EventsSolver;
import utils.PopulationUtils;
import utils.exceptions.SolverException;

/**
 *
 * @author Pedro Isidoro
 */
public class FunctionRealCircle extends FunctionReal {

//    public static void main(String[] args) throws SolverException {
//        ArrayList<Operator> operators = new ArrayList<Operator>(4);
//        Solver solver;
//
//        operators.add(new SUS(10));
//        operators.add(new CrossoverAX(0.75));
//        operators.add(new MutationGaussian(0.25));
//        operators.add(new Truncation());
//
//        solver = new Solver(
//                new Population(10, 1, 1, 0,
//                new FunctionRealCircle()),
//                new StopCriterion(-0.5, 1, 5, StopCriterion.TYPE_PROBLEM_MINIMIZATION),
//                operators,
//                new EventsSolver() {
//
//                    @Override
//                    public void EventStartSolver() {
//                        // throw new UnsupportedOperationException("Not supported yet.");
//                    }
//
//                    @Override
//                    public void EventIteraction(int iteractionNumber, Population currentPopulation) {
//                        FunctionRealCircle x = (FunctionRealCircle)PopulationUtils.getHallOfFame(currentPopulation, 1).getIndividual(0);
//                        System.out.println("X: " + x.getVariavel(0) + "\tY: " + x.fitness());
//                    }
//
//                    @Override
//                    public void EventFinishSolver(int totalIteracoes, Population lastPopulation) {
//                        FunctionRealCircle x = (FunctionRealCircle)PopulationUtils.getHallOfFame(lastPopulation, 1).getIndividual(0);
//                        System.out.println("X: " + x.getVariavel(0));
//                    }
//                });
//
//        solver.run();
//    }

    public FunctionRealCircle() {
        super(new double[]{-0.5}, new double[]{0.5}, "");
    }

    public FunctionRealCircle(FunctionRealCircle function) {
        super(function);
    }

    @Override
    public double fitness() {
        double[] __variavels = new double[this._beginDomains.length];
        double __fitness;
        
        for (int i = 0; i < this._beginDomains.length; i++) {
            __variavels[i] = this.getVariavel(i);
        }

        __fitness = this._logicFunction(__variavels[0], 1.0, 0.0);
        
        return  __fitness;
    }

    private double _logicFunction(double point, double scale, double centre) {
        double radius = scale / 2.0D;
        double radsq = radius * radius;
        double distx = Math.abs(point - centre);
        double distxSq = distx * distx;
        double value = 0.0D;
        
        if (distxSq < radsq) {
            value = -Math.sqrt(radsq - distxSq);
        }
        return value;
    }
    
    @Override
    public Individual clone() {
        return new FunctionRealCircle(this);
    }
    
    @Override
    public String getInfo() {
        return "<p>Function Real Circle Best Fitness - -0.5</p>"
                + "<p>Default Stop Criteria: -0.5, 1, 5, 1</p>";
    }
}
