package genetics.algorithms;

/**
 *
 * @author Chorinca-Notebook
 */
public class K50 extends KnapSack {

    /**
     * Construtor por defeito
     */
    public K50() {
        this(ModeFunction.RANDOM);
    }

    /**
     * Construtor como o modo de funcionamento da penalização
     * @param modeFunction Tipo de penalização
     */
    public K50(ModeFunction modeFunction) {
        // Data que vai gerar a tabela com os valores e pesos
        // Tamanho Allelo: 50
        // Maximo Saco: 1473
        // Valores: ...
        // Pesos: ...
        super("50 1473 3 41 22 30 45 99 75 76 79 77 41 98 31 28 58 32 99 48 20 3 81 17 3 62 39 76 94 75 44 63 35 11 21 45 43 46 26 2 53 37 32 78 74 66 61 51 11 85 90 40 94 70 90 97 54 31 82 97 1 58 96 96 87 53 62 89 68 58 81 83 67 41 50 58 61 45 64 55 12 87 32 53 25 59 23 77 22 18 64 85 14 23 76 81 49 47 88 19 74 31",
                modeFunction,
                KnapSack.PENALTY_ORDER_QUADRATIC);
    }

    @Override
    public String getInfo() {
        return "<p>K50 Best Fitness - 1920</p>"
                + "<p>Solver para o K50: setParameters(«num Individuos» «num Cromossomas» «num Genes Cromossoma» «tamanho array allelo (OBRIGATORIO SER 50)» K50)</p>"
                + "<p>Ex: setParameters(1000 1 1 50 K50) - É criado um solver que terá uma </p>"
                + "<p>população inicial de 1000 individuos, cada um com um cromossoma, cada cromossoma</p>"
                + "<p>com um gene e cada gene contem um array de tamanho 50. Os individuos são do tipo</p>"
                + "<p>K50.</p>";
    }
}