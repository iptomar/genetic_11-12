package operators.selections;

import genetics.Population;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Ponteiro;
import utils.PopulationUtils;
import utils.exceptions.PonteiroForaDoLimiteException;

/**
 *
 * @author Aurélien Mota Nº13673
 */
public class SUS extends Selection {

    //*********************************************************************************
    //*****************************Métodos para Reflection*****************************
    //*********************************************************************************    
    @Override
    public String getInfo() {
        return "Descrição do operador.... SUS";
    }

    @Override
    public boolean setParameters(String parameters) {
        return false;
    }    
    //*********************************************************************************
    //*********************************************************************************
    //*********************************************************************************      
    
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
        this.ponteiro = Ponteiro.pontoAleatorio(PopulationUtils.getFitnessTotal(population));
        //correr cada individuo da população
        for (int numeroIndividuos = 0; numeroIndividuos < super._dimensionsNewPopulation; numeroIndividuos++) {
            try {
                //acrecenta um individou para a nova população
                newPopulation.addIndividual(
                        Ponteiro.devolveIndividuoParaOndeOPonteiroAponta(this.ponteiro, population));
            } catch (PonteiroForaDoLimiteException ex) {
                Logger.getLogger(SUS.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //faz a incrementação do ponteiro e o mod para voltar ao inicio caso ultrapasse o fitness total
            this.ponteiro = (this.ponteiro + this.offset) % PopulationUtils.getFitnessTotal(population);
        }

        return newPopulation;
    }
    
    //calcula o offset total fitness/total individuos da população
    private double calculateOffset(Population population) {
        // Devolve o novo offset
        return ((double)PopulationUtils.getFitnessTotal(population)) / ((double)population.getSizePopulation());
    }
    
}
