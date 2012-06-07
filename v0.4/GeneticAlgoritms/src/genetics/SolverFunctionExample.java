package genetics;

import java.util.ArrayList;
import operators.Operator;
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
public class SolverFunctionExample {

    ArrayList<Operator> operators = new ArrayList<Operator>(4);
    Solver solver;

    public SolverFunctionExample() {
        solver = new Solver();
        //Adiciona os operadores que foram escolhidos

        solver.SetStopCrit("390 1 5 1");
        solver.setParameters("102 1 1 76 FunctionRealShiftedRosenbrock");
        
    }

    public static void main(String[] args) throws SolverException {
        SolverFunctionExample solver = new SolverFunctionExample();
        solver.solver.run();
    }
}
