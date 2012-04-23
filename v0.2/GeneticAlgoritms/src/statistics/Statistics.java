package statistics;

import genetics.Individual;
import genetics.Population;
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
 * Classe que servirá para fazer a análise estatistica dos valores da população que é recebida no
 * parametro do construtor da classe.
 * @author Ruben Felix <Ruben.Felix@gmail.com>
 */
public class Statistics {
    /**
     * Variavél que guardará a população a ser analisada estatisticamente
     */
    final private Population _population;
    /**
     * ArrayList que irá conter todos os fitness's dos individuos da população
     */
    private Double[] _arrayFitness;

    /**
     * Construtor da classe que recebe como parametro a população a ser analisada estatisticamente
     * @param population (Population) - População a ser analisada estatisticamente
     */
    public Statistics(Population population) {
        this._population = population;
    }
    
    /**
     * Método que converte todos os fitness's dos individuos num array de doubles, guardando o mesmo na
     * variavél _arrayFitness
     */
    private void convertPopulationFitnessInArrayOfDoubles() {
        ArrayList<Double> fitnessArray = new ArrayList<Double>(this._population.getSizePopulation());
        for (Individual individuo : this._population) {
            fitnessArray.add((double) individuo.fitness());
        }
        _arrayFitness = fitnessArray.toArray(new Double[0]);
    }
    
    /**
     * Método que devolve a média de fitness da população, ou seja, devolve a soma de todos os fitness's dos
     * individuos da população dividido pelo total de individuos da mesma
     * @return (Double) - Média de fitness da população
     */
    public Double getMediaFitnessPopulation() {
        //Converte os fitness's dos individuos para o array de doubles, já que os fitness's podem ter sido modificados desde o inicio da população
        convertPopulationFitnessInArrayOfDoubles();
        //Variaveis necessárias ao calculo da média de fitness da população
        double media = 0;
        double soma = 0;
        //Soma todos os fitness's dos individuos
        for (int counter = 0; counter < this._arrayFitness.length; counter++) {
            soma += this._arrayFitness[counter];
        }
        //Divide os fitness's somados pelo número de fitness's
        media = soma / this._arrayFitness.length;
        //Devolve o valor da média de fitness's da população em analise
        return media;
    }

    /**
     * Método que devolve o valor da variância da população em análise
     * @return (Double) - Valor da variância da população em análise
     */
    public Double getVarianciaPopulation() {
        //Converte os fitness's dos individuos para o array de doubles, já que os fitness's podem ter sido modificados desde o inicio da população
        convertPopulationFitnessInArrayOfDoubles();
        //Variavel necessária ao cálculo da variância
        double somatorio = 0;
        
        //Calcula o somatório
        for (int counter = 0; counter < this._arrayFitness.length; counter++) {
            somatorio += Math.pow(this._arrayFitness[counter] - getMediaFitnessPopulation(), 2);
        }
        double variancia = (somatorio / (this._arrayFitness.length - 1));
        //Devolve o valor da variância da população
        return variancia;
    }

    /**
     * Método que devolve o valor do desvio padrão da população
     * @return (Double) - Valor do desvio padrão da população
     */
    public Double getDesvioPadraoPopulation() {
        return Math.sqrt(getVarianciaPopulation());
    }
    
}
