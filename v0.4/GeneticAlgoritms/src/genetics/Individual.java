package genetics;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Classe que representa um individuo. A classe é abstrata para que possa ser utilizada em cada tipo de individuo definido
 * na aplicação.
 * Recebe como parâmetro o tamanho do genoma, tamanho do genótipo e tamanho dos alelos.
 * É composta por um ArrayList com elementos do tipo Chromossome, chamados genome.
 * @author goncalo
 */
public abstract class Individual implements Iterable<Chromosome>, Serializable {

    /**
     * ArrayList de cromossomas que farão a definição do genoma do individuo
     */
    private final ArrayList<Chromosome> _genome;
    /**
     * Variavel que contem o número de cromossomas do individuo (genoma)
     */
    private int _sizeGenome;
    /**
     * Variavel que contem o tamanho do genes por cromossoma do individuo (genotype)
     */
    private int _sizeGenotype;
    /**
     * Variavel que contem o tamanho do allelo que cada gene irá contem para este individuo
     */
    private int _sizeAllelo;
    /**
     * Variavel que contem a idade do individuo
     */
    private int _ageIndividual = Population.DEFAULT_AGE_POPULATION;

    /**
     * Construtor da classe onde não são passados parametros para a construção do mesmo, sendo que 
     * as variaveis são inicializadas com valores por defeito que estão definidas na classe Population
     */
    public Individual() {
        this(Population.DEFAULT_SIZE_GENOME,
                Population.DEFAULT_SIZE_GENOTYPE,
                Population.DEFAULT_SIZE_ALLELO);
    }

    /**
     * Construtor da classe onde são passados por parametro o tamanho do genoma, do genotype e do tamanho do allelo
     * do individuo.
     * @param sizeGenome - Tamanho do genoma do individuo
     * @param sizeGenotype - Tamanho do genotype do individup
     * @param sizeAllelo - Tamanho do allelo do individuo
     */
    public Individual(int sizeGenome, int sizeGenotype, int sizeAllelo) {
        this._sizeGenome = sizeGenome;
        this._sizeGenotype = sizeGenotype;
        this._sizeAllelo = sizeAllelo;
        this._genome = new ArrayList<Chromosome>(sizeGenome);
    }

    /**
     * Construtor que recebe um individuo como parametro, sendo que o novo individuo criado através deste construtor
     * irá receber os parametros do individuo passado por parametro, sendo que o genoma é também copiado para este
     * novo individuo criado
     * @param newIndividual (Individual) - Individuo que será copiado para este novo individuo criado
     */
    public Individual(Individual newIndividual) {
        //Definições do novo individuo iguais ao que é passado por parametro
        this._sizeGenome = newIndividual.getSizeGenome();
        this._sizeGenotype = newIndividual.getSizeGenotype();
        this._sizeAllelo = newIndividual.getSizeAllelo();
        this._genome = new ArrayList<Chromosome>(this._sizeGenome);
        this._ageIndividual = newIndividual.getAgeIndividual();
        //Faz a cópia de todos os cromossomas do individuo passado por parametro para este novo individuo criado
        for (Chromosome __chromosome : newIndividual.getGenome()) {
            this._genome.add(new Chromosome(__chromosome));
        }
    }

    /**
     * Métodos abstractos que terão que ser implementados em cada tipo especifico de individuo
     */
    public abstract double fitness();
    public abstract Object[] inicializationAllelo(int indexGene);
    @Override
    public abstract Individual clone();

    /**
     * Método que inicializa o genoma do individuo
     */
    public void inicializationGenome() {
        for (int __indexChromosome = 0; __indexChromosome < this.getSizeGenome(); __indexChromosome++) {
            _genome.add(new Chromosome(this));
        }
    }

    /**
     * Método que permite retirar um cromossoma do genoma do individuo
     * @param index (int) - Index do cromossoma pretendido
     * @return (Chromosome) - Cromossoma especifico do genoma do individuo
     */
    public Chromosome getChromosome(int index) {
        return _genome.get(index);
    }

    /**
     * Método que permite definir o cromossoma especifico do genoma do individuo
     * @param index (int) - Index no qual o cromossoma irá ser colocado
     * @param cromosome (Chormosome) - Cromossoma a ser definido ao genoma do individuo
     */
    public void setChromosome(int index, Chromosome cromosome) {
        _genome.add(index, cromosome);
    }

    /**
     * Método que devolve o tamanho do Genotype (Número de genes por cromossoma)
     * @return _sizeGenotype (int) - Tamanho do genotype do individuo
     */
    public int getSizeGenotype() {
        return _sizeGenotype;
    }

    /**
     * Método que permite fazer a definição do número de genes por cromossoma
     * @param sizeGenotype (int) - Número de genes por cromossoma do individuo
     */
    public void setSizeGenotype(int sizeGenotype) {
        this._sizeGenotype = sizeGenotype;
    }

    /**
     * Método que devolve o tamanho do genoma do individuo (número de cromossomas)
     * @return _sizeGenome (int) - Tamanho do genoma do individuo
     */
    public int getSizeGenome() {
        return _sizeGenome;
    }

    /**
     * Método que permite fazer a definição do tamanho do genoma do individuo (número de cromossomas)
     * @param sizeGenome (int) - Tamanho do genoma do individuo a ser definido
     */
    public void setSizeGenome(int sizeGenome) {
        this._sizeGenome = sizeGenome;
    }

    /**
     * Método que devolve o tamanho do allelo do individuo
     * @return _sizeAllelo (int) - Tamanho do allelo do individuo
     */
    public int getSizeAllelo() {
        return _sizeAllelo;
    }

    /**
     * Método que pertmite fazer a definição do tamanho do allelo do individuo
     * @param sizeAllelo (int) - Tamanho do allelo do individuo para ser definido
     */
    public void setSizeAllelo(int sizeAllelo) {
        this._sizeAllelo = sizeAllelo;
    }

    /**
     * Método que devolve o genoma do individuo, ou seja, um arraylist com todos os seus cromossomas
     * @return _genome (ArrayList<Chromosome>) - ArrayList que contem todos os cromossomas do individuo
     */
    public ArrayList<Chromosome> getGenome() {
        return _genome;
    }

    /**
     * Método que devolve a idade actual do individuo
     * @return _ageIndividual (int) - Idade do individuo
     */
    public int getAgeIndividual() {
        return _ageIndividual;
    }

    /**
     * Método que permite definir a idade do individuo
     * @param ageIndividual (int) - Idade para ser definida para o individuo
     */
    public void setAgeIndividual(int ageIndividual) {
        this._ageIndividual = ageIndividual;
    }

    /**
     * Método que permite incrementar a idade do invididuo
     */
    public void incrementAge() {
        this._ageIndividual++;
    }

    /**
     * Método que devolve uma string com as informações dos cromossomas do individuo
     * @return (String) - String com as informações do individuo
     */
    @Override
    public String toString() {
        final StringBuilder __output = new StringBuilder();
        for (Chromosome __chromosome : this) {
            __output.append(__chromosome.toString());
        }
        return __output.toString();
    }

    @Override
    public Iterator<Chromosome> iterator() {
        return this.getGenome().iterator();
    }
    
    //*********************************************************************************
    //*****************************Métodos para Reflection*****************************
    //*********************************************************************************
    /**
     * Método para obter informações sobre o algoritmo
     * @return 
     */
    public abstract String getInfo();
    
    /**
     * Método para introduzir parametros atrvés de uma string
     * @param parameters parametros introduzidos por uma string para o problema.
     * @return True parametros introduzidos correctamente, False erro ao introduzir parametros
     */
    public abstract boolean setParameters(String parameters);      

    //*********************************************************************************
    //*********************************************************************************
    //*********************************************************************************    
}
