package GeneticV01;

/**
 *
 * @author Pedro
 */
public class Mutacao {
    /**
     * Variavel que contem a probabilidade de um determinado gene ser ou não mudado
     */
    private double probabil;
    /**
     * Variavel que irá conter a população a ser geneticamente modificada
     */
    private Population pop;
    
    /**
     * Construtor da classe que recebe a probabilidade de um determinado gene ser modificado, bem como
     * a população afectada pela mutação
     * @param probabil (double) - Probabilidade de um gene sofrer mutação 
     * @param pop - População afecta a esta mutação
     */
    public Mutacao(double probabil, Population pop) {
        this.probabil = probabil;
        this.pop = pop;
    }

    /**
     * Método que inicia a mutação na população definida na classe e devolve a nova
     * população, já afectada pela mutação
     * @return (Population) - População depois de ter sido afectada pela mutação
     */
    public Population IniciarMutacao() {
        //percorre toda a polulação
        for (int i = 0; i < pop.getNumberIndividuals(); i++) {
            //vai a cada individuo da população e vai muta-lo  
            Individual indi = pop.getIndividual(i);
            //Faz a mutação no individuo da população
            Muta(indi);
        }
        return pop;
    }

    /**
     * Método auxiliar que, conforme as probabilidades de ocorrer uma mutação, faz a 
     * mutação ou não de um determinado gene do individuo.
     * @param indiv (Individual) - Individuo a ser mutado
     */
    private void Muta(Individual indiv) {
        Individual i = indiv;
        boolean novo, aux;
        double gr;

        //Vai ao cromossoma de cada individuo e percorre todos os genes pertencentes ao mesmo
        for (int j = 0; j < pop.getIndividual(0).getChromosome(0).getNumberGenes(); j++) {
            //Conforme a percentagem, poderá ou não mutar o gene para o seu valor contrário
            gr = (Math.random()*100);
            aux = i.getChromosome(0).getGene(j).getValue();
            
            if (gr <= probabil) {
                if (aux == true) {
                    novo = false;
                } else {
                    novo = true;
                }
                i.getChromosome(0).getGene(j).setValue(novo);
            }
        }
    }
}
