/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genetics.algorithms;

import genetics.Chromosome;
import genetics.Gene;
import genetics.Individual;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.*;

/**
 *
 * @author PedroIsi
 */
public class Function extends Individual {

    public static final Random RANDOM_GENERATOR = new Random();

    private double[]    _beginDomains;    
    private double[]    _endDomains;    
    private double[]    _sizeDomains; 
    //private boolean[][] _variavels;
    
    //Expressão matemática
    private String _scriptFunctionMath;
    
    public Function(Function function){
//        this(function._variavels,
//                function._beginDomains,
//                function._endDomains,
//                function._scriptFunctionMath);
    }
    
    public Function(double[] beginDomains, double[] endDomains, String scriptFunctionMath){
        this._beginDomains       = beginDomains;
        this._endDomains         = endDomains;
        _sizeDomainCalculation(this._sizeDomains, beginDomains, endDomains);
        this._scriptFunctionMath = scriptFunctionMath;
        
        this._initIndividual();
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
    
    private void _initIndividual() {  
        Chromosome __chromosome = new Chromosome(this);
        
        for (int __indexIndividuals = 0; __indexIndividuals < this._sizeDomains.length; __indexIndividuals++) {
            Gene __gene = new Gene(Function.generateRandomIndividual(
                    Function.calculateSizeAllelo(
                        Function.convertRealToInteger(this._sizeDomains[__indexIndividuals]))));
            
            __chromosome.setGene(__gene);
        }
        
        this.setChromosome(0, __chromosome);
    }

    private void _sizeDomainCalculation(double[] sizeDomains, double[] beginDomains, double[] endDomains) {
        sizeDomains = new double[beginDomains.length];
        for (int __indexDomain = 0; __indexDomain < beginDomains.length; __indexDomain++) {
            sizeDomains[__indexDomain] = endDomains[__indexDomain] - beginDomains[__indexDomain];
        }
    }
    
    @Override
    public double fitness() {
        double[] __variavels = new double[this.getGenome().get(0).getGenotype().size()];
        
        for (int i = 0; i < this.getGenome().get(0).getGenotype().size(); i++) {
            __variavels[i] = Function.convertBinaryToReal(this._beginDomains[i], this.getGenome().get(0).getGenotype().get(i).toString(), this._sizeDomains[i]);
        }
        
        return Function.parseFunctionECMAScript(_scriptFunctionMath, __variavels);
    }
    
    @Override
    public Object[] inicializationAllelo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Individual clone() {
        throw new UnsupportedOperationException("Not supported yet.");
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
        return Integer.parseInt(binaryValue, __base);
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
    
    public static boolean[] generateRandomIndividual(int size) {
        boolean[] __newIndividual = new boolean[size];
        // fill individual width indexes between 0 and size
        for (int __indexGene = 0; __indexGene < __newIndividual.length; __indexGene++) {
            __newIndividual[__indexGene] = RANDOM_GENERATOR.nextBoolean();
        }
        return __newIndividual;
    }

}
