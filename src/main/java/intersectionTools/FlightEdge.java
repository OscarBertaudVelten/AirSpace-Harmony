package intersectionTools;

import graphTools.GraphPlus;
import org.graphstream.graph.Node;
import tools.PointPlus;

/**
 * FlightEdge represents a flight edge between two points.
 */
public class FlightEdge {

    /** The departure point of the flight edge. */
    PointPlus departurePoint;

    /** The arrival point of the flight edge. */
    PointPlus arrivalPoint;

    /**
     * Constructs a FlightEdge object.
     */
    public FlightEdge() {}

    /**
     * Extracts flight information from the graph.
     *
     * @param graphe The graph containing flight information.
     * @param ind The index of the flight edge.
     */
    public void extractFlight(GraphPlus graphe, int ind) {
        Node node;
        Object[] coords;
        double x, y;
        String depAirportCode, arrAirportCode;

        // Extract departure point information
        depAirportCode = graphe.getEdge(ind).getNode0().getId();
        node = graphe.getNode(depAirportCode);
        coords = (Object[]) node.getAttribute("xy");
        x = (double) coords[0];
        y = (double) coords[1];
        departurePoint = new PointPlus(x, y);

        // Extract arrival point information
        arrAirportCode = graphe.getEdge(ind).getNode1().getId();
        node = graphe.getNode(arrAirportCode);
        coords = (Object[]) node.getAttribute("xy");
        x = (double) coords[0];
        y = (double) coords[1];
        arrivalPoint = new PointPlus(x, y);
    }
}
