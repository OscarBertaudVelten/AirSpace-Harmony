package testTools;

import graphTools.GraphPlus;
import org.graphstream.graph.Node;

public class TestGraph {

    /**
     * Creates a test graph.
     *
     * @return  A GraphPlus object representing the test graph.
     */
    public static GraphPlus createTestGraph() {
        GraphPlus testGraph = new GraphPlus("graphTest");

        Node A = testGraph.addNode("A");
        A.setAttribute("xy", 3, 5);
        A.setAttribute("ui.label", "A");

        Node B = testGraph.addNode("B");
        B.setAttribute("xy", 1, 4);
        B.setAttribute("ui.label", "B");

        Node C = testGraph.addNode("C");
        C.setAttribute("xy", 0, 2);
        C.setAttribute("ui.label", "C");

        Node D = testGraph.addNode("D");
        D.setAttribute("xy", 2, 0);
        D.setAttribute("ui.label", "D");

        Node E = testGraph.addNode("E");
        E.setAttribute("xy", 4, 0);
        E.setAttribute("ui.label", "E");

        Node F = testGraph.addNode("F");
        F.setAttribute("xy", 6, 2);
        F.setAttribute("ui.label", "F");

        Node G = testGraph.addNode("G");
        G.setAttribute("xy", 8, 5);
        G.setAttribute("ui.label", "G");

        Node H = testGraph.addNode("H");
        H.setAttribute("xy", 5, 4);
        H.setAttribute("ui.label", "H");

        testGraph.addEdge("AB", A, B);
        testGraph.addEdge("AC", A, C);
        testGraph.addEdge("AD", A, D);
        testGraph.addEdge("AE", A, E);
        testGraph.addEdge("AH", A, H);
        testGraph.addEdge("BC", B, C);
        testGraph.addEdge("BE", B, E);
        testGraph.addEdge("CH", C, H);
        testGraph.addEdge("CE", C, E);
        testGraph.addEdge("DE", D, E);
        testGraph.addEdge("EF", E, F);
        testGraph.addEdge("EH", E, H);
        testGraph.addEdge("FH", F, H);

        return testGraph;
    }
}
