/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package operators.selections;

import genetics.Individual;
import genetics.Population;
import java.util.ArrayList;
import utils.PopulationUtils;

/**
 *
 * @author Chorinca-Notebook
 */
public class Tournament extends Selection {

    static final int SIZE_TOURNAMENT_DEFAULT = 2;   
    static final boolean  DEFAULT_REMOVE_INDIVIDUAL_FROM_POPULATION = false;
    
    static final short MAXIMIZATION_TOURNAMENT = 0;
    static final short MINIMIZATION_TOURNAMENT = 1;
    
    private int _sizeTournament;
    
    private boolean _removeIndividualFromPopulation = DEFAULT_REMOVE_INDIVIDUAL_FROM_POPULATION;
    
    public Tournament(){
        this(Selection.DIMENDIONS_NEW_POPULATION_DEFAULT, Tournament.SIZE_TOURNAMENT_DEFAULT);
    }
    
    public Tournament(int dimensionsNewPopulation, int sizeTournament){
        super._dimensionsNewPopulation = dimensionsNewPopulation;
        this._sizeTournament = sizeTournament;
        this._typeSelection = Tournament.MAXIMIZATION_TOURNAMENT;
    }
    
    @Override
    public Population execute(Population population) {
        // nova população a ser criada com os individuos mais aptos
        final Population _newPopulation = 
                new Population(
                    super._dimensionsNewPopulation, 
                    population.getSizeGenome(), 
                    population.getSizeGenotype(),
                    population.getSizeAllelo(),
                    population.getTypePopulation(), 
                    false);
        
        // variavel que vai guardar o individuo mais apto
        Individual __bestIndividualTournament;
        // variavel que vai guardar o candidato a individuo mais apto
        Individual __individualsToFightForBest;
        
        // Criar nova população
        for (int __numberIndividualsInTheNewPopulation = 0; __numberIndividualsInTheNewPopulation < super._dimensionsNewPopulation; __numberIndividualsInTheNewPopulation++) {
            // Selecionar de forma aleatoria os individuos da população de pais
            ArrayList<Individual> __individualToEnterInTheTournament = PopulationUtils.getArrayIndividualsRandom(population, this._sizeTournament, this._removeIndividualFromPopulation);

            // Selecionar o primeiro individuo, e por defeito é logo o bestIndividuo
            __bestIndividualTournament = __individualToEnterInTheTournament.get(0);
            // remover esse individuo da lista de candidatos
            __individualToEnterInTheTournament.remove(__bestIndividualTournament);
            
            // Corre a lista de candidatos ate não existir mais candidatos
            while(__individualToEnterInTheTournament.size() > 0) {
                
                // Seleciona o primeiro individuo dos candidatos a best
                __individualsToFightForBest = __individualToEnterInTheTournament.get(0);
                if(this._typeSelection == Tournament.MAXIMIZATION_TOURNAMENT)
                    __bestIndividualTournament = _logicOfMaximization(__bestIndividualTournament, __individualsToFightForBest, __individualToEnterInTheTournament, population);
                else
                    __bestIndividualTournament = _logicOfMinimization(__bestIndividualTournament, __individualsToFightForBest, __individualToEnterInTheTournament, population);                    
            }

            // por fim quando não existir mais candidatos adicionamos o best à nova população
            _newPopulation.addIndividual(__bestIndividualTournament.clone());
        }
        
        // devolve a nova população com os individuos mais aptos
        return _newPopulation;
    }

    private Individual _logicOfMaximization(Individual __bestIndividualTournament, Individual __individualsToFightForBest, ArrayList<Individual> __individualToEnterInTheTournament, Population population) {
        // Comparar os individuos com base no fiteness
        if(__bestIndividualTournament.fitness() >= __individualsToFightForBest.fitness()){
            // se o individuo continuar a ser o melhor entao remove se o candidato a best
            __individualToEnterInTheTournament.remove(__individualsToFightForBest);
            // e volta a por esse individuo na população
            population.addIndividual(__individualsToFightForBest);
        } else {
            // devolve a população o individuo que perdeu, neste caso era o que ate agora era considerado
            // o melhor candidato
            population.addIndividual(__bestIndividualTournament);
            // se o candidato a best tiver mais fiteness então passa a ser o best e remove se da lista
            // de candidatos
            __bestIndividualTournament = __individualsToFightForBest;
            __individualToEnterInTheTournament.remove(__bestIndividualTournament);
        }
        return __bestIndividualTournament;
    }
    
    private Individual _logicOfMinimization(Individual __bestIndividualTournament, Individual __individualsToFightForBest, ArrayList<Individual> __individualToEnterInTheTournament, Population population) {
        // Comparar os individuos com base no fiteness
        if(__bestIndividualTournament.fitness() <= __individualsToFightForBest.fitness()){
            // se o individuo continuar a ser o melhor entao remove se o candidato a best
            __individualToEnterInTheTournament.remove(__individualsToFightForBest);
            // e volta a por esse individuo na população
            population.addIndividual(__individualsToFightForBest);
        } else {
            // devolve a população o individuo que perdeu, neste caso era o que ate agora era considerado
            // o melhor candidato
            population.addIndividual(__bestIndividualTournament);
            // se o candidato a best tiver mais fiteness então passa a ser o best e remove se da lista
            // de candidatos
            __bestIndividualTournament = __individualsToFightForBest;
            __individualToEnterInTheTournament.remove(__bestIndividualTournament);
        }
        return __bestIndividualTournament;
    }

    /**
     * @return the _removeIndividualFromPopulation
     */
    public boolean isRemoveIndividualFromPopulation() {
        return _removeIndividualFromPopulation;
    }

    /**
     * @param removeIndividualFromPopulation the _removeIndividualFromPopulation to set
     */
    public void setRemoveIndividualFromPopulation(boolean removeIndividualFromPopulation) {
        this._removeIndividualFromPopulation = removeIndividualFromPopulation;
    }
    
    //*********************************************************************************
    //*****************************Métodos para Reflection*****************************
    //*********************************************************************************    
    @Override
    public String getInfo() {
        String s = "<p>Método usado para fazer uma selecção de indivíduos a uma população.</p>"
                + "<p>Este método tem como parâmetros de entrada o tamanho da nova população</p> "
                + "<p>e o número de indivíduos que vão competir entre si em cada iteração do operador.</p>"
                + "<p>Ambos os parâmetros são valores inteiros positivos.</p>";
        return s;
    }

    @Override
    public boolean setParameters(String parameters) {
        int dimensaoPop = Integer.parseInt(parameters.split(" ")[0]);
        int tamanhoTorneio= Integer.parseInt(parameters.split(" ")[1]);
        
        try{
           this._dimensionsNewPopulation = dimensaoPop;
           this._sizeTournament = tamanhoTorneio;
           return true;
       }catch(Exception ex){
           //parametos por defeito
           this._dimensionsNewPopulation = 100;
           this._sizeTournament = SIZE_TOURNAMENT_DEFAULT;
           return false;
       }
    }    
    //*********************************************************************************
    //*********************************************************************************
    //*********************************************************************************
}
