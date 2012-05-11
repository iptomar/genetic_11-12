package genetics.algorithms;

/**
 *
 * @author Chorinca-Notebook
 */
public class K100 extends KnapSack {

    /**
     * Construtor por defeito
     */
    public K100() {
        this(ModeFunction.RANDOM);
    }

    /**
     * Construtor como o modo de funcionamento da penalização
     * @param modeFunction Tipo de penalização
     */
    public K100(ModeFunction modeFunction) {
        super._modeFunction = modeFunction;
        // Data que vai gerar a tabela com os valores e pesos
        // Tamanho Allelo: 100
        // Maximo Saco: 1352
        // Valores: ...
        // Pesos: ...
        super._parseStringData("100 1352 26 10 13 39 34 43 52 24 27 17 35 47 18 25 8 35 36 36 21 16 27 26 34 15 12 35 40 47 14 55 28 40 51 51 8 41 28 20 11 42 11 23 49 13 54 49 40 18 45 13 28 12 51 4 15 48 33 43 32 39 36 7 44 11 45 36 27 52 33 11 27 6 22 52 36 7 45 13 17 52 19 47 15 38 35 23 16 15 30 42 34 34 39 21 18 37 41 13 48 36 24 8 8 38 33 40 50 21 24 15 33 45 16 22 7 32 33 33 16 14 23 25 33 14 7 32 36 46 11 50 26 38 46 49 7 36 26 19 6 38 10 18 45 9 50 47 38 15 42 11 24 9 49 2 13 44 31 40 28 35 32 5 39 7 43 35 23 48 28 8 26 4 21 47 33 2 42 11 15 49 16 44 12 33 33 22 15 14 28 39 31 30 37 19 14 33 39 10 46 32");
    }
    
    @Override
    public String getInfo() {
        return "<p>K100 Best Fitness - 1561</p>"
                +"<p>Solver para o K100: setParameters(«num Individuos» «num Cromossomas» «num Genes Cromossoma» «tamanho array allelo (OBRIGATORIO SER 100)» K100)</p>"
                +"<p>Ex: setParameters(1000 1 1 100 K100) - É criado um solver que terá uma </p><p>população inicial de 1000 individuos, cada um com um cromossoma, cada cromossoma</p><p>com um gene e cada gene contem um array de tamanho 100. Os individuos são do tipo</p><p>K100.</p>";
    }
}