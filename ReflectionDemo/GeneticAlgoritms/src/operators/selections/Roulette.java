package operators.selections;

import genetics.Population;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Ponteiro;
import utils.PopulationUtils;
import utils.exceptions.PonteiroForaDoLimiteException;

/**
 *
 * @author PedroIsi
 */
public class Roulette extends Selection {

    //*********************************************************************************
    //*****************************Métodos para Reflection*****************************
    //*********************************************************************************    
    @Override
    public String getInfo() {
        return "Descrição do operador.... Roulette";
    }

    @Override
    public boolean setParameters(String parameters) {
        return false;
    }    
    //*********************************************************************************
    //*********************************************************************************
    //*********************************************************************************      
    
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
            this.ponteiro = Ponteiro.pontoAleatorio(PopulationUtils.getFitnessTotal(population));
            
            try {
                //acrecenta um individou para a nova população
                newPopulation.addIndividual(
                        Ponteiro.devolveIndividuoParaOndeOPonteiroAponta(this.ponteiro, population));
            } catch (PonteiroForaDoLimiteException ex) {
                Logger.getLogger(Roulette.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return newPopulation;
    }
    
}
