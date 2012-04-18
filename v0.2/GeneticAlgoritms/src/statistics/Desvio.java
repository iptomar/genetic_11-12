/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package statistics;

/**
 *
 * @author ASUS
 */
public class Desvio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DesvioPadrao dv = new DesvioPadrao();
        System.out.println("Variância: " + dv.getVariancia());
        System.out.println("Média:" + dv.getMedia());
        System.out.println("Desvio Padrão: " + dv.getDesvioPadrao());
        
    }
}
