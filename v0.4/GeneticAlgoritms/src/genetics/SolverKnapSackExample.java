package genetics;

import genetics.algorithms.K50;
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
        this.operators.add(new SUS(200));
        this.operators.add(new Flipbit(0.025));
        operators.add(new Truncation());
        operators.add(new UniformCrossover(0.40));
        
        //Solver do k100 que tem como critério de paragem as iterações e os 1352 de fitness que é o maximo de fitness
        //Não corre porque será preciso passar o EventSolver
        solver = new Solver();
        solver._operators = operators;
        //solver.SetStopCrit("10000 385");
        solver.SetStopCrit("1920 4 3600 0");
        //solver.SetStopCrit("1561 7 3600 0");
        /**
         * **********************************************************************************************************
         * ***** NÃO PUDEMOS ESQUECER DE METER OS 17 (TAMANHO ALLELO) E OS 1300 (PESO MÁXIMO MOCHILA) NA STRING *****
         * **********************************************************************************************************
         */
        //solver.setParameters("1000 1 1 17 KnapSack ModeFunction.RANDOM 2:17 1300 10 20 30 10 10 12 4 24 25 21 52 32 13 43 13 53 13 2 6 9 8 69 78 65 8 89 78 68 56 97 68 98 76 87");
        solver.setParameters("100 1 1 50 K50");
        //solver.setParameters("200 1 1 100 K100");
    }
    
    public static void main(String[] args) throws SolverException {
        //Executa o solver
        SolverKnapSackExample solver = new SolverKnapSackExample();
        solver.solver.run();
    }
}
