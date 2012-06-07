package operators.recombinations;

import genetics.algorithms.OnesMax;
import genetics.Population;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pedro Alves
 */
public class CrossoverTest {
    
    public CrossoverTest() {
    }

    
    
    
    int sizePopulation = 5;
    int sizeGenome = 1;
    int sizeGenotype = 1;
    int sizeAllelo = 10;
    int DimSelectedPopulation = 5;
    int testeAleatorio = 100;
    double probabilidade = 0.75;
    Population p, popEntrada, popSaida;
    Crossover co;
    

//##########################################################################################################################
//###################$$$$$ Testar se existe alteração dos bits dos individuos com probabilidade a 100% $$$$$################
//##########################################################################################################################
    
    @Test
    public void testAlteracao(){
       
        p = new Population(sizePopulation, sizeGenome, sizeGenotype, sizeAllelo, new OnesMax());
        
        popEntrada=p;
        //probabilidade de 100%
        probabilidade=1;
        co = new  Crossover(probabilidade,1);
        
        popSaida=co.execute(p);
        //vai ver se as populações são diferentes, uma vez que foram mutadas com probabilidade a 100%
        assertNotSame(popEntrada, popSaida);
     
    }
    
//***************************************************************************************************************************
    
    
//##########################################################################################################################
//###################***** Testar se existe alteração dos bits dos individuos com probabilidade a 0% *****##################
//##########################################################################################################################
    
    @Test
    public void testIgualdade(){
       
        p = new Population(sizePopulation, sizeGenome, sizeGenotype, sizeAllelo, new OnesMax());
        
        popEntrada=p;
        //probabilidade de 0%
        probabilidade=0*0;
        co = new  Crossover(probabilidade,1);
        
        popSaida=co.execute(p);
        
        
        //vai ver se as populações são iguais, uma vez que foram mutadas com probabilidade a 0%
        assertEquals(popEntrada.toString() ,popSaida.toString());
        
        
        //System.out.println(""+popEntrada.toString());
        //System.out.println(""+popSaida.toString());
     
    }
    
//***************************************************************************************************************************
    
    
//##########################################################################################################################
//###################!!!!!! Testa se a população de entrada tem o mesmo tamanho da população de saída   !!!!!!!#############
//##########################################################################################################################
    
    @Test
    public void testTamanhoPopulacao(){
       
        p = new Population(sizePopulation, sizeGenome, sizeGenotype, sizeAllelo, new OnesMax());
        
        popEntrada=p;
        //probabilidade de 0%
        probabilidade=0.75;
        co = new  Crossover(probabilidade,1);
        
        popSaida=co.execute(p);
        //compara o tamanho das populações
        assertTrue(popEntrada.getSizePopulation() ==popSaida.getSizePopulation());
     
    }
    
//***************************************************************************************************************************
    // Vê se com a probabilidade a 0% e com 0 cortes a população se mantem igual    
    @Test
    public void testCorte1(){
       
        p = new Population(sizePopulation, sizeGenome, sizeGenotype, sizeAllelo, new OnesMax());
        
        popEntrada=p;
        //probabilidade de 0%
        probabilidade=0;
        co = new  Crossover(probabilidade,0);
        
        popSaida=co.execute(p);
        //compara o tamanho das populações
        assertTrue(popEntrada.toString().equals(popSaida.toString()));
     
    }
    
//***************************************************************************************************************************
   // Vê se com a probabilidade a 0% e com 1 corte a população se mantem igual    
    @Test
    public void testCorte2(){
       
        p = new Population(sizePopulation, sizeGenome, sizeGenotype, sizeAllelo, new OnesMax());
        
        popEntrada=p;
        //probabilidade de 0%
        probabilidade=0;
        co = new  Crossover(probabilidade,1);
        
        popSaida=co.execute(p);
        //compara o tamanho das populações
        assertTrue(popEntrada.toString().equals(popSaida.toString()));
     
    }
    
//***************************************************************************************************************************
    // Vê se com a probabilidade a 100% e com 0 cortes a população se mantem igual    
    @Test
    public void testCorte3(){
       
        p = new Population(sizePopulation, sizeGenome, sizeGenotype, sizeAllelo, new OnesMax());
        
        popEntrada=p;
        //probabilidade de 100%
        probabilidade=1;
        co = new  Crossover(probabilidade,0);
        
        popSaida=co.execute(p);
        //compara o tamanho das populações
        assertTrue(popEntrada.toString().equals(popSaida.toString()));
     
    }
    
//***************************************************************************************************************************
    // Vê se com a probabilidade a 100% e com 1 corte a população fica diferente    
    @Test
    public void testCorte4(){
       
        p = new Population(sizePopulation, sizeGenome, sizeGenotype, sizeAllelo, new OnesMax());
        
        popEntrada=p;
        //probabilidade de 100%
        probabilidade=1;
        co = new  Crossover(probabilidade,1);
        
        popSaida=co.execute(p);
        //compara o tamanho das populações
        assertFalse(popEntrada.toString().equals(popSaida.toString()));
     
    }
    
//***************************************************************************************************************************
   
}
