package operators.mutation;

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
 * Operador de mutação Invertion. Este operador calcula dois pontos destintos de
 * corte no allelo do individuo e troca todos os bits entre esses dois pontos de
 * corte. Funciona apenas para arrays de inteiros no allelo dos individuos
 *
 * @author Ruben Felix <Ruben.Felix@gmail.com>
 */
/**
 * ****************************************************************
 * ** EM FUNCIONAMENTO APENAS PARA ARRAY DE INTEIROS NOS ALLELOS **
 * ****************************************************************
 */
public class Invertion extends Mutation {

    /**
     * Variavel que recebe o controlo da probabilidade costumizada
     */
    private boolean customProb = false;

    /**
     * Construtor da classe onde a probabilidade de mutação do gene é passado
     * por parametro
     *
     * @param probability (double) - Probabilidade de haver uma mutação no
     * individuo (Ex: 0.4 significa 40% de probabilidade de mutação)
     */
    public Invertion(double probability) {
        super.probability = probability;
        //Variavel a true para que assim não seja dada a probabilidade por defeito do operador (Probabilidade = 1 / TamanhoCromossoma)
        this.customProb = true;
    }

    /**
     * Construtor da classe onde a probabilidade de mutação do operador será
     * definida como 1 / TamanhoCromossoma.
     */
    public Invertion() {
        this.customProb = false;
    }

    @Override
    public Population execute(Population population) {
        //Caso a probabilidade não tenha sido definida pelo utilizador, utilizará o que está definido por defeito para os operadores de mutação (1 / TamanhoCromossoma)
        if (!customProb) {
            super.probability = 1 / (population.getSizeGenotype());
        }
        //População que irá ser a população filho a ser devolvida pela execução do operador depois de devidamente aplicada a mutação
        Population sons = new Population(population.getSizePopulation(), population.getSizeGenome(), population.getSizeGenotype(), population.getSizeAllelo(), population.getTypePopulation(), false);
        //Ciclo que percorre todos os individuos da população pai
        for (int i = 0; i < population.getSizePopulation(); i++) {
            //Verifica se será efectuado o operador no individuo, conforme a probabilidade que está definida para a mutação de cada um
            if (Genetic.RANDOM_GENERATOR.nextDouble() < super.probability) {
                //Faz o clone do individuo para um novo individuo chamado son, que por agora é apenas uma cópia genética do pai
                Individual son = population.getIndividual(i).clone();
                //Para todos os cromossomas do individuo
                for (int k = 0; k < son.getSizeGenome(); k++) {
                    //cromossomas do individuo
                    Chromosome cSon = son.getChromosome(i);
                    //Para todos os genes do cromossoma do individuo
                    for (int j = 0; j < cSon.getGenotype().size(); j++) {
                        //genes dos cromossomas
                        Gene gSon = cSon.getGene(j);
                        //Verifica se o allelo do gene é um array de inteiros
                        if (gSon.getAllele() instanceof Integer[]) {
                            //Allelo do individuo
                            Integer[] aSon = (Integer[]) gSon.getAllele();
                            //Array de inteiros que irão conter os pontos de troca no allelo do individuo
                            int[] points = new int[]{0, 0};
                            //Gera dois pontos distintos de corte no allelo do individuo (Array de objectos)
                            generatorTwoPointsDistincts(points, son.getSizeAllelo());
                            // inverte a ordem dos valores dentro dos pontos de corte
                            _invertOrderBetweenTwoPointers(points[0], aSon, points[1]);
                        }
                    }
                }
                //Adiciona o individuo já com a mutação feita à população filho
                sons.addIndividual(son);
            } //Caso não haja troca de genes, adiciona o individuo sem qualquer alteração na sua informação genética
            else {
                sons.addIndividual(population.getIndividual(i));
            }
        }
        //Devolve a população filho
        return sons;
    }

    /**
     * Método que irá calcular dois locais de corte no array de objectos do
     * allelo do individuo
     *
     * @param points - Pontons de corte
     * @param maxValueExclusive - Valor máximo de ponto de corte (Valor do
     * tamanho do allelo)
     */
    private static void generatorTwoPointsDistincts(int[] points, int maxValueExclusive) {
        points[0] = RANDOM_GENERATOR.nextInt(maxValueExclusive);
        // vai gerar numeros enquanto os dois valores forem iguais
        do {
            points[1] = RANDOM_GENERATOR.nextInt(maxValueExclusive);
        } while (points[0] == points[1]);
    }

    protected static void _invertOrderBetweenTwoPointers(int point1, Integer[] individual, int point2) {
        int __pointTemp;
        int __distanceBetweenPointers;

        __pointTemp = point1;
        __distanceBetweenPointers = 0;

        // conta a distancia entre os dois pontos
        while (((__pointTemp++) % individual.length) != point2) {
            __distanceBetweenPointers++;
        }

        // corre o array entre os dois pontos e vai trocando os valores ate
        // estarem todos trocados na sua ordem
        for (int pontoMover = 0; pontoMover < __distanceBetweenPointers; pontoMover++) {
            for (int indexValue = 0; indexValue < __distanceBetweenPointers - pontoMover; indexValue++) {
                _exchangeValuesFromArrayInTwoPoints(individual,
                        new int[]{
                            indexValue + point1, // ponto actual
                            indexValue + point1 + 1});  // ponto a seguir
            }
        }
    }

    protected static void _exchangeValuesFromArrayInTwoPoints(Integer[] individual, int[] points) {
        // guarda o primeiro valor numa variavel temporaria
        int __temp = individual[points[0] % individual.length];
        // troca o primeiro valor
        individual[points[0] % individual.length] = individual[points[1] % individual.length];
        // troca o valor guardado na variavel temporaria
        individual[points[1] % individual.length] = __temp;
    }

    @Override
    public String getInfo() {
        String s = "<p>O operador recebe uma probabilidade de os individuos serem</p>"
                + " <p> recombinados. Essa probabilidade tem de estar entre 0 e 1.</p>"
                + " <p> Após isso, no allelo do individuo serão selecionados dois</p>"
                + " <p> pontos de corte, e entre esses dois pontos os bits serão</p>"
                + " <p> invertidos.</p>"
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
