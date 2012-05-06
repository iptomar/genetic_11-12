/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genetics.algorithms;

/**
 *
 * @author Chorinca-Notebook
 */
public class K5 extends KnapSack {
    
    public K5() {
        this(ModeFunction.RANDOM);
    }
    
    public K5(ModeFunction modeFunction) {
        super._modeFunction = modeFunction;
        super._parseStringData("5 15 4 2 1 10 2 12 2 1 4 1");    
    }
}
