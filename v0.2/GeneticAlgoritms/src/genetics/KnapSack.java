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
    public KnapSack() {
    }

    /**
     * Construtor que recebe um indiviou do tipo KnapSack e instancia o mesmo
     * @param knap - Individuo do tipo KnapSack 
     */
    public KnapSack(KnapSack knap) {
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
                        fitness += mocha.getItems().get(__indexAlleloValue).getValor();
                        peso += mocha.getItems().get(__indexAlleloValue).getPeso();
                    }
                }
            }
        }
        //Verifica se a penalização está activa para este determinado problema da mochila
        if (mocha.isPenalizacao()) {
            //Verifica se o individuo é penalizado ou não
            if (peso > mocha.getPesoMaximo()) {
                fitness -= (peso - mocha.getPesoMaximo());
                return fitness;
            }
        } //Caso a penalização não esteja activa mas o individuo exceda o peso máximo da mochila, terá então que ser reparado até
        //o seu peso não ultrapasse o peso da mochila
        else if (peso > mocha.getPesoMaximo()) {
            return reparaIndividuo(fitness, peso);
        }
        //Caso não haja penalizações e o individuo esteja dentro do peso máximo permitido da mochila, devolve o seu fitness
        //calculado sem alterações
        return fitness;
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

    /**
     * Método que fará a reparação do individuo. Mudará, aleatóriamente, os '1' do individuo
     * para '0' até que o mesmo não ultrapasse o peso máximo da mochila
     * @param fitness - Fitness actual do individuo, sem a reparação
     * @return - Fitness do individuo depois da sua reparação
     */
    private int reparaIndividuo(int fitness, int peso) {
        //Variavel que tem o fitness do individuo durante a sua reparação
        int newFitness = fitness;
        //Variavel que tem o peso do individuo durante o processo de reparação
        int newPeso = peso;
        //Allelo do individuo em causa
        boolean[] allelo = ((boolean[]) this.getChromosome(0).getGene(peso).getAllele());
        //Enquanto o peso do individuo ultrapassar o peso máximo da mochila
        int index;
        //Enquanto o peso do individuo ultrapassar o peso máximo da mochila, irá sofrer a reparação no seu allelo
        while (peso > mocha.getPesoMaximo()) {
            //Encontra um index a true para ser mudado aleatóriamente
            do {
                index = Population.RANDOM_GENERATOR.nextInt(allelo.length);
            } while (!allelo[index]);
            allelo[index] = false;
            //Define o novo allelo já modifica para o individuo
            this.getChromosome(0).getGene(0).setAllele(allelo);
            //recalcula o peso e o fitness do individuo
            for (Chromosome __chromosome : this) {
                for (Gene<Boolean[]> __gene : __chromosome) {
                    for (int __indexAlleloValue = 0; __indexAlleloValue < __gene.getAllele().length; __indexAlleloValue++) {
                        if (__gene.getAllele()[__indexAlleloValue]) {
                            newFitness += mocha.getItems().get(__indexAlleloValue).getValor();
                            newPeso += mocha.getItems().get(__indexAlleloValue).getPeso();
                        }
                    }
                }
            }
        }
        return fitness;
    }
}
