package operators.recombinations;

import genetics.Gene;
import genetics.Individual;
import genetics.Population;
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
        //Nova população que irá receber os filhos depois de ser aplicado o uniform-crossover na população pai
        sons = new Population(parents.getSizePopulation(), parents.getSizeGenome(), parents.getSizeGenotype(), parents.getSizeAllelo(), parents.getTypePopulation(), false);
        //Variavel que detem o ponto aleatorio no qual os genes serão cortados
        int cutPlace;
        //Variavel de controlo para saber se o operadorserá executado ao nivel de um array ou se de um cromossoma
        boolean alleloArray;
        //Verifica se os allelos dos individuos são ou não array de objectos para saber como tratar da recombinação dos individuos
        if (parents.getIndividual(0).getChromosome(0).getGene(0).getAllele() instanceof Object[]) {
            //Caso o allelo seja um array de objectos, calcula o ponto de corte ao nivel desse array
            cutPlace = Population.RANDOM_GENERATOR.nextInt(parents.getSizeAllelo());
            alleloArray = true;
        } //Caso não seja array's de objectos
        else {
            //Caso o allelo não seja um array, calcula o ponte de corte de acordo com o número de genes que cada cromossoma tem
            cutPlace = Population.RANDOM_GENERATOR.nextInt(parents.getSizeGenotype());
            alleloArray = false;
        }
        for (int i = 0; i < parents.getSizePopulation(); i += 2) {
            //Verifica se existem ainda dois pais para a recombinação
            try {
                Individual parent1 = parents.getIndividual(i);
                Individual parent2 = parents.getIndividual(i + 1);
                //Guarda os filhos que serão a cópia dos seus respectivos pais
                Individual son = parent1.clone();
                Individual daughter = parent2.clone();
                //Calcula um random a ver se cai na probabilidade para fazer a recombinação
                if (Genetic.RANDOM_GENERATOR.nextDouble() < this.probability) {
                    try {
                        //Caso o random caia na probabilidade, faz a troca
                        aplicaCorte(son, daughter, cutPlace, alleloArray);
                    } catch (Exception ex) {
                        System.out.println("" + ex);
                    }
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
     * Método que aplicará a recombinação OrderCrossover nos dois filhos recebidos como parametros. É também passado
     * por parametro o sitio de corte nos individuos e uma variavel de controlo para saber onde aplciar o operador:
     * Se num array de objectos dentro do allelo do individuo se no cromossoma do individuo ao nivel dos genes (APENAS A ULTIMA REFERIDA ESTÁ IMPLEMENTADA)
     * @param son (Individual) - Filho para a recombinação genética
     * @param daughter (Individual) - Filha para a recombinação genética
     * @param cutPlace (int) - Sitio de corte para aplicação do operador
     * @param array (boolean) - Variavel de controlo (true caso o allelo seja um array de objectos, false caso contrário)
     */
    private void aplicaCorte(Individual son, Individual daughter, int cutPlace, boolean array) throws Exception {
        /**
         * Caso seja array de objectos no allelo do individuo
         * **********************************************************
         * ***************** NÃO IMPLEMENTADO ***********************
         * **********************************************************
         */
        if(array){
            
        }
        //Caso não seja um array de objectos, o operador será aplicado ao nivel do crossoma do individuo
        else{
            //Ciclo que percorre todos os cromossomas dos dois filhos
            for (int i = 0; i < son.getSizeGenome(); i++) {
                //ArrayList's que contem todos os genes do cromossoma em questão
                ArrayList<Gene> genesSon = son.getChromosome(i).getGenotype();
                ArrayList<Gene> genesDaug = daughter.getChromosome(i).getGenotype();
                //Novos ArrayList's para receberem os genes já recombinados
                ArrayList<Gene> novosGenesSon = new ArrayList<Gene>();
                ArrayList<Gene> novosGenesDaug = new ArrayList<Gene>();
                //Ciclo que copia os primeiros genes para os filhos, já que estes não sofrem qualquer alteração até ao ponto de corte
                for (int j = 0; j < cutPlace; j++) {
                    novosGenesDaug.add(genesDaug.get(j));
                    novosGenesSon.add(genesSon.get(j));
                }
                //Percorre o resto dos genes que faltam (parte direita do sitio de corte)
                for (int j = cutPlace; j < son.getSizeGenotype(); j++) {
                    //Caso a filha ainda não contenha o próximo gene do pai, copia
                    if(!novosGenesDaug.contains(genesSon.get(j))) novosGenesDaug.add(genesSon.get(j));
                    //Caso contrário, terá que ir procurar qual o próximo gene a ser adicionado sem ser repetido
                    else{
                        Gene geneAdd = procuraNextValidGene(genesSon, novosGenesDaug, j);
                        //Verifica se encontrou o gene ou não
                        if(geneAdd != null) novosGenesDaug.add(geneAdd);
                        //Caso seja null, devolve excepção ao utilizador
                        else throw new Exception("OrderCrossover mal implementado na procura de um gene válido.");
                    }
                    //Caso o filho ainda não contenha o próximo gene da mãe, copia o mesmo
                    if(!novosGenesSon.contains(genesDaug.get(j))) novosGenesSon.add(genesDaug.get(j));
                    //Caso contrário, terá que ir buscar o próximo gene válido
                    else{
                        Gene geneAdd = procuraNextValidGene(genesDaug, novosGenesSon, j);
                        //Verifica se encontrou o gene ou não válido para adição
                        if(geneAdd != null) novosGenesSon.add(geneAdd);
                        //Caso seja null, devolve excepção ao utilizador
                        else throw new Exception("OrderCrossover mal implementado na procura de um gene válido.");
                    }
                }
                //Define o arraylist de genes para os cromossomas em questão, tanto do filho como da filha
                son.getChromosome(i).setGenotype(novosGenesSon);
                daughter.getChromosome(i).setGenotype(novosGenesDaug);
            }
        }
    }
    
    /**
     * Método auxiliar que procurará qual o próximo gene válido a ser utilizado no processo de recombinação genético entre pais e filhos
     * @param son (ArrayList<Gene>) - ArrayList de genes do filho/filha
     * @param mother (ArrayList<Gene>) - ArrayList de genes da mãe/pai
     * @param place (int) - Index de começo de procura
     * @return (Gene) - Gene encontrado
     */
    private Gene procuraNextValidGene(ArrayList<Gene> son, ArrayList<Gene> mother, int place){
        //Comeca no sitio onde foi enontrado o duplicado e segue até ao fim da lista
        for(int i = place; i < mother.size(); i++) {
            //Caso o filho ainda não contenha o gene da mãe, devolve esse mesmo gene
            if(!son.contains(mother.get(i))) return mother.get(i);
        }
        //Caso tenha ido até ao fim da lista e ainda não tenha encontrado nenhum gene válido para adicionar, começa entao agora do principio da lista
        for (int i = 0; i < place; i++) {
            //Caso o filho ainda não contenha o gene da mãe, devolve esse mesmo gene
            if(!son.contains(mother.get(i))) return mother.get(i);
        }
        //Caso não encontre, devolve null
        return null;
    }
}
