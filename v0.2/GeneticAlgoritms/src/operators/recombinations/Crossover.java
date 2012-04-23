/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package operators.recombinations;

import genetics.Chromosome;
import genetics.Individual;
import genetics.Population;
import operators.Genetic;

/**
 *
 * @author Chorinca-Notebook
 */
public class Crossover extends Recombination {

    /**
     * Aplica o operador de crossover aos individuos de entrada e
     * devolve os seus filhos
     * @param population População dos pais
     * @return População dos filhos
     */
    @Override
    public Population execute(Population population) {
        final Population __newPopulation = 
                new Population(
                    population.getSizePopulation(), 
                    population.getSizeGenome(), 
                    population.getSizeGenotype(),
                    population.getSizeAllelo(),
                    population.getTypePopulation(), 
                    false);
        
        // Cilco que corre a população dos pais. O ciclo anda de 
        // 2 em 2 para trazer sempre dois pais
        for (int __indexParents = 0; __indexParents < population.getSizePopulation(); __indexParents = __indexParents + 2) {
            // pointOfCutAllelo gerador aleatório da população
            int __pointOfCutAllelo = Genetic.RANDOM_GENERATOR.nextInt(__newPopulation.getSizeAllelo() - 1) + 1;

            // Escolhe um pai e uma mãe da população dos pais
            Boolean[] __father = (Boolean[])((Chromosome)population.getIndividual(__indexParents).getGenome().get(0)).getGene(0).getAllele();  
            Boolean[] __mother = (Boolean[])((Chromosome)population.getIndividual(__indexParents + 1).getGenome().get(0)).getGene(0).getAllele();

            // Cria os filhos que vão ser gerados com base nos pais
            Boolean[] __son = new Boolean[__newPopulation.getSizeAllelo()];
            Boolean[] __daughter = new Boolean[__newPopulation.getSizeAllelo()];
            
            // Pecorre os allelos do pai
            for (int __indexAlleloValuesFather = 0; __indexAlleloValuesFather < __newPopulation.getSizeAllelo(); __indexAlleloValuesFather++) {
                // Se estiver antes do ponto de corte então esses allelos
                // são para o filho, senão são para a filha
                if(__indexAlleloValuesFather < __pointOfCutAllelo - 1)
                    __son[__indexAlleloValuesFather] = __father[__indexAlleloValuesFather];
                else
                    __daughter[__indexAlleloValuesFather] = __father[__indexAlleloValuesFather];
            }

            // Pecorre os allelos da mãe
            for (int __indexAlleloValuesMother = 0; __indexAlleloValuesMother < __newPopulation.getSizeAllelo(); __indexAlleloValuesMother++) {
                // Se estiver antes do ponto de corte então esses allelos
                // são para a filha, senão são para o filho
                if(__indexAlleloValuesMother < __pointOfCutAllelo - 1)
                    __daughter[__indexAlleloValuesMother] = __mother[__indexAlleloValuesMother];
                else
                    __son[__indexAlleloValuesMother] = __mother[__indexAlleloValuesMother];
            }

            // Cria o individuo filho com base no individuo pai
            Individual __sonIndividual = population.getIndividual(0).clone();
            // utiliza as caracteristicas do pai mas muda os valores do allelo
            __sonIndividual.getChromosome(0).getGene(0).setAllele((Object)__son);

            // Cria o individuo filha com base no individuo pai
            Individual __daughterIndividual = population.getIndividual(0).clone();
            // utiliza as caracteristicas do pai mas muda os valores do allelo
            __daughterIndividual.getChromosome(0).getGene(0).setAllele((Object)__daughter);

            // Junta os filhos à nova população
            __newPopulation.addIndividual(__sonIndividual);
            __newPopulation.addIndividual(__daughterIndividual);
        
        }
        
        // Devolve a nova população com os filhos
        return __newPopulation;
    }
    
}
