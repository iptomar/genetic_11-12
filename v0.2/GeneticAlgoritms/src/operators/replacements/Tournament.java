/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package operators.replacements;

import genetics.Individual;
import genetics.Population;
import java.util.ArrayList;

/**
 * Operador que pega em duas populações e faz lutar os individuos, de ambas, para ver 
 * qual os melhores. No final colaca os melhores numa nova população.
 * @author Chorinca-Notebook
 */
public class Tournament extends Replacement {

    static final int SIZE_TOURNAMENT_DEFAULT = 2;    

    final private int _sizeTournament;
    
    /**
     * Construtor por defeito dp Tournament
     */
    public Tournament(){
        this(Replacement.DIMENDIONS_NEW_POPULATION_DEFAULT, SIZE_TOURNAMENT_DEFAULT);
    }
    
    /**
     * Construtor do Tournament
     * @param dimensionsNewPopulation Dimensão da nova população que vai ser gerada
     * @param sizeTournament Numero de individuos a entrar num torneio
     */
    public Tournament(int dimensionsNewPopulation, int sizeTournament){
        super.dimensionsNewPopulation = dimensionsNewPopulation;
        this._sizeTournament = sizeTournament;
    }
    
    /**
     * Aplica o operador de Tournament as duas populações
     * @param parents População dos pais
     * @param sons População dos filhos
     * @return Devolve a população com os melhores individuos
     */
    @Override
    public Population execute(Population parents, Population sons) {
        Population __newPopulation = new Population(
                super.dimensionsNewPopulation, 
                parents.getSizeGenome(), 
                parents.getSizeGenotype(), 
                parents.getSizeAllelo(),
                parents.getTypePopulation());
        
        // Utiliza um operador de Tournament do tipo Selection
        operators.selections.Tournament __tournamentOnePopulation;
        
        // Array de individuos que vai juntar os individuos das
        // duas populações
        ArrayList<Individual> __newArrayListIndividuos;

        // Junta as duas populações numa unica população
        __newArrayListIndividuos = new ArrayList<Individual>(parents.getPopulation());
        __newArrayListIndividuos.addAll(sons.getPopulation());
        
        // aplica um Tournament de uma população as duas populações juntas
        __tournamentOnePopulation = new operators.selections.Tournament(
                super.dimensionsNewPopulation, 
                this._sizeTournament);
        // activa a removeção de individuos apos ganhar o torneio
        __tournamentOnePopulation.setRemoveIndividualFromPopulation(true);
        
        // Adiciona os individuos a população que vai lutar.
        // Atenção, esta população é nova, mas contem dentro dela os 
        // pais e filhos passados em paramentro. Usamos este metodo
        // para aproveitar o codigo da do Tournament de selecção
        __newPopulation.setPopulation(__newArrayListIndividuos);
        
        // devolve uma população com os melhores individuos apos o torneio
        return __tournamentOnePopulation.execute(__newPopulation);
    }
    
}
