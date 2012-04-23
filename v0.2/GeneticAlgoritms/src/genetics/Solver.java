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
import operators.mutation.Mutation;
import operators.recombinations.Recombination;
import operators.replacements.Replacement;
import operators.selections.Selection;
import utils.EventsSolver;
import utils.PopulationUtils;
import utils.exceptions.SolverException;
import utils.exceptions.SonsInicialitazionException;

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


    /**
     * Construtor do solver
     * @param operators Array de operadores geneticos
     * @param eventSolver Eventos que são chamados consuante o solver vai correndo
     */
    public Solver(ArrayList<Operator> operators, EventsSolver eventSolver) {
        this(100, 20, new OnesMax(), new StopCriterion(100, 18.0), operators, eventSolver);
    }

    /**
     * Construtor do solver que gera uma população de forma aleatoria
     * @param sizePopulation Tamanho da população
     * @param sizeAllelo Tamanho do Allelo
     * @param prototypeIndividual Tipo de individuo que a população vai conter
     * @param stopCriterion Criterio de paragem para o solver
     * @param operators Array de operadores geneticos
     * @param eventSolver Eventos que são chamados consuante o solver vai correndo
     */
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
    
    /**
     * Construtore do solver que passa uma população como parametro
     * @param population População inicial
     * @param stopCriterion Criterios de paragem para o solver
     * @param operators Array de operadores geneticos
     * @param eventSolver Eventos que são chamados consuante o solver vai correndo
     */
    public Solver(Population population, StopCriterion stopCriterion, ArrayList<Operator> operators, EventsSolver eventSolver) {
        this(population.getSizePopulation(), population.getSizeAllelo(), population.getTypePopulation(), stopCriterion, operators, eventSolver);
        
        // Por defeito cria logo um clone da população para que ela
        // a partir de agora seja uma população independente da original
        this._parentsPopulation = population.clone();
    }

    /**
     * Metodo que faz correr o Solver, ou seja, aplica os operadores geneticos
     * sobre a população ate encontrar o individuo com o fitness desejado ou atingir
     * o numero maximo de gerações pre-determinado pelo utilizador
     * @throws SolverException Caso exista um erro no correr do solver dispara esta excepção
     */
    public void run() throws SolverException, SonsInicialitazionException {

        // Capturar erros de codigo não programados
        try {

            // Evento inicial quando o solver inicia
            this._eventSolver.EventStartSolver();

            this._numberIteractions = 0;
            
            // So cria uma população nova e aleatoria quando não é passado uma
            // população no construtor. Situação onde neste ponto de codigo ja 
            // existe uma população é quando estamos a correr a mesma população
            // com diferentes configurações de operadores
            if(this._parentsPopulation == null)
                this._parentsPopulation = new Population(this._sizePopulation, this._sizeGenome, this._sizeGenotype, this._sizeAllelo, this._prototypeIndividual);

            // Ciclo que corre o solver e que só termina quando atingir o numero
            // maximo de gerações/iterações definadas para o solver ou um individuo
            // atingir o fitness desejado
            while ((this._numberIteractions < this._stopCriterion.getNumberIteractions())
                    && (PopulationUtils.getBestFitness(this._parentsPopulation) < this._stopCriterion.getGoodFiteness())) {

                // Corre todos os operadores que foram passados para este solver
                for (int __indexOperators = 0; __indexOperators < this._operators.size(); __indexOperators++) {

                    // Se o operador for do tipo Selection
                    if(this._operators.get(__indexOperators) instanceof Selection){
                        // aplica o operador a população de pais e devolve uma nova população de filhos
                        this._sonsPopulation = ((Genetic) this._operators.get(__indexOperators)).execute(this._parentsPopulation);
                    }
                    
                    // Se o operador por do tipo Recombinação ou Mutação
                    if (this._operators.get(__indexOperators) instanceof Recombination || this._operators.get(__indexOperators) instanceof Mutation) {
                        
                        // Dispara um erro se a população de filhos não tiver sido inicializada
                        if(this._sonsPopulation == null)
                            throw new SonsInicialitazionException();
                        
                        // aplica o operador a população de filhos e devolve uma nova população de filhos
                        _sonsPopulation = ((Genetic) this._operators.get(__indexOperators)).execute(_sonsPopulation);
                    }
                    
                    // Se o operador por do tipo Replacements
                    if (this._operators.get(__indexOperators) instanceof Replacement) {
                        // aplica o operador a população de filhos e pais e devolve 
                        // os melhores para a proxima geração. Este processo faz deles
                        // os proximos pais
                        _parentsPopulation = ((Replacement) this._operators.get(__indexOperators)).execute(this._parentsPopulation, this._sonsPopulation);
                    }
                    
                }

                // no final de cada iteração dispara um evento que passa
                // o numero da iteração e a população gerada
                this._eventSolver.EventIteraction(this._numberIteractions, this._parentsPopulation);

                // incrementa mais uma geração/iteração à variavel
                this._numberIteractions++;
            }

            // Evento final quando o solver esta terminado
            this._eventSolver.EventFinishSolver(this._numberIteractions, this._parentsPopulation);

        } catch (SonsInicialitazionException ex) {
            Logger.getLogger(Solver.class.getName()).log(Level.SEVERE, null, ex);
            throw new SonsInicialitazionException();
        } catch (Exception ex) {
            Logger.getLogger(Solver.class.getName()).log(Level.SEVERE, null, ex);
            throw new SolverException();
        }
    }
    
}
