package genetics;
/**
 *Classe em que é definido o critério de paragem.
 *_numberIterations = número de iterações que irão ser executadas
 *_goodFitness = fitness que se pretende atingir
 * @author Chorinca-Notebook
 */
public class StopCriterion {
    
    private int _numberIteractions;    
    private Double _goodFiteness;
/**
 * Constructor que recebe o numero de Iterações e o valor do melhor fitness
 * @param numberIteractions
 * @param goodFiteness 
 */
    public StopCriterion(int numberIteractions, Double goodFiteness) {
        this._numberIteractions = numberIteractions;
        this._goodFiteness = goodFiteness;
    }

    /**
     * Método que devolve o número de iterações.
     * @return the _numberIteractions
     */
    public int getNumberIteractions() {
        return _numberIteractions;
    }

    /**
     * Método que define o numero de iterações.
     * @param numberIteractions the _numberIteractions to set
     */
    public void setNumberIteractions(int numberIteractions) {
        this._numberIteractions = numberIteractions;
    }

    /**
     * Método que devolve o valor do melhor fitness de um indivíduo
     * @return the _goodFiteness
     */
    public Double getGoodFiteness() {
        return _goodFiteness;
    }

    /**
     * Método que define o valor do melhor fitness de um indivíduo.
     * @param goodFiteness the _goodFiteness to set
     */
    public void setGoodFiteness(Double goodFiteness) {
        this._goodFiteness = goodFiteness;
    }
    
}
