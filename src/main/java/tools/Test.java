package tools;

import colorationAlgorithms.DSatur;
import colorationAlgorithms.WelshPowell;
import com.lowagie.text.pdf.codec.GifImage;
import graphTools.GraphPlus;

import java.io.File;
import java.io.FileWriter;

/**
 * This class contains methods for testing various functionalities.
 */
public class Test {

    /**
     * Main method to run tests.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) throws Exception {
        GraphPlus graph;
        File csvFile = new File("coloration-groupe1.1.csv");
        FileWriter csvFileWriter = new FileWriter(csvFile);

        for (int ii = 0; ii <= 19; ii++) {
            graph = new GraphPlus("");
            try {
                graph.loadTestGraph(new File("Data Eval/graph-eval" + ii + ".txt"));
            } catch (Exception ignored) {}
            DSatur dSatur = new DSatur(graph, true);
            dSatur.dSaturAlgo();
            int bestConflicts = dSatur.getNbConflicts();
            DSatur bestDSatur = dSatur;
            if (bestConflicts > 0) {
                for (int jj = 0; jj < 100; jj++) {
                    System.out.println(jj);
                    dSatur = new DSatur(graph, true);
                    dSatur.dSaturAlgo();


                    if (bestConflicts > dSatur.getNbConflicts()) {
                        System.out.println("Better conflicts found with random");
                        bestConflicts = dSatur.getNbConflicts();
                        bestDSatur = dSatur;
                    }

                    if (bestConflicts == 0) {
                        bestDSatur = dSatur;
                        break;
                    }
                }
            }


            File exportFile = new File("colo-eval" + ii + "txt");
            FileWriter fileWriter = new FileWriter(exportFile);
            WelshPowell wp = new WelshPowell(graph);
            wp.welshPowellAlgo();
            if (wp.getNbConflicts() < dSatur.getNbConflicts()) {
                for (int jj=0; jj < wp.getGraph().getNodeCount(); jj++) {
                    fileWriter.write((jj+1) + "; " + wp.getNodeColors()[jj] + "\n");
                }
                fileWriter.flush();

                csvFileWriter.write("graph-eval" + ii + ".txt ; " + wp.getNbConflicts() + "\n");
                csvFileWriter.flush();
            } else {
                for (int jj=0; jj < bestDSatur.getGraph().getNodeCount(); jj++) {
                    fileWriter.write((jj+1) + "; " + dSatur.getNodeColors()[jj] + "\n");
                }
                fileWriter.flush();

                csvFileWriter.write("graph-eval" + ii + ".txt ; " + bestDSatur.getNbConflicts() + "\n");
                csvFileWriter.flush();
            }


        }
    }
}
