/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package statistics;

import genetics.Individual;
import genetics.Population;
import java.util.ArrayList;
import utils.PopulationUtils;

/**
 *
 * @author ASUS
 */
public class Statistics {

    final private Population _population;
    
    private double soma = 0;
    private double somatorio;
    private double media;
    
    private Double[] _arrayFitness;

    public Statistics(Population population) {
        this._population = population;
        this._convertPopulationFitnessInArrayOfDoubles();
    }
    
    // Converte todos os fitness dos individuos num array de doubles
    private void _convertPopulationFitnessInArrayOfDoubles() {
        ArrayList<Double> fitnessArray = new ArrayList<Double>(this._population.getSizePopulation());

        for (Individual individuo : this._population) {
            fitnessArray.add(individuo.fitness());
        }

        _arrayFitness = fitnessArray.toArray(new Double[0]);
    }
    
    //metodo que devolve a media
    public Double getMedia() {
        media = 0;
        soma = 0;

        for (int counter = 0; counter < this._arrayFitness.length; counter++) {
            soma += this._arrayFitness[counter];
        }
        media = soma / this._arrayFitness.length;
        return media;
    }

    //metodo que devolve a variância
    public Double getVariancia() {
        soma = 0;
        somatorio = 0;
        
        //Calcula o somatório
        for (int counter = 0; counter < this._arrayFitness.length; counter++) {
            somatorio += Math.pow(this._arrayFitness[counter] - getMedia(), 2);
        }
        double variancia = (somatorio / (this._arrayFitness.length - 1));
        return variancia;
    }

    //metodo que devolve o desvio padrão
    public Double getDesvioPadrao() {
        return Math.sqrt(getVariancia());
    }
    
}
