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
import utils.Benchmark;
import utils.EventsSolver;
import utils.PopulationUtils;
import utils.exceptions.SolverException;

/**
 *
 * @author Pedro Isidoro
 */
public class FunctionRealScherfelProblem extends FunctionReal {

    public static void main(String[] args) throws SolverException {
        ArrayList<Operator> operators = new ArrayList<Operator>(4);
        Solver solver;

        operators.add(new SUS(10));
        operators.add(new CrossoverAX(0.75));
        operators.add(new MutationGaussian(0.25));
        operators.add(new Truncation());

        solver = new Solver(
                new Population(10, 1, 2, 0,
                new FunctionRealScherfelProblem()),
                new StopCriterion(-450.0, 1, 5, StopCriterion.TYPE_PROBLEM_MINIMIZATION),
                operators,
                new EventsSolver() {

                    @Override
                    public void EventStartSolver() {
                        // throw new UnsupportedOperationException("Not supported yet.");
                    }

                    @Override
                    public void EventIteraction(int iteractionNumber, Population currentPopulation) {
                        if((iteractionNumber % 1000) == 0) {
                            FunctionRealScherfelProblem x = (FunctionRealScherfelProblem)PopulationUtils.getHallOfFame(currentPopulation, 1).getIndividual(0);
                            System.out.println("X: " + x.getVariavel(0) + "\tY: " + x.getVariavel(1) + "\tW: " + x.fitness());
                        }
                    }

                    @Override
                    public void EventFinishSolver(int totalIteracoes, Population lastPopulation) {
                        FunctionRealScherfelProblem x = (FunctionRealScherfelProblem)PopulationUtils.getHallOfFame(lastPopulation, 1).getIndividual(0);
                        System.out.println("X: " + x.getVariavel(0) + "\tY: " + x.getVariavel(1) + "\tW: " + x.fitness());                        
                    }
                });

        solver.run();
    }

    private Benchmark _benchmark;
    
    public FunctionRealScherfelProblem() {
        super(new double[]{-100, -100 }, new double[]{100, 100}, "");
        _benchmark = new Benchmark();
    }

    public FunctionRealScherfelProblem(FunctionRealScherfelProblem function) {
        super(function);
        _benchmark = new Benchmark();
    }

    @Override
    public double fitness() {
        double[] __variavels = new double[this._beginDomains.length];
        double __fitness;
        
        for (int i = 0; i < this._beginDomains.length; i++) {
            __variavels[i] = this.getVariavel(i);
        }

        __fitness = _benchmark.func(Benchmark.FUNC_SCHWEFEL_PROBLEM, __variavels.length, __variavels);
        
        return  __fitness;
    }
    
    @Override
    public Individual clone() {
        return new FunctionRealScherfelProblem(this);
    }
    
    @Override
    public String getInfo() {
        return "<p>Function Real Scherfel Problem Best Fitness - -450.0</p>";
    }
}
