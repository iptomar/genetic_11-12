package operators.mutation;

import genetics.Chromosome;
import genetics.Gene;
import genetics.Individual;
import genetics.Population;
import genetics.algorithms.OnesMax;
import java.util.ArrayList;
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
 * Operador de mutação Swap Genes que escolhe  dois allelos aleatórios de um indivíduo e trocá-os de sítio, sendo que a probabilidade de
 * mutação do operador é definida por defeito como sendo 1 / TamanhoCromossoma. Se o utilizador pretender outra probabilidade, poderá então passar
 * a mesma em um dos contrutores da classe.
 * @author Ruben Felix <Ruben.Felix@gmail.com>
 */
/**
 * *************************************************************************************
 * ** OPERADOR APENAS PREPARADO PARA FAZER A TROCA ENTRE DOIS GENES DE UM CROMOSSOMA ***
 * ** EM CASO DE HAVER APENAS UM GENE, O OPERADOR DISPARA UMA EXCEPTION AO UTILIZADOR **
 * *************************************************************************************
 */
public class SwapGenes extends Mutation {

    private boolean customProb = false;

    /**
     * Construtor da classe onde a probabilidade de mutação do gene é passado por parametro 
     * @param probability (double) - Probabilidade de haver uma mutação no individuo (Ex: 0.4 significa 40% de probabilidade de mutação)
     */
    public SwapGenes(double probability) {
        super.probability = probability;
        //Variavel a true para que assim não seja dada a probabilidade por defeito do operador (Probabilidade = 1 / TamanhoCromossoma)
        this.customProb = true;
    }

    /**
     * Construtor da classe onde a probabilidade de mutação do operador será definida como 1 / TamanhoCromossoma.
     */
    public SwapGenes() {
        this.customProb = false;
    }

    @Override
    public Population execute(Population population) {
        //Caso a probabilidade não tenha sido definida pelo utilizador, utilizará o que está definido por defeito para este operador (1 / TamanhoCromossoma)
        if (!customProb) {
            super.probability = 1 / (population.getSizeGenotype());
        }
        //População que irá ser a população filho a ser devolvida pela execução do operador depois de devidamente aplicada a mutação
        Population sons = new Population(population.getSizePopulation(), population.getSizeGenome(), population.getSizeGenotype(), population.getSizeAllelo(), population.getTypePopulation(), false);
        //Ciclo que percorre todos os individuos da população pai
        for (int i = 0; i < population.getSizePopulation(); i++) {
            //Verifica se será efectuada a troca de genes no individuo conforme a probabilidade e o double gerado aleatóriamente
            if (Genetic.RANDOM_GENERATOR.nextDouble() < super.probability) {
                //Try catch para ver se o individuo apenas tem um gene. Se sim, lança uma excepção já que o mesmo não poderá sofrer o operador
                //swap genes que apenas funcionará quando um individuo tem mais que um gene no seu cromossoma
                try {
                    sons.addIndividual(trocaGenes(population.getIndividual(i)));
                } catch (Exception ex) {
                    System.out.println("" + ex);
                }
                //Caso não haja troca de genes, adiciona o individuo sem qualquer alteração na sua informação genética
            } else {
                sons.addIndividual(population.getIndividual(i));
            }
        }
        //Devolve a população de filhos
        return sons;
    }

    /**
     * Método que irá fazer a troca de genes aleatóriamente ao individuo que seja passado por parametro
     * @param ind (Individual) - Individuo que sofrerá a troca de genes
     * @return (Individual) - Individuo com os genes já trocados aleatóriamente
     */
    private Individual trocaGenes(Individual ind) throws Exception {
        //Individuo para a mutação
        Individual indTroca = ind.clone();
        //Verifica se o individuo apenas tem um gene. Se sim, não é possivel efectuar a troca de genes e lança um excepção
        if (indTroca.getSizeGenotype() <= 1) {
            throw new Exception("SwapGenes Esception: Size of genotype equals 0. Cannot do swap genes on population.");
        } else {
            //Variaveis que recebem os números aleatórios que referem os dois genes que serão trocados ao genotype do individuo
            //A primeira é calculada aleatóriamente e pode ir de 0 até ao fim dos genes do cromossoma
            int firstGene = Population.RANDOM_GENERATOR.nextInt(indTroca.getSizeGenotype()-1);
            int secondGene;
            do {
                //O segundo apontador para o gene será calculado entre 0 e o número de genes do individuo, sendo que este apontador 
                //terá obrigatóriamente ser diferente do primeira, caso contrário, não haveria qualquer tipo de troca
                secondGene = Population.RANDOM_GENERATOR.nextInt(indTroca.getSizeGenotype()-1);
            } while (secondGene == firstGene);
            //Sout dos dois pontos calculados (para testes)
//            System.out.println("First point: " + firstGene);
//            System.out.println("Second point: " + secondGene);
            //Para todos os cromossomas do individuo, será efectuada a troca de genes nos pontos definidos acima
            for (int i = 0; i < indTroca.getSizeGenome(); i++) {
                //Cromossoma onde será efectuado a troca de genes
                Chromosome cro = indTroca.getChromosome(i);
                //ArrayList de genes do cromossoma actual
                ArrayList<Gene> genes = cro.getGenotype();
                //Gene auxiliar para a troca
                Gene geneAux = genes.get(firstGene);
                //Define, na primeira posição calculada, o gene apontado pelo segundo apontador calculado
                genes.set(firstGene, genes.get(secondGene));
                //Define, na segunda posição calculada, o gene que estava na primeira posição calculada 
                genes.set(secondGene, geneAux);
            }
        }
        return indTroca;
    }
//    //Pequeno teste à classe
//    public static void main(String[] args) throws Exception {
//        OnesMax i1 = new OnesMax();        
//        i1.setSizeAllelo(1);
//        i1.setSizeGenome(1);
//        i1.setSizeGenotype(5);
//        i1.inicializationGenome();
//
//        SwapGenes sg = new SwapGenes();
//        System.out.println("" + i1);
//        System.out.println("" + sg.trocaGenes(i1));        
//    }
}
