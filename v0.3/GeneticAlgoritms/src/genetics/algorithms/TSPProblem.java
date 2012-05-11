package genetics.algorithms;

import java.util.ArrayList;

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

/**
 * Classe que contem as denições do problema do travelling salesman problem (TSP) ou caixeiro viajante
 * @author Ruben Felix <Ruben.Felix@gmail.com>
 */
public class TSPProblem {
    /**
     * String com o nome do problema
     */
    public String Name              = "";
    /**
     * String com o comentário do problema
     */
    public String Comment           = "";
    /**
     * String com o tipo de problema a que se refere
     */
    public String Type              = "";
    /**
     * Dimensão do problema
     */
    public String Dimension         = "";
    public String Edge_Weight_Type  = "";
  
    /**
     * ArrayList de cidades que definem o problema
     */
    public ArrayList<City> Cidades = new ArrayList<City>();
    
    /**
     * Método da classe que recebe duas cidades e devolve a distância entre as mesmas
     * @param cidadeA - Cidade A para ser calculada a distância
     * @param cidadeB - Cidade B para ser calculada a distância
     * @return Distância entre as duas cidades recebidas por parametro
     */
    public double distanciaEntreCidades(City cidadeA, City cidadeB) {
        double xd = cidadeA.X - cidadeB.X;
        double yd = cidadeA.Y - cidadeB.Y;
        return Math.sqrt(xd*xd + yd*yd);
    }
    
    public String getInfo() {
        return "Não implementado pela Genetic";
    }

    public boolean setParameters(String parameters) {
        return false;
    }
    
}
