import graphTools.GraphPlus;
import graphTools.IntersectionGraph;
import org.graphstream.graph.Edge;
import org.graphstream.graph.implementations.MultiGraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tools.PointPlus;

import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

public class GraphPlusTest {

    private GraphPlus graph;

    @BeforeEach
    public void setUp() {
        graph = new GraphPlus("testGraph");
    }

    @Test
    public void testCreateNodes() {
        graph.createNodes(5);
        assertEquals(5, graph.getNodeCount());
    }

    @Test
    public void testGetConnectedComponentsCount() {
        graph.createNodes(5);
        assertEquals(5, graph.getConnectedComponentsCount());
    }

    @Test
    public void testDiameter() {
        graph.createNodes(5);
        graph.addEdge("1,2", "1", "2");
        assertEquals(1.0, graph.getDiameter());
    }

    // Additional tests can be added for other methods like loadAirportsFile, loadFlightsFile, etc.

    @Test
    public void testConstructIntersectGraph() {
        graph.createNodes(5);
        graph.addEdge("1,2", "1", "2");
        IntersectionGraph intersectGraph = graph.constructIntersectGraph(10.0);
        assertNotNull(intersectGraph);
        assertEquals(0, intersectGraph.getEdgeCount());
    }
}
