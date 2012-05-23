package genetics.algorithms;

/**
 *
 * @author Chorinca-Notebook
 */
public class K5 extends KnapSack {

    /**
     * Construtor por defeito
     */
    public K5() {
        this(ModeFunction.RANDOM);
    }

    /**
     * Construtor como o modo de funcionamento da penalização
     * @param modeFunction Tipo de penalização
     */
    public K5(ModeFunction modeFunction) {
        super._modeFunction = modeFunction;
        // Data que vai gerar a tabela com os valores e pesos
        // Tamanho Allelo: 5
        // Maximo Saco: 15
        // Valores: 4 2 1 10 2
        // Pesos: 12 2 1 4 1
        super._parseStringData("5 15 4 2 1 10 2 12 2 1 4 1");
    }
    
   
}