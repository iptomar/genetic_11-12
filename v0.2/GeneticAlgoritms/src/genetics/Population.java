package genetics;

import genetics.algorithms.OnesMax;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe que representa uma população de individuos.
 * Recebe como parãmetros o tamanho da população, o tamanho do genoma,
 * tamanho do genótipo, tamanho dos alelos e o tipo da população(ex:boolean)
 * É composta por um ArrayList de elementos do tipo Individual
 * 
 * @author goncalo
 */
public class Population implements Iterable<Individual> {

    public static final int DEFAULT_AGE_POPULATION = 0;
    public static final int DEFAULT_SIZE_POPULATION = 10;
    public static final int DEFAULT_SIZE_GENOME = 1;
    public static final int DEFAULT_SIZE_GENOTYPE = 1;
    public static final int DEFAULT_SIZE_ALLELO = 10;
    public static final Individual DEFAULT_PROTOTYPE_POPULATION = new OnesMax();
    public static final Random RANDOM_GENERATOR = new Random();
    
    private ArrayList<Individual> _population;
    private int _sizePopulation;
    private int _sizeGenotype;
    private int _sizeGenome;
    private int _sizeAllelo;
    private Individual _prototypeIndividual;

    public Population(Individual prototypeIndividual) {
        this(Population.DEFAULT_SIZE_POPULATION,
                Population.DEFAULT_SIZE_GENOME,
                Population.DEFAULT_SIZE_GENOTYPE,
                Population.DEFAULT_SIZE_ALLELO,
                prototypeIndividual);
    }

    public Population(int sizePopulation, int sizeGenome, int sizeGenotype, int sizeAllelo, Individual prototypeIndividual) {
        this(sizePopulation, sizeGenome, sizeGenotype, sizeAllelo, prototypeIndividual, true);
    }

    public Population(int sizePopulation, int sizeGenome, int sizeGenotype, int sizeAllelo, Individual prototypeIndividual, boolean initializesPopulation) {
        this._sizePopulation = sizePopulation;
        this._sizeGenome = sizeGenome;
        this._sizeGenotype = sizeGenotype;
        this._sizeAllelo = sizeAllelo;
        this._prototypeIndividual = prototypeIndividual;

        _population = new ArrayList<Individual>(sizePopulation);

        if (initializesPopulation) {
            _inicializationPopulation();
        }
    }

    public Population(Population newPopulation){
        this(newPopulation.getSizePopulation(), newPopulation.getSizeGenome(), newPopulation._sizeGenotype, newPopulation._sizeAllelo, newPopulation.getTypePopulation(), false);
        
        // Cria chromosomes novos
        for (Individual __individual : newPopulation) {     
            this._population.add(__individual.clone());
        }
    }
    
    private void _inicializationPopulation() {
        for (int __indexIndividual = 0; __indexIndividual < this._sizePopulation; __indexIndividual++) {

            // Cria um novo individuo do tipo de class do prototipo
            Individual __newIndividual = _prototypeIndividual.clone();

            // Define os parametros de inicialização do individuo
            __newIndividual.setSizeGenome(_sizeGenome);
            __newIndividual.setSizeGenotype(_sizeGenotype);
            __newIndividual.setSizeAllelo(_sizeAllelo);

            // Inicializa o genoma
            __newIndividual.inicializationGenome();

            // Adiciona o novo individuo à população actual
            this.getPopulation().add(__newIndividual);
            
        }
    }

    public Individual getIndividual(int index) {
        return getPopulation().get(index);
    }

    public void setIndividual(int index, Individual individual) {
        getPopulation().add(index, individual);
    }

    /**
     * @return the _numberIndividuals
     */
    public int getSizePopulation() {
        return getPopulation().size();
    }

    /**
     * @param sizePopulation the _numberIndividuals to set
     */
    public void setSizePopulation(int sizePopulation) {
        this._sizePopulation = sizePopulation;
    }

    /**
     * @return the _typeIndividual
     */
    public Individual getTypePopulation() {
        return _prototypeIndividual;
    }

    /**
     * @param prototypeIndividual the _typeIndividual to set
     */
    public void setTypePopulation(Individual prototypeIndividual) {
        this._prototypeIndividual = prototypeIndividual;
    }

    public void addIndividual(Individual individual) {
        this.getPopulation().add(individual);
    }

    /**
     * @return the _population
     */
    public ArrayList<Individual> getPopulation() {
        return _population;
    }

    /**
     * @param population the _population to set
     */
    public void setPopulation(ArrayList<Individual> population) {
        this._population = population;
    }

    @Override
    public Iterator<Individual> iterator() {
        return this.getPopulation().iterator();
    }

    /**
     * @return the _sizeAllelo
     */
    public int getSizeAllelo() {
        return _sizeAllelo;
    }

    /**
     * @param sizeAllelo the _sizeAllelo to set
     */
    public void setSizeAllelo(int sizeAllelo) {
        this._sizeAllelo = sizeAllelo;
    }

    @Override
    public String toString() {
        StringBuilder __output = new StringBuilder();
        int __countIndividuals = 1;

        for (Individual __individual : this) {
            __output.append(__countIndividuals);
            __output.append(" - ");
            __output.append(__individual.toString());
            __output.append(" - ");
            __output.append(__individual.fitness());
            __output.append("\n");
            __countIndividuals++;
        }

        return __output.toString();
    }

    /**
     * @return the _sizeGenotype
     */
    public int getSizeGenotype() {
        return _sizeGenotype;
    }

    /**
     * @param sizeGenotype the _sizeGenotype to set
     */
    public void setSizeGenotype(int sizeGenotype) {
        this._sizeGenotype = sizeGenotype;
    }

    /**
     * @return the _sizeGenome
     */
    public int getSizeGenome() {
        return _sizeGenome;
    }

    /**
     * @param sizeGenome the _sizeGenome to set
     */
    public void setSizeGenome(int sizeGenome) {
        this._sizeGenome = sizeGenome;
    }
    
    @Override
    public Population clone() {
        return new Population(this);
    }
    
}
