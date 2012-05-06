package operators;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Chorinca-Notebook
 */
public abstract class Operator {
    //*********************************************************************************
    //*****************************Métodos para Reflection*****************************
    //*********************************************************************************
    /**
     * Método para obter informações sobre o algoritmo
     * @return 
     */
    public abstract String getInfo();
    
    /**
     * Método para introduzir parametros atrvés de uma string
     * @param parameters parametros introduzidos por uma string para o problema.
     * @return True parametros introduzidos correctamente, False erro ao introduzir parametros
     */
    public abstract boolean setParameters(String parameters);      

    //*********************************************************************************
    //*********************************************************************************
    //*********************************************************************************    
}
