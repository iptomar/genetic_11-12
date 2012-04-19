/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import genetics.KnapSack;
import genetics.SolverKnapSack;
import java.util.ArrayList;
import utils.EventsSolver;
import utils.Item;
import utils.Mochila;
import utils.exceptions.SolverException;

/* -------------------------------------------------------------------------
 * -------------------------------------------------------------------------
 *  I n s t i t u t o   P o l i t e c n i c o   d e   T o m a r
 *   E s c o l a   S u p e r i o r   d e   T e c n o l o g i a
 *
 * @author Ruben Felix <Ruben.Felix@gmail.com>
 * -------------------------------------------------------------------------
 * Número de Aluno: 13691 
 * E-mail: Ruben.Felix@gmail.com
 * -------------------------------------------------------------------------
 * -------------------------------------------------------------------------
 */
public class Versao02TesteSolverKnapSack {
    public static void main(String[] args) throws SolverException {
        //Mochila a ser utilizada no problema
        ArrayList<Item> items = new ArrayList<Item>();
        
        for (int i = 0; i < 200; i++) {
            items.add(new Item(i,i,true));
        }
        
        //Mochila mochila = new Mochila(25,15,30,10);
        Mochila mochila = new Mochila(200,items, false);
        //Solver a ser corrido
        EventsSolver eventSolver = null;
        SolverKnapSack solver = new SolverKnapSack(10000,new KnapSack(),99,1000,mochila, eventSolver);
        //SolverKnapSack solver = new SolverKnapSack(mochila, eventSolver);
        solver.run();
    }
}
