/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package operators.mutation;

import genetics.Population;
import genetics.algorithms.TSP;
import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import utils.TSPProblem;

/**
 *
 * @author Simon
 */
public class InvertionTest {

    private Population p;
    private Invertion inv;
    int sizePopulation = 100;
    int sizeGenome = 1;
    int sizeGenotype = 1;
    int sizeAllelo = 280;
    double probabilidade = 0.75;
    int testeAleatorio = 10;
    

    public InvertionTest() {
        
//******************** Ver se todos os números são diferentes em cada individuo  ****************************************************************

        TSPProblem matriz = new TSPProblem("NAME : a280;COMMENT : drilling problem (Ludwig);TYPE : TSP;DIMENSION: 280;EDGE_WEIGHT_TYPE : EUC_2D;NODE_COORD_SECTION;1 288 149;2 288 129;3 270 133;4 256 141;5 256 157;6 246 157;7 236 169;8 228 169;9 228 161;10 220 169;11 212 169;12 204 169;13 196 169;14 188 169;15 196 161;16 188 145;17 172 145;18 164 145;19 156 145;20 148 145;21 140 145;22 148 169;23 164 169;24 172 169;25 156 169;26 140 169;27 132 169;28 124 169;29 116 161;30 104 153;31 104 161;32 104 169;33 90 165;34 80 157;35 64 157;36 64 165;37 56 169;38 56 161;39 56 153;40 56 145;41 56 137;42 56 129;43 56 121;44 40 121;45 40 129;46 40 137;47 40 145;48 40 153;49 40 161;50 40 169;51 32 169;52 32 161;53 32 153;54 32 145;55 32 137;56 32 129;57 32 121;58 32 113;59 40 113;60 56 113;61 56 105;62 48 99;63 40 99;64 32 97;65 32 89;66 24 89;67 16 97;68 16 109;69 8 109;70 8 97;71 8 89;72 8 81;73 8 73;74 8 65;75 8 57;76 16 57;77 8 49;78 8 41;79 24 45;80 32 41;81 32 49;82 32 57;83 32 65;84 32 73;85 32 81;86 40 83;87 40 73;88 40 63;89 40 51;90 44 43;91 44 35;92 44 27;93 32 25;94 24 25;95 16 25;96 16 17;97 24 17;98 32 17;99 44 11;100 56 9;101 56 17;102 56 25;103 56 33;104 56 41;105 64 41;106 72 41;107 72 49;108 56 49;109 48 51;110 56 57;111 56 65;112 48 63;113 48 73;114 56 73;115 56 81;116 48 83;117 56 89;118 56 97;119 104 97;120 104 105;121 104 113;122 104 121;123 104 129;124 104 137;125 104 145;126 116 145;127 124 145;128 132 145;129 132 137;130 140 137;131 148 137;132 156 137;133 164 137;134 172 125;135 172 117;136 172 109;137 172 101;138 172 93;139 172 85;140 180 85;141 180 77;142 180 69;143 180 61;144 180 53;145 172 53;146 172 61;147 172 69;148 172 77;149 164 81;150 148 85;151 124 85;152 124 93;153 124 109;154 124 125;155 124 117;156 124 101;157 104 89;158 104 81;159 104 73;160 104 65;161 104 49;162 104 41;163 104 33;164 104 25;165 104 17;166 92 9;167 80 9;168 72 9;169 64 21;170 72 25;171 80 25;172 80 25;173 80 41;174 88 49;175 104 57;176 124 69;177 124 77;178 132 81;179 140 65;180 132 61;181 124 61;182 124 53;183 124 45;184 124 37;185 124 29;186 132 21;187 124 21;188 120 9;189 128 9;190 136 9;191 148 9;192 162 9;193 156 25;194 172 21;195 180 21;196 180 29;197 172 29;198 172 37;199 172 45;200 180 45;201 180 37;202 188 41;203 196 49;204 204 57;205 212 65;206 220 73;207 228 69;208 228 77;209 236 77;210 236 69;211 236 61;212 228 61;213 228 53;214 236 53;215 236 45;216 228 45;217 228 37;218 236 37;219 236 29;220 228 29;221 228 21;222 236 21;223 252 21;224 260 29;225 260 37;226 260 45;227 260 53;228 260 61;229 260 69;230 260 77;231 276 77;232 276 69;233 276 61;234 276 53;235 284 53;236 284 61;237 284 69;238 284 77;239 284 85;240 284 93;241 284 101;242 288 109;243 280 109;244 276 101;245 276 93;246 276 85;247 268 97;248 260 109;249 252 101;250 260 93;251 260 85;252 236 85;253 228 85;254 228 93;255 236 93;256 236 101;257 228 101;258 228 109;259 228 117;260 228 125;261 220 125;262 212 117;263 204 109;264 196 101;265 188 93;266 180 93;267 180 101;268 180 109;269 180 117;270 180 125;271 196 145;272 204 145;273 212 145;274 220 145;275 228 145;276 236 145;277 246 141;278 252 125;279 260 129;280 280 133;EOF;");
        //matriz de custo
    

        //população gerada
        p = new Population(sizePopulation, sizeGenome, sizeGenotype, sizeAllelo, new TSP(matriz.getCostMatrix()), true);
        //PMX com a probabilidade definida
        inv = new Invertion(probabilidade);
        Population popOUT = inv.execute(p);

        Integer[] alelo; 


        boolean teste = true;

        for (int i = 0; i < sizePopulation; i++) {
            for (int j = 0; j < sizeAllelo; j++) {
                //int n1=popOUT.getIndividual(i);
                alelo = (Integer[]) popOUT.getIndividual(i).getChromosome(0).getGene(0).getAllele();
                int n1 = alelo[j];

                //***********testes*************
                String s = popOUT.getIndividual(i).toString();

                s = s.replace('>', ' ');
                s = s.replace(" ", "");
                s = s.replace('-', ' ');
                //System.out.println(""+s);

                //******************
                for (int k = 0; k < sizeAllelo; k++) {
                    int n2 = alelo[k];
                    if (n1 == n2 && j != k) {
                        teste = false;
                    }

                }

            }

        }


        assertTrue(teste);
        //*************************************************************************
    
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Test
    public void testExecute() {
    }
    //Os testes foram efectuados manualmente, mas em breve disponobilizo testes genéricos

    @Test
    public void test_invertOrderBetweenTwoPointers() {
        Integer[] individuo = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7};
        int ponto_1 = 3, ponto_2 = 5;

        Invertion._invertOrderBetweenTwoPointers(ponto_1, individuo, ponto_2);

        assertArrayEquals(individuo, new Integer[]{0, 1, 2, 5, 4, 3, 6, 7});
    }

    @Test
    public void test_exchangeValuesFromArrayInTwoPoints() {
        int[] pontos = new int[]{0, 4};
        Integer[] individuo = new Integer[]{0, 10, 20, 30, 40, 50};

        Invertion._exchangeValuesFromArrayInTwoPoints(individuo, pontos);

        assertArrayEquals(new Integer[]{40, 10, 20, 30, 0, 50}, individuo);
    }

  

}
