/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genetics;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import operators.Genetic;
import operators.Operator;
import operators.mutation.Flipbit;
import operators.recombinations.Crossover2;
import operators.replacements.Replacement;
import operators.selections.Roulette;
import utils.EventsSolver;
import utils.exceptions.SolverException;

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

    private ArrayList<Operator> operadores;
    private EventsSolver eventSolver;
    
    public Solver(EventsSolver eventSolver) {
        this(100, 20, new OnesMax(), 100, 18, new ArrayList<Operator>(), eventSolver);
    }

    public Solver(int sizePopulation, int sizeAllelo, Individual typeIndividual, int iteractions, int bestfiteness, ArrayList<Operator> operadores, EventsSolver eventSolver) {
        this._sizePopulation = sizePopulation;
        this._sizeAllelo = sizeAllelo;
        this._typeIndividual = typeIndividual;
        this._stopCriterion = new StopCriterion(iteractions, bestfiteness);
        this.operadores = operadores;
        this.eventSolver = eventSolver;
    }

    public void run() throws SolverException {

        // Capturar erros de codigo não programados
        try {

            // Evento inicial quando o solver inicia
            this.eventSolver.EventStartSolver();
            
            this._numberIteractions = 0;
            this._parentsPopulation = new Population(this._sizePopulation, this._sizeGenome, this._sizeGenotype, this._sizeAllelo, this._typeIndividual);

            while ((this._numberIteractions < this._stopCriterion.getNumberIteractions())
                    && (this._parentsPopulation.getBestFiteness() < this._stopCriterion.getGoodFiteness())) {

                this._parentsPopulation.incrementAgePopulation();
                this._sonsPopulation = ((Genetic) this._operators.get(0)).execute(this._parentsPopulation);

                for (int __indexOperators = 1; __indexOperators < this._operators.size(); __indexOperators++) {

                    if (this._operators.get(__indexOperators) instanceof Genetic) {
                        _sonsPopulation = ((Genetic) this._operators.get(__indexOperators)).execute(_sonsPopulation);
                    } else {
                        _parentsPopulation = ((Replacement) this._operators.get(__indexOperators)).execute(this._parentsPopulation, this._sonsPopulation);
                    }

                }
                
                // no final de cada iteração dispara um evento que passa
                // o numero da iteração e a população gerada
                this.eventSolver.EventIteraction(this._numberIteractions, this._parentsPopulation);
                
                this._numberIteractions++;
            }

            // Evento final quando o solver esta terminado
            this.eventSolver.EventFinishSolver(_parentsPopulation);
            
        } catch (Exception ex) {
            throw new SolverException();
        }
    }

}
