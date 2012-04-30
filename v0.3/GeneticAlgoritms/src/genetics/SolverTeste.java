package genetics;

import genetics.algorithms.K50;
import genetics.algorithms.OnesMax;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import operators.Genetic;
import operators.Operator;
import operators.mutation.Flipbit;
import operators.mutation.Mutation;
import operators.recombinations.Crossover;
import operators.recombinations.Recombination;
import operators.replacements.Replacement;
import operators.replacements.Truncation;
import operators.selections.SUS;
import operators.selections.Selection;
import utils.PopulationUtils;
import utils.exceptions.SolverException;
import utils.exceptions.SonsInicialitazionException;

/* -------------------------------------------------------------------------
 * -------------------------------------------------------------------------
 *  I n s t i t u t o   P o l i t e c n i c o   d e   T o m a r
 *   E s c o l a   S u p e r i o r   d e   T e c n o l o g i a
 *
 * @author Ruben Felix <Ruben.Felix@gmail.com>
 * -------------------------------------------------------------------------
 * Número de Aluno: 13691 
 * E-mail: Ruben.Felix@gmail.com
 * -------------------------------------------------------------------------
 * -------------------------------------------------------------------------
 */
public class SolverTeste {

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

    /**
     * Construtor do solver
     * @param operators Array de operadores geneticos
     * @param eventSolver Eventos que são chamados consuante o solver vai correndo
     */
    public SolverTeste(ArrayList<Operator> operators) {
        this(100, 20, new OnesMax(), new StopCriterion(100, 18.0), operators);
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
    public SolverTeste(int sizePopulation, int sizeAllelo, Individual prototypeIndividual, StopCriterion stopCriterion, ArrayList<Operator> operators) {
        this._sizePopulation = sizePopulation;
        this._sizeAllelo = sizeAllelo;
        this._prototypeIndividual = prototypeIndividual;
        this._stopCriterion = stopCriterion;
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
    public SolverTeste(Population population, StopCriterion stopCriterion, ArrayList<Operator> operators) {
        this(population.getSizePopulation(), population.getSizeAllelo(), population.getTypePopulation(), stopCriterion, operators);

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
            this._numberIteractions = 0;

            // So cria uma população nova e aleatoria quando não é passado uma
            // população no construtor. Situação onde neste ponto de codigo ja 
            // existe uma população é quando estamos a correr a mesma população
            // com diferentes configurações de operadores
            if (this._parentsPopulation == null) {
                this._parentsPopulation = new Population(this._sizePopulation, this._sizeGenome, this._sizeGenotype, this._sizeAllelo, this._prototypeIndividual);
            }

            // Ciclo que corre o solver e que só termina quando atingir o numero
            // maximo de gerações/iterações definadas para o solver ou um individuo
            // atingir o fitness desejado
            while ((this._numberIteractions < this._stopCriterion.getNumberIteractions())
                    && (PopulationUtils.getBestFitness(this._parentsPopulation) < this._stopCriterion.getGoodFiteness())) {

                // Corre todos os operadores que foram passados para este solver
                for (int __indexOperators = 0; __indexOperators < this._operators.size(); __indexOperators++) {

                    // Se o operador for do tipo Selection
                    if (this._operators.get(__indexOperators) instanceof Selection) {
                        // aplica o operador a população de pais e devolve uma nova população de filhos
                        this._sonsPopulation = ((Genetic) this._operators.get(__indexOperators)).execute(this._parentsPopulation);
                    }

                    // Se o operador por do tipo Recombinação ou Mutação
                    if (this._operators.get(__indexOperators) instanceof Recombination || this._operators.get(__indexOperators) instanceof Mutation) {

                        // Dispara um erro se a população de filhos não tiver sido inicializada
                        if (this._sonsPopulation == null) {
                            throw new SonsInicialitazionException();
                        }

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

                // incrementa mais uma geração/iteração à variavel
                this._numberIteractions++;
                System.out.println("Best Fitness: " + PopulationUtils.getBestFitness(this._parentsPopulation));
                System.out.println("Iteração número: " + _numberIteractions);
            }

        } catch (SonsInicialitazionException ex) {
            Logger.getLogger(Solver.class.getName()).log(Level.SEVERE, null, ex);
            throw new SonsInicialitazionException();
        } catch (Exception ex) {
            Logger.getLogger(Solver.class.getName()).log(Level.SEVERE, null, ex);
            throw new SolverException();
        }
    }

    public static void main(String[] args) throws SolverException {
        ArrayList<Operator> operators = new ArrayList<Operator>(4);
        SolverTeste solver;
        //Adiciona os operadores que foram escolhidos

        
        operators.add(new SUS());
        
        operators.add(new Flipbit(0.05));
        
        operators.add(new Truncation(50));
        //operators.add(new Tournament(100,2));
        
        operators.add(new Crossover());

        
        solver = new SolverTeste(100, 50, ((Individual)new K50()), new StopCriterion(100000, 1921.0), operators);
        solver.run();
        
//        solver = new SolverTeste(50, 100, ((Individual)new K100()), new StopCriterion(100000, 1561.0), operators);
//        solver.run();
        
//        solver = new SolverTeste(100, 100, ((Individual) new OnesMax()), new StopCriterion(100000, 100.0), operators);
//        solver.run();
    }
}
