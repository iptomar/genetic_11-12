package genetics.algorithms;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


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
 * Classe que contem as denições do problema do travelling salesman problem (TSP) ou caixeiro viajante
 * @author Ruben Felix <Ruben.Felix@gmail.com>
 */
public class TSPProblem {

    /**
     * String com o nome do problema
     */
    public String Name = "";
    /**
     * String com o comentário do problema
     */
    public String Comment = "";
    /**
     * String com o tipo de problema a que se refere
     */
    public String Type = "";
    /**
     * Dimensão do problema
     */
    public String Dimension = "";
    public String Edge_Weight_Type = "";
    /**
     * Variavel que recebe o caminho para o ficheiro com as definições do problema
     */
    private String filename = null;
    /**
     * ArrayList de cidades que definem o problema
     */
    public ArrayList<City> Cidades = new ArrayList<City>();

    /**
     * Construtor da classe que recebe como parametro de entrada o caminho para o ficheiro que contem
     * as especificações do problema
     * @param problemFile - Ficheiro que contem as especificações do problema
     */
    public TSPProblem(String problemFile) {
        this.filename = problemFile;
        this.read();
        this.inicializaCostMatrix();
    }

    /**
     * Método que fará a leitura das especificações do problema do ficheiro especificado.
     * O método retira as informações do problema, bem como das suas cidades e adiciona ao arraylist
     * de cidades que será a população inicial do problema.
     */
    private void read() {
        //Verifica se a variavel que contem o caminho para o ficheiro com as especificações do problema não é nulo nem está vazio
        if (!this.filename.equals("") && this.filename != null) {
            BufferedReader in = null;
            try {
                /**
                 * Objecto de leitura do ficheiro especificado
                 */
                in = new BufferedReader(new FileReader(this.filename));
            } catch (FileNotFoundException ex) {
                //Caso não encontre o ficheiro especificado, devolve mensagem de erro
                System.out.println("O ficheiro especificado não foi encontrado: " + ex);
            }
            //Verifica se o Buffer não está a null e foi bem inicializado
            if (in != null) {
                //String que irá conter cada linha do problema a ser processada
                String leitura;
                try {
                    this.Name = in.readLine();
                    this.Comment = in.readLine();
                    this.Type = in.readLine();
                    this.Dimension = in.readLine();
                    this.Edge_Weight_Type = in.readLine();
                    // Le o "NODE_COORD_SECTION" onde indica onde comecam os dados
                    in.readLine();
                } catch (IOException ex) {
                    System.out.println("Erro de leitura do ficheiro de especificação do problema: " + ex);
                }
                try {
                    //Cilco que acaba apenas quando acabar o ficheiro de especificação do problema
                    while (!(leitura = in.readLine()).equals("EOF")) {
                        City cidade = new City();
                        String[] arrayDados = leitura.split(" ");
                        int indexDados = 0;
                        // Passar espacos em branco
                        while (arrayDados[indexDados].equals("")) {
                            indexDados++;
                        }
                        cidade.Index = Integer.parseInt(arrayDados[indexDados]);
                        // Passar espacos em branco
                        do {
                            indexDados++;
                        } while (arrayDados[indexDados].equals(""));
                        cidade.X = Integer.parseInt(arrayDados[indexDados]);
                        // Passar espacos em branco
                        do {
                            indexDados++;
                        } while (arrayDados[indexDados].equals(""));
                        cidade.Y = Integer.parseInt(arrayDados[indexDados]);
                        //Adiciona a cidade já ao arraylist de cidades do problema
                        this.Cidades.add(cidade);
                    }
                } catch (IOException ex) {
                    System.out.println("Erro de leitura do ficheiro: " + ex);
                }
            } else {
                System.out.println("Erro de leitura do ficheiro.");
            }
        }//Caso o caminho não esteja especificado, lança um excepção
        else {
            try {
                throw new Exception("Caminho para o ficheiro que contem o problema TSP inválido.");
            } catch (Exception ex) {
            }
        }
    }

    /**
     * Método que devolve a matriz de custos depois de todas as cidades estarem lidas para o problema
     * @return Matriz de custo entre as cidades lidas do problema
     */
    private double[][] convertToCostMatrix() {
        double[][] __costMatrix = new double[this.Cidades.size()][this.Cidades.size()];
        // Calcula a distancia para todas as cidades e devolve em forma de matriz de custos
        // Atenção: O custo de ir para a mesma cidade, CidadeA -> CidadeA, tem custo de um e sera
        // acrescentado ao fitness, mas segundo a formula dada pelo site é assim que se deve calcular
        // as distancias, se mais tarde vir que essa distancia deve ser 0 então deve se alterar aqui neste
        // ciclo
        for (int __indexCidadeA = 0; __indexCidadeA < this.Cidades.size(); __indexCidadeA++) {
            for (int __indexCidadeB = 0; __indexCidadeB < this.Cidades.size(); __indexCidadeB++) {
                __costMatrix[__indexCidadeA][__indexCidadeB] = this.distanciaEntreCidades(
                        this.Cidades.get(__indexCidadeA),
                        this.Cidades.get(__indexCidadeB));
            }
        }
        return __costMatrix;
    }
    
    /**
     * Método que faz a definição das matrizes de custo para cada cidade especificada
     */
    private void inicializaCostMatrix(){
        //Matriz de custo para as cidades especificadas
        double[][] costMatrix = this.convertToCostMatrix();
        //Define a matriz em todas as cidades do problema
        for (int i = 0; i < this.Cidades.size(); i++) {
            this.Cidades.get(i).costMatrix = costMatrix;
        }
    }

    /**
     * Método da classe que recebe duas cidades e devolve a distância entre as mesmas
     * @param cidadeA - Cidade A para ser calculada a distância
     * @param cidadeB - Cidade B para ser calculada a distância
     * @return Distância entre as duas cidades recebidas por parametro
     */
    public double distanciaEntreCidades(City cidadeA, City cidadeB) {
        double xd = cidadeA.X - cidadeB.X;
        double yd = cidadeA.Y - cidadeB.Y;
        return Math.sqrt(xd * xd + yd * yd);
    }

    public String getInfo() {
        return "Não implementado pela Genetic";
    }

    public boolean setParameters(String parameters) {
        return false;
    }
}
