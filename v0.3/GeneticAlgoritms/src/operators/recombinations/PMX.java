package operators.recombinations;

import genetics.Chromosome;
import genetics.Gene;
import genetics.Individual;
import genetics.Population;
import operators.Genetic;

/*
 * -------------------------------------------------------------------------
 * ------------------------------------------------------------------------- I n
 * s t i t u t o P o l i t e c n i c o d e T o m a r E s c o l a S u p e r i o r
 * d e T e c n o l o g i a
 *
 * @author Ruben Felix <Ruben.Felix@gmail.com>
 * -------------------------------------------------------------------------
 * Número de Aluno: 13691 E-mail: Ruben.Felix@gmail.com
 * -------------------------------------------------------------------------
 * -------------------------------------------------------------------------
 */
/**
 * ****************************************************************
 * ** EM FUNCIONAMENTO APENAS PARA ARRAY DE INTEIROS NOS ALLELOS **
 * ****************************************************************
 */
public class PMX extends Recombination {
    //Variavel que obterá a população filho

    private Population sons;
    //Variaveis que receberão os individuos pais e os filhos depois de ser aplicado o operador nos pais
    private Individual father, mother;
    //Variavel que recebel a probabilidade de haver a recombinação ou nao entre dois filhos
    private double probability;

    /**
     * Construtor da classe em que a probabilidade dos filhos sofrerem uma
     * recombinação é de 75%
     */
    public PMX() {
        this(0.75);
    }

    /**
     * Construtor da classe onde é passado por parametro a probabilidade de dois
     * filhos sofrerem a recombinação
     *
     * @param probability (double) - Probabilidade, entre 0 e 1, de dois filhos
     * sofrerem a recombinação
     */
    public PMX(double probability) {
        this.probability = probability;
    }

    @Override
    public Population execute(Population parents) {
        //Nova população que irá receber os filhos depois de ser aplicado o PMX na população pai
        sons = new Population(parents.getSizePopulation(), parents.getSizeGenome(), parents.getSizeGenotype(), parents.getSizeAllelo(), parents.getTypePopulation(), false);
        //Ciclo que percorre toda a população pai que entra
        for (int i = 0; i < parents.getSizePopulation(); i += 2) {
            //Verifica se existem ainda dois pais para a recombinação
            try {
                father = parents.getIndividual(i);
                //puderá dar nullPointerException
                mother = parents.getIndividual(i + 1);
                //Guarda os filhos que serão a cópia dos seus respectivos pais
                Individual son = father.clone();
                Individual daughter = mother.clone();
                //Calcula um random a ver se cai na probabilidade para fazer a recombinação
                if (Genetic.RANDOM_GENERATOR.nextDouble() < this.probability) {
                    //Caso o random caia na probabilidade, aplica o operador
                    //Pontos de corte no allelo do individuo
                    int[] pointsOfCut = new int[2];
                    //gera dois pontos de corte
                    generatorTwoPointsDistinctsSequential(pointsOfCut, parents.getSizeAllelo());
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
                                //aplica a logica do pmx
                                partiallyMatchedCrossoverLogic(aFather, aMother, aSon, aDaug, pointsOfCut);
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
        //devolve a população de filhos que sofreu o uniform crossover
        return sons;
    }

    private static void generatorTwoPointsDistinctsSequential(int[] points, int maxValueExclusive) {
        // Soma se 1 ao valor gerado para nunca dar 0 e subtrai-se 3 ao maximo para dar margem para gerar
        // o segundo ponto, e esse mesmo ponto ter margem para não ser o valor do maximo
        points[0] = 1 + RANDOM_GENERATOR.nextInt(maxValueExclusive - 3);
        // vai gerar numeros enquanto os dois valores forem iguais
        do {
            // O valor a ser gerado vai ser o valor que sobra entre o maximo e o primeiro ponto
            // e subtrai-se 1 para deixar pelo menos um ultimo valor livre
            points[1] = points[0] + RANDOM_GENERATOR.nextInt(maxValueExclusive - points[0] - 1);
        } while (points[0] == points[1]);
    }

    protected static void partiallyMatchedCrossoverLogic(Integer[] father, Integer[] mother, Integer[] son, Integer[] daughter, int[] pointsOfCut) {
        int __dimensionProgenitors;

        __dimensionProgenitors = father.length;

        // corre os valores todos
        for (int __indexGene = 0; __indexGene < __dimensionProgenitors; __indexGene++) {
            // passa os valores entre os dois pontos para os filhos
            if (__indexGene >= pointsOfCut[0] && __indexGene <= pointsOfCut[1]) {
                son[__indexGene] = mother[__indexGene];
                daughter[__indexGene] = father[__indexGene];
            } else {
                // procura os valores que faltam e poe nos sitios certos
                son[__indexGene] = _searchNextGenePMX(father, mother, __indexGene, pointsOfCut);
                daughter[__indexGene] = _searchNextGenePMX(mother, father, __indexGene, pointsOfCut);
            }
        }
    }

    protected static int _searchNextGenePMX(Integer[] progenitorA, Integer[] progenitorB, int positionGene, int[] pointsOfCut) {
        for (int __indexGene = pointsOfCut[0]; __indexGene <= pointsOfCut[1]; __indexGene++) {
            if (progenitorA[positionGene] == progenitorB[__indexGene]) // depois de sellecionado um valor temos que verificar se esse valor não existe
            // tambem no individuo ja
            {
                return _searchNextGenePMX(progenitorA, progenitorB, __indexGene, pointsOfCut); // progenitorA[__indexGene];
            }
        }

        return progenitorA[positionGene];
    }

    //*********************************************************************************
    //*****************************Métodos para Reflection*****************************
    //*********************************************************************************   
    @Override
    public String getInfo() {
        String s = "<p>O operador recebe uma probabilidade de os individuos serem</p>"
                + " <p> recombinados. Essa probabilidade tem de estar entre 0 e 1.</p>"
                + " <p> Após isso, essa probabilidade é aplicada a uma população</p>"
                + " <p> e são criados, dois pais e dois filhos.</p>"
                + " <p> No final são retornados dois filhos.</p>"
                + "<p><marquee> (Ex: 0.40 indica 40% de probabilidade"
                + " <p>de acontecer a recombinação)</marquee></p>";
        return s;
    }

    @Override
    public boolean setParameters(String parameters) {
        int probabilidade = Integer.parseInt(parameters);
        try {
            this.probability = probabilidade;
            return true;
        } catch (Exception ex) {
            //parametos por defeito
            this.probability = 0.75;
            return false;
        }

    }
}
