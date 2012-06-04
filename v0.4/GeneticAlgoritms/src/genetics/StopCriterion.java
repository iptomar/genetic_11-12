package genetics;

/**
 * Classe em que é definido o critério de paragem. _numberIterations = número de
 * iterações que irão ser executadas _goodFitness = fitness que se pretende
 * atingir
 *
 * @author Chorinca-Notebook
 */
public class StopCriterion {

    public static short NO_ITERACTIONS_LIMIT = -1;
    
    public static short TYPE_PROBLEM_MAXIMIZATION = 0;
    public static short TYPE_PROBLEM_MINIMIZATION = 1;
    
    private int _numberIteractions;
    private Double _goodFiteness;
    private int _numberBests;
    private long _secondsToRun;
    private short _typeProblem;

    /**
     * Constructor que recebe o numero de Iterações e o valor do melhor fitness
     *
     * @param numberIteractions
     * @param goodFiteness
     */
    public StopCriterion(int numberIteractions, Double goodFiteness) {
        this._numberIteractions = numberIteractions;
        this._goodFiteness = goodFiteness;
        this._secondsToRun = 3600;
        this._typeProblem = TYPE_PROBLEM_MINIMIZATION;
    }

    /**
     * Constructor que recebe o valor do melhor fitness, numero de bests e numero de minutos
     * @param goodFiteness
     * @param numberBests
     * @param secondsToRun 
     */
    public StopCriterion(Double goodFiteness, int numberBests, long secondsToRun, short typeProble) {
        this._numberIteractions = NO_ITERACTIONS_LIMIT;
        this._goodFiteness = goodFiteness;
        this._numberBests = numberBests;
        this._secondsToRun = secondsToRun;
        this._typeProblem = typeProble;
    }

    /**
     * Constructor que recebe o valor do melhor fitness, numero de bests e numero de minutos
     * @param goodFiteness
     * @param numberBests
     * @param secondsToRun 
     */
    public StopCriterion(Double goodFiteness, int numberBests, long secondsToRun, short typeProble) {
        this._numberIteractions = NO_ITERACTIONS_LIMIT;
        this._goodFiteness = goodFiteness;
        this._numberBests = numberBests;
        this._secondsToRun = secondsToRun;
        this._typeProblem = typeProble;
    }

    /**
     * Método que devolve o número de iterações.
     *
     * @return the _numberIteractions
     */
    public int getNumberIteractions() {
        return _numberIteractions;
    }

    /**
     * Método que define o numero de iterações.
     *
     * @param numberIteractions the _numberIteractions to set
     */
    public void setNumberIteractions(int numberIteractions) {
        this._numberIteractions = numberIteractions;
    }

    /**
     * Método que devolve o valor do melhor fitness de um indivíduo
     *
     * @return the _goodFiteness
     */
    public Double getGoodFiteness() {
        return _goodFiteness;
    }

    /**
     * Método que define o valor do melhor fitness de um indivíduo.
     *
     * @param goodFiteness the _goodFiteness to set
     */
    public void setGoodFiteness(Double goodFiteness) {
        this._goodFiteness = goodFiteness;
    }

    /**
     * @return the _numberBests
     */
    public int getNumberBests() {
        return _numberBests;
    }

    /**
     * @param numberBests the _numberBests to set
     */
    public void setNumberBests(int numberBests) {
        this._numberBests = numberBests;
    }

    /**
     * @return the _minutesToRun
     */
    public long getSecondsToRun() {
        return _secondsToRun;
    }

    /**
     * @param secondsToRun the _minutesToRun to set
     */
    public void setSecondsToRun(long secondsToRun) {
        this._secondsToRun = secondsToRun;
    }

    /**
     * @return the _typeProblem
     */
    public short getTypeProblem() {
        return _typeProblem;
    }

    /**
     * @param typeProblem the _typeProblem to set
     */
    public void setTypeProblem(short typeProblem) {
        this._typeProblem = typeProblem;
    }
}
