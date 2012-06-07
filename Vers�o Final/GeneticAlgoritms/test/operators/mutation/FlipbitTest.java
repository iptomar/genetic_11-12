package operators.mutation;

import genetics.algorithms.OnesMax;
import genetics.Population;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pedro Alves
 */
public class FlipbitTest {
    
    
    int sizePopulation = 5;
    int sizeGenome = 1;
    int sizeGenotype = 1;
    int sizeAllelo = 10;
    int DimSelectedPopulation = 5;
    int testeAleatorio = 100;
    double probabilidade = 0.75;
    Population p, popEntrada, popSaida;
    Flipbit fb;
    
    

    
    public FlipbitTest() {
    }


    
    
//##########################################################################################################################
//###################$$$$$ Testar se existe alteração dos bits dos individuos com probabilidade a 100% $$$$$################
//##########################################################################################################################
    
    @Test
    public void testAlteracao(){
       
        p = new Population(sizePopulation, sizeGenome, sizeGenotype, sizeAllelo, new OnesMax());
        
        popEntrada=p;
        //probabilidade de 100%
        probabilidade=1;
        fb = new  Flipbit(probabilidade);
        
        popSaida=fb.execute(p);
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
        fb = new  Flipbit(probabilidade);
        
        popSaida=fb.execute(p);
        
        
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
        fb = new  Flipbit(probabilidade);
        
        popSaida=fb.execute(p);
        //compara o tamanho das populações
        assertTrue(popEntrada.getSizePopulation() ==popSaida.getSizePopulation());
     
    }
    
//***************************************************************************************************************************
    
    
}
