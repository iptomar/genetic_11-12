/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package statistics;

/**
 *
 * @author ASUS
 */
public class DesvioPadrao {

    double soma = 0;
    double somatorio;
    double media;
    double array[] = {1, 4, 6, 9, 2};

    //metodo que devolve a media
    public double getMedia() {
        media = 0;
        soma = 0;

        for (int counter = 0; counter < array.length; counter++) {

            soma += array[counter];
        }
        media = soma / array.length;
        return media;
    }

  //metodo que devolve a variância
    public double getVariancia() {
        soma = 0;
        somatorio = 0;
        //Calcula o somatório
        for (int counter = 0; counter < array.length; counter++) {
            somatorio += Math.pow(array[counter] - getMedia(), 2);
        }
        double variancia = (somatorio / (array.length - 1));
        return variancia;
    }

    //metodo que devolve o desvio padrão
    public double getDesvioPadrao() {
        return Math.sqrt(getVariancia());
    }

    
    public double[] getArray() {
        return array;
    }
    
    public void setArray(double[] array) {
        this.array = array;
    }
}
