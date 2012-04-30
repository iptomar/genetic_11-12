/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genetics;

import genetics.algorithms.K100;
import java.util.ArrayList;
import operators.Operator;
import operators.mutation.Flipbit;
import operators.recombinations.UniformCrossover;
import operators.replacements.Truncation;
import operators.selections.SUS;
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
public class SolverKnapSackExample {
    //ArrayList de operadores a serem utilizados no solver
    ArrayList<Operator> operators = new ArrayList<Operator>(4);
    Solver solver;
    
    public SolverKnapSackExample(){
        //Adiciona os operadores que foram escolhidos
        this.operators.add(new SUS());
        this.operators.add(new Flipbit(0.01));
        operators.add(new Truncation(100));
        operators.add(new UniformCrossover());
        //Solver do k100 que tem como critério de paragem as iterações e os 1352 de fitness que é o maximo de fitness
        //Não corre porque será preciso passar o EventSolver
        solver = new Solver(1000, 10, ((Individual)new K100()), new StopCriterion(100000, 1352.0), operators, null);
    }
    
    public static void main(String[] args) throws SolverException {
        //Executa o solver
        SolverKnapSackExample solver = new SolverKnapSackExample();
        solver.solver.run();
    }
}
