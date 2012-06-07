package genetics.algorithms.functions;

import genetics.Individual;
import genetics.algorithms.FunctionReal;
import utils.Benchmark;

/**
 *
 * @author Pedro Isidoro
 */
public class FunctionRealShiftedGriewank extends FunctionReal {

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
//                new Population(10, 1, 2, 0,
//                new FunctionRealShiftedGriewank()),
//                new StopCriterion(-180.0, 1, 5, StopCriterion.TYPE_PROBLEM_MINIMIZATION),
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
//                        if((iteractionNumber % 1000) == 0) {
//                            FunctionRealShiftedGriewank x = (FunctionRealShiftedGriewank)PopulationUtils.getHallOfFame(currentPopulation, 1).getIndividual(0);
//                            System.out.println("X: " + x.getVariavel(0) + "\tY: " + x.getVariavel(1) + "\tW: " + x.fitness());
//                        }
//                    }
//
//                    @Override
//                    public void EventFinishSolver(int totalIteracoes, Population lastPopulation) {
//                        FunctionRealShiftedGriewank x = (FunctionRealShiftedGriewank)PopulationUtils.getHallOfFame(lastPopulation, 1).getIndividual(0);
//                        System.out.println("X: " + x.getVariavel(0) + "\tY: " + x.getVariavel(1) + "\tW: " + x.fitness());                        
//                    }
//                });
//
//        solver.run();
//    }

    private Benchmark _benchmark;
    
    public FunctionRealShiftedGriewank() {
        super(new double[]{-600, -600 }, new double[]{600, 600}, "");
        _benchmark = new Benchmark();
    }

    public FunctionRealShiftedGriewank(FunctionRealShiftedGriewank function) {
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

        __fitness = _benchmark.func(Benchmark.FUNC_SHIFTED_GRIEWANK, __variavels.length, __variavels);
        //__fitness = this._logicFunction(__variavels, 1.0, new double[] { 0, 0 });
        
        return  __fitness;
    }
    
    @Override
    public Individual clone() {
        return new FunctionRealShiftedGriewank(this);
    }
    
    @Override
    public String getInfo() {
        return "<p>Function Real Shifted Griewank Best Fitness - -180.0</p>"
                + "Default Stop Criteria: -180.0, 1, 5, 1";
    }
}
