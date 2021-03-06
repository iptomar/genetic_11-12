package utils;

import genetics.Population;
import java.util.Random;

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
 * Classe que guardará a informação referente a cada item da mochila.
 * @author Ruben Felix <Ruben.Felix@gmail.com>
 */
public class ItemOld {
    
    private int valor;
    private int peso;
    private Random random;
    
    /**
     * Construtor por defeito da classe. São passados por parametro o peso máximo da mochila
     * e o valor máximo de um item para que o item gerado aleatóriamente não ultrapasse esses
     * valores.
     * @param pesoMax - Peso máximo da mochila
     * @param valorMax - Valor máximo que um item da mochila poderá ter
     */
    public ItemOld(int pesoMax, int valorMax){
        this.peso = Population.RANDOM_GENERATOR.nextInt(pesoMax);;
        this.valor = Population.RANDOM_GENERATOR.nextInt(valorMax);;
    }
    
    /**
     * Construtor da classe que é passado por parametro o peso do item e o valor do mesmo
     * O boolean é apenas para diferenciar construtores, não sendo importante
     * @param PesoItem - Peso do item
     * @param ValorItem - Valor do item
     * @param b - False ou true, apenas para diferenciar construtores
     */
    public ItemOld(int PesoItem, int ValorItem, boolean b){
        this.peso = PesoItem;
        this.valor = ValorItem;
    }

    /**
     * Construtor da classe onde não não precisos parametros para a instanciar
     */
    public ItemOld(){}
    
    /**
     * Método que devolve o valor do item
     * @return the valor - Valor do item
     */
    public int getValor() {
        return valor;
    }

    /**
     * Método que permite definir o valor do item
     * @param valor  - Valor do item a ser definido
     */
    public void setValor(int valor) {
        this.valor = valor;
    }

    /**
     * Método que devolve o peso do item
     * @return the peso - Peso do item
     */
    public int getPeso() {
        return peso;
    }

    /**
     * Método que define o peso do item
     * @param peso - Peso a ser definido para o item
     */
    public void setPeso(int peso) {
        this.peso = peso;
    }
    
    @Override
    public boolean equals(Object o){
        if((this.peso == ((ItemOld)o).getPeso()) && (this.valor == ((ItemOld)o).getValor())) return true;
        else return false;
    }
}
