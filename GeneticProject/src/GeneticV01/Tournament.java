/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GeneticV01;

import java.util.ArrayList;

/**
 *
 * @author Catarina Neves 13877
 * 
 * 
 * Classe que realiza torneio entre elementos de uma população, dois a dois
 */
public class Tournament {

    //lista que guarda os melhores, escolhidos após competirem
    private ArrayList<Individual> popBest;
    //lista que recebe a populacao que irá competir
    private ArrayList<Individual> receiver; 

    //compara os individuos 2 a 2, escolhe o melhor e guarda em popBest
    public ArrayList<Individual> Tournament(ArrayList<Individual> receiver) {
        try {
            //incrementa de 2 em 2 para não comparar elementos repetidos
            for (int i = 0; i <= receiver.size(); i = i + 2) { 
                //primeiro elemento a comparar
                int first = receiver.get(i).getFitness();       
                //segundo elemento a comparar - elemento seguinte na lista da populacao a comparar - lista receiver
                int second = receiver.get(i + 1).getFitness();
                //comparacao entre os 2 elementos
                if (first > second) {
                    //se o primeiro elemento tiver um fitness mais elevado, este é guardado na lista popBest
                    popBest.add(first, null);

                } else {
                    //se o segundo elemento tiver um fitness mais elevado, este é guardado na lista popBest
                    popBest.add(second, null);
                }

            }
        } catch (Exception e) {
            
        }
        return popBest;
    }
}
