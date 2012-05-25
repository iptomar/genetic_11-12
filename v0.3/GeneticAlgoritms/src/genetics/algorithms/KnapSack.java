package genetics.algorithms;

import genetics.Chromosome;
import genetics.Gene;
import genetics.Individual;
import genetics.Population;

/* -------------------------------------------------------------------------
 * -------------------------------------------------------------------------
 * I n s t i t u t o P o l i t e c n i c o d e T o m a r
 * E s c o l a S u p e r i o r d e T e c n o l o g i a
 *
 * @author Ruben Felix <Ruben.Felix@gmail.com>
 * -------------------------------------------------------------------------
 * Número de Aluno: 13691
 * E-mail: Ruben.Felix@gmail.com
 * -------------------------------------------------------------------------
 * -------------------------------------------------------------------------
 */
/**
 * Classe do tipo KnapSack que será o individuo que receberá os parametros necessários para a resolução do problema da mochila.
 * A mochila terá que ficar definida no individuo para que seja possivel o cálculo do fitness do mesmo.
 * @author Ruben Felix <Ruben.Felix@gmail.com>
 */
public class KnapSack extends Individual {

    public enum ModeFunction {

        PENALTY, RANDOM, PSEUDO_RANDOM
    }
    protected final int VALUE = 0;
    protected final int WEIGHT = 1;
    public final static int PENALTY_ORDER_LINEAR = 1;
    public final static int PENALTY_ORDER_QUADRATIC = 2;
    public final static int PENALTY_ORDER_CUBIC = 3;
    protected int _maxWeight;
    protected ModeFunction _modeFunction;
    protected boolean _isPenalized;
    private int[][] _table;
    private int _penaltyOrder;

    /**
     * Construtor por defeito
     */
    public KnapSack() {
        // Invoca o construtor do Individual
        super();
    }

    /**
     * Construtor do KnapSack que cria uma copia a partir de um individuo do tipo KnapSack
     * @param newKnapSack
     */
    public KnapSack(KnapSack newKnapSack) {
        this((Individual) newKnapSack);
    }

    /**
     * Construtor do KnapSack que cria uma copia a partir de um individuo do tipo Individual
     * @param newIndividual
     */
    public KnapSack(Individual newIndividual) {
        super(newIndividual);

        this._maxWeight = ((KnapSack) newIndividual).getMaxWeight();
        this._table = ((KnapSack) newIndividual).getTable();
        this._modeFunction = ((KnapSack) newIndividual).getModeFunction();
        this._penaltyOrder = ((KnapSack) newIndividual).getPenaltyOrder();
        this._isPenalized = false;
    }

    /**
     * Construtor do KnapSack
     * @param maxWeight Peso maximo do saco
     * @param table Matriz com os valores e pesos
     * @param modeFunction Tipo de penalização aplicar quando existe excesso de peso
     * @param penaltyOrder Qual a ordem de penalização aplicar
     */
    public KnapSack(int maxWeight, int[][] table, ModeFunction modeFunction, int penaltyOrder) {
        // Invoca o construtor do Individual
        super();
        this._maxWeight = maxWeight;
        this._table = table;
        this._modeFunction = modeFunction;
        this._penaltyOrder = penaltyOrder;
        this._isPenalized = false;
    }

    /**
     * Construtor do KnapSack
     * @param data String de dados. A ordem da string deve ser { TamanhoAllelo MaximoPeso Valor1 Valor2 Peso1 Peso2 }
     * @param modeFunction Tipo de penalização aplicar quando existe excesso de peso
     * @param penaltyOrder Qual a ordem de penalização aplicar
     */
    public KnapSack(String data, ModeFunction modeFunction, int penaltyOrder) {
        // Invoca o construtor do Individual
        super();
        this._modeFunction = modeFunction;
        this._isPenalized = false;
        this._penaltyOrder = penaltyOrder;
        System.out.println("Data: " + data);
        this._parseStringData(data);
    }

    /**
     * Calcula o fitness
     * @return Devolve o fitness
     */
    @Override
    public double fitness() {
        int __fitness;

        // Se o peso estiver dentro dos limites então devolve o fitness logo
        if (this._calculateIndividualWeight() <= this._maxWeight) {
            return this._calculateTotalValue();
        }

        // Senão aplica os criterios de penalização
        if (this._modeFunction == ModeFunction.PENALTY) {
            __fitness = this._applyPenalty(this._penaltyOrder);
        } else if (this._modeFunction == ModeFunction.RANDOM) {
            __fitness = this._applyRandom();
        } else {
            __fitness = this._applyPesudoRandom();
        }

        // Devolve o fitness ja com as penalidades aplicadas
        return __fitness;
    }

    private int _applyPesudoRandom() {
        double __alleloPickRandom;
        int __overWeight;
        double __totalValueGene;
        double __totalValueGeneInvert;

        // Calcula o peso em excesso
        __overWeight = this._calculateIndividualWeight() - this._maxWeight;
        __totalValueGene = 0;
        __totalValueGeneInvert = 0;

        // Ciclo que so termina quando não existir overWeight
        while (__overWeight > 0) {
            for (Chromosome __chromosome : this) {
                for (Gene<Boolean[]> __gene : __chromosome) {

                    // Calcula o valor total do gene
                    for (int __indexAllelo = 0; __indexAllelo < __gene.getAllele().length; __indexAllelo++) {
                        if (__gene.getAllele()[__indexAllelo]) {
                            __totalValueGene += (double) this._table[__indexAllelo][VALUE] / (double) this._table[__indexAllelo][WEIGHT];
                        }
                    }

                    // Calcula o valor total do gene invertido, mas desta vez ao valor total anterior
                    // vai subtrair o valor desse gene, e desta forma os objectos com a pior
                    // relação valor/peso são os que tem maior probabilidade de serem escolhidos
                    for (int __indexAllelo = 0; __indexAllelo < __gene.getAllele().length; __indexAllelo++) {
                        if (__gene.getAllele()[__indexAllelo]) {
                            __totalValueGeneInvert += __totalValueGene - ((double) this._table[__indexAllelo][VALUE] / (double) this._table[__indexAllelo][WEIGHT]);
                        }
                    }

                    // Gerar um numero aleatorio entre 0 e o valor total invertido
                    __alleloPickRandom = Population.RANDOM_GENERATOR.nextDouble() * __totalValueGeneInvert;

                    __totalValueGeneInvert = 0;

                    // Corre os allelos todos
                    for (int __indexAllelo = 0; __indexAllelo < __gene.getAllele().length; __indexAllelo++) {
                        if (__gene.getAllele()[__indexAllelo]) {
                            __totalValueGeneInvert += __totalValueGene - ((double) this._table[__indexAllelo][VALUE] / (double) this._table[__indexAllelo][WEIGHT]);

                            // E quando encontrar um que o seu acumulado seja maior ou igual ao numero aleatorio
                            // é esse objecto que é removido
                            if (__alleloPickRandom <= __totalValueGeneInvert) {
                                __gene.getAllele()[__indexAllelo] = !__gene.getAllele()[__indexAllelo];
                                __overWeight -= this._table[__indexAllelo][WEIGHT];
                                break;
                            }
                        }
                    }
                }
            }
        }

        // devolve o novo valor
        return this._calculateTotalValue();
    }

    /**
     * Metodo responsavel por tirar um objecto, de forma aleatoria do saco
     * @return Devolve o valor quando o peso já estiver dentro dos limites aceitaveis
     */
    private int _applyRandom() {
        boolean __valueAlleloPickRandom;
        int __indexAlleloPickRandom;
        int __overWeight;

        // Calcula o peso em excesso
        __overWeight = this._calculateIndividualWeight() - this._maxWeight;

        // Ciclo que so termina quando não existir overWeight
        while (__overWeight > 0) {
            for (Chromosome __chromosome : this) {
                for (Gene<Boolean[]> __gene : __chromosome) {
                    // Gera uma posição aleatoria para escolher um allelo
                    __indexAlleloPickRandom = Population.RANDOM_GENERATOR.nextInt(__gene.getAllele().length);
                    // Recolhe o valor do allelo
                    __valueAlleloPickRandom = __gene.getAllele()[__indexAlleloPickRandom];

                    // verifica se o allelo esta a 1
                    if (__valueAlleloPickRandom == true) {
                        // Se dim troca o valor para 0
                        __gene.getAllele()[__indexAlleloPickRandom] = !__valueAlleloPickRandom;
                        // E retira o peso desse objecto ao overWeight
                        __overWeight -= this._table[__indexAlleloPickRandom][WEIGHT];
                    }
                }
            }
        }

        // devolve o novo valor
        return this._calculateTotalValue();
    }

    /**
     * Metodo responsavel por aplicar a penalidade aos individuos caso ultrapassem
     * o peso maximo
     * @param penaltyOrder Ordem de penalização: Linear, Quadratica...
     * @return Devolve o fitness ja com a penalidade aplicada
     */
    private int _applyPenalty(int penaltyOrder) {
        int __overWeight;
        int __fitness;

        // Calcula o peso em excesso
        __overWeight = this._calculateIndividualWeight() - this._maxWeight;

        // Se der valor negativo no overWeight então não ultrapassa o peso da mochila
        if (__overWeight < 0) {
            __overWeight = 0;
            this._isPenalized = false;
        } else {
            this._isPenalized = true;
        }

        // Aplica a penalidade ao fitness
        __fitness = this._calculateTotalValue() - (int) Math.pow(__overWeight, penaltyOrder);

        // Devolve zero caso a penalidade deixe o fitness a negativo, senão
        // devolve o valor do fitness
        return __fitness < 0 ? 0 : __fitness;
    }

    /**
     * Calcula o valor deste individuo
     * @return
     */
    private int _calculateTotalValue() {
        int __totalValue = 0;

        // Ciclo que soma o valor do individuo. Valor esse que são os bits a 1
        for (Chromosome __chromosome : this) {
            for (Gene<Boolean[]> __gene : __chromosome) {
                for (int __indexAlleloValue = 0; __indexAlleloValue < __gene.getAllele().length; __indexAlleloValue++) {
                    if (__gene.getAllele()[__indexAlleloValue]) {
                        __totalValue += this._table[__indexAlleloValue][VALUE];
                    }
                }
            }
        }

        return __totalValue;
    }

    /**
     * Calcula o peso deste individuo
     * @return Devolve o peso total do individuo
     */
    public int _calculateIndividualWeight() {
        int __totalWeight = 0;

        // Ciclo que soma o peso do individuo. Peso esse que são os bits a 1
        for (Chromosome __chromosome : this) {
            for (Gene<Boolean[]> __gene : __chromosome) {
                for (int __indexAlleloValue = 0; __indexAlleloValue < __gene.getAllele().length; __indexAlleloValue++) {
                    if (__gene.getAllele()[__indexAlleloValue]) {
                        __totalWeight += this._table[__indexAlleloValue][WEIGHT];
                    }
                }
            }
        }
        return __totalWeight;
    }
    
    /**
     * Método que devolve a soma total de todos os pesos dos items da mochila
     * @return Peso de todos os items da mochila
     */
    public int calculateTotalItemsWeight(){
        int weight = 0;
        for (int i = 0; i < _table.length/2; i++) {
            weight += this._table[i][WEIGHT];
        }
        return weight;
    }

    /**
     * Inicialização do Allelo a usar no KnapSack
     * @return Devolve o allelo ja criado
     */
    @Override
    public Boolean[] inicializationAllelo() {
        Boolean[] __allelo = new Boolean[super.getSizeAllelo()];

        // gerar de forma aleatoria os valores em binario para o allelo
        for (int __indexAllelo = 0; __indexAllelo < super.getSizeAllelo(); __indexAllelo++) {
            __allelo[__indexAllelo] = Population.RANDOM_GENERATOR.nextBoolean();
        }

        return __allelo;
    }

    @Override
    public Individual clone() {
        return new KnapSack(this);
    }

    /**
     * @return the _maxWeight
     */
    public int getMaxWeight() {
        return _maxWeight;
    }

    /**
     * @param maxWeight the _maxWeight to set
     */
    public void setMaxWeight(int maxWeight) {
        this._maxWeight = maxWeight;
    }

    /**
     * @return the _modeFunction
     */
    public ModeFunction getModeFunction() {
        return _modeFunction;
    }

    /**
     * @param modeFunction the _modeFunction to set
     */
    public void setModeFunction(ModeFunction modeFunction) {
        this._modeFunction = modeFunction;
    }

    /**
     * @return the _table
     */
    public int[][] getTable() {
        return _table;
    }

    /**
     * @param table the _table to set
     */
    public void setTable(int[][] table) {
        this._table = table;
    }

    /**
     * @return the _penaltyOrder
     */
    public int getPenaltyOrder() {
        return _penaltyOrder;
    }

    /**
     * @param penaltyOrder the _penaltyOrder to set
     */
    public void setPenaltyOrder(int penaltyOrder) {
        this._penaltyOrder = penaltyOrder;
    }

    /**
     * Pega numa string de dados e transforma na matriz com valor e peso
     * @param data String de dados. A ordem da string deve ser { TamanhoAllelo MaximoPeso Valor1 Valor2 Peso1 Peso2 }
     */
    protected final void _parseStringData(String data) {
        // Constantes com as posições em que se encontram os dados na string de dados
        final int SIZE_GENE = 0;
        final int MAX_KNAPSACK = 1;
        final int DATA_START = 2;
        // Constante com o caracter de separação
        final String CHAR_SEPARATOR = " ";

        // Variavel que vai conter a string ja partida
        final String[] __dataSplit;

        // Devide a string de dados com base no caracter escolhido para separar
        __dataSplit = data.split(CHAR_SEPARATOR);

        // Tira da string o peso maximo
        this._maxWeight = Integer.parseInt(__dataSplit[MAX_KNAPSACK]);
        // Tira da string o tamanho do allelo
        super.setSizeAllelo(Integer.parseInt(__dataSplit[SIZE_GENE]));

        // Cria a matriz com os valores
        this._table = new int[super.getSizeAllelo()][2];

        // Corre os dados que foram passados como parametro
        for (int __indexData = 0; __indexData < super.getSizeAllelo(); __indexData++) {
            // Copia os dados relacionados com os valores
            this._table[__indexData][VALUE] = Integer.parseInt(__dataSplit[DATA_START + __indexData]);
            // Copia os dados relacionados com os pesos
            this._table[__indexData][WEIGHT] = Integer.parseInt(__dataSplit[DATA_START + super.getSizeAllelo() + __indexData]);
        }
    }
    
    @Override
    public String getInfo() {
        String descricao = "<p>Em caso do individuo ser do tipo KnapSack genérico, será preciso passar mais</p><p>três parametros que serão o peso da mochila, o modo de funcionamento do problema</p><p>e os dados com os pesos e valores da mochila.</p><p></p>";
        descricao += "<p>Em caso do individuo ser do tipo KnapSack genérico, será preciso passar mais</p><p>três parametros que serão o peso da mochila, o modo de funcionamento do problema</p><p>e os dados com os pesos e valores da mochila.</p><p></p>";
        descricao += "<p>Exemplo do solver para o KnapSack: setParameters(<num Individuos> <num Cromossomas> <num Genes Cromossoma> <tamanho do array no allelo (OBRIGATORIO SER IGUAL AO NUM DE ITEMS DA MOCHILA)> KnapSack <modo de funcionamento> <ordem de penalidade>$$<String com os valores> <String com os pesos>)</p>";
        descricao += "<p>Ex: setParameters(1000 1 1 3 KnapSack ModeFunction.RANDOM 2$$3 2 3 10 3 2) - Neste</p>";
        descricao += "<p>caso, a população será igual à de cima, excepto o allelo do individuo que tem um </p>";
        descricao += "<p>array de tamanho 3. O ModeFunction refere-se à forma como o problema será abordado </p>";
        descricao += "<p>em termos de penalidade e o 2 refere-se a uma penalidade de orndem 2. A partir dos $$,</p>";
        descricao += "<p>será a string de dados que contem o valor1, valor2, valor3 e peso1, peso2 e peso3</p>";
        descricao += "<p>respectivamente aos items da mochila do problema.</p><p></p>";
        
        return descricao;
    }

    @Override
    public boolean setParameters(String parameters) {
        return false;
    }
}