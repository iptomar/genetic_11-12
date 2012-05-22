package utils;

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
 * Classe City que recebe as cidades do problema do caixeiro viajante para que a matriz de
 * custo possa ser calculada para o problema em questão.
 * @author Ruben Felix <Ruben.Felix@gmail.com>
 */
public class City {

    /**
     * Index da cidade - Indica a ordem da mesma no problema
     */
    public int Index = 0;
    /**
     * coordenadas X e Y que serão usadas para gerar a distância entre as cidadaes
     */
    public double X = 0;
    public double Y = 0;

    /**
     * Construtor onde apenas é inicializado a super classe (Individual) da classe City
     */
    public City() {
    }

    /**
     * Construtor da classe onde são passados por parametro o Index da cidade e a posição X e Y.
     * @param Index - Index da cidade
     * @param x - Posição X da cidade
     * @param y - Posição Y da cidade
     */
    public City(int Index, double x, double y) {
        this.X = x;
        this.Y = y;
        this.Index = Index;
    }
}
