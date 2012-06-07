package genetics.algorithms.functions;

import genetics.Individual;
import genetics.algorithms.FunctionBinary;

/**
 *
 * @author Chorinca-Notebook
 */
public class FunctionBinarySphere extends FunctionBinary {
    
//    public static void main(String[] args) throws SolverException {
//        ArrayList<Operator> operators = new ArrayList<Operator>(4);
//        Solver solver;
//        
//        operators.add(new Tournament(10, 2));
//        operators.add(new Invertion(0.03));
//        operators.add(new Crossover());
//        operators.add(new Truncation());
//
//        solver = new Solver(
//                new Population(10, 1, 2, 0, 
//                    new FunctionBinarySphere()),
//                new StopCriterion(2.0, 1, 30, StopCriterion.TYPE_PROBLEM_MAXIMIZATION), 
//                operators, 
//                new EventsSolver() {
//
//            @Override
//            public void EventStartSolver() {
//                // throw new UnsupportedOperationException("Not supported yet.");
//            }
//
//            @Override
//            public void EventIteraction(int iteractionNumber, Population currentPopulation) {
//                FunctionBinary x = (FunctionBinary)currentPopulation.getPopulation().get(0);
//                System.out.println("X: " + x.getVariavel(0) +  "\tY: " + x.getVariavel(1));
//            }
//
//            @Override
//            public void EventFinishSolver(int totalIteracoes, Population lastPopulation) {
//                FunctionBinary x = (FunctionBinary)lastPopulation.getPopulation().get(0);
//                System.out.println("X: " + x.getVariavel(0) +  "\tY: " + x.getVariavel(1));
//            }
//        });
//        
//        solver.run();
//    }
    
    public FunctionBinarySphere() {
        super(new double[] { -1, -1 }, new double[] { 1, 1 }, "");        
    }
    
    public FunctionBinarySphere(FunctionBinarySphere function) {
        super(function);        
    }
    
    @Override
    public double fitness() {
        double[] __variavels = new double[this._beginDomains.length];
        
        for (int i = 0; i < this._beginDomains.length; i++) {
            __variavels[i] = this.getVariavel(i);
        }
        
        return __variavels[0]*__variavels[0] + __variavels[1]*__variavels[1];
    }
    
    @Override
    public Individual clone() {
        return new FunctionBinarySphere(this);
    }
    
    @Override
    public String getInfo() {
        return "<p>Function Binary Sphere Best Fitness - 2.0</p>"
                + "<p>Default Stop Criteria: 2.0, 1, 30, 0</p>";
    }
}
