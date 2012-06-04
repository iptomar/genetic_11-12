package operators.recombinations;

import genetics.Chromosome;
import genetics.Gene;
import genetics.Individual;
import genetics.Population;
import genetics.algorithms.FunctionReal;
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
public class CrossoverAX extends Recombination {
    //Variavel que obterá a população filho
    
    private Population sons;
    //Variaveis que receberão os individuos pais e os filhos depois de ser aplicada a mascara nos pais
    private Individual parent1, parent2;
    //Variavel que recebel a probabilidade de haver a recombinação ou nao entre dois filhos
    private double probability;

    /**
     * Construtor da classe em que a probabilidade dos filhos sofrerem uma recombinação é de 75%
     */
    public CrossoverAX() {
        this(0.75);
    }

    /**
     * Construtor da classe onde é passado por parametro a probabilidade de dois filhos sofrerem a recombinação
     * @param probability (double) - Probabilidade, entre 0 e 1, de dois filhos sofrerem a recombinação
     */
    public CrossoverAX(double probability) {
        this.probability = probability;
    }

    @Override
    public Population execute(Population parents) {
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
                    _performeAXCrossover(son, daughter);
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
    protected void _performeAXCrossover(Individual son, Individual daughter) {
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

                double domain = ((FunctionReal)son).getSizeDomains(j);
                double min = ((FunctionReal)son).getBeginDomains(j);
                double max = ((FunctionReal)son).getEndDomains(j); 
                
                //random [ -0.5 , 1.5 ]
                double __alpha = Population.RANDOM_GENERATOR.nextDouble() * domain + min;
                //get original value of genes
                double __gene1 = (Double)gSon.getAllele();
                double __gene2 = (Double)gDaug.getAllele();
                
                double __alleloSon = __gene1 * __alpha + __gene2 * (1 - __alpha);
                double __alleloDaug = __gene1 * (1 - __alpha) + __gene2 * __alpha;
                
                if(__alleloSon > max) __alleloSon = max;
                if(__alleloDaug > max) __alleloDaug = max;
                if(__alleloSon < min) __alleloSon = min;
                if(__alleloDaug < min) __alleloDaug = min;
                
                //perform AX crossover
                gSon.setAllele(__alleloSon);
                gDaug.setAllele(__alleloDaug);
            }
        }
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
