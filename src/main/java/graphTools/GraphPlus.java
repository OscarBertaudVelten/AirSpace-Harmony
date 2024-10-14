package graphTools;

import airports.Airport;
import airports.AirportsList;
import flights.Flight;
import flights.FlightsList;
import intersectionTools.IntersectionTools;
import org.graphstream.algorithm.ConnectedComponents;
import org.graphstream.algorithm.Toolkit;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import tools.PointPlus;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

/**
 * Extension of the MultiGraph class 
 * with additional functionalities for managing airports and flights.
 */
public class GraphPlus extends MultiGraph {

    /** Instance of IntersectionTools for intersection-related operations. */
    private final IntersectionTools intersectionTools = new IntersectionTools();

    /** Maximum degree of the graph. */
    private int kMax;

    /**
     * Constructs a new graph.
     *
     * @param id The identifier of the graph.
     */
    public GraphPlus(String id) {
        super(id);
        intersectionTools.setSafetyMargin(15);
    }

    /**
     * Retrieves the maximum degree of the graph.
     *
     * @return The maximum degree of the graph.
     */
    public int getKMax() { 
        return kMax; 
    }

    /**
     * Sets the maximum degree of the graph.
     *
     * @param newKMax The maximum degree to set.
     */
    @SuppressWarnings("unused")
    public void setKMax(int newKMax) { 
        kMax = newKMax; 
    }

    /**
     * Retrieves the average degree of the graph.
     *
     * @return The average degree of the graph.
     */
    public double getAvgDegree() {
        double result = Toolkit.averageDegree(this);
        BigDecimal bd = new BigDecimal(result);
        bd = bd.setScale(2, RoundingMode.HALF_EVEN);
        return bd.doubleValue();
    }

    /**
     * Retrieves the count of connected components in the graph.
     *
     * @return The count of connected components.
     */
    public int getConnectedComponentsCount() {
        ConnectedComponents cc = new ConnectedComponents(this);
        return cc.getConnectedComponentsCount();
    }

    /**
     * Retrieves the diameter of the graph.
     *
     * @return The diameter of the graph.
     */
    public double getDiameter() {
        return Toolkit.diameter(this);
    }

    /**
     * Creates nodes in the graph.
     *
     * @param nbNodes The number of nodes to create.
     */
    public void createNodes(int nbNodes) {
        for (int ii = 1; ii <= nbNodes; ii++) {
            this.addNode(String.valueOf(ii));
        }
    }

    /**
     * Retrieves the coordinates of an airport.
     *
     * @param flight The flight representing the airport.
     * @param attribute The attribute indicating the airport code.
     * @return The coordinates of the airport.
     */
    public PointPlus getAirportCoords(Edge flight, String attribute) {
        String airportCode = (String) flight.getAttribute(attribute);
        Node airport = this.getNode(airportCode);
        PointPlus coordsPointPlus = new PointPlus();
        Object[] xyCoords = (Object[]) airport.getAttribute("xy");
        coordsPointPlus.setX((double) xyCoords[0]);
        coordsPointPlus.setY((double) xyCoords[1]);
        coordsPointPlus.setExistence(true);
        return coordsPointPlus;
    }

    /**
     * Loads a test graph from a file.
     *
     * @param file The file containing the graph data.
     * @throws Exception If the file cannot be read or parsed.
     */
    public void loadTestGraph(File file) throws Exception {
        Scanner scanner = new Scanner(file);
        kMax = scanner.nextInt();
        int nbNodes = scanner.nextInt();
        String id, nodeName1, nodeName2;
        this.createNodes(nbNodes);
        while (scanner.hasNextInt()) {
            nodeName1 = String.valueOf(scanner.nextInt());
            nodeName2 = String.valueOf(scanner.nextInt());
            id = String.format("%s,%s", nodeName1, nodeName2);
            this.addEdge(id, nodeName1, nodeName2);
        }
        scanner.close();
        if (nodeCount == 0 || edgeCount == 0) {
            throw new Exception();
        }
    }

    /**
     * Loads airports from a file and adds them as nodes to the graph.
     *
     * @param file The file containing the airport data.
     * @return The list of loaded airports.
     */
    public AirportsList loadAirportsFile(File file) {
        AirportsList airportsList = new AirportsList();
        try {
            airportsList.loadAirportsFile(file);
            for (int ii = 0; ii < airportsList.size(); ii++) {
                Airport tmpAirport = airportsList.indexGet(ii);
                String nodeName = tmpAirport.getAirportCode();
                double xCoord = tmpAirport.getXCoord();
                double yCoord = tmpAirport.getYCoord();
                Node tmpNode = this.addNode(nodeName);
                tmpNode.setAttribute("xy", xCoord, yCoord);
                tmpNode.setAttribute("airportLoc", tmpAirport.getAirportLocation());
                tmpNode.setAttribute("ui.label", tmpAirport.getAirportCode());
                tmpNode.setAttribute("ui.style", "text-size: 10; text-offset: 0, 10;");
            }
        } catch (Exception ignored) { // ignored because only for tests
        }
        return airportsList;
    }

    /**
     * Loads a list of airports and adds them as nodes to the graph.
     *
     * @param airportsList The list of airports.
     */
    public void loadAirportsList(AirportsList airportsList) {
        for (int ii = 0; ii < airportsList.size(); ii++) {
            Airport tmpAirport = airportsList.indexGet(ii);
            String nodeName = tmpAirport.getAirportCode();
            double xCoord = tmpAirport.getXCoord();
            double yCoord = tmpAirport.getYCoord();
            Node tmpNode = this.addNode(nodeName);
            tmpNode.setAttribute("xy", xCoord, yCoord);
            tmpNode.setAttribute("airportLoc", tmpAirport.getAirportLocation());
            tmpNode.setAttribute("ui.label", tmpAirport.getAirportCode());
            tmpNode.setAttribute("ui.style", "text-size: 10; text-offset: 0, 10;");
        }
    }

    /**
     * Loads flights from a file and adds them as edges to the graph.
     *
     * @param file The file containing the flight data.
     * @param airportsList The list of airports.
     */
    public void loadFlightsFile(File file, AirportsList airportsList) {
        FlightsList flightsList = new FlightsList();
        try {
            flightsList.loadFlightsFile(file, airportsList);
        } catch (Exception ignored) { // ignored because only for tests
        }
        for (int ii = 0; ii < flightsList.size(); ii++) {
            Flight tmpFlight = flightsList.get(ii);
            String nodeName1 = tmpFlight.getDepAirportCode();
            String nodeName2 = tmpFlight.getArrAirportCode();
            String id = tmpFlight.getFlightID();
            Edge edge = this.addEdge(id, nodeName1, nodeName2, true);
            edge.setAttribute("depAirport", tmpFlight.getDepAirportCode());
            edge.setAttribute("arrAirport", tmpFlight.getArrAirportCode());
            edge.setAttribute("hDeparture", tmpFlight.gethDeparture());
            edge.setAttribute("duration", tmpFlight.getDuration());
        }
    }

    /**
     * Loads a list of flights and adds them as edges to the graph.
     *
     * @param flightsList The list of flights.
     */
    public void loadFlightsList(FlightsList flightsList) {
        for (int ii = 0; ii < flightsList.size(); ii++) {
            Flight tmpFlight = flightsList.get(ii);
            String nodeName1 = tmpFlight.getDepAirportCode();
            String nodeName2 = tmpFlight.getArrAirportCode();
            String id = tmpFlight.getFlightID();
            Edge edge = this.addEdge(id, nodeName1, nodeName2, true);
            edge.setAttribute("depAirport", tmpFlight.getDepAirportCode());
            edge.setAttribute("arrAirport", tmpFlight.getArrAirportCode());
            edge.setAttribute("hDeparture", tmpFlight.gethDeparture());
            edge.setAttribute("duration", tmpFlight.getDuration());
        }
    }

    /**
     * Loads flights and airports from their respective files
     * and adds them to the graph.
     *
     * @param flightsFile  The file containing the flight data.
     * @param airportsFile The file containing the airport data.
     */
    public void loadFlightsAirportsFile(File flightsFile, File airportsFile) {
        AirportsList airportsList = loadAirportsFile(airportsFile);
        loadFlightsFile(flightsFile, airportsList);
    }

    /**
     * Loads lists of flights and airports and adds them to the graph.
     *
     * @param airportsList The list of airports.
     * @param flightsList The list of flights.
     * @return The current instance of GraphPlus.
     */
    public GraphPlus loadFlightsAirportsList(AirportsList airportsList, 
                                             FlightsList flightsList) {
        loadAirportsList(airportsList);
        loadFlightsList(flightsList);
        return this;
    }

    /**
     * Constructs an intersection graph from the current flight graph.
     *
     * @param safetyMargin The safety margin to consider for intersections.
     * @return The constructed intersection graph.
     */
    public IntersectionGraph constructIntersectGraph(double safetyMargin) {
        IntersectionGraph intersectGraph = new IntersectionGraph("intersectGraph");
        int size = this.edgeCount;
        Edge flight1, flight2;
        PointPlus PointI;
        int intersectCase;
        if (safetyMargin > 0) {
            intersectionTools.setSafetyMargin(safetyMargin);
        }
        intersectGraph.setNodesFlights(this, size);
        for (int flightInd1 = 0; flightInd1 < size; flightInd1++) {
            for (int flightInd2 = 0; flightInd2 < size; flightInd2++) {
                if (flightInd1 < flightInd2) {
                    flight1 = this.getEdge(flightInd1);
                    flight2 = this.getEdge(flightInd2);
                    PointI = intersectionTools.findIntersection(
                            this, flightInd1, flightInd2);
                    intersectCase = intersectionTools.intersectionCase(
                            flight1, flight2, PointI);
                    if (intersectionTools.hasCollision(
                            this, flight1, flight2, PointI, intersectCase))
                        intersectGraph.copyFlightAttributesToIntersectGraph(
                                flight1, flight2, PointI);
                }
            }
        }
        return intersectGraph;
    }

    /**
     * Returns a string representation of the graph.
     *
     * @return A string containing the number of nodes and edges in the graph.
     */
    public String toString() {
        return String.format("Number of Nodes : %d\nNumber of Edges : %s", 
                nodeCount, edgeCount);
    }
}
