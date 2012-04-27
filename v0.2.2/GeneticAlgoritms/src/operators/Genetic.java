package operators;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import genetics.Population;
import java.security.Timestamp;
import java.util.Random;
import java.util.TimeZone;
import sun.security.timestamp.Timestamper;

/**
 *
 * @author Chorinca-Notebook
 */
public abstract class Genetic extends Operator {
    
    static final public Random RANDOM_GENERATOR = new Random(TimeZone.LONG);
    
    public abstract Population execute(Population population);
    
}
