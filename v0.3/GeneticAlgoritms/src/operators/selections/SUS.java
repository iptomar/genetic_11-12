package operators.selections;

import genetics.Individual;
import genetics.Population;
import genetics.algorithms.City;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Ponteiro;
import utils.PopulationUtils;
import utils.exceptions.PonteiroForaDoLimiteException;

/**
 *
 * @author Aurélien Mota Nº13673
 */
public class SUS extends Selection {

    private double offset = 0.0;
    private double ponteiro = 0.0;
    //Matriz de curso caso o SUS pedido seja o minimazo para o problema do caixeiro viajante
    private double[][] costMatrix = null;

    public double getOffset() {
        return offset;
    }

    public double getPonteiro() {
        return ponteiro;
    }

    public SUS() {
        this(Selection.DIMENDIONS_NEW_POPULATION_DEFAULT);
    }

    //contructor com parametro, dimensão da nova população
    public SUS(int dimensionsNewPopulation) {
        super._dimensionsNewPopulation = dimensionsNewPopulation;
    }

    /**
     * Costrutor da classe SUS onde é passado por parametro a matriz de custo para o problema do caixeiro viajante.
     * Desta forma, se a matriz de custo não for nula, o operador, aquando da sua execução, saberá que será o SUS
     * minimizado que irá correr.
     * @param costMatrix - Matriz de custos do problema do caixeiro viajante
     */
    public SUS(double[][] costMatrix) {
        this.costMatrix = costMatrix;
    }

    @Override
    public Population execute(Population population) {
        //criar nova população
        //variavel so de leitura ao ser inicializada
        final Population newPopulation =
                new Population(
                super._dimensionsNewPopulation,
                population.getSizeGenome(),
                population.getSizeGenotype(),
                population.getSizeAllelo(),
                population.getTypePopulation(),
                false);
        //verifica se a população é do tipo City - Se sim, fará a definição da matriz de custo atravez do primeiro individuo dessa população
        //já que a matriz de custo será igual para todos os individuos.
        if(population.getTypePopulation() instanceof City){
            this.costMatrix = ((City)population.getIndividual(0)).costMatrix;
        }
        //Caso a matriz de custo seja nula, o operador irá correr o SUS maiximizado
        if (this.costMatrix == null) {
            //calcula offset
            this.offset = calculateOffset(population);
            //ponteiro que vai apontar para os individuos, inicialização com ponto aleatorio
            this.ponteiro = Ponteiro.pontoAleatorio(PopulationUtils.getFitnessTotal(population));
            //correr cada individuo da população
            for (int numeroIndividuos = 0; numeroIndividuos < super._dimensionsNewPopulation; numeroIndividuos++) {
                try {
                    //acrecenta um individou para a nova população
                    newPopulation.addIndividual(
                            Ponteiro.devolveIndividuoParaOndeOPonteiroAponta(this.ponteiro, population));
                } catch (PonteiroForaDoLimiteException ex) {
                    Logger.getLogger(SUS.class.getName()).log(Level.SEVERE, null, ex);
                }

                //faz a incrementação do ponteiro e o mod para voltar ao inicio caso ultrapasse o fitness total
                this.ponteiro = (this.ponteiro + this.offset) % PopulationUtils.getFitnessTotal(population);
            }

            return newPopulation;
        } //Caso a matriz de custo não seja nula, sabemos então que teremos que correr o SUS minimizado já que o problema será o do caixeiro viajante
        else {

            double totalFitness;
            double[][] newCostMatrixMinimization;
            int numIndividualsToSelect = this._dimensionsNewPopulation;

            // nova matrix de custo, mas agora com os valores menores como maiores, ou seja,
            // temos um SUS de minimização e não de maximização
            newCostMatrixMinimization = calculateNewCostMatrixForMinimization(costMatrix);
            totalFitness = PopulationUtils.totalFitnessAcumulation(population, newCostMatrixMinimization);

            // calcula o offset do SUS
            this.offset = ((double) totalFitness) / ((double) numIndividualsToSelect);
            // calcula um ponto aleatorio entre 0 e o maximo de fitness para ser o ponteiro
            // inicial do SUS
            this.ponteiro = RANDOM_GENERATOR.nextDouble() * (double) totalFitness;
            // Ciclo ate ter todos os individuos pedidos
            while ((numIndividualsToSelect--) > 0) {
                newPopulation.addIndividual(
                        returnIndividualFromPointer(
                        this.ponteiro,
                        population,
                        newCostMatrixMinimization));

                this.ponteiro = (this.ponteiro + this.offset) % totalFitness;
            }
            return newPopulation;
        }
    }

    public static Individual returnIndividualFromPointer(double ponteiro, Population population, double[][] costMatrix) {
        double totalFitnessAccumulate = 0;
        Individual individualSelect = null;
        //Corre a população para cada individuo
        for (Individual individuo : population) {
            // incrementa o total fitness
            // recalcula o fitness do individuo para que o menor seja agora o maior e vise-versa
            // porque o problema é de minimização, os individuos com menor fitness são os melhores
            totalFitnessAccumulate += PopulationUtils.calculateFitness(individuo, costMatrix);
            
            //escolhe o individuo onde o ponteiro aponta 
            if (ponteiro <= totalFitnessAccumulate) {
                //clone para criar um novo individuo e não ser individuo da pop original
                individualSelect = individuo;
                break;
            }
            
        }
        //Caso não existam individuos na população recebida, devolve null
        return individualSelect;
    }

    //calcula o offset total fitness/total individuos da população
    private double calculateOffset(Population population) {
        // Devolve o novo offset
        return ((double) PopulationUtils.getFitnessTotal(population)) / ((double) population.getSizePopulation());
    }

    private static double[][] calculateNewCostMatrixForMinimization(double[][] costMatrix) {
        double[][] __newCostMatrixMinimization;
        double __maxCostInMatrix;

        // define o tamanho da matriz
        __newCostMatrixMinimization = new double[costMatrix.length][costMatrix.length];
        // obtem o valor maximo que a matriz original tem
        __maxCostInMatrix = maxCostMatrix(costMatrix);

        for (int __linhas = 0; __linhas < __newCostMatrixMinimization.length; __linhas++) {
            __newCostMatrixMinimization[__linhas][__linhas] = 0;
            for (int __colunas = __linhas + 1; __colunas < __newCostMatrixMinimization.length; __colunas++) {
                // ao valor maximo subtrai-se os valores dentro da matriz
                __newCostMatrixMinimization[__linhas][__colunas] = __newCostMatrixMinimization[__colunas][__linhas] = (__maxCostInMatrix + 1) - costMatrix[__linhas][__colunas];
            }
        }

        // devolve a nova matriz invertida nos valores
        return __newCostMatrixMinimization;
    }
    
    private static double maxCostMatrix(double[][] costMatrix) {
        double __maxFitness;
        __maxFitness = 0;  
        // procura o valor mais alto na matriz
        for (int i = 0; i < costMatrix.length; i++) {
            // não preciso correr a matriz toda porque o triangulo inferior é
            // o espelho do de cima
            for (int j = i; j < costMatrix.length; j++) {
                if(__maxFitness < costMatrix[i][j]){
                    __maxFitness = costMatrix[i][j];
                }
            }
        }
        return __maxFitness;
    }
    

    //*********************************************************************************
    //*****************************Métodos para Reflection*****************************
    //*********************************************************************************    
    @Override
    public String getInfo() {
        String s = "<p>Método usado para fazer uma selecção de indivíduos a uma população. </p>"
                + "<p>Este método tem como parâmetros de entrada o tamanho da nova população,</p>"
                + "<p> isto é, o número de indivíduos a serem seleccionados da população de entrada.</p>"
                + "<p>O parâmetro deverá conter um valor inteiro positivo.</p>";
        return s;
    }

    @Override
    public boolean setParameters(String parameters) {
        try {
            this._dimensionsNewPopulation = Integer.parseInt(parameters);
            return true;
        } catch (Exception ex) {
            //Devolve false caso exista algum problema a fazer a definição de parametros
            return false;
        }
    }
    //*********************************************************************************
    //*********************************************************************************
    //*********************************************************************************
}