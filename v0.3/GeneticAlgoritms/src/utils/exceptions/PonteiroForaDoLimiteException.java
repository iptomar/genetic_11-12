/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.exceptions;

/**
 *
 * @author PedroIsi
 * 
 * Exception para indicar que o ponteiro a ser usado esta fora do limite aceitavel
 * 
 */
public class PonteiroForaDoLimiteException extends Exception {
    String mistake;

    public PonteiroForaDoLimiteException() {
        super();             
        mistake = "Ponteiro fora do limite permitido.";
    }

    public PonteiroForaDoLimiteException(String err) {
        super(err);    
        mistake = err;  
    }

    public String getError() {
        return mistake;
    }
}
