/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.exceptions;

/**
 * Excepção que é disparada quando ocorre um erro ao aplicar um operador, dentro de um solver, 
 * que precise de uma população de filhos e ela ainda não foi inicializada.
 * @author Chorinca
 */
public class SonsInicialitazionException extends SolverException {
    String mistake;

    public SonsInicialitazionException() {
        super();             
        mistake = "Ocorreu um erro com a população dos filhos. Verifique a ordem dos operadores para garantir que existe primeiro um operador de selecção para criar a população de filhos.";
    }

    public SonsInicialitazionException(String err) {
        super(err);    
        mistake = err;  
    }

    @Override
    public String getError() {
        return mistake;
    }
}
