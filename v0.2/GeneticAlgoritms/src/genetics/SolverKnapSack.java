package genetics;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import operators.Genetic;
import operators.Operator;
import operators.recombinations.UniformCrossover;
import operators.replacements.Replacement;
import operators.replacements.Truncation;
import operators.selections.SUS;
import utils.EventsSolver;
import utils.Mochila;
import utils.exceptions.SolverException;

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
/**
 * Classe que será o solver para os individuos do tipo KnapSack para a resolução do problema da mochila.
 * A mochila é definida pelo utiliador, sendo que levará 
 * @author Ruben Felix <Ruben.Felix@gmail.com>
 */
public class SolverKnapSack {

    /**
     * Arraylist's que contem a população pai e a população filho
     */
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
    private EventsSolver eventSolver;
    //Variavel que receberá a mochila definida para o problema
    private Mochila mochila;

    /**
     * Construtor onde é passado por parametro a mochila definida para o problema e a população é criada 
     * @param mochila - Mochila definida para o problema
     */
    public SolverKnapSack(Mochila mochila, EventsSolver eventSolver) {
        this(100, new KnapSack(), 100, 30, mochila, eventSolver);
    }

    /**
     * Construtor da classe onde são passados por parametro o tamanho da população, o tamanho do alelo, o tipo de individuos a ser utilizado no solver
     * o número máximo de iterações e o best fitness como critério de paragem e também a mochila definida para o problema.
     * @param sizePopulation - Tamanho da população
     * @param sizeAllelo - Tamanho do alelo de cada individuo
     * @param typeIndividual - Tipo de individuo a ser utilizado na resolução do problema
     * @param iteractions - Iterações máximas
     * @param bestfiteness - Best fitness para a paragem
     * @param mochila - Mochila utilizada na definição do problema
     */
    public SolverKnapSack(int sizePopulation, Individual typeIndividual, int iteractions, int bestfiteness, Mochila mochila, EventsSolver eventSolver) {
        this._sizePopulation = sizePopulation;
        this._sizeAllelo = mochila.getNumItems();
        this._typeIndividual = typeIndividual;
        this._stopCriterion = new StopCriterion(iteractions, bestfiteness);
        this.mochila = mochila;
        this._numberIteractions = 0;
        this.eventSolver = eventSolver;
        this._parentsPopulation = new Population(this._sizePopulation, this._sizeGenome, this._sizeGenotype, this._sizeAllelo, this._typeIndividual);
        //Ciclo que define a mochila que pode ser utilizada para cada individuo do tipo KnapSack
        for (int i = 0; i < _parentsPopulation.getPopulation().size(); i++) {
            ((KnapSack) _parentsPopulation.getPopulation().get(i)).setMocha(mochila);
        }

    }

    public void run() throws SolverException {
        // Capturar erros de codigo não programados
        try {
            // Evento inicial quando o solver inicia
            //this.eventSolver.EventStartSolver();

            this._operators = new ArrayList<Operator>(4);
            //this._operators.add(new Tournament(8, 2));        
            this._operators.add(new UniformCrossover());
            this._operators.add(new Truncation(this._sizePopulation));
            //this._operators.add(new Flipbit(0.01));
            this._operators.add(new SUS());
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
                  
                    System.out.println("For");
                    
                }

                // no final de cada iteração dispara um evento que passa
                // o numero da iteração e a população gerada
                System.out.println("Evento");
                //this.eventSolver.EventIteraction(this._numberIteractions, this._parentsPopulation);

                this._numberIteractions++;
            }

            System.out.println("Hall of Fame");
            System.out.println(this._parentsPopulation.getHallOfFame(5).toString());

            System.out.println("");

            System.out.println("Population");
            System.out.println(this._parentsPopulation.toString());

        } catch (Exception ex) {
            Logger.getLogger(Solver.class.getName()).log(Level.SEVERE, null, ex);
            throw new SolverException();
        }

    }
}
