/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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

    public StopCriterion(int numberIteractions, Double goodFiteness) {
        this._numberIteractions = numberIteractions;
        this._goodFiteness = goodFiteness;
    }

    /**
     * @return the _numberIteractions
     */
    public int getNumberIteractions() {
        return _numberIteractions;
    }

    /**
     * @param numberIteractions the _numberIteractions to set
     */
    public void setNumberIteractions(int numberIteractions) {
        this._numberIteractions = numberIteractions;
    }

    /**
     * @return the _goodFiteness
     */
    public Double getGoodFiteness() {
        return _goodFiteness;
    }

    /**
     * @param goodFiteness the _goodFiteness to set
     */
    public void setGoodFiteness(Double goodFiteness) {
        this._goodFiteness = goodFiteness;
    }
    
}
