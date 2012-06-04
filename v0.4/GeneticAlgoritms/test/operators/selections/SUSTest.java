/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package operators.selections;

import utils.PopulationUtils;
import genetics.Population;
import genetics.algorithms.OnesMax;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pedro Alves
 */
public class SUSTest {
 
    /**
     * População pai
     */
    private Population p;
    private SUS s;
    int sizePopulation = 10;
    int sizeGenome = 1;
    int sizeGenotype = 1;
    int sizeAllelo = 10;
    int DimSelectedPopulation = 5;
    int testeAleatorio = 100;
    
    public SUSTest() {
    }
    
<<<<<<< HEAD
    @BeforeClass
    public static void setUpClass() throws Exception {
    }
    
    @AfterClass
    public static void tearDownClass() throws Exception {
    }
=======
>>>>>>> origin/master

    /**
     * Test of execute method, of class SUS.
     */
    @Test
    public void testExecute() {
        //população gerada
        p = new Population(sizePopulation, sizeGenome, sizeGenotype, sizeAllelo, new OnesMax());
        //SUS com a dimensão a ser seleccionada
        s = new SUS(DimSelectedPopulation);
        //executa o SUS com a população que foi gerada
        s.execute(p);
        //soma total de fitness de todos os individuos da população
        int maxFitness = (int) PopulationUtils.getFitnessTotal(p);
        //variavel com o valor que é gerado aleatoriamente no SUS
        double pontoAleatorio = s.getPonteiro();
//************ condição que verifica se o ponto aleatorio está dentro no intervalo ************************************
        assertTrue(pontoAleatorio >= 0 || pontoAleatorio <= maxFitness);

//**********************************************************************************************************************
//**********************************************************************************************************************
        //TESTE AO NUMERO ALEATORIO QUE É GERADO PARA O VALOR ALEATORIO - Conta o numero de vezes em que sai os numeros

        
        maxFitness = 100;
        double[] ndigits = new double[maxFitness];
        
        int nAleatorio = 10000;
        
        for (int i = 0; i < nAleatorio; i++) {
            s = new SUS(DimSelectedPopulation);
            s.execute(p);
            ndigits[(int) s.getPonteiro()] += 1;
        }


        // Print the results
        for (int i = 0; i < maxFitness; i++) {
            System.out.println(i + ": " + ndigits[i]);
        }
        //**********************************************************************************************************************
//**********************************************************************************************************************
        //TESTE AO NUMERO ALEATORIO QUE É GERADO PARA O VALOR ALEATORIO - Testa se há sequencia      
        maxFitness = 100;
        double[] ndigits1 = new double[maxFitness];
        double[] ndigits2 = new double[maxFitness];
        nAleatorio = 10000;
        
        for (int i = 0; i < nAleatorio; i++) {
            s = new SUS(DimSelectedPopulation);
            s.execute(p);
            ndigits1[(int) s.getPonteiro()] += 1;
        }
        for (int i = 0; i < nAleatorio; i++) {
            s = new SUS(DimSelectedPopulation);
            s.execute(p);
            ndigits2[(int) s.getPonteiro()] += 1;
        }
        
        
        assertTrue(ndigits1 != ndigits2);



//****************************************************************************************************************
//****************************************************************************************************************
//  teste ao numero de individuos da população que sai é < ou = ao numero de individuos da população que entra 
        p = new Population(sizePopulation, sizeGenome, sizeGenotype, sizeAllelo, new OnesMax());
        
        int popEntrada = p.getSizePopulation();
        int popSaida = s.execute(p).getSizePopulation();
        
        assertTrue(popSaida <= popEntrada);

//*****************************************************************************************************************
// testar se os individuos da população de saida existem na população de origem

        Population pi = new Population(sizePopulation, sizeGenome, sizeGenotype, sizeAllelo, new OnesMax());
        Population pf = s.execute(pi);



        int tpf = pf.getSizePopulation();
        for (int j = 0; j < tpf; j++) {
            for (int i = 0; i < pi.getSizePopulation(); i++) {
                if (pf.getIndividual(0).fitness() == pi.getIndividual(i).fitness()) {
                    pf.getPopulation().remove(0);
                    pf.DecSize();
                    break;
                }
            }
        }
        
        
        
        
        boolean t = false;
        if (pf.getSizePopulation() == 0) {
            t = true;
        }
        
        assertTrue(t);
        //assertArrayE    assertTrue(t);quals(p.getIndividual(i), s.execute(p).getIndividual(j));



//****************************************************************************************************************
//****************************************************************************************************************
//                                            
//  teste ao numero de individuos da população que sai é = ao numero de individuos que definimos inicialmente para serem seleccionados
        p = new Population(sizePopulation, sizeGenome, sizeGenotype, sizeAllelo, new OnesMax());
        int popSaida2 = s.execute(p).getSizePopulation();
        
        assertTrue(popSaida == DimSelectedPopulation);
        
        
        
        
        
        
        
        
        
        
    }
}
