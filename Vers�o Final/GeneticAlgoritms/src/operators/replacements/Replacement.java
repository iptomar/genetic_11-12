package operators.replacements;

import genetics.Population;
import operators.Operator;


/**
 *
 * @author Chorinca-Notebook
 */
public abstract class Replacement extends Operator {
 
    static final int DIMENDIONS_NEW_POPULATION_DEFAULT = 10;
    protected int dimensionsNewPopulation;
    
    public abstract Population execute(Population parents, Population sons);

    protected short _typeReplacement;
    /**
     * Define se o replacement vai funcionar com um operador de recolocação de minimizalção ou de maximização
     * @param typeReplacement 0 é maximixação e 1 é minimização, por defeito esta a 0
     */
    public void setTypeReplacement(short typeReplacement) {
        this._typeReplacement = typeReplacement;
    }
    
}
