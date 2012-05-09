package operators.replacements;

import genetics.Population;
import utils.PopulationUtils;

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
 * Classe que implementa o operador de substituição truncation.
 * Este operador recebe duas populações, junta as duas numa só população,
 * ordena a nova população por fitness de individuos do melhor para o pior
 * e devolve uma nova população que será os melhores individuos das duas
 * populações que foram juntas
 * @author Ruben Felix <Ruben.Felix@gmail.com>
 */
public class Truncation extends Replacement {

    /**
     * Construtor da classe onde é passado por parametro a dimensão da nova população
     * @param dimensionsNewPopulation - Dimensão da nova população
     */
    public Truncation(int dimensionsNewPopulation) {
        super.dimensionsNewPopulation = dimensionsNewPopulation;
    }

    /**
     * Construtor da classe que cria um novo Truncation com o número de individuos
     * da nova população a ser igual ao que está definido por defeito
     */
    public Truncation() {
        this(Replacement.DIMENDIONS_NEW_POPULATION_DEFAULT);
    }

    /**
     * Execução do método de substituição, tendo como entradas as duas populações e
     * devolvendo apenas a população já depois de ser efectuado a substituição.
     * @param parents - População pai a sofrer o método
     * @param sons - População filho a sofrer o método
     * @return - Nova população já depois de o método Truncation ser aplicado
     */
    @Override
    public Population execute(Population parents, Population sons) {
        //Cria uma nova população que irá receber os individuos das duas populações para depois ser ordenada
        Population newPopulationToOrder = new Population((parents.getSizePopulation() + sons.getSizePopulation()), parents.getSizeGenome(), parents.getSizeGenotype(), parents.getSizeAllelo(), parents.getTypePopulation(), false);
        //Copia os individuos das duas populações para uma só
        for (int i = 0; i < parents.getPopulation().size(); i++) {
            newPopulationToOrder.addIndividual(parents.getPopulation().get(i));
        }
        for (int i = 0; i < sons.getPopulation().size(); i++) {
            newPopulationToOrder.addIndividual(sons.getPopulation().get(i));
        }

        //Ordena a população por fitness
        PopulationUtils.orderPopulation(newPopulationToOrder);

        //Cria uma nova população que irá receber os melhores individuos das duas populações que foram passadas como parametro
        Population newPopulation = new Population(this.dimensionsNewPopulation, parents.getSizeGenome(), parents.getSizeGenotype(), parents.getSizeAllelo(), parents.getTypePopulation(), false);
        //Copia X de individuos, que serão os X melhores das duas populações juntas
        for (int i = 0; i < newPopulation.getSizePopulation(); i++) {
            newPopulation.addIndividual(newPopulationToOrder.getPopulation().get(i));
        }
        return newPopulation;
    }

    //*********************************************************************************
    //*****************************Métodos para Reflection*****************************
    //*********************************************************************************    
    @Override
    public String getInfo() {
        String s = "<p>Método de reprodução dos indivíduos.</p>"
                + "<p>Este método tem como parâmetros de entrada o tamanho da nova população.</p>"
                + "<p>O parâmetro tem que ser um valor inteiro positivo!</p>";
        return s;
    }

    @Override
    public boolean setParameters(String parameters) {
        int dimensaoPop = Integer.parseInt(parameters);

        try {
            super.dimensionsNewPopulation = dimensaoPop;
            return true;
        } catch (Exception ex) {
            //parametos por defeito
            super.dimensionsNewPopulation = 1000;
            return false;
        }

    }
    //*********************************************************************************
    //*********************************************************************************
    //********************************************************************************* 
}
