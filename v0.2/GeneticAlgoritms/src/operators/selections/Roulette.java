/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package operators.selections;

import genetics.Individual;
import genetics.Population;
import operators.Genetic;
import utils.Ponteiro;

/**
 *
 * @author PedroIsi
 */
public class Roulette extends Selection {

    private double ponteiro = 0.0;
    
    public Roulette(){
        this(Selection.DIMENDIONS_NEW_POPULATION_DEFAULT);
    }
    
    //contructor com parametro, dimensão da nova população
    public Roulette(int dimensionsNewPopulation){
        super._dimensionsNewPopulation = dimensionsNewPopulation;
    }
    
    @Override
    public Population execute(Population population) {
        //criar nova população
        //variavel so de leitura ao ser inicializada 
        final Population newPopulation = 
                new Population(
                    super._dimensionsNewPopulation, 
                    population.getSizeGenome(), 
                    population.getSizeGenotype(),
                    population.getSizeAllelo(),
                    population.getTypePopulation(), 
                    false);
        
        //correr cada individuo da população
        for (int numeroIndividuos = 0; numeroIndividuos < super._dimensionsNewPopulation; numeroIndividuos++) {
            //ponteiro que vai apontar para os individuos, inicialização com ponto aleatorio
            this.ponteiro = Ponteiro.pontoAleatorio(population.getFitnessTotal());
            
            //acrecenta um individou para a nova população
            newPopulation.addIndividual(
                    Ponteiro.devolveIndividuoParaOndeOPontoAponta(this.ponteiro, population));
        }

        return newPopulation;
    }
    
}
