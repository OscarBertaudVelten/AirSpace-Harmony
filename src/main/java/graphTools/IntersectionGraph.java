package graphTools;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;
import tools.PointPlus;

/**
 * This class represents an intersection graph, 
 * which is a specialized type of graph that
 * represents intersections between flights.
 */
public class IntersectionGraph extends GraphPlus {

    /**
     * Constructs a new IntersectionGraph with the specified identifier.
     *
     * @param id The identifier for the IntersectionGraph.
     */
    public IntersectionGraph(String id) {
        super(id);
    }

    /**
     * Sets nodes representing flights in the intersection graph 
     * based on the given flightsAiportsGraph.
     *
     * @param flightsAiportsGraph The graph containing flights and airports information.
     * @param size                The number of flights to process.
     */
    public void setNodesFlights(GraphPlus flightsAiportsGraph, int size) {
        Edge flight;
        Node flightNode;
        String flightId;
        for (int ii = 0; ii < size; ii++) {
            flight = flightsAiportsGraph.getEdge(ii);
            flightId = flight.getId();
            flightNode = this.addNode(flightId);

            flightNode.setAttribute("depAirport", flight.getAttribute("depAirport"));
            flightNode.setAttribute("arrAirport", flight.getAttribute("arrAirport"));
            flightNode.setAttribute("hDeparture", flight.getAttribute("hDeparture"));
            flightNode.setAttribute("duration", flight.getAttribute("duration"));
        }
    }

    /**
     * Copies flight attributes to the intersection graph and sets the intersection point.
     *
     * @param flight1 The first flight edge.
     * @param flight2 The second flight edge.
     * @param I       The intersection point.
     */
    public void copyFlightAttributesToIntersectGraph(Edge flight1, Edge flight2, 
                                                     PointPlus I) {
        String node1 = flight1.getId();
        String node2 = flight2.getId();
        String id = node1 + "," + node2;

        Edge edge = this.addEdge(id, node1, node2);

        edge.setAttribute("xCoord", I.getX());
        edge.setAttribute("yCoord", I.getY());
    }
}
