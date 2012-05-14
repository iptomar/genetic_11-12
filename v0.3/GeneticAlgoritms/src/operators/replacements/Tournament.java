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
    private int _sizeTournament;

    public Tournament() {
        this(SIZE_TOURNAMENT_DEFAULT);
    }

//    public Tournament(int dimensionsNewPopulation, int sizeTournament) {
//        super.dimensionsNewPopulation = dimensionsNewPopulation;
//        this._sizeTournament = sizeTournament;
//    }
    public Tournament(int sizeTournament) {
        super.dimensionsNewPopulation = Replacement.DIMENDIONS_NEW_POPULATION_DEFAULT;
        this._sizeTournament = sizeTournament;
    }

    @Override
    public Population execute(Population parents, Population sons) {
        //Serão devolvidos tantos individuos quanto os que entram
        this.dimensionsNewPopulation = parents.getSizePopulation();
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
        String s = "<p>Método de reprodução dos indivíduos de duas populações.</p>"
                + "<p>O operador recebe duas populações, uma pai e uma filho, que</p>"
                + "<p>serão reproduzidas entre elas. A população devolvida tem o mesmo número</p>"
                + "<p>de individuos que o número de individuos da população pai que entra para a</p>"
                + "<p>reprodução.</p>"
                + "<p></p>"
                + "<p>O parametro de entrada do operador será quantos individuos serão escolhidos</p>"
                + "</p>para o torneio a cada iteração do mesmo.</p>";
        return s;
    }

    @Override
    public boolean setParameters(String parameters) {
        try {
            this._sizeTournament = Integer.parseInt(parameters.split(" ")[0]);
            return true;
        } catch (Exception ex) {
            //Devolve false caso alguma coisa corra mal
            return false;
        }
    }
    //*********************************************************************************
    //*********************************************************************************
    //*********************************************************************************
}
