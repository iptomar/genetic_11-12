/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genetics;

import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import operators.Genetic;
import operators.Operator;
import operators.replacements.Replacement;
import operators.selections.SUS;
import statistics.DesvioPadrao;
import utils.EventsSolver;
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
    private Individual _typeIndividual;
    private StopCriterion _stopCriterion;
    private int _numberIteractions;

    private ArrayList<Operator> operadores;
    private EventsSolver eventSolver;
    
    private DesvioPadrao desvioPadrao;
    
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
        
        desvioPadrao = new DesvioPadrao();
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
                this._sonsPopulation = ((Genetic) this.operadores.get(0)).execute(this._parentsPopulation);

                for (int __indexOperators = 1; __indexOperators < this.operadores.size(); __indexOperators++) {

                    if (this.operadores.get(__indexOperators) instanceof Genetic) {
                        _sonsPopulation = ((Genetic) this.operadores.get(__indexOperators)).execute(_sonsPopulation);
                    } else {
                        _parentsPopulation = ((Replacement) this.operadores.get(__indexOperators)).execute(this._parentsPopulation, this._sonsPopulation);
                    }

                }
                
                // converte população final em double[] e atribui ao objecto desvioPadrao
                desvioPadrao.setArray(devolveArrayFitnessPopulacao(_parentsPopulation));
                
                // no final de cada iteração dispara um evento que passa
                // o numero da iteração e a população gerada
                this.eventSolver.EventIteraction(this._numberIteractions, this._parentsPopulation, this.desvioPadrao);
                
                this._numberIteractions++;
            }

            // Evento final quando o solver esta terminado
            this.eventSolver.EventFinishSolver(this._numberIteractions, this._parentsPopulation, this.desvioPadrao);
            
        } catch (Exception ex) {
            Logger.getLogger(Solver.class.getName()).log(Level.SEVERE, null, ex);
            throw new SolverException();
        }
    }

    // Recolhe todos os fitness's da população e devolve em um array de Doubles
    private Double[] devolveArrayFitnessPopulacao(Population populacao) {
        ArrayList<Double> fitnessArray = new ArrayList<Double>(populacao.getSizePopulation());
        
        for (Individual individuo : populacao) {
            fitnessArray.add((double)individuo.fiteness());
        }
        
        return fitnessArray.toArray(new Double[0]);
    }
}
