package genetics.algorithms;

import genetics.Chromosome;
import genetics.Gene;
import genetics.Individual;
import genetics.Population;

/* -------------------------------------------------------------------------
 * -------------------------------------------------------------------------
 *  I n s t i t u t o   P o l i t e c n i c o   d e   T o m a r
 *   E s c o l a   S u p e r i o r   d e   T e c n o l o g i a
 *
 * @author Ruben Felix <Ruben.Felix@gmail.com>
 * -------------------------------------------------------------------------
 * Número de Aluno: 13691 
 * E-mail: Ruben.Felix@gmail.com
 * -------------------------------------------------------------------------
 * -------------------------------------------------------------------------
 */
/**
 * Classe City que recebe as cidades do problema do caixeiro viajante
 * @author Ruben Felix <Ruben.Felix@gmail.com>
 */
public class City extends Individual {

    /**
     * Index da cidade - Indica a ordem da mesma no problema
     */
    public int Index = 0;
    /**
     * coordenadas X e Y que serão usadas para gerar a distância entre as cidadaes
     */
    public double X = 0;
    public double Y = 0;
    /**
     * Matriz que detem os custos de cada caminho
     */
    public double[][] costMatrix;

    /**
     * Construtor onde apenas é inicializado a super classe (Individual) da classe City
     */
    public City() {
        super();
    }

    public City(City city) {
        this(city.Index, city.X, city.Y, city.costMatrix);
    }

    /**
     * Construtor da classe onde são passados por parametro o Index da cidade, a posição X e Y e a matriz de custo
     * do problema TSP ao qual a cidade pertence.
     * @param Index - Index da cidade
     * @param x - Posição X da cidade
     * @param y - Posição Y da cidade
     * @param matriz - Matriz de custo do problema TSP
     */
    public City(int Index, double x, double y, double[][] matriz) {
        this.costMatrix = matriz;
        this.X = x;
        this.Y = y;
        this.Index = Index;
    }

    /**
     * Método que calcula o fitness da cidade do problema e devolve o mesmo. 
     * @return  Fitness da cidade em questão
     */
    @Override
    public double fitness() {
        //Variavel que receberá o valor do fitness
        double fitness = 0;
        //Foreach que percorre todo o genoma do individuo
        for (Chromosome chromosome : this) {
            for (Gene<Integer[]> gene : chromosome) {
                for (int __indexAlleloValue = 0; __indexAlleloValue < gene.getAllele().length; __indexAlleloValue++) {
                    fitness += costMatrix[gene.getAllele()[__indexAlleloValue - 1]][gene.getAllele()[__indexAlleloValue % gene.getAllele().length]];
                }
            }
        }
        //Devolve o valor do fitness calculado
        return fitness;
    }

    /**
     * Método que faz a inicialização do allelo do individuo, devolvendo o mesmo depois de devidamente inicializado
     * @return Allelo do individuo já inicializado
     */
    @Override
    public Integer[] inicializationAllelo() {
        //Array de inteiro, com o tamanho do allelo do individuo, que será especificado para o allelo do individuo
        Integer[] newIndividual = new Integer[this.getSizeAllelo()];
        //Preenche o allelo com valores entre 0 e o seu tamanho (Todas as cidades disponiveis no problema)
        for (int i = 0; i < newIndividual.length; i++) {
            newIndividual[i] = i;
        }
        int max = newIndividual.length - 1;
        //Método que irá "misturar" a ordem pelas quais as cidades aparecem no array de inteiros
        while (max > 0) {
            int index = Population.RANDOM_GENERATOR.nextInt(max);
            int aux = newIndividual[index];
            newIndividual[index] = newIndividual[max];
            newIndividual[max] = aux;
            max--;
        }
        //Devolve o array de inteiros que será o allelo do individuo
        return newIndividual;
    }

    /**
     * Método que permite fazer a reparação do allelo do individuo. Sempre que este método é chamado,
     * o individuo irá ficar sempre com os seus allelos a começarem na cidade que contem o index 0 no problema.
     */
    public void individualNormalization() {
        /**
         * *********************************************************************************************************
         * ** O PROBLEMA TERÁ SEMPRE QUE CONTER UMA CIDADE COM O INDEX 0, CASO CONTRÁRIO, O METODO NÃO FUNCIONARÁ **
         * *********************************************************************************************************
         */
        //Array de inteiros que irá receber o allelo do individuo já reparado
        Integer[] alleloRepaired = new Integer[this.getSizeAllelo()];
        //Variavel que irá receber a posição onde se encontra a cidade com o index 0
        int zeroIndex = 0;
        //Foreach que percorre todo o genoma do individuo
        for (Chromosome chromosome : this) {
            for (Gene<Integer[]> gene : chromosome) {
                //Reinicializa a variável
                alleloRepaired = new Integer[this.getSizeAllelo()];
                //Percorre o array de inteiros do gene e procura pelo index que contem a cidade 0
                for (int indexAlleloValue = 0; indexAlleloValue < gene.getAllele().length; indexAlleloValue++) {
                    if (gene.getAllele()[indexAlleloValue] == 0) {
                        zeroIndex = indexAlleloValue;
                        break;
                    }
                }
                //Fará entrão a reparação ao allelo do individuo
                for (int i = 0; i < gene.getAllele().length; i++) {
                    // rotating vector from the zero position
                    // example: [2,4,0,3] -> [2,4|0,3] -> [0,3,2,4]
                    alleloRepaired[i] = gene.getAllele()[(i + zeroIndex) % gene.getAllele().length];
                }
                //Define o gene do individuo com o allelo do mesmo já reparado, ou seja, com a primeira cidade a ser a cidade com o index 0 do problema 
                gene.setAllele(alleloRepaired);
            }
        }
    }

    /**
     * Método que faz o clone do individuo
     * @return Novo individuo, já depois de ser devidamente "clonado"
     */
    @Override
    public Individual clone() {
        return new City(this);
    }
    
    @Override
    public String getInfo() {
        return "Não implementado pela Genetic";
    }

    @Override
    public boolean setParameters(String parameters) {
        return false;
    }
}
