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
/**
 * Classe responsável por aplicar o OrderCrossover na população pai que recebe. Esta classe poderá ser instanciada com uma probabilidade
 * de acontecimento da recombinação por defeito (75%) ou o utilizador, aquando da sua construção, poderá definir a probabilidade de recombinação
 * pretendida para o operador. O ponte de corte nos cromossomas dos individuos é calculado de forma aleatória. O operador recebe uma população de pais
 * e devolve uma população de filhos, já com a recombinação feita, com o mesmo tamanho da população de pais recebida.
 * @author Ruben Felix <Ruben.Felix@gmail.com>
 */
/**
 * ****************************************************************
 * ** EM FUNCIONAMENTO APENAS PARA ARRAY DE INTEIROS NOS ALLELOS **
 * ****************************************************************
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
            cutPlace = Population.RANDOM_GENERATOR.nextInt((parents.getSizeAllelo() - 2) + 1);
            alleloArray = true;
        } //Caso não seja array's de objectos
        else {
            //Caso o allelo não seja um array, calcula o ponte de corte de acordo com o número de genes que cada cromossoma tem
            do {
                cutPlace = Population.RANDOM_GENERATOR.nextInt(parents.getSizeGenotype() - 1);
                //certifica-se que o corte está entre 1 e o penultimo gene, de forma a que o corte possa ser efectuado
            } while (cutPlace > 0 && cutPlace < parents.getSizeGenotype() - 2);
            alleloArray = false;
        }
        for (int i = 0; i < parents.getSizePopulation(); i += 2) {
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
                        Chromosome cFather = daughter.getChromosome(j);
                        Chromosome cMother = son.getChromosome(j);
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
                                Integer[] aSon = (Integer[]) gSon.getAllele();
                                Integer[] aDaug = (Integer[]) gDaug.getAllele();
                                Integer[] aFather = (Integer[]) gFather.getAllele();
                                Integer[] aMother = (Integer[]) gMother.getAllele();
                                // troca os genes dos pais com os filhos
                                for (int indexGene = 0; indexGene < cutPlace; indexGene++) {
                                    aSon[indexGene] = aFather[indexGene];
                                    aDaug[indexGene] = aMother[indexGene];
                                }
                                // depois preenche o resto com genes do outro progenitor, diferente do de cima,
                                // com os restantes genes que faltam
                                for (int __indexGene = cutPlace; __indexGene < aSon.length; __indexGene++) {
                                    aSon[__indexGene] = searchForNextGene(__indexGene, aMother, aSon);
                                    aDaug[__indexGene] = searchForNextGene(__indexGene, aFather, aDaug);
                                }
                            }
                        }
                    }
//                    try {
//                        //Caso o random caia na probabilidade, faz a troca
//                        aplicaCorte(son, daughter, cutPlace, alleloArray);
//                    } catch (Exception ex) {
//                        System.out.println("" + ex);
//                    }
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
//    /**
//     * Método que aplicará a recombinação OrderCrossover nos dois filhos recebidos como parametros. É também passado
//     * por parametro o sitio de corte nos individuos e uma variavel de controlo para saber onde aplciar o operador:
//     * Se num array de objectos dentro do allelo do individuo se no cromossoma do individuo ao nivel dos genes (APENAS A ULTIMA REFERIDA ESTÁ IMPLEMENTADA)
//     * @param son (Individual) - Filho para a recombinação genética
//     * @param daughter (Individual) - Filha para a recombinação genética
//     * @param cutPlace (int) - Sitio de corte para aplicação do operador
//     * @param array (boolean) - Variavel de controlo (true caso o allelo seja um array de objectos, false caso contrário)
//     */
//    private void aplicaCorte(Individual son, Individual daughter, int cutPlace, boolean array) throws Exception {
//        /**
//         * Variaveis que seram os pais dos individuos, já que os filhos que entram são igiaus aos pais: ainda não sofreram alterações genéticas
//         */
//        Individual pai = son.clone();
//        Individual mae = daughter.clone();
//        /**
//         * Caso seja array de objectos no allelo do individuo
//         */
//        if (array) {
//            //para todos os cromossomas dos individuos
//            for (int i = 0; i < son.getSizeGenome(); i++) {
//                //cromossomas dos filhos
//                Chromosome cSon = son.getChromosome(i);
//                Chromosome cDaug = daughter.getChromosome(i);
//                //para todos os genes 
//                for (int j = 0; j < cSon.getGenotype().size(); j++) {
//                    //array com os genes
//                    Object[] aSon = new Object[pai.getSizeAllelo()];
//                    Object[] aDaug = new Object[pai.getSizeAllelo()];
//                    //Dimensão do allelo
//                    int dim = aSon.length;
//                    //Copia os primeiros genes antes do corte, que serão iguais ao progenitores
//                    for (int y = 0; y < cutPlace; y++) {
//                        aSon[y] = ((Object[]) pai.getChromosome(i).getGene(j).getAllele())[y];
//                        aDaug[y] = ((Object[]) mae.getChromosome(i).getGene(j).getAllele())[y];
//                    }
//                    //procura os próximos genes válidos para serem inseridos
//                    for (int k = cutPlace; k < dim; k++) {
//                        aSon[k] = procuraArray(k, (Object[]) mae.getChromosome(i).getGene(j).getAllele(), aSon);
//                        aDaug[k] = procuraArray(k, (Object[]) pai.getChromosome(i).getGene(j).getAllele(), aDaug);
//                    }
//                }
//            }
//        } //Caso não seja um array de objectos, o operador será aplicado ao nivel do crossoma do individuo
//        else {
//            //Ciclo que percorre todos os cromossomas dos pais
//            for (int i = 0; i < pai.getSizeGenome(); i++) {
//                //ArrayList's que contem todos os genes do cromossoma em questão
//                ArrayList<Gene> genesPai = pai.getChromosome(i).getGenotype();
//                ArrayList<Gene> genesMae = mae.getChromosome(i).getGenotype();
//                //Novos ArrayList's para receberem os genes já recombinados
//                ArrayList<Gene> novosGenesSon = new ArrayList<Gene>();
//                ArrayList<Gene> novosGenesDaug = new ArrayList<Gene>();
//                //Ciclo que copia os primeiros genes para os filhos, já que estes não sofrem qualquer alteração até ao ponto de corte
//                for (int j = 0; j < cutPlace; j++) {
//                    novosGenesDaug.add(genesMae.get(j));
//                    novosGenesSon.add(genesPai.get(j));
//                }
//                //Dimensão do array de genes
//                int dim = pai.getSizeGenotype();
//
//                //procura os próximos genes válidos para serem inseridos
//                for (int k = cutPlace; k < dim; k++) {
//                    novosGenesSon.add(procuraGenes(k, genesMae, novosGenesSon));
//                    novosGenesDaug.add(procuraGenes(k, genesPai, novosGenesDaug));
//                }
//                //Define o arraylist de genes para os cromossomas em questão, tanto do filho como da filha
//                son.getChromosome(i).setGenotype(novosGenesSon);
//                daughter.getChromosome(i).setGenotype(novosGenesDaug);
//            }
//        }
//    }
//
//    /**
//     * Método que permite definir qual o próximo gene a ser adicionado depois do corte a um filho ou uma filha
//     * @param pos (int) - Posição onde a procura começará
//     * @param progenitor (Object[]) - ArrayList de objectos que consta no gene do progenitor
//     * @param descendente (Object[]) - ArrayList de objectos que consta no gene do descendente
//     * @return (Object) - Próximo objecto válido para ser adicionado ao gene do descendente
//     */
//    private Object procuraArray(int pos, Object[] progenitor, Object[] descendente) {
//        //p := pos-1
//        int p = pos - 1;
//        //q := pos
//        int q = pos;
//        //enquanto (p >= 0)
//        while (p >= 0) {
//            //se (descendente.gene[p] = progenitor.gene[q]) entao
//            if (descendente[p] == progenitor[p]) {
//                //q := q+1
//                q++;
//                //q := q%tamanho(progenitor.gene[])
//                q = q % progenitor.length;
//                //p := pos
//                p = pos;
//            }
//            //p := p-1
//            p--;
//        }
//        //retorna progenitor.gene[q]
//        return progenitor[q];
//    }
//
//    /**
//     * Método que permite definir qual o próximo gene a ser adicionado depois do corte a um filho ou uma filha
//     * @param pos (int) - Posição onde a procura começará
//     * @param progenitor (ArrayList<Gene>) - ArrayList de genes que consta no cromossoma do progenitor
//     * @param descendente (ArrayList<Gene>) - ArrayList de genes que consta no cromossoma do descendente
//     * @return (Gene) - Próximo gene válido para ser adicionado ao cromossoma do descendente
//     */
//    private Gene procuraGenes(int pos, ArrayList<Gene> progenitor, ArrayList<Gene> descendente) {
//        //p := pos-1
//        int p = pos - 1;
//        //q := pos
//        int q = pos;
//        //enquanto (p >= 0)
//        while (p >= 0) {
//            //se (descendente.gene[p] = progenitor.gene[q]) entao
//            if (descendente.get(p).getAllele() == progenitor.get(p).getAllele()) {
//                //q := q+1
//                q++;
//                //q := q%tamanho(progenitor.gene[])
//                q = q % progenitor.size();
//                //p := pos
//                p = pos;
//            }
//            //p := p-1
//            p--;
//        }
//        //retorna progenitor.gene[q]
//        return progenitor.get(q);
//    }

    @Override
    public String getInfo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean setParameters(String parameters) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
