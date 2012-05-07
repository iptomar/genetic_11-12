package utils.exceptions;

/**
 * Exception para indicar que houve algum poblema na classe Solver
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
