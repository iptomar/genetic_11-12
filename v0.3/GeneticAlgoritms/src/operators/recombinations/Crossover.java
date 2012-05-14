package operators.recombinations;

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
public class Crossover extends Recombination {

    /**
     * Variavél que obtem o número de cortes a ser utilizado no crossover
     */
    private int numCuts;
    /**
     * Objecto uniform crossover que será uma ajuda para o crossover
     */
    private UniformCrossover UC;
    /**
     * Variavel que receberá a probabilidade de recombinação
     */
    private double probability;

    /**
     * Construtor da classe em que apenas é feito um corte ao allelo dos individuos a ser recombinados
     * e a probabilidade da recombinação acontecer é de 75%
     */
    public Crossover() {
        this(0.75, 1);
    }

    /**
     * Construtor do operador em que são passados por parametro o número de cortes que o allelo sofrerá, bem como a probabilidade
     * da recombinação acontecer a dois individuos.
     * @param numCuts (int) - Número de cortes no allelo dos individuos
     * @param probability (double) - Valor entre 0 e 1 que será a probabilidade da recombinação acontecer (Ex: 0.40 indica 40% de probabilidade de acontecer a recombinação)
     */
    public Crossover(double probability, int numCuts) {
        this.numCuts = numCuts;
        this.probability = probability;
        UC = new UniformCrossover(probability);
    }

    /**
     * Execução do operador croossover onde será aplicado à população que se pretende com os parametros definidos
     * @param population (Population) - População de pais à qual será aplicado a recombinação crossover
     * @return (Population) sons - População de filhos que já sofreu a recombinação do operador crossover
     */
    @Override
    public Population execute(Population population) {
        //calcula a máscara a ser utilizada pelo uniform-crossover para que o crossover funcione correctamente sem a máscara ser calculada aleatóriamente
        UC.setMask(calculaMask(population.getSizeAllelo()));
        //Corre o uniform-crossover com a máscara calculada, acabando assim por fazer apenas um crossover normal
        return UC.execute(population);
    }

    /**
     * Método que calculará a máscara a ser utilizada tendo em conta o número de cortes que se pretende e também
     * o tamanho do allelo do individuo
     * @param sizeAllelo (int) - Tamanho do allelo do individuo
     * @return (Boolean[]) mask - Máscara a ser utilizada para o corte correcto dos allelos dos individuos a sofrerem a recombinação
     */
    private Boolean[] calculaMask(int sizeAllelo) {
        //Variavel que detem em quantas divisões será dividido o allelo do individuo
        int divAllelo = numCuts + 1;
        //Variavel que receberá a máscara calculada
        Boolean[] mask = new Boolean[sizeAllelo];
        //Variavel que conta o número de casas que deterá cada divisão do allelo
        int parse = sizeAllelo / divAllelo;
        //Boolean para saber se true ou false para a máscara; Irá ser comutado ao longo dos ciclo for's que percorrem o preenchimento da máscara
        boolean maskApp = false;
        for (int i = 0; i < sizeAllelo; i += parse) {
            for (int j = 0; j < parse; j++) {
                //Certeza que não ultrapassa o tamanho da máscara
                if (i + j < mask.length) {
                    mask[i + j] = maskApp;
                }
            }
            //Comutação do boolean a ser posto na máscara
            maskApp = !maskApp;
        }
        //Devolve a máscara já calculada
        return mask;
    }

    //*********************************************************************************
    //*****************************Métodos para Reflection*****************************
    //*********************************************************************************    
    @Override
    public String getInfo() {
        String s = "<p>Método de recombinação de individuos que tem como parâmetros de entrada o número de</p>"
                + "<p>cortes que o allelo sofre e a probabilidade da recombinação que dois individuos têm.</p>"
                + "<p>O número de cortes tem que ser um valor inteiro positivo.</p>"
                + "<p>A probabilidade é passado como valor real ou seja, um valor entre 0 e 1 em que, por exemplo,</p>"
                + "<p>o parametro 0.40 representa 40% de probabilidade de recombinação dos individuos.</p>";
        return s;
    }

    @Override
    public boolean setParameters(String parameters) {
        try {
            int nCortes = Integer.parseInt(parameters.split(" ")[0]);
            int probabilidade = Integer.parseInt(parameters.split(" ")[1]);
            this.numCuts = nCortes;
            this.probability = probabilidade;
            return true;
        } catch (Exception ex) {
            //Caso algo corra mal, devolve false
            return false;
        }
    }
    //*********************************************************************************
    //*********************************************************************************
    //*********************************************************************************
    //Pequeno teste ao operador
//    public static void main(String[] args) {
//
//        Crossover crossover = new Crossover(2,0.75);
//        Boolean[] __mask = crossover.calculaMask(11);
//
//        System.out.println("");
//    }
}
