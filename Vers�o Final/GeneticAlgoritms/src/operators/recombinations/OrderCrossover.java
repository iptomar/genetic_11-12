package operators.recombinations;

import genetics.Chromosome;
import genetics.Gene;
import genetics.Individual;
import genetics.Population;
import genetics.algorithms.TSP;
import operators.Genetic;

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
 * Classe responsável por aplicar o OrderCrossover na população pai que recebe. Esta classe poderá ser instanciada com uma probabilidade
 * de acontecimento da recombinação por defeito (75%) ou o utilizador, aquando da sua construção, poderá definir a probabilidade de recombinação
 * pretendida para o operador. O ponte de corte nos cromossomas dos individuos é calculado de forma aleatória. O operador recebe uma população de pais
 * e devolve uma população de filhos, já com a recombinação feita, com o mesmo tamanho da população de pais recebida.
 * @author Ruben Felix <Ruben.Felix@gmail.com>
 */
public class OrderCrossover extends Recombination {

    //Variavel que recebel a probabilidade de haver a recombinação ou nao entre dois filhos
    private double probability;
    //Variavel que obterá a população filho
    private Population sons;

    /**
     * Construtor da classe em que a probabilidade dos filhos sofrerem uma recombinação é de 75%
     */
    public OrderCrossover() {
        this(0.75);
    }

    /**
     * Construtor da classe onde é passado por parametro a probabilidade de dois filhos sofrerem a recombinação
     * @param probability (double) - Probabilidade, entre 0 e 1, de dois filhos sofrerem a recombinação
     */
    public OrderCrossover(double probability) {
        this.probability = probability;
    }

    @Override
    public Population execute(Population parents) {

//
//        for (int i = 0; i < parents.getPopulation().size(); i++) {
//            Integer[] allelo = (Integer[]) ((TSP) parents.getPopulation().get(i)).getChromosome(0).getGene(0).getAllele();
//            for (int j = 0; j < allelo.length; j++) {
//                for (int k = 0; k < allelo.length; k++) {
//                    if (allelo[j] == allelo[k] && j != k) {
//                        System.out.println("ERROR: REPEATED CITYS AT OrderCrossOver (BEGIN)");
//                    }
//                }
//            }
//        }



        //Nova população que irá receber os filhos depois de ser aplicado o uniform-crossover na população pai
        sons = new Population(parents.getSizePopulation(), parents.getSizeGenome(), parents.getSizeGenotype(), parents.getSizeAllelo(), parents.getTypePopulation(), false);
        //Variavel que detem o ponto aleatorio no qual os genes serão cortados
        int cutPlace;
        //Verifica se os allelos dos individuos são ou não array de objectos para saber como tratar da recombinação dos individuos
        if (parents.getIndividual(0).getChromosome(0).getGene(0).getAllele() instanceof Object[]) {
            for (int i = 0; i < parents.getSizePopulation(); i += 2) {
                //Caso o allelo seja um array de objectos, calcula o ponto de corte ao nivel desse array
                cutPlace = Population.RANDOM_GENERATOR.nextInt((parents.getSizeAllelo() - 2) + 1);
                //Verifica se existem ainda dois pais para a recombinação
                try {
                    Individual father = parents.getIndividual(i);
                    Individual mother = parents.getIndividual(i + 1);
                    //Guarda os filhos que serão a cópia dos seus respectivos pais
                    Individual son = father.clone();
                    Individual daughter = mother.clone();
                    //Calcula um random a ver se cai na probabilidade para fazer a recombinação
                    if (Genetic.RANDOM_GENERATOR.nextDouble() < this.probability) {
                        //Para todos os cromossomas dos individuos
                        for (int j = 0; j < father.getSizeGenome(); j++) {
                            //cromossomas dos individuos
                            Chromosome cDaug = daughter.getChromosome(j);
                            Chromosome cSon = son.getChromosome(j);
                            Chromosome cFather = father.getChromosome(j);
                            Chromosome cMother = mother.getChromosome(j);
                            //para todos os genes dos cromossomas dos individuos 
                            for (int q = 0; q < cSon.getGenotype().size(); q++) {
                                //genes dos cromossomas dos individuos
                                Gene gSon = cSon.getGene(q);
                                Gene gDaug = cDaug.getGene(q);
                                Gene gFather = cFather.getGene(q);
                                Gene gMother = cMother.getGene(q);
                                //Verifica se os allelos são arrays de inteiros
                                if (gSon.getAllele() instanceof Integer[]) {
                                    //array de inteiros que são os allelos dos individuos
                                    //Integer[] aSon = (Integer[]) gSon.getAllele();
                                    //Integer[] aDaug = (Integer[]) gDaug.getAllele();
                                    Integer[] aFather = (Integer[]) gFather.getAllele();
                                    Integer[] aMother = (Integer[]) gMother.getAllele();
                                    Integer[] aSon = new Integer[aFather.length];
                                    Integer[] aDaug = new Integer[aMother.length];

//                                    System.out.println("");
//                                    System.out.println("CutPlace: " + cutPlace);
//                                    System.out.println("Allelo Father before: ");
//                                    for (int k = 0; k < aFather.length; k++) {
//                                        System.out.print(" " + aFather[k]);
//                                    }
//                                    System.out.println("");
//                                    System.out.println("Allelo Mother before: ");
//                                    for (int k = 0; k < aMother.length; k++) {
//                                        System.out.print(" " + aMother[k]);
//                                    }
//                                    System.out.println("");
//                                    System.out.println("Allelo Son before: ");
//                                    for (int k = 0; k < aSon.length; k++) {
//                                        System.out.print(" " + aSon[k]);
//                                    }
//                                    System.out.println("");
//                                    System.out.println("Allelo Daughter before: ");
//                                    for (int k = 0; k < aDaug.length; k++) {
//                                        System.out.print(" " + aDaug[k]);
//                                    }





                                    //System.out.println("CutPlace: " + cutPlace);
                                    // troca os genes dos pais com os filhos
                                    for (int indexGene = 0; indexGene < cutPlace; indexGene++) {
                                        //System.out.println("indexCopy: " + indexGene);
                                        aSon[indexGene] = aFather[indexGene];
                                        aDaug[indexGene] = aMother[indexGene];
                                    }
//                                     System.out.println("");
//                                    System.out.println("Allelo Son: ");
//                                    for (int k = 0; k < aSon.length; k++) {
//                                        System.out.print(" " + aFather[k]);
//                                    }
//                                      System.out.println("");
//                                    System.out.println("Allelo Daug: ");
//                                    for (int k = 0; k < aDaug.length; k++) {
//                                        System.out.print(" " + aFather[k]);
//                                    }
                                    
                                    
                                    // depois preenche o resto com genes do outro progenitor, diferente do de cima,
                                    // com os restantes genes que faltam
                                    for (int __indexGene = cutPlace; __indexGene < aSon.length; __indexGene++) {
                                        aSon[__indexGene] = searchForNextGene(__indexGene, aMother, aSon);
                                        aDaug[__indexGene] = searchForNextGene(__indexGene, aFather, aDaug);
                                    }



//                                    System.out.println("");
//                                    System.out.println("Allelo Father after: ");
//                                    for (int k = 0; k < aFather.length; k++) {
//                                        System.out.print(" " + aFather[k]);
//                                    }
//                                    System.out.println("");
//                                    System.out.println("Allelo Mother after: ");
//                                    for (int k = 0; k < aMother.length; k++) {
//                                        System.out.print(" " + aMother[k]);
//                                    }
//                                    System.out.println("");
//                                    System.out.println("Allelo Son after: ");
//                                    for (int k = 0; k < aSon.length; k++) {
//                                        System.out.print(" " + aSon[k]);
//                                    }
//                                    System.out.println("");
//                                    System.out.println("Allelo Daughter after: ");
//                                    for (int k = 0; k < aDaug.length; k++) {
//                                        System.out.print(" " + aDaug[k]);
//                                    }
                                }
                            }
                        }
                    }
                    sons.addIndividual(son);
                    sons.addIndividual(daughter);
                } catch (IndexOutOfBoundsException ex) {
                    //Senão não houverem dois pais e apenas um, adiciona o individuo em falta sem sofrer alterações
                    sons.addIndividual(parents.getIndividual(i));
                }
            }
        }

//
//        for (int i = 0; i < sons.getPopulation().size(); i++) {
//            Integer[] allelo = (Integer[]) ((TSP) sons.getPopulation().get(i)).getChromosome(0).getGene(0).getAllele();
//            for (int j = 0; j < allelo.length; j++) {
//                for (int k = 0; k < allelo.length; k++) {
//                    if (allelo[j] == allelo[k] && j != k) {
//                        System.out.println("ERROR: REPEATED CITYS AT OrderCrossOver (LAST)");
//                    }
//                }
//            }
//        }



        //devolve a população de filhos que sofreu o uniform crossover
        return sons;
    }

    private static int searchForNextGene(int positionOfCutProgenitor, Integer[] progenitor, Integer[] descendant) {
        int __valueProgenitor;
        boolean __valueExiste;

        __valueProgenitor = 0;

        // corre os valores do progenitor todos
        for (int __projenitorIndex = 0; __projenitorIndex < progenitor.length; __projenitorIndex++) {

            // valor do progenitor a partir do ponto de corte
            __valueProgenitor = progenitor[(__projenitorIndex + positionOfCutProgenitor) % progenitor.length];
            // variavel que mostra se o valor ja existe
            __valueExiste = false;

            for (int __descendantIndex = 0; __descendantIndex < positionOfCutProgenitor; __descendantIndex++) {
                // corre o descendente todos a ver se esse valor ja esta presente nele
                if (__valueProgenitor == descendant[__descendantIndex]) {
                    // se sim então indica na variavel e termina o ciclo
                    __valueExiste = true;
                    break;
                }
            }

            // se o valor não existir então termina o ciclo
            if (!__valueExiste) {
                break;
            }
        }

        // devolve o valor
        return __valueProgenitor;
    }

    //*********************************************************************************
    //*****************************Métodos para Reflection*****************************
    //*********************************************************************************    
    @Override
    public String getInfo() {
        String s = "<p>Método de recombinação de individuos que tem como parâmetro de entrada</p>"
                + "<p>a probabilidade da recombinação que dois individuos têm.</p>"
                + "<p>A probabilidade é passado como valor real ou seja, um valor entre 0 e 1 em que, por exemplo,</p>"
                + "<p>o parametro 0.40 representa 40% de probabilidade de recombinação dos individuos.</p>";
        return s;
    }

    @Override
    public boolean setParameters(String parameters) {
        try {
            int probabilidade = Integer.parseInt(parameters.split(" ")[0]);
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
}
