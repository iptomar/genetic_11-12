/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genetics.algorithms;

import genetics.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.*;
import operators.Operator;
import operators.mutation.Invertion;
import operators.recombinations.Crossover;
import operators.recombinations.PMX;
import operators.selections.Tournament;
import operators.replacements.Truncation;
import operators.selections.SUS;
import utils.EventsSolver;
import utils.exceptions.SolverException;

/**
 *
 * @author PedroIsi
 */
public class Function extends Individual {

    public static void main(String[] args) throws SolverException {
        ArrayList<Operator> operators = new ArrayList<Operator>(4);
        Solver solver;
        
        operators.add(new Tournament(10, 2));
        operators.add(new Invertion(0.03));
        operators.add(new Crossover());
        operators.add(new Truncation());

        solver = new Solver(
                new Population(10, 1, 2, 0, 
                    new Function(
                        new double[] { -1, -1 }, 
                        new double[] { 1, 1 }, 
                        "variavels[0]*variavels[0]+variavels[1]*variavels[1]")),
                new StopCriterion(1000, 5.0), 
                operators, 
                new EventsSolver() {

            @Override
            public void EventStartSolver() {
                // throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void EventIteraction(int iteractionNumber, Population currentPopulation) {
                Function x = (Function)currentPopulation.getPopulation().get(0);
                System.out.println("X: " + x.getVariavel(0) +  "\tY: " + x.getVariavel(1));
            }

            @Override
            public void EventFinishSolver(int totalIteracoes, Population lastPopulation) {
                Function x = (Function)lastPopulation.getPopulation().get(0);
                System.out.println("X: " + x.getVariavel(0) +  "\tY: " + x.getVariavel(1));
            }
        });
        
        solver.run();
    }
    
    public static final Random RANDOM_GENERATOR = new Random();

    private double[]    _beginDomains;    
    private double[]    _endDomains;    
    private double[]    _sizeDomains; 
    
    //Expressão matemática
    private String _scriptFunctionMath;
    
    public Function(Function function){
        this(function._beginDomains,
             function._endDomains, 
             function._scriptFunctionMath);
        
        if(function.getGenome().size() > 0)
            for (Object __gene : function.getChromosome(0)) {
                Chromosome __chromosome = new Chromosome(this);
                __chromosome.setGene(new Gene((Gene)__gene));
                
                this.setChromosome(0, __chromosome);
            }
    }
    
    public Function(double[] beginDomains, double[] endDomains, String scriptFunctionMath){
        this._beginDomains  = new double[beginDomains.length];
        this._endDomains    = new double[endDomains.length];
        this._sizeDomains   = new double[beginDomains.length];

        System.arraycopy(beginDomains, 0, this._beginDomains, 0, beginDomains.length);
        System.arraycopy(endDomains, 0, this._endDomains, 0, endDomains.length);
        
        this._sizeDomains = _sizeDomainCalculation(beginDomains, endDomains);
        this._scriptFunctionMath = scriptFunctionMath;
        this.setSizeGenotype(beginDomains.length);
    }
    
    public Function(boolean[][] variavels, double[] beginDomains, double[] endDomains, String scriptFunctionMath){
//        System.arraycopy(beginDomains, 0, this._beginDomains, 0, this._beginDomains.length);        
//        System.arraycopy(endDomains, 0, this._endDomains, 0, this._endDomains.length);
//        
//        System.arraycopy(variavels, 0, this._variavels, 0, this._variavels.length);
//        
//        _sizeDomainCalculation(this._sizeDomains, beginDomains, endDomains);
//        
//        for (int __indexVariavel = 0; __indexVariavel < this._variavels.length; __indexVariavel++) {
//            System.arraycopy(variavels[__indexVariavel], 0, this._variavels[__indexVariavel], 0, this._variavels[__indexVariavel].length);          
//        }
//        
//        this._scriptFunctionMath = scriptFunctionMath;
    }

    private double[] _sizeDomainCalculation(double[] beginDomains, double[] endDomains) {
        double[] sizeDomains = new double[beginDomains.length];
        
        for (int __indexDomain = 0; __indexDomain < beginDomains.length; __indexDomain++) {
            sizeDomains[__indexDomain] = endDomains[__indexDomain] - beginDomains[__indexDomain];
        }
        
        return sizeDomains;
    }
    
    @Override
    public double fitness() {
        double[] __variavels = new double[this._beginDomains.length];
        
        for (int i = 0; i < this._beginDomains.length; i++) {
            __variavels[i] = Function.convertBinaryToReal(this._beginDomains[i], this.getGenome().get(0).getGenotype().get(i).toString(), this._sizeDomains[i]);
        }
        
        return Function.parseFunctionECMAScript(_scriptFunctionMath, __variavels);
    }
    
    public double getVariavel(int index) {
        return Function.convertBinaryToReal(this._beginDomains[index], this.getGenome().get(0).getGenotype().get(index).toString(), this._sizeDomains[index]);
    }
    
    @Override
    public Object[] inicializationAllelo(int indexGene) {
        return Function.generateRandomIndividual(
                    Function.calculateSizeAllelo(
                        Function.convertRealToInteger(this._sizeDomains[indexGene])));
    }

    @Override
    public Individual clone() {
        return new Function(this);
    }

    @Override
    public String getInfo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean setParameters(String parameters) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    
    public static int calculateSizeAllelo(int value){
        final int __sizeAllelo;
        
        __sizeAllelo = (int)Math.round((Math.log10(value) / Math.log10(2)) + 0.5);
        
        return __sizeAllelo;
    } 
    
    public static int convertRealToInteger(double realValue){
        final int __integerValue;
        final int __fourDecimalsHouses;
        
        __fourDecimalsHouses    = 10000;
        __integerValue          = (int)(realValue * __fourDecimalsHouses);
        
        return __integerValue;
    }
    
    public static int convertBinaryToInteger(String binaryValue){
        final int __base;
        __base = 2;
        return Integer.parseInt(binaryValue.replaceAll(" ", ""), __base);
    } 
    
    public static double sizeDomain(double value1, double value2){
        return value2-value1;
    }

    public static double convertBinaryToReal(double beginDomain, String binary, double sizeDomain){
        double __valueRealOfBinary;
        __valueRealOfBinary = Function.convertBinaryToInteger(binary);
        
        return beginDomain + __valueRealOfBinary * ( sizeDomain / ( Math.pow(2, Function.calculateSizeAllelo((int)__valueRealOfBinary)) - 1 ) );
    }

    public static double parseFunctionECMAScript(String script, double[] variavels){
        double              __resultValue;
        ScriptEngineManager __scriptEngineManager;
        ScriptEngine        __ECMAScript;
        ScriptEngineFactory __ECMAScriptParse;
        String              __programScript;
        StringBuffer        __scriptInFunction;
        
        __scriptInFunction = new StringBuffer(script.length());
        __scriptInFunction.append("function functionMath(variavels) { return ");
        __scriptInFunction.append(script);
        __scriptInFunction.append("; }");
        
        __resultValue           = Double.NaN;
        __scriptEngineManager   = new ScriptEngineManager();
        __ECMAScript            = __scriptEngineManager.getEngineByName("ECMAScript");
        __ECMAScriptParse       = __ECMAScript.getFactory();
        __programScript         = __ECMAScriptParse.getProgram(__scriptInFunction.toString());
        
        try {
            __ECMAScript.eval(__programScript);
            
            if (__ECMAScript instanceof Invocable) {
                
                Invocable __invokeFunction = (Invocable) __ECMAScript;
                
                try {
                    
                    Object __valueReturn = __invokeFunction.invokeFunction("functionMath", variavels);
                    if(__valueReturn instanceof Double) 
                        __resultValue = (Double)__valueReturn;
                    
                } catch (NoSuchMethodException __noSuchMethodException) {
                } catch (ScriptException __scriptException) {
                }
            }

        } catch (ScriptException ex) {
            Logger.getLogger(Function.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return __resultValue;
    }
    
    public static Boolean[] generateRandomIndividual(int size) {
        Boolean[] __newIndividual = new Boolean[size];
        // fill individual width indexes between 0 and size
        for (int __indexGene = 0; __indexGene < __newIndividual.length; __indexGene++) {
            __newIndividual[__indexGene] = RANDOM_GENERATOR.nextBoolean();
        }
        return __newIndividual;
    }

}
