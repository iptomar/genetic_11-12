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
 * Classe do tipo KnapSack que será o individuo que receberá os parametros necessários para a resolução do problema da mochila.
 * A mochila terá que ficar definida no individuo para que seja possivel o cálculo do fitness do mesmo.
 * @author Ruben Felix <Ruben.Felix@gmail.com>
 */
public class KnapSack extends Individual {

    public      enum            ModeFunction { PENALTY, RANDOM, PSEUDO_RANDOM }  
    
    protected   final int       VALUE   = 0;
    protected   final int       WEIGHT  = 1;

    public      final static int PENALTY_ORDER_LINEAR    = 1;
    public      final static int PENALTY_ORDER_QUADRATIC = 2;
    public      final static int PENALTY_ORDER_CUBIC     = 3;
    
    protected   int             _maxWeight;
    protected   ModeFunction    _modeFunction;
    protected   boolean         _isPenalized;
    private     int[][]         _table;
    private     int             _penaltyOrder;

    public KnapSack() { }
    
    public KnapSack(KnapSack newKnapSack){
        this((Individual)newKnapSack);
    }
    
    public KnapSack(Individual newIndividual){
        super(newIndividual);
        
        this._maxWeight     = ((KnapSack)newIndividual).getMaxWeight();
        this._table         = ((KnapSack)newIndividual).getTable();
        this._modeFunction  = ((KnapSack)newIndividual).getModeFunction();
        this._penaltyOrder  = ((KnapSack)newIndividual).getPenaltyOrder();
        this._isPenalized   = false;
    }
    
    public KnapSack(int maxWeight, int[][] table, ModeFunction modeFunction, int penaltyOrder){
        this._maxWeight     = maxWeight;
        this._table         = table;
        this._modeFunction  = modeFunction;
        this._penaltyOrder  = penaltyOrder;
        this._isPenalized   = false;
    }
    
    public KnapSack(String data, ModeFunction modeFunction, int penaltyOrder){
        this._modeFunction  = modeFunction;
        this._isPenalized   = false;
        this._penaltyOrder  = penaltyOrder;
        this._parseStringData(data);
    }
    
    @Override
    public int fitness() {
        int __fitness;
        
        if(this._calculateTotalWeight() <= this._maxWeight)
            return this._calculateTotalValue();
        
        if(this._modeFunction == ModeFunction.PENALTY){
            __fitness = this._applyPenalty(this._penaltyOrder);
        } else if(this._modeFunction == ModeFunction.RANDOM){
            __fitness = this._applyRandom();
        } else {
            __fitness = this._applyPesudoRandom();
        }
        
        return __fitness;
    }
    
    private int _applyPesudoRandom() {
        double  __alleloPickRandom;
        int     __overWeight;
        double  __totalValueGene;        
        double  __totalValueGeneInvert;

        // Calcula o peso em excesso
        __overWeight            = this._calculateTotalWeight() - this._maxWeight;
        __totalValueGene        = 0;
        __totalValueGeneInvert  = 0;
        
        while(__overWeight > 0) {
            for (Chromosome __chromosome : this) {
                for (Gene<Boolean[]> __gene : __chromosome) {
                    
                    for (int __indexAllelo = 0; __indexAllelo < __gene.getAllele().length; __indexAllelo++) {
                        if(__gene.getAllele()[__indexAllelo]){
                            __totalValueGene += (double)this._table[__indexAllelo][VALUE] / (double)this._table[__indexAllelo][WEIGHT];
                        }
                    }
                    
                    for (int __indexAllelo = 0; __indexAllelo < __gene.getAllele().length; __indexAllelo++) {
                        if(__gene.getAllele()[__indexAllelo]){
                            __totalValueGeneInvert += __totalValueGene - ((double)this._table[__indexAllelo][VALUE] / (double)this._table[__indexAllelo][WEIGHT]);
                        }
                    }
                    
                    __alleloPickRandom = Population.RANDOM_GENERATOR.nextDouble() * __totalValueGeneInvert;
                    
                    __totalValueGeneInvert = 0;

                    for (int __indexAllelo = 0; __indexAllelo < __gene.getAllele().length; __indexAllelo++) {
                        if(__gene.getAllele()[__indexAllelo]){
                            __totalValueGeneInvert += __totalValueGene - ((double)this._table[__indexAllelo][VALUE] / (double)this._table[__indexAllelo][WEIGHT]);
                            
                            if(__alleloPickRandom <= __totalValueGeneInvert) {
                                __gene.getAllele()[__indexAllelo] = !__gene.getAllele()[__indexAllelo];
                                __overWeight -= this._table[__indexAllelo][WEIGHT];
                                break;
                            }
                        }
                    }                    
                }
            }
        }
        
        return this._calculateTotalValue();
    }
    
    private int _applyRandom() {
        boolean __valueAlleloPickRandom;
        int     __indexAlleloPickRandom;
        int     __overWeight;
        
        // Calcula o peso em excesso
        __overWeight = this._calculateTotalWeight() - this._maxWeight;
        
        while(__overWeight > 0) {
            for (Chromosome __chromosome : this) {
                for (Gene<Boolean[]> __gene : __chromosome) {
                    __indexAlleloPickRandom = Population.RANDOM_GENERATOR.nextInt(__gene.getAllele().length);
                    __valueAlleloPickRandom = __gene.getAllele()[__indexAlleloPickRandom];
                    
                    if(__valueAlleloPickRandom == true) {
                        __gene.getAllele()[__indexAlleloPickRandom] = !__valueAlleloPickRandom;
                        __overWeight -= this._table[__indexAlleloPickRandom][WEIGHT];
                    }
                }
            }
        }
        
        return this._calculateTotalValue();
    }
    
    private int _applyPenalty(int penaltyOrder) {
        int __overWeight;
        int __fitness;
        
        // Calcula o peso em excesso
        __overWeight = this._calculateTotalWeight() - this._maxWeight;
        
        // Se der valor negativo no overWeight então não ultrapassa o peso da mochila
        if(__overWeight < 0) { 
            __overWeight = 0;
            this._isPenalized = false;
        } else {
            this._isPenalized = true;
        }

        __fitness = this._calculateTotalValue() - (int)Math.pow(__overWeight, penaltyOrder);
        
        return __fitness < 0 ? 0 : __fitness;
    }
    
    private int _calculateTotalValue() {
        int __totalValue = 0;
        
        for (Chromosome __chromosome : this) {
            for (Gene<Boolean[]> __gene : __chromosome) {
                for (int __indexAlleloValue = 0; __indexAlleloValue < __gene.getAllele().length; __indexAlleloValue++) {
                    if ( __gene.getAllele()[__indexAlleloValue]) {
                        __totalValue += this._table[__indexAlleloValue][VALUE];
                    }
                }
            }
        }
        
        return __totalValue;
    }
    
    private int _calculateTotalWeight() {
        int __totalWeight = 0;
        
        for (Chromosome __chromosome : this) {
            for (Gene<Boolean[]> __gene : __chromosome) {
                for (int __indexAlleloValue = 0; __indexAlleloValue < __gene.getAllele().length; __indexAlleloValue++) {
                    if ( __gene.getAllele()[__indexAlleloValue]) {
                        __totalWeight += this._table[__indexAlleloValue][WEIGHT];
                    }
                }
            }
        }
        
        return __totalWeight;
    }

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
    
    protected final void _parseStringData(String data){
        final int       SIZE_GENE       = 0;
        final int       MAX_KNAPSACK    = 1;
        final int       DATA_START      = 2;
        final String    CHAR_SEPARATOR  = " ";
        
        final String[]  __dataSplit;
        
        __dataSplit = data.split(CHAR_SEPARATOR);
        
        this._maxWeight = Integer.parseInt(__dataSplit[MAX_KNAPSACK]);
        super.setSizeAllelo(Integer.parseInt(__dataSplit[SIZE_GENE]));
        
        this._table = new int[super.getSizeAllelo()][2];
        
        for (int __indexData = 0; __indexData < super.getSizeAllelo(); __indexData++) {
            this._table[__indexData][VALUE]     = Integer.parseInt(__dataSplit[DATA_START + __indexData]);
            this._table[__indexData][WEIGHT]    = Integer.parseInt(__dataSplit[DATA_START + super.getSizeAllelo() + __indexData]);
        }
    }
}
