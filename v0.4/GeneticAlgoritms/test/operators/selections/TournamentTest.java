package operators.selections;

import genetics.algorithms.OnesMax;
import genetics.Population;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pedro Alves
 */
public class TournamentTest {

    private Population p;
    private Tournament tour;
    int sizePopulation = 10;
    int sizeGenome = 1;
    int sizeGenotype = 1;
    int sizeAllelo = 10;
    int DimSelectedPopulation = 5;
    int testeAleatorio = 100;
    int sizeTorneio = 2;

    @Test
    public void testExecute() {
    }

    @Test
    public void testIndividuosSaida() {

//###################################################################################################################        
//################Vê se cada individuo está contido na pop de entrada################################################
//###################################################################################################################
        tour = new Tournament(DimSelectedPopulation, sizeTorneio);
        Population pi = new Population(sizePopulation, sizeGenome, sizeGenotype, sizeAllelo, new OnesMax());
        Population pf = tour.execute(pi);
        //população gerada




        //percorrer a pop de saida, e ver se cada individuo está contido na pop de entrada
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
    }

//****************************************************************************************************************************
    @Test
    public void testTamanhoPopSaida() {
//###################################################################################################################        
//################   vê se a população de saida tem o tamanho que tinha sido seleccionado    ########################
//###################################################################################################################
        
        tour = new Tournament(DimSelectedPopulation, sizeTorneio);
        Population pi = new Population(sizePopulation, sizeGenome, sizeGenotype, sizeAllelo, new OnesMax());
        Population pf = tour.execute(pi);
        //população gerada
        
        int tpf = pf.getSizePopulation();
        assertTrue(tpf==DimSelectedPopulation);
        
        
        
        
    }
}
