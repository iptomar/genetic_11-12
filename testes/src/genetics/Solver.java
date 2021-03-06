/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genetics;

import java.util.ArrayList;
import operators.Genetic;
import operators.Operator;
import operators.mutation.Flipbit;
import operators.recombinations.Crossover2;
import operators.replacements.Replacement;
import operators.selections.Tournament;
import utils.EventsSolver;

/**
 *
 * @author diogoantonio
 */
public class Solver {
    
    private Population _parentsPopulation;
    private Population _sonsPopulation;
    
    private ArrayList<Operator> _operators;
    private int _sizePopulation;
    private int _sizeGenotype = 1;
    private int _sizeGenome = 1; 
    private int _sizeAllelo;
    private Individual _typeIndividual;
    private StopCriterion _stopCriterion;
    private int _numberIteractions;
    
    private EventsSolver _eventSolver;
    
    public Solver(EventsSolver eventSolver) {
        this(100, 20, new OnesMax(), 100, 18, eventSolver);
    }
    
    public Solver(int sizePopulation, int sizeAllelo, Individual typeIndividual, int iteractions, int bestfiteness, EventsSolver eventSolver) {
        this._sizePopulation = sizePopulation;
        this._sizeAllelo = sizeAllelo;
        this._typeIndividual = typeIndividual;
        this._stopCriterion = new StopCriterion(iteractions, bestfiteness);
        this._eventSolver = eventSolver;
    }
    
    public void run(){
        
        // Evento inicial quando o solver inicia
        this._eventSolver.EventStartSolver();
        
        this._operators = new ArrayList<Operator>(4);
        this._operators.add(new Tournament(8, 2));        
        this._operators.add(new Crossover2());
        this._operators.add(new Flipbit(0.01));
        this._operators.add(new operators.replacements.Tournament(this._sizePopulation, 2));

        this._numberIteractions = 0;
        this._parentsPopulation = new Population(this._sizePopulation, this._sizeGenome, this._sizeGenotype, this._sizeAllelo, this._typeIndividual);
        
        while((this._numberIteractions < this._stopCriterion.getNumberIteractions()) && 
              (this._parentsPopulation.getBestFitness() < this._stopCriterion.getGoodFitness())) {
        
            this._parentsPopulation.incrementAgePopulation();
            this._sonsPopulation = ((Genetic)this._operators.get(0)).execute(this._parentsPopulation);

            for (int __indexOperators = 1; __indexOperators < this._operators.size(); __indexOperators++) {

                if(this._operators.get(__indexOperators) instanceof Genetic) {
                    _sonsPopulation = ((Genetic)this._operators.get(__indexOperators)).execute(_sonsPopulation);
                } else {
                    _parentsPopulation = ((Replacement)this._operators.get(__indexOperators)).execute(this._parentsPopulation, this._sonsPopulation);
                }

            }

            // no final de cada iteração dispara um evento que passa
            // o numero da iteração e a população gerada
            this._eventSolver.EventIteraction(this._numberIteractions, this._parentsPopulation);
            
            this._numberIteractions++;
        }
        
//        System.out.println("Hall of Fame");
//        System.out.println(this._parentsPopulation.getHallOfFame(5).toString());
//    
//        System.out.println("");
//        
//        System.out.println("Population");
//        System.out.println(this._parentsPopulation.toString());
        
        // Evento final quando o solver esta terminado
        this._eventSolver.EventFinishSolver(_parentsPopulation);
    }
}
