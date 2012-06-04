package genetics.algorithms;

import genetics.Chromosome;
import genetics.Gene;
import genetics.Individual;
import genetics.Population;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
 * Classe que representa cada individuo do problema do caixeiro viajante (TSP). Todos os 
 * individuos deste problema possoem uma matriz de custo para que possa ser calculado o
 * custo do caminho representado no seu allelo.
 * @author Ruben Felix <Ruben.Felix@gmail.com>
 */
public class TSP extends Individual {

    /**
     * Matriz que detem os custos de cada caminho
     */
    public double[][] costMatrix;

    /**
     * Conbstrutor da classe onde apenas é instanciado a super classe Individual
     */
    public TSP() {
        super();
    }

    /**
     * COnstrutor da classe onde é passado por parametro a matriz de custo do problema TSP em questão
     * @param matriz - Matriz de custo do problema TSP
     */
    public TSP(double[][] matriz) {
        super();
        this.costMatrix = matriz;
    }

    /**
     * Construtor da classe onde o individuo que é passado por parametro é clonado
     * @param tsp - Individuo a ser clonado
     */
    public TSP(TSP tsp) {
        super(tsp);
        this.costMatrix = tsp.costMatrix;
    }

    /**
     * Método que calcula o fitness da cidade do problema e devolve o mesmo. 
     * @return Fitness do individuo em questão
     */
    @Override
    public double fitness() {
        //Variavel que receberá o valor do fitness
        double fitness = 0;
        //Foreach que percorre todo o genoma do individuo
        for (Chromosome chromosome : this) {
            for (Gene<Integer[]> gene : chromosome) {
                for (int __indexAlleloValue = 1; __indexAlleloValue < gene.getAllele().length; __indexAlleloValue++) {
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
    public Integer[] inicializationAllelo(int indexGene) {
        //Array de inteiro, com o tamanho do allelo do individuo, que será especificado para o allelo do individuo
        Integer[] newIndividual = new Integer[this.getSizeAllelo()];
 
        // Comeca sempre em 0
        newIndividual[0] = 0;
        
        //Preenche o allelo com os melhores valores
        for (int __indexAllelo = 1; __indexAllelo < newIndividual.length; __indexAllelo++) {
            newIndividual[__indexAllelo] = searchRandomBestPath(
                    __indexAllelo - 1,
                    __indexAllelo,
                    newIndividual,
                    costMatrix);
        }
        
        //Devolve o array de inteiros que será o allelo do individuo
        return newIndividual;
    }
    
    protected static int searchRandomBestPath(int indexPreviewsCity, int indexNextCity, Integer[] newIndividual, double[][] costMatrix) {
        int __cityIndex = 0;
        
        ArrayList<Object[]> __listOfItensIndexAndRatio = new ArrayList<Object[]>();
        
        for (int __indexCity = 0; __indexCity < costMatrix.length; __indexCity++) {
            boolean __exist = false;
            
            // se for a mesma cidade salta
            if(__indexCity == indexPreviewsCity) continue;
            
            // verifica se essa cidade ja esta na percurso
            for (int __indexCityDuplicate = 0; __indexCityDuplicate < indexNextCity; __indexCityDuplicate++) {
                if(newIndividual[__indexCityDuplicate] == __indexCity) {
                    __exist = true;
                    break;
                }
            }
            
            // se sim então salta essa cidade
            if(__exist) continue;
            
            __listOfItensIndexAndRatio.add(new Object[] { __indexCity, costMatrix[indexPreviewsCity][__indexCity] });
        }

        // Ordena do maior para o mais pequeno a lista, para os melhores ratios ficarem
        // em primeiro
        Collections.sort(__listOfItensIndexAndRatio, new Comparator<Object[]>() {
            @Override
            public int compare(Object[] o1, Object[] o2) {
                if((Double)o1[1] > (Double)o2[1]) return 1;
                if((Double)o1[1] < (Double)o2[1]) return -1;
                return 0;
            }
        });
        
        // por defeito selecciona a cidade com caminho mais curto
        __cityIndex = (Integer)__listOfItensIndexAndRatio.get(0)[0];
        
        // Corre a lista dos itens com ratio
        for (Object[] __item : __listOfItensIndexAndRatio) {
                // calcula uma probabilidade de 95% de esse item ir para o saco
                if(Population.RANDOM_GENERATOR.nextDouble() <= 0.95){
                    __cityIndex = (Integer)__item[0];
                    break;
                }
        }
        
        return __cityIndex;
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
     * Método que permite fazer o clone do individuo TSP
     * @return Individuo TSP já clonado
     */
    @Override
    public Individual clone() {
        return new TSP(this);
    }

    /**
     * Método que devolve uma string com a informação das cidades que o individuo percorre, ordenadamente, bem
     * como o seu fitness.
     * @return String com a informação do individuo TSP
     */
    @Override
    public String toString() {
        /**
         * *********************************************************
         * *** APENAS PARA O PRIMEIRO CROMOSSOMA E PRIMEIRO GENE ***
         * *********************************************************
         */
        StringBuilder __result = new StringBuilder();

        for (int i = 0; i < this.getSizeAllelo(); i++) {
            __result.append(((Integer[])this.getChromosome(0).getGene(0).getAllele())[i]);
            __result.append(" ");
        }

        __result.append(" 0 ");
        return __result.toString();
    }

    //*********************************************************************************
    //*****************************Métodos para Reflection*****************************
    //*********************************************************************************
    @Override
    public String getInfo() {
        return "<p>TSP</p>"
                + "<p>Solver para o TSP: setParameters(«num Individuos» «num Cromossomas» «num Genes Cromossoma» «tamanho array allelo (OBRIGATORIO SER O NÚMERO DE CIDADES DO PROBLEMA)» TSP)</p>"
                + "<p>Ex: setParameters(100 1 1 50 TSP) - É criado um solver que terá uma </p><p>população inicial de 100 individuos, cada um com um cromossoma, cada cromossoma</p><p>com um gene e cada gene contem um array de tamanho 50, já que este</p><p>problema teria 50 cidades definidas. Os individuos são do tipo</p><p>TSP.</p>"
                + "<p>A string com as definições do problema terá que ser passada através do SetTSPProbl</p>";
    }

    @Override
    public boolean setParameters(String parameters) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    //*********************************************************************************
    //*********************************************************************************
    //*********************************************************************************
}
