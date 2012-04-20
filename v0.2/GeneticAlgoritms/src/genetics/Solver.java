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
import operators.replacements.Replacement;
import statistics.DesvioPadrao;
import utils.EventsSolver;
import utils.PopulationUtils;
import utils.exceptions.SolverException;

/**
 *
 * @author diogoantonio
 */
public class Solver {

    private Population _parentsPopulation;
    private Population _sonsPopulation;
    private int _sizePopulation;
    private int _sizeGenotype = 1;
    private int _sizeGenome = 1;
    private int _sizeAllelo;
    private Class _prototypeIndividual;
    private StopCriterion _stopCriterion;
    private int _numberIteractions;
    private ArrayList<Operator> _operators;
    private EventsSolver _eventSolver;

    private DesvioPadrao _desvioPadrao;
    
    public Solver(ArrayList<Operator> operators, EventsSolver eventSolver) {
        this(100, 20, OnesMax.class, 100, 18, operators, eventSolver);
    }

    public Solver(int sizePopulation, int sizeAllelo, Class prototypeIndividual, int iteractions, int bestFitness, ArrayList<Operator> operators, EventsSolver eventSolver) {
        this._sizePopulation = sizePopulation;
        this._sizeAllelo = sizeAllelo;
        this._prototypeIndividual = prototypeIndividual;
        this._stopCriterion = new StopCriterion(iteractions, bestFitness);
        this._eventSolver = eventSolver;
        this._operators = operators; 
    }

    public void run() throws SolverException {

        // Capturar erros de codigo não programados
        try {

            // Evento inicial quando o solver inicia
            this._eventSolver.EventStartSolver();

            this._numberIteractions = 0;
            this._parentsPopulation = new Population(this._sizePopulation, this._sizeGenome, this._sizeGenotype, this._sizeAllelo, this._prototypeIndividual);

            // Criar um objecto desvio padrao para processar os dados contindos nesta população
            // Usasse a população dos Pais porque é a população utilizada para criar no final 
            // a proxima geração de pais
            this._desvioPadrao = new DesvioPadrao(_parentsPopulation);
            
            while ((this._numberIteractions < this._stopCriterion.getNumberIteractions())
                    && (PopulationUtils.getBestFitness(this._parentsPopulation) < this._stopCriterion.getGoodFiteness())) {

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
                this._eventSolver.EventIteraction(this._numberIteractions, this._parentsPopulation, this._desvioPadrao);

                this._numberIteractions++;
            }

            // Evento final quando o solver esta terminado
            this._eventSolver.EventFinishSolver(this._numberIteractions, this._parentsPopulation, this._desvioPadrao);

        } catch (Exception ex) {
            Logger.getLogger(Solver.class.getName()).log(Level.SEVERE, null, ex);
            throw new SolverException();
        }
    }
    
}
