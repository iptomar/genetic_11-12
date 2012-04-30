package operators.recombinations;

import genetics.Chromosome;
import genetics.Gene;
import genetics.Individual;
import genetics.Population;
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
public class UniformCrossover extends Recombination {
    //Variavel que obterá a população filho

    private Population sons;
    //Variaveis que receberão os individuos pais e os filhos depois de ser aplicada a mascara nos pais
    private Individual parent1, parent2;
    //Array de booleans que será a máscara a ser utilizada no método
    private Boolean[] mask = null;
    //Variavel que recebel a probabilidade de haver a recombinação ou nao entre dois filhos
    private double probability;

    /**
     * Construtor da classe em que a probabilidade dos filhos sofrerem uma recombinação é de 75%
     */
    public UniformCrossover() {
        this(0.75);
    }

    /**
     * Construtor da classe onde é passado por parametro a probabilidade de dois filhos sofrerem a recombinação
     * @param probability (double) - Probabilidade, entre 0 e 1, de dois filhos sofrerem a recombinação
     */
    public UniformCrossover(double probability) {
        this.probability = probability;
    }

    @Override
    public Population execute(Population parents) {
        //Fará apenas a mascara aleatória caso a mesma não esteja ainda definida
        if (mask == null) {
            //Cria a máscara a ser utilizada na população
            setMask(new Boolean[parents.getSizeAllelo()]);
            for (int i = 0; i < mask.length; i++) {
                mask[i] = Population.RANDOM_GENERATOR.nextBoolean();;
            }
        }
        //Nova população que irá receber os filhos depois de ser aplicado o uniform-crossover na população pai
        sons = new Population(parents.getSizePopulation(), parents.getSizeGenome(), parents.getSizeGenotype(), parents.getSizeAllelo(), parents.getTypePopulation(), false);
        for (int i = 0; i < parents.getSizePopulation(); i += 2) {
            //Verifica se existem ainda dois pais para a recombinação
            try {
                parent1 = parents.getIndividual(i);
                //puderá dar nullPointerException
                parent2 = parents.getIndividual(i + 1);
                //Guarda os filhos que serão a cópia dos seus respectivos pais
                Individual son = parent1.clone();
                Individual daughter = parent2.clone();
                //Calcula um random a ver se cai na probabilidade para fazer a recombinação
                if (Genetic.RANDOM_GENERATOR.nextDouble() < this.probability) {
                    //Caso o random caia na probabilidade, faz a troca
                    aplicaMask(son, daughter, mask);
                }
                sons.addIndividual(son);
                sons.addIndividual(daughter);
            } catch (IndexOutOfBoundsException ex) {
                //Senão não houverem dois pais e apenas um, adiciona o individuo em falta sem sofrer alterações
                sons.addIndividual(parents.getIndividual(i));
            }
        }
        //devolve a população de filhos que sofreu o uniform crossover
        return sons;
    }

    /**
     * Método que recebe os dois filhos para serem então recombinados, bem como a mascára de recombinação
     * @param son - Filho a ser aplicado a troca
     * @param daughter - Filha a ser aplicada a troca
     * @param mask - Máscara do uniforme crossover
     */
    protected void aplicaMask(Individual son, Individual daughter, Boolean[] mask) {
        //para todos os cromossomas dos individuos
        for (int i = 0; i < son.getSizeGenome(); i++) {
            //cromossomas dos filhos
            Chromosome cSon = son.getChromosome(i);
            Chromosome cDaug = daughter.getChromosome(i);
            //para todos os genes 
            for (int j = 0; j < cSon.getGenotype().size(); j++) {
                //genes dos filhos
                Gene gSon = cSon.getGene(j);
                Gene gDaug = cDaug.getGene(j);

                Object aux = gSon.getAllele();
                //array de 
                if (aux instanceof Object[]) {
                    //array com os genes
                    Object[] aSon = (Object[]) gSon.getAllele();
                    Object[] aDaug = (Object[]) gDaug.getAllele();
                    for (int k = 0; k < aDaug.length; k++) {
                        //se for para trocar
                        if (mask[k]) {
                            //trocar os alelos entre o filho e a filha
                            Object auxBit = aSon[k];
                            aSon[k] = aDaug[k];
                            aDaug[k] = auxBit;
                        }
                    }
                } else {
                    gSon.setAllele(gDaug.getAllele());
                    gDaug.setAllele(aux);
                }



            }
        }
    }
    
    /**
     * Método que permite fazer a definição da máscara a ser executada na recombinação
     * @param mask (Boolean[]) - Máscara a ser utilizada na recombinação dos filhos
     */
    public void setMask(Boolean[] mask) {
        this.mask = mask;
    }
    
    //Pequeno teste ao operador
//    public static void main(String[] args) {
//        OnesMax i1 = new OnesMax();
//        Boolean[] _i1Allelo = new Boolean[]{true, true, true, true, true, true, true, true, true, true};
//
//        i1.inicializationGenome();
//
//        for (Object __chromosome : i1) {
//            for (Gene __gene : (Chromosome) __chromosome) {
//                __gene.setAllele(_i1Allelo);
//            }
//        }
//
//        OnesMax i2 = new OnesMax();
//        Boolean[] _i2Allelo = new Boolean[]{false, false, false, false, false, false, false, false, false, false};
//
//        i2.inicializationGenome();
//
//        for (Object __chromosome : i2) {
//            for (Gene __gene : (Chromosome) __chromosome) {
//                __gene.setAllele(_i2Allelo);
//            }
//        }
//
//        //Boolean[] __mask = new Boolean[] { false, false,false,false,false,false,false,false,false,false };
//        //Boolean[] __mask = new Boolean[] { true, true, true, true, true, true, true, true, true, true };
//        Boolean[] __mask = new Boolean[]{false, false, false, false, false, true, true, true, true, true};
//
//        UniformCrossover __uniformeCrossover = new UniformCrossover();
//        __uniformeCrossover.aplicaMask(i1, i2, __mask);
//
//        System.out.println("");
//    }
}
