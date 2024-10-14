package intersectionTools;

import graphTools.GraphPlus;
import org.graphstream.graph.Edge;
import tools.PointPlus;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

/**
 * Provides tools for determining flight intersections and collision detection.
 */
public class IntersectionTools {

    /** The safety margin for collision detection. */
    private double safetyMargin;

    /**
     * Constructs an IntersectionTools object with default safety margin.
     */
    public IntersectionTools() {}

    /**
     * Sets a new safety margin.
     *
     * @param newSafetyMargin the new safety margin to set
     */
    public void setSafetyMargin(double newSafetyMargin) {
        safetyMargin = newSafetyMargin;
    }

    /**
     * Determines the type of intersection between two flights.
     * <p>
     * There are six possible cases for flight intersections:<br>
     * 1. Same departure (and same route)<br>
     * 2. Same arrival<br>
     * 3. Same route but in the opposite direction<br>
     * 4. Departure of flight 1 equals arrival of flight 2<br>
     * 5. Departure of flight 2 equals arrival of flight 1<br>
     * 6. Any other case<br>
     *
     * @param flight1 the first flight
     * @param flight2 the second flight
     * @param I       the intersection point (if available)
     * @return the number representing the intersection case
     */
    public int intersectionCase(Edge flight1, Edge flight2, PointPlus I) {
        int result = 0;
        String codeAirpDep1 = (String) flight1.getAttribute("depAirport");
        String codeAirpDep2 = (String) flight2.getAttribute("depAirport");
        String codeAirpArr1 = (String) flight1.getAttribute("arrAirport");
        String codeAirpArr2 = (String) flight2.getAttribute("arrAirport");

        if (codeAirpDep1.equals(codeAirpArr2) && codeAirpDep2.equals(codeAirpArr1))
            result = 3;
        else if (codeAirpDep1.equals(codeAirpDep2))
            result = 1;
        else if (codeAirpArr1.equals(codeAirpArr2))
            result = 2;
        else if (codeAirpDep1.equals(codeAirpArr2))
            result = 4;
        else if (codeAirpDep2.equals(codeAirpArr1))
            result = 5;
        else if (I.exists())
            result = 6;
        return result;
    }

    /**
     * Finds the intersection point of two flights.
     *
     * @param graph      the graph containing flight data
     * @param flightInd1 the index of the first flight
     * @param flightInd2 the index of the second flight
     * @return the intersection point
     */
    public PointPlus findIntersection(GraphPlus graph, int flightInd1, int flightInd2) {
        FlightEdge[] flights = new FlightEdge[2];
        IntersectionPoint intersectionPoint;

        flights[0] = new FlightEdge();
        flights[1] = new FlightEdge();
        flights[0].extractFlight(graph, flightInd1);
        flights[1].extractFlight(graph, flightInd2);

        intersectionPoint = new IntersectionPoint(flights[0].departurePoint, flights[0].arrivalPoint,
                flights[1].departurePoint, flights[1].arrivalPoint);
        intersectionPoint.findIntersectionPointPlus();

        return intersectionPoint.Intersection;
    }

    /**
     * Finds the time of intersection for a flight and a point.
     *
     * @param graph  the graph containing flight data
     * @param flight the flight to consider
     * @param I      the intersection point
     * @return the time of intersection
     */
    public double findTimeAtIntersection(GraphPlus graph, Edge flight, PointPlus I) {
        PointPlus dep = graph.getAirportCoords(flight, "depAirport");
        PointPlus arr = graph.getAirportCoords(flight, "arrAirport");
        double hDeparture = (double) flight.getAttribute("hDeparture");
        int duration = (int) flight.getAttribute("duration");

        double flightDistance = sqrt((arr.getX() - dep.getX()) * (arr.getX() - dep.getX()) + (arr.getY() - dep.getY()) * (arr.getY() - dep.getY()));
        double avgSpeed = flightDistance / duration;

        double intersectDistance = sqrt((I.getX() - dep.getX()) * (I.getX() - dep.getX()) + (I.getY() - dep.getY()) * (I.getY() - dep.getY()));
        double timeToIntersect = intersectDistance / avgSpeed;

        return hDeparture + timeToIntersect;
    }

    /**
     * Determines if two flights have a collision.
     *
     * @param graph         the graph containing flight data
     * @param flight1       the first flight
     * @param flight2       the second flight
     * @param I             the intersection point
     * @param intersectCase the intersection case
     * @return true if collision occurs within the safety margin, false otherwise
     */
    public boolean hasCollision(GraphPlus graph, Edge flight1, Edge flight2, PointPlus I, int intersectCase) {
        double timeDelta = safetyMargin;
        double hDep1 = (double) flight1.getAttribute("hDeparture");
        double hDep2 = (double) flight2.getAttribute("hDeparture");
        double hArr1 = hDep1 + ((int) flight1.getAttribute("duration"));
        double hArr2 = hDep2 + ((int) flight2.getAttribute("duration"));
        switch (intersectCase) {
            case 1:
                timeDelta = abs(hDep1 - hDep2);
                break;
            case 2:
                timeDelta = abs(hArr1 - hArr2);
                break;
            case 3:
                if (hDep1 < hDep2)
                    timeDelta = hDep2 - hArr1;
                else
                    timeDelta = hDep1 - hArr2;
                break;
            case 4:
                timeDelta = abs(hArr2 - hDep1);
                break;
            case 5:
                timeDelta = abs(hArr1 - hDep2);
                break;
            case 6:
                double hIntersect1 = findTimeAtIntersection(graph, flight1, I);
                double hIntersect2 = findTimeAtIntersection(graph, flight2, I);
                timeDelta = abs(hIntersect1 - hIntersect2);
                break;
        }
        return (timeDelta < safetyMargin);
    }
}
