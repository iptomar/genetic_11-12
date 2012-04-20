package genetics;

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
    public static final Class DEFAULT_PROTOTYPE_POPULATION = OnesMax.class;
    public static final Random RANDOM_GENERATOR = new Random();
    
    private ArrayList<Individual> _population;
    private int _sizePopulation;
    private int _sizeGenotype;
    private int _sizeGenome;
    private int _sizeAllelo;
    private Class _prototypeIndividual;

    public Population(Class prototypeIndividual) {
        this(Population.DEFAULT_SIZE_POPULATION,
                Population.DEFAULT_SIZE_GENOME,
                Population.DEFAULT_SIZE_GENOTYPE,
                Population.DEFAULT_SIZE_ALLELO,
                prototypeIndividual);
    }

    public Population(int sizePopulation, int sizeGenome, int sizeGenotype, int sizeAllelo, Class prototypeIndividual) {
        this(sizePopulation, sizeGenome, sizeGenotype, sizeAllelo, prototypeIndividual, true);
    }

    public Population(int sizePopulation, int sizeGenome, int sizeGenotype, int sizeAllelo, Class prototypeIndividual, boolean initializesPopulation) {
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

    private void _inicializationPopulation() {
        for (int __indexIndividual = 0; __indexIndividual < this._sizePopulation; __indexIndividual++) {
            
            try {
                
                // Cria um novo individuo do tipo de class do prototipo
                Individual __newIndividual = (Individual) _prototypeIndividual.newInstance();

                // Define os parametros de inicialização do individuo
                __newIndividual.setSizeGenome(_sizeGenome);
                __newIndividual.setSizeGenotype(_sizeGenotype);
                __newIndividual.setSizeAllelo(_sizeAllelo);

                // Inicializa o genoma
                __newIndividual.inicializationGenome();

                // Adiciona o novo individuo à população actual
                this.getPopulation().add(__newIndividual);

            } catch (InstantiationException ex) {
                Logger.getLogger(Population.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Population.class.getName()).log(Level.SEVERE, null, ex);
            }
            
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
    public Class getTypePopulation() {
        return _prototypeIndividual;
    }

    /**
     * @param prototypeIndividual the _typeIndividual to set
     */
    public void setTypePopulation(Class prototypeIndividual) {
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
            __output.append(__individual.fiteness());
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

//    public void incrementAgePopulation() {
//        for (Individual __individual : this) {
//            __individual.incrementAge();
//        }
//    }
//
//    public void resetAgePopulation() {
//        for (Individual __individual : this) {
//            __individual.setAgeIndividual(0);
//        }
//    }


    
//    /**
//     * Método que devolve o número de individúos que tem o fitness máximo na população
//     * @return - Número de individuos na população que tem o valor de fitness máximo
//     */
//    public int getNumBestFitness(){
//        int bestFitness = getBestFiteness();
//        int numBests = 0;
//        for (int i = 0; i < this._sizePopulation; i++) {
//            Individual Ind = this.getIndividual(i);
//            if (bestFitness == Ind.fiteness()) numBests++;
//        }
//        return numBests;
//    }
    
//    /**
//     * **************************************************************************
//     * ***** FALTA IMPLEMENTAÇÃO PARA VÁRIOS INDIVIDUOS COM O MESMO FITNESS *****
//     * **************************************************************************
//     */
//    /**
//     * Método que devolve o melhor individuo da população para ser analisado
//     * @return (Individual) - O melhor individuo da população
//     */
//    public Individual getBestInd(){
//        int bestFitness = getBestFiteness();
//        for (int i = 0; i < this._sizePopulation; i++) {
//            Individual Ind = this.getIndividual(i);
//            if (bestFitness == Ind.fiteness()) return Ind;
//        }
//        return null;
//    }
    
//    /**
//     * **************************************************************************
//     * ***** FALTA IMPLEMENTAÇÃO PARA VÁRIOS INDIVIDUOS COM O MESMO FITNESS *****
//     * **************************************************************************
//     */
//    /**
//     * Método que devolve uma string com as informações do melhor individuo desta população
//     * @return (String) - String com o conteudo do melhor individuo da população
//     */
//    public String getBestIndString(){
//        return getBestInd().toString();
//    }

//    public Population getHallOfFame(int sizeHallOfFame) {
//        final Population __newPopulation =
//                new Population(
//                sizeHallOfFame,
//                this._sizeGenome,
//                this._sizeGenotype,
//                this._sizeAllelo,
//                this._typePopulation,
//                false);
//
//        Collections.sort(this.getPopulation(), new ComparatorIndividual());
//        for (Individual __individualHallOfFame : this.getPopulation().subList(0, 5)) {
//            __newPopulation.addIndividual(__individualHallOfFame);
//        }
//
//        return __newPopulation;
//    }

//    /**
//     * Método que ordena o array de individuos da poipulação pelo seu fitness 
//     */
//    public void orderPopulation() {
//        Collections.sort(this.getPopulation(), new ComparatorIndividual());
//    }
//    public void orderPopulation() {
//        Collections.sort(this._population);
//    }

//    /**
//     * Método que devolve a média de fitness da população
//     * @return Media - Média de fitness da população
//     */
//    public double getMediaFitness() {
//        double media = 0.0;
//        double soma = 0;
//        //Array do tamanho da população
//        double array[] = new double[_population.size()];
//        //Guarda no array todos os fitness de todos os individuos
//        for (int i = 0; i < _population.size(); i++) {
//            array[i] = _population.get(i).fiteness();
//        }
//
//        for (int counter = 0; counter < array.length; counter++) {
//            soma += array[counter];
//        }
//        media = soma / array.length;
//        //Devolve a média de fitness da população
//        return media;
//    }
//
//    /**
//     * Método que devolve o valor da variância da população
//     * @return Variancia - Variância da população
//     */
//    public double getVariancia() {
//        double mediaFitness = getMediaFitness();
//        double soma, somatorio = 0.0;
//        //Array do tamanho da população
//        double array[] = new double[_population.size()];
//        //Guarda no array todos os fitness de todos os individuos
//        for (int i = 0; i < _population.size(); i++) {
//            array[i] = _population.get(i).fiteness();
//        }
//        //Calcula o somatório
//        for (int counter = 0; counter < array.length; counter++) {
//            somatorio += Math.pow(array[counter] - mediaFitness, 2);
//        }
//        double variancia = (somatorio / (array.length - 1));
//        return variancia;
//    }
//
//    /**
//     * Método que devolve o desvio padrão da população
//     * @return DesvioPadrao - Desvio padrão da população
//     */
//    public double getDesvioPadrao() {
//        return Math.sqrt(getVariancia());
//    }

}
