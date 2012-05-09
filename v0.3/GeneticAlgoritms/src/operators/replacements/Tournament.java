package operators.replacements;

import genetics.Individual;
import genetics.Population;
import java.util.ArrayList;

/**
 *
 * @author Chorinca-Notebook
 */
public class Tournament extends Replacement {

    static final int SIZE_TOURNAMENT_DEFAULT = 2;
    final private int _sizeTournament;

    public Tournament() {
        this(Replacement.DIMENDIONS_NEW_POPULATION_DEFAULT, SIZE_TOURNAMENT_DEFAULT);
    }

    public Tournament(int dimensionsNewPopulation, int sizeTournament) {
        super.dimensionsNewPopulation = dimensionsNewPopulation;
        this._sizeTournament = sizeTournament;
    }

    @Override
    public Population execute(Population parents, Population sons) {
        Population __newPopulation = new Population(
                super.dimensionsNewPopulation,
                parents.getSizeGenome(),
                parents.getSizeGenotype(),
                parents.getSizeAllelo(),
                parents.getTypePopulation());
        operators.selections.Tournament __tournamentOnePopulation;
        ArrayList<Individual> __newArrayListIndividuos;

        // Junta as duas populações numa unica população
        __newArrayListIndividuos = new ArrayList<Individual>(parents.getPopulation());
        __newArrayListIndividuos.addAll(sons.getPopulation());

        __tournamentOnePopulation = new operators.selections.Tournament(
                super.dimensionsNewPopulation,
                this._sizeTournament);
        __tournamentOnePopulation.setRemoveIndividualFromPopulation(true);

        __newPopulation.setPopulation(__newArrayListIndividuos);

        return __tournamentOnePopulation.execute(__newPopulation);
    }

    //*********************************************************************************
    //*****************************Métodos para Reflection*****************************
    //*********************************************************************************    
    @Override
    public String getInfo() {
        String s = "<p>Método de recombinação dos indivíduos. Este método tem como </p>"
                + "<p>parâmetros de entrada o tamanho da nova população e o número de </p>"
                + "<p>indivíduos que vão ser recombinados.Ambos os parâmetros são valores</p>"
                + "<p> inteiros como positivos!<p>";
        return s;
    }

    @Override
    public boolean setParameters(String parameters) {
        return true;
    }
    //*********************************************************************************
    //*********************************************************************************
    //*********************************************************************************
}
