package operators.recombinations;

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
public class UniformCrossover extends Recombination {
    //Variavel que obterá a população filho

    private Population sons;
    //Variaveis que receberão os individuos pais e os filhos depois de ser aplicada a mascara nos pais
    private Individual parent1, parent2, son1, son2;
    //Array de booleans que será a máscara a ser utilizada no método
    private Boolean[] mask;

    @Override
    public Population execute(Population parents) {
        //Cria a máscara a ser utilizada na população
        mask = new Boolean[parents.getSizeAllelo()];
        for (int i = 0; i < mask.length; i++) {
            mask[i] = Population.RANDOM_GENERATOR.nextBoolean();;
        }
        //Nova população que irá receber os filhos depois de ser aplicado o uniform-crossover na população pai
        sons = new Population(parents.getSizePopulation(), parents.getSizeGenome(), parents.getSizeGenotype(), parents.getSizeAllelo(), parents.getTypePopulation(), false);
        for (int i = 0; i < parents.getSizePopulation(); i++) {
            //Verifica se existem ainda dois pais para a recombinação
            try {
                    parent1 = parents.getIndividual(i);
                    //puderá dar nullPointerException
                    parent2 = parents.getIndividual(i + 1);
                    aplicaMask(parent1, parent2, mask);
                    sons.addIndividual(son1);
                    sons.addIndividual(son2);
            } catch (IndexOutOfBoundsException ex) {
                //Senão não houverem dois pais e apenas um, adiciona o individuo em falta sem sofrer alterações
                sons.addIndividual(parents.getIndividual(i));
            }
            //Incrementa o i mais uma vez para andar de dois em dois
            i++;
        }
        //devolve a população de filhos que sofreu o uniform crossover
        return sons;
    }

    /**
     * Método que recebe dois pais, copia os seus allelos para os filhos e só fará a troca de genes na posição X dos
     * filhos caso a máscara dessa posição seja true
     * @param parent1 - Pai
     * @param parent2 - Mãe
     * @param mask - Máscara do uniforme crossover
     */
    private void aplicaMask(Individual parent1, Individual parent2, Boolean[] mask) {
        //Guarda os filhos que serão a cópia dos seus respectivos pais
        Individual sonMask = parent1.clone();
        Individual daughterMask = parent2.clone();
        //Allelos de cada filho para que possa ser efectuada a troca
        Boolean[] sonAllelo = new Boolean[sonMask.getSizeAllelo()];
        //Copia do allelo do individuo para uma variavel local(allelo) afim de ser modificada
        for (Chromosome __chromosome : sonMask) {
            for (Gene<Boolean[]> __gene : __chromosome) {
                for (int __indexAlleloValue = 0; __indexAlleloValue < __gene.getAllele().length; __indexAlleloValue++) {
                    if (__gene.getAllele()[__indexAlleloValue]) {
                        sonAllelo[__indexAlleloValue] = true;
                    } else {
                        sonAllelo[__indexAlleloValue] = false;
                    }
                }
            }
        }
        Boolean[] daughterAllelo = new Boolean[daughterMask.getSizeAllelo()];
        //Copia do allelo do individuo para uma variavel local(allelo) afim de ser modificada
        for (Chromosome __chromosome : daughterMask) {
            for (Gene<Boolean[]> __gene : __chromosome) {
                for (int __indexAlleloValue = 0; __indexAlleloValue < __gene.getAllele().length; __indexAlleloValue++) {
                    if (__gene.getAllele()[__indexAlleloValue]) {
                        daughterAllelo[__indexAlleloValue] = true;
                    } else {
                        daughterAllelo[__indexAlleloValue] = false;
                    }
                }
            }
        }
        //Percorre todas as posições do allelo
        for (int i = 0; i < mask.length; i++) {
            //faz a troca apenas se a máscara for igual a true
            if (mask[i]) {
                Boolean aux = sonAllelo[i];
                //Troca os booleans dos allelos do filho para a filha
                sonAllelo[i] = daughterAllelo[i];
                daughterAllelo[i] = aux;
            }
        }
        //Define os novos allelos para os filhos
        sonMask.getChromosome(0).getGene(0).setAllele(sonAllelo);
        daughterMask.getChromosome(0).getGene(0).setAllele(daughterAllelo);
        //Cria os novos objectos filhos
        son1 = sonMask.clone();
        son2 = sonMask.clone();
    }
}
