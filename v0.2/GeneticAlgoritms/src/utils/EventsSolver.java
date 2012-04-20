/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import genetics.Population;
import statistics.DesvioPadrao;

/**
 *
 * @author Aurélien Mota
 * 
 * Eventos que são disparados atraves do Solver a informar
 * o inicio, cada iteração e o fim do solver
 * 
 */
public interface EventsSolver {
    
    public void EventStartSolver();    
    public void EventIteraction(int iteractionNumber, Population currentPopulation, DesvioPadrao desvioPadrao);
    public void EventFinishSolver(int totalIteracoes, Population lastPopulation, DesvioPadrao desvioPadrao);

}
