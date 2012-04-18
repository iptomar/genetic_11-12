/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.exceptions;

/**
 *
 * @author PedroIsi
 */
public class SolverException extends Exception {
    String mistake;

    public SolverException() {
        super();             
        mistake = "Ocorreu um erro no solver.";
    }

    public SolverException(String err) {
        super(err);    
        mistake = err;  
    }

    public String getError() {
        return mistake;
    }
}
