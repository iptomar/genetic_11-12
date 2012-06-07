package utils;

import java.io.*;
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
 * Classe que contem as denições do problema do travelling salesman problem (TSP) ou caixeiro viajante.
 * @author Ruben Felix <Ruben.Felix@gmail.com>
 */
public class TSPProblem {

    public static void main(String[] args) {
        TSPProblem __d = new TSPProblem();
        __d.setFilename("C:\\tsp\\ulysses16.tsp");
        __d.read();
        __d.getCostMatrix();
    }
    
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
        dataSplit(problemFile);
    }

    public TSPProblem() {
    }
    /**
     * Método que fará a leitura das especificações do problema do ficheiro especificado.
     * O método retira as informações do problema, bem como das suas cidades e adiciona ao arraylist
     * de cidades que será a população inicial do problema.
     */
    public void read() {
        //Verifica se a variavel que contem o caminho para o ficheiro com as especificações do problema não é nulo nem está vazio
        if (!this.filename.equals("") && this.getFilename() != null) {
            BufferedReader in = null;
            try {
                /**
                 * Objecto de leitura do ficheiro especificado
                 */
                in = new BufferedReader(new FileReader(new File(this.getFilename())));
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
                    while (!(leitura = in.readLine()).contains("EOF")) {
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
                        cidade.X = Double.parseDouble(arrayDados[indexDados]);
                        // Passar espacos em branco
                        do {
                            indexDados++;
                        } while (arrayDados[indexDados].equals(""));
                        cidade.Y = Double.parseDouble(arrayDados[indexDados]);
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
    public double[][] getCostMatrix() {
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

    /**
     * Método que fará a separação da informação através da string recebida por parametro de forma
     * a que as cidades possam ser adicionadas e definidas.
     * @param problemData - String que contem a definição do problema
     */
    private void dataSplit(String problemData) {
        //Array de strings que contem as linhas da definição do problema
        String lines[] = problemData.split(";");
        //As seis primeiras linhas serão processadas manualmente
        //Linha 1 - Nome do problema
        String dataLine[] = lines[0].split(":");
        this.Name = dataLine[1];
        //Linha 2 - Comentário do problema
        dataLine = lines[1].split(":");
        this.Comment = dataLine[1];
        //Linha 3 - Tipo de problema
        dataLine = lines[2].split(":");
        this.Type = dataLine[1];
        //Linha 4 - Dimensão do problema
        dataLine = lines[3].split(":");
        this.Dimension = dataLine[1];
        //Linha 5 - Edge Weight Type
        dataLine = lines[4].split(":");
        this.Edge_Weight_Type = dataLine[1];

        //O resto do problema será a definição de todas as cidades que fazem parte do problema
        for (int i = 6; i < lines.length-1; i++) {
            //Split de cada linha por espaço
            dataLine = lines[i].split(" ");
            
            /**
             * ****************************************************
             * *** CILCOS FOR PARA PROCURA DE ESPAÇOS EM BRANCO ***
             * *** JÀ QUE OS MESMOS ESTÃO PRESENTES NA MAIORIA ****
             * ******* DOS PROBLEMAS DEFINIDOS EM FICHEIRO ********
             */
            int indexIndex = 0;
            for (int j = 0; j < dataLine.length; j++) {
                if(!dataLine[j].equals("")){
                    indexIndex = j;
                    break;
                }
            }
            
            
            int indexX = indexIndex + 1;
            for (int j = indexX; j < dataLine.length; j++) {
                if(!dataLine[j].equals("")){
                    indexX = j;
                    break;
                }
            }
            
            
            int indexY = indexX + 1;
            for (int j = indexY; j < dataLine.length; j++) {
                if(!dataLine[j].equals("")){
                    indexY = j;
                    break;
                }
            }
            //Nova cidade com as definições correctas
            City newCidade = new City();
            newCidade.Index = Integer.parseInt(dataLine[indexIndex]);
            newCidade.X = Double.parseDouble(dataLine[indexX]);
            newCidade.Y = Double.parseDouble(dataLine[indexY]);
            this.Cidades.add(newCidade);
        }
    }
    
    //*********************************************************************************
    //*****************************Métodos para Reflection*****************************
    //*********************************************************************************
    
    public String getInfo() {
        return "<p>TSP Problem</p>"
                + "<p>Classe que receberá uma string com a informação do problema.</p>"
                + "<p>Esta string deverá vir formatada com os problemas dos exemplos dados pelo professor, sendo que a linha seguinte é definida por ;.</p>"
                + "<p>Formato da String:</p>"
                + "<p>NAME : a280;COMMENT : drilling problem (Ludwig);TYPE : TSP;DIMENSION: 280;EDGE_WEIGHT_TYPE : EUC_2D;NODE_COORD_SECTION;1 288 149;2 288 129;3 270 133;4 256 141;5 256 157;6 246 157;7 236 169;8 228 169;9 228 161;10 220 169;</p>"
                + "<p>Os primeiros campos serão a definição do problema e então depois serão as cidades do problema em questão. A cidade é composta por um index, uma posição X e uma posição Y.</p>"
                + "<p>Depois de ser processada a string, a classe detem então todas as cidades do problema e será capaz de devolver a matriz de custo do problema através do método getCostMatrix(). Esta matriz é fundamental para a</p> "
                + "<p>inicialização dos individuos do tipo TSP já que a mesma tem de estar presente em todos afim de o fitness de cada um ser calculado.</p>";
    }

    public boolean setParameters(String parameters) {
        try{
            //Faz o split da informação e devolve true ao utilizador
            this.dataSplit(parameters);
            return true;
        }
        catch(Exception ex){
            //Caso algo corra mal, devolve false
            return false;
        }   
    }
    //*********************************************************************************
    //*********************************************************************************
    //*********************************************************************************

    /**
     * @return the filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * @param filename the filename to set
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }
}
