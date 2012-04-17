package genetics;

import utils.Mochila;

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
    /**
     * Variável que guardará a mochila definida para que o fitness possa ser devidamente calculado para cada individuo
     */
    private Mochila mocha;
    
    /**
     * Construtor onde não se passa nenhuma variavel por parametro para que a classe
     * possa ser instanciada.
     */
    public KnapSack(){
    
    }
    /**
     * Construtor que recebe um indiviou do tipo KnapSack e instancia o mesmo
     * @param knap - Individuo do tipo KnapSack 
     */
    public KnapSack(KnapSack knap){
        super(knap);
    }
    
    @Override
    public int fiteness() {
        /**
         * **********************************************************
         * ******* SÓ FUNCIONA PARA UM CROMOSSOMA COM UM GENE *******
         * **********************************************************
         */
        
        //Variaveis que recebe o fitness total do individuo (valor dos seus items) e o peso total dos mesmos
        int fitness = 0;
        int peso = 0;
        
        for (Chromosome __chromosome : this) {
            for (Gene<Boolean[]> __gene : __chromosome) {
                for (int __indexAlleloValue = 0; __indexAlleloValue < __gene.getAllele().length; __indexAlleloValue++) {
                    if (__gene.getAllele()[__indexAlleloValue]) {
                        fitness+= mocha.getItems().get(__indexAlleloValue).getValor();
                        peso+= mocha.getItems().get(__indexAlleloValue).getPeso();
                    }
                }
            }
        }
        //Verifica se o individuo é penalizado ou não
        if(peso>mocha.getPesoMaximo()){
            fitness -= (peso - mocha.getPesoMaximo());
            return fitness;
        }
        else return fitness;
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
       KnapSack newKnap = new KnapSack(this);
       newKnap.setMocha(mocha);
       return newKnap;
    }

    /**
     * Método que devolve a mochila definida
     * @return the mocha - Mochila definida
     */
    public Mochila getMocha() {
        return mocha;
    }

    /**
     * Método que permite fazer a definição da mochila que pode ser utilizada
     * @param mocha  - Mochila a ser definida
     */
    public void setMocha(Mochila mocha) {
        this.mocha = mocha;
    }
    
}
