package genetics;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import operators.Operator;
import operators.mutation.Invertion;
import operators.recombinations.OrderCrossover;
import operators.recombinations.PMX;
import operators.replacements.Truncation;
import operators.selections.SUS;
import operators.selections.Tournament;
import utils.exceptions.SolverException;

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
public class SolverTSPExample {
    //ArrayList de operadores a serem utilizados no solver

    ArrayList<Operator> operators = new ArrayList<Operator>(4);
    Solver solver;

    public SolverTSPExample() throws MalformedURLException, IOException {
        solver = new Solver();
        //Adiciona os operadores que foram escolhidos

        System.out.println("Population: 102");
        System.out.println("Invertion: " + solver.SetMutation("Invertion 0.30"));
        System.out.println("PMX: " + solver.SetRecombination("PMX 0.75"));
        System.out.println("Truncation: " + solver.SetReplacement("Truncation "));
        System.out.println("Tournament: " + solver.SetSelection("Tournament 102 2"));
        solver.SetStopCrit("541 1 1540 1");
        solver.setParameters("102 1 1 76 TSP");
        solver.SetTSPProbl("NAME : eil76;COMMENT : 76;TYPE : TSP;DIMENSION : 76;EDGE_WEIGHT_TYPE : EUC_2D;NODE_COORD_SECTION;1 22 22;2 36 26;3 21 45;4 45 35;5 55 20;6 33 34;7 50 50;8 55 45;9 26 59;10 40 66;11 55 65;12 35 51;13 62 35;14 62 57;15 62 24;16 21 36;17 33 44;18 9 56;19 62 48;20 66 14;21 44 13;22 26 13;23 11 28;24 7 43;25 17 64;26 41 46;27 55 34;28 35 16;29 52 26;30 43 26;31 31 76;32 22 53;33 26 29;34 50 40;35 55 50;36 54 10;37 60 15;38 47 66;39 30 60;40 30 50;41 12 17;42 15 14;43 16 19;44 21 48;45 50 30;46 51 42;47 50 15;48 48 21;49 12 38;50 15 56;51 29 39;52 54 38;53 55 57;54 67 41;55 10 70;56 6 25;57 65 27;58 40 60;59 70 64;60 64 4;61 36 6;62 30 20;63 20 30;64 15 5;65 50 70;66 57 72;67 45 42;68 38 33;69 50 4;70 66 8;71 59 5;72 35 60;73 27 24;74 40 20;75 40 37;76 40 40;EOF;");

        //solver.SetTSPProbl("NAME : eil76;COMMENT : 76;TYPE : TSP;DIMENSION : 76;EDGE_WEIGHT_TYPE : EUC_2D;NODE_COORD_SECTION;1 22 22;2 36 26;3 21 45;4 45 35;5 55 20;6 33 34;7 50 50;8 55 45;9 26 59;10 40 66;11 55 65;12 35 51;13 62 35;14 62 57;15 62 24;16 21 36;17 33 44;18 9 56;19 62 48;20 66 14;21 44 13;22 26 13;23 11 28;24 7 43;25 17 64;26 41 46;27 55 34;28 35 16;29 52 26;30 43 26;31 31 76;32 22 53;33 26 29;34 50 40;35 55 50;36 54 10;37 60 15;38 47 66;39 30 60;40 30 50;41 12 17;42 15 14;43 16 19;44 21 48;45 50 30;46 51 42;47 50 15;48 48 21;49 12 38;50 15 56;51 29 39;52 54 38;53 55 57;54 67 41;55 10 70;56 6 25;57 65 27;58 40 60;59 70 64;60 64 4;61 36 6;62 30 20;63 20 30;64 15 5;65 50 70;66 57 72;67 45 42;68 38 33;69 50 4;70 66 8;71 59 5;72 35 60;73 27 24;74 40 20;75 40 37;76 40 40;EOF;");
        //solver.SetTSPProbl(Download(new URL("file:D:\\Documents and Settings\\Ruben Felix\\Ambiente de trabalho\\ALL_tsp\\a280.tsp")));

    }

    public static void main(String[] args) throws SolverException, MalformedURLException, IOException {
        //Executa o solver
        SolverTSPExample solver = new SolverTSPExample();
        solver.solver.run();

    }

    public static String Download(URL url) throws IOException {
        url.openConnection();
        InputStream dataIN = url.openStream();

        byte[] buffer = new byte[1024];
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();

        while ((dataIN.read(buffer)) > 0) {
            byteBuffer.write(buffer, 0, buffer.length);
            buffer = new byte[1024];
        }

        buffer = byteBuffer.toByteArray();
        String data = new String(buffer);
        data = data.replace("  ", " ");
        data = data.replace("   ", " ");
        return data.replace("\n", ";");
    }
}
