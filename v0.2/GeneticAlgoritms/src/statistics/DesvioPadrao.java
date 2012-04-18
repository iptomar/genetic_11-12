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
    private Double[] array;

    public DesvioPadrao() {
        array = new Double[] { 2.0, 5.0, 7.0, 9.0, 10.0 };
    }
    
    //metodo que devolve a media
    public Double getMedia() {
        media = 0;
        soma = 0;

        for (int counter = 0; counter < getArray().length; counter++) {
            soma += getArray()[counter];
        }
        media = soma / getArray().length;
        return media;
    }

    //metodo que devolve a variância
    public Double getVariancia() {
        soma = 0;
        somatorio = 0;
        //Calcula o somatório
        for (int counter = 0; counter < getArray().length; counter++) {
            somatorio += Math.pow(getArray()[counter] - getMedia(), 2);
        }
        double variancia = (somatorio / (getArray().length - 1));
        return variancia;
    }

    //metodo que devolve o desvio padrão
    public Double getDesvioPadrao() {
        return Math.sqrt(getVariancia());
    }

    
    public Double[] getArray() {
        return array;
    }
    
    public void setArray(Double[] array) {
        this.array = array;
    }
}
