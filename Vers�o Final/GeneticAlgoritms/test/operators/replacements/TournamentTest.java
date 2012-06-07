package operators.replacements;

import genetics.algorithms.OnesMax;
import genetics.Individual;
import genetics.Population;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pedro Alves
 */
public class TournamentTest {

    Population popEntrada;
    int sizePopulation = 200;
    int sizeGenome = 1;
    int sizeGenotype = 1;
    int sizeAllelo = 10;
    int DimSelectedPopulation = 5;

    public TournamentTest() {
    }

    @Test
    public void testExecute() {
//####################################################################################################        
//########## Testa tamanho população de saida == (populações de entrada)/2 ###########################
//####################################################################################################


        System.out.println("-- Teste ao operador Tournament --");
        /**
         * População de pais, com 100 individuos e com o alelo a 10.
         */
        Population parents = new Population(100, 1, 1, 10, new genetics.algorithms.OnesMax(), true);
        /**
         * População de filhos, com 100 individuos e com o alelo a 10.
         */
        Population sons = new Population(100, 1, 1, 10, new genetics.algorithms.OnesMax(), true);
        /**
         * Criação do torneio de 100 elementos 2 a 2
         */
        Tournament instance = new Tournament(2);
        /**
         * Execução do torneio
         */
        Population pf = instance.execute(parents, sons);
        assertEquals(pf.getPopulation().size(), parents.getSizePopulation());

//#########################################################################################################       
//########### Testa se os individuos da população final estão contidos nas populações de entrada###########
//#########################################################################################################
        //
        popEntrada = new Population(sizePopulation, sizeGenome, sizeGenotype, sizeAllelo, new OnesMax());
        //pais
        for (int i = 0; i < parents.getSizePopulation(); i++) {
            Individual indi = parents.getIndividual(i);
            popEntrada.addIndividual(indi);
        }
        //filhos
        for (int i = 0; i < sons.getSizePopulation(); i++) {
            Individual indi = sons.getIndividual(i);
            popEntrada.addIndividual(indi);
        }

        int tpf = pf.getSizePopulation();
        for (int j = 0; j < tpf; j++) {
            for (int i = 0; i < popEntrada.getSizePopulation(); i++) {
                if (pf.getIndividual(0).fitness() == popEntrada.getIndividual(i).fitness()) {
                    pf.getPopulation().remove(0);
                    pf.DecSize();
                    break;
                }
            }
        }


        int t = pf.getSizePopulation();
        assertTrue(t == 0);
    }
}