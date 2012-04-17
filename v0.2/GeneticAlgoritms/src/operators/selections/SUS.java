/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package operators.selections;

import genetics.Individual;
import genetics.Population;
import operators.Genetic;

/**
 *
 * @author Aurélien Mota Nº13673
 */
public class SUS extends Selection {

    
    private double offset = 0.0; 
    private double ponteiro = 0.0;
    
    
    public SUS(){
        this(Selection.DIMENDIONS_NEW_POPULATION_DEFAULT);
    }
    
    //contructor com parametro, dimensão da nova população
    public SUS(int dimensionsNewPopulation){
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
        
        //calcula offset
        this.offset = calculateOffset(population);
        //ponteiro que vai apontar para os individuos, inicialização com ponto aleatorio
        this.ponteiro = pontoAleatorioInicial(population.getFitnessTotal());
        //correr cada individuo da população
        for (int numeroIndividuos = 0; numeroIndividuos < super._dimensionsNewPopulation; numeroIndividuos++) {
            //acrecenta um individou para a nova população
            newPopulation.addIndividual(
                    devolveIndividuoParaOndeOPontoAponta(this.ponteiro, population));
            //faz a incrementação do ponteiro e o mod para voltar ao inicio caso ultrapasse o fitness total
            this.ponteiro = (this.ponteiro + this.offset) % population.getFitnessTotal();
        }

        return newPopulation;
    }
    
    //calcula o offset total fitness/total individuos da população
    private double calculateOffset(Population population) {
        double newOffset = 0.0;
        newOffset = ((double)population.getFitnessTotal()) / ((double)population.getSizePopulation());
        return newOffset;
    }
    
    //define um ponto aleatorio entre o e o total de fitness da população
    private double pontoAleatorioInicial(int totalFitnessPopulacao){
        return Genetic.RANDOM_GENERATOR.nextDouble() * (double)totalFitnessPopulacao;
    }
    
    //devolve individuo para onde o ponto aponta
    private Individual devolveIndividuoParaOndeOPontoAponta(double ponteiro, Population population){
        int totalFitness = 0;
        
        //corre a população para cada individuo
        for (Individual individuo : population) {
            //incrementa o total fitness
            totalFitness += individuo.fiteness();
            
            //escolhe o individuo onde o ponteiro aponta 
            if(ponteiro <= (double)totalFitness) {
                //clone para criar um novo individuo e não ser individuo da pop original
                return individuo.clone();
            }
        }
        
        return null;
    }
    
}
