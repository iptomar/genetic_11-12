/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genetics;

import genetics.algorithms.OnesMax;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import operators.Genetic;
import operators.Operator;
import operators.replacements.Replacement;
import statistics.Statistics;
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
    private int _sizeGenotype;
    private int _sizeGenome;
    private int _sizeAllelo;
    private Individual _prototypeIndividual;
    private StopCriterion _stopCriterion;
    private int _numberIteractions;
    private ArrayList<Operator> _operators;
    private EventsSolver _eventSolver;

//    private Statistics _statistics;
    
    public Solver(ArrayList<Operator> operators, EventsSolver eventSolver) {
        this(100, 20, new OnesMax(), new StopCriterion(100, 18), operators, eventSolver);
    }

    public Solver(int sizePopulation, int sizeAllelo, Individual prototypeIndividual, StopCriterion stopCriterion, ArrayList<Operator> operators, EventsSolver eventSolver) {
        this._sizePopulation = sizePopulation;
        this._sizeAllelo = sizeAllelo;
        this._prototypeIndividual = prototypeIndividual;
        this._stopCriterion = stopCriterion;
        this._eventSolver = eventSolver;
        this._operators = operators; 
        
        this._sizeGenotype = Population.DEFAULT_SIZE_GENOTYPE;
        this._sizeGenome = Population.DEFAULT_SIZE_GENOME;
    }
    
    public Solver(Population population, StopCriterion stopCriterion, ArrayList<Operator> operators, EventsSolver eventSolver) {
        this(population.getSizePopulation(), population.getSizeAllelo(), population.getTypePopulation(), stopCriterion, operators, eventSolver);
        
        // Por defeito cria logo um clone da população para que ela
        // a partir de agora seja uma população independente da original
        this._parentsPopulation = population.clone();
    }

    public void run() throws SolverException {

        // Capturar erros de codigo não programados
        try {

            // Evento inicial quando o solver inicia
            this._eventSolver.EventStartSolver();

            this._numberIteractions = 0;
            this._parentsPopulation = new Population(this._sizePopulation, this._sizeGenome, this._sizeGenotype, this._sizeAllelo, this._prototypeIndividual);

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

                // Criar um objecto desvio padrao para processar os dados contindos nesta população
                // Usasse a população dos Pais porque é a população utilizada para criar no final 
                // a proxima geração de pais
//                this._statistics = new Statistics(_parentsPopulation);
            
                
                // no final de cada iteração dispara um evento que passa
                // o numero da iteração e a população gerada
                this._eventSolver.EventIteraction(this._numberIteractions, this._parentsPopulation);

                this._numberIteractions++;
            }

            // Evento final quando o solver esta terminado
            this._eventSolver.EventFinishSolver(this._numberIteractions, this._parentsPopulation);

        } catch (Exception ex) {
            Logger.getLogger(Solver.class.getName()).log(Level.SEVERE, null, ex);
            throw new SolverException();
        }
    }
    
}
