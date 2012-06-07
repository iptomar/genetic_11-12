/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package operators.selections;

import operators.Genetic;

/**
 *
 * @author Chorinca-Notebook
 */
public abstract class Selection extends Genetic {
    
    static final int DIMENDIONS_NEW_POPULATION_DEFAULT = 10;
    protected int _dimensionsNewPopulation;
    
    protected short _typeSelection;
    /**
     * Define se o tournament vai funcionar com um operador de selecção de minimizalção ou de maximização
     * @param typeSelection 0 é maximixação e 1 é minimização, por defeito esta a 0
     */
    public void setTypeSelection(short typeSelection) {
        this._typeSelection = typeSelection;
    }
    
}
