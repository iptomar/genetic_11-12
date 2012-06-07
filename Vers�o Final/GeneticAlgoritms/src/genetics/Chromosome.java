package genetics;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
/**
 * Classe que representa um cromossoma. Neste momento existe apenas um cromossoma,
 * mas está preparada para no futuro se fazer a implementação de mais.
 * Recebe como parâmetro a variável typeIndividual, que representa o tipo do individuo(ex:boolean)
 * É composta por um ArrayList de elementos do tipo Gene, com o nome genotype.
 * @author goncalo
 */
public class Chromosome implements Iterable<Gene>, Serializable {
    /**
     * ArrayList de Genes que será o genotype do cromossoma
     */
    private ArrayList<Gene> _genotype;
    /**
     * Tipo de individuo ao qual se refere este cromossoma
     */
    private Individual _individual;

    
    /**
     * Construtor da classe que recebe apenas o tipo de individuo a que este cromossoma vai pertencer e inicializa
     * o cromossoma, criando assim tantos Genes quantos os definidos para o individuo, inicializando os mesmos.
     * @param typeIndividual (Individual) - Tipo de individuo a que este cromossoma pertence
     */
    public Chromosome(Individual typeIndividual) {
        this._individual    = typeIndividual;
        this._genotype      = new ArrayList<Gene>(typeIndividual.getSizeGenotype());
        //Inicializa o cromossoma
        this._inicializationGenotype();
    }
    
    /**
     * Construtor da classe que recebe como parametro um cromossoma já definido, fazendo a cópia do mesmo
     * para eta nova instancia da classe e copiando para a variavel _individual o individuo e os parametros definidos
     * para o mesmo.
     * @param newChromosome (Chormosome) - Cromossoma a ser copiado para esta nova instancia da classe 
     */
    public Chromosome(Chromosome newChromosome) {
        this._genotype = new ArrayList<Gene>(newChromosome.getGenotype().size());
        for (Gene __gene : newChromosome) {
            this._genotype.add(new Gene(__gene));
        }
        this._individual = newChromosome.getIndividual();
    }
    
    /**
     * Método que faz a inicialização deste cromossoma, inicializando tantos genes como os definidos para
     * o individuo ao qual o cromossoma pertence
     */
    private void _inicializationGenotype() {
        for (int __indexGene = 0; __indexGene < this.getIndividual().getSizeGenotype(); __indexGene++) {
             this.getGenotype().add(new Gene(getIndividual().inicializationAllelo(__indexGene)));
        }
    }
    /**
     * Método que devolve o Gene que istá no index do ArrayList de genes deste cromossoma
     * @param index (int) - Index do gene que se pretende
     * @return (Gene) - Gene que está definido no index do ArrayList de genes do cromossoma
     */
    public Gene getGene(int index){
        return getGenotype().get(index);
    }
    
    /**
     * Método que permite adicionar um gene ao cromossoma
     * @param gene (Gene) - Gene a ser adicionado ao cromossoma
     */
    public void setGene(Gene gene){
        getGenotype().add(gene);
    }

    /**
     * Método que devolve o ArrayList de genes (genotype) deste cromossoma
     * @return _genotype(ArrayList<Gene>) - ArrayList que contem todos os genes deste cromossoma
     */
    public ArrayList<Gene> getGenotype() {
        return _genotype;
    }

    /**
     * Método que permite fazer a definição do genotype deste cromossoma
     * @param genotype (ArrayList<Gene>) - ArrayList de genes (genotype) a ser definido para este cromossoma
     */
    public void setGenotype(ArrayList<Gene> genotype) {
        this._genotype = genotype;
    }

    /**
     * Método que devolve o individuo e seus parametros ao qual este cromossoma pertence
     * @return _individual (Individual) - Individuo e seus parametros ao qual o cromossoma pertence
     */
    public Individual getIndividual() {
        return _individual;
    }

    /**
     * Método que permite fazer a definição do individuo e seus parametros ao qual o cromossoma pertence
     * @param individual (Individual) - Individuo e seus parametros ao qual o cromossoma pertence
     */
    public void setIndividual(Individual individual) {
        this._individual = individual;
    }
    
    /**
     * Método que devolve uma string com as informações do cromossoma.
     * Este método irá percorrer todos os genes que contem e devolver uma string com a informação
     * de todos os genes.
     * @return (String) - String com a informação de todos os genes que o cromossoma contem
     */
    @Override
    public String toString() {
        StringBuilder __output = new StringBuilder();
        //Percorre todos os genes deste cromossoma
        for (Gene __gene : this) {
            //Faz o append da informação do gene.toString() e muda de linha, para que os genes fiquem separados
            //por linha
            //__output.append(__gene.toString()).append(System.getProperty("line.separator"));
            __output.append(__gene.toString());
            __output.append(" ");
        }
        //Devolve a string com a informação do cromossoma
        return __output.toString();
    }
    
    @Override
    public Iterator<Gene> iterator() {
        return this.getGenotype().iterator();
    }
}
