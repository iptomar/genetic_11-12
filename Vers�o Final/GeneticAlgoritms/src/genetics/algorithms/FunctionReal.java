/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genetics.algorithms;

import genetics.Chromosome;
import genetics.Individual;
import genetics.Population;
import genetics.Solver;
import genetics.StopCriterion;
import java.io.Serializable;
import java.util.ArrayList;
import operators.Operator;
import operators.mutation.MutationGaussian;
import operators.recombinations.CrossoverAX;
import operators.replacements.Truncation;
import operators.selections.Tournament;
import utils.EventsSolver;
import utils.exceptions.SolverException;

/**
 *
 * @author Aurélien Mota
 */
public class FunctionReal extends Individual implements Serializable{
    
//    public static void main(String[] args) throws SolverException {
//        ArrayList<Operator> operators = new ArrayList<Operator>(4);
//        Solver solver;
//        
//        operators.add(new Tournament(10, 2));
//        operators.add(new CrossoverAX(0.75));
//        operators.add(new MutationGaussian(0.25));
//        operators.add(new Truncation());
//
//        solver = new Solver(
//                new Population(10, 1, 2, 0, 
//                    new FunctionReal(
//                        new double[] { -1, -1 }, 
//                        new double[] { 1, 1 }, 
//                        "variavels[0]*variavels[0]+variavels[1]*variavels[1]")),
//                new StopCriterion(2.0, 1, 20, StopCriterion.TYPE_PROBLEM_MAXIMIZATION), 
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
//                FunctionReal x = (FunctionReal)currentPopulation.getPopulation().get(0);
//                System.out.println("X: " + x.getVariavel(0) +  "\tY: " + x.getVariavel(1));
//            }
//
//            @Override
//            public void EventFinishSolver(int totalIteracoes, Population lastPopulation) {
//                FunctionReal x = (FunctionReal)lastPopulation.getPopulation().get(0);
//                System.out.println("X: " + x.getVariavel(0) +  "\tY: " + x.getVariavel(1));
//            }
//        });
//        
//        solver.run();
//    }
    
    protected double[]    _beginDomains;    
    protected double[]    _endDomains;    
    protected double[]    _sizeDomains; 
    
    //Expressão matemática
    private String _scriptFunctionMath;
    
    public FunctionReal(FunctionReal function){
        this(function._beginDomains,
             function._endDomains, 
             function._scriptFunctionMath);
        
        if(function.getGenome().size() > 0){
            Chromosome __chromosome = new Chromosome(this);
            this.setChromosome(0, __chromosome);
        }
    }
    
    public FunctionReal(double[] beginDomains, double[] endDomains, String scriptFunctionMath){
        this._beginDomains  = new double[beginDomains.length];
        this._endDomains    = new double[endDomains.length];
        this._sizeDomains   = new double[beginDomains.length];

        System.arraycopy(beginDomains, 0, this._beginDomains, 0, beginDomains.length);
        System.arraycopy(endDomains, 0, this._endDomains, 0, endDomains.length);
        
        this._sizeDomains = FunctionBinary.sizeDomainCalculation(beginDomains, endDomains);
        this._scriptFunctionMath = scriptFunctionMath;
        this.setSizeGenotype(beginDomains.length);
    }

    public double getVariavel(int index) {
        return (Double)this.getGenome().get(0).getGenotype().get(index).getAllele();
    }
    
    @Override
    public double fitness() {
        double[] __variavels = new double[this._beginDomains.length];
        
        for (int i = 0; i < this._beginDomains.length; i++) {
            __variavels[i] = (Double)this.getGenome().get(0).getGenotype().get(i).getAllele();
        }
        
        return FunctionBinary.parseFunctionECMAScript(_scriptFunctionMath, __variavels);
    }

    @Override
    public Object inicializationAllelo(int indexGene) {
        return this._beginDomains[indexGene] + Population.RANDOM_GENERATOR.nextDouble() * this._sizeDomains[indexGene];
    }

    @Override
    public Individual clone() {
        return new FunctionReal(this);
    }

    @Override
    public String getInfo() {
        return "Not supported yet.";
    }

    @Override
    public boolean setParameters(String parameters) {
        return false;
    }

    /**
     * @return the _sizeDomains
     */
    public double getSizeDomains(int index) {
        return _sizeDomains[index];
    }    
    
    /**
     * @return the _beginDomains
     */
    public double getBeginDomains(int index) {
        return _beginDomains[index];
    } 
    
    /**
     * @return the _endDomains
     */
    public double getEndDomains(int index) {
        return _endDomains[index];
    }  
}
