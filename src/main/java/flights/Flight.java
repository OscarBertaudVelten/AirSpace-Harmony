package flights;

import airports.Airport;
import airports.AirportsList;
import tools.TimeTools;

import java.sql.Time;
import java.util.StringTokenizer;

/**
 * Represents a flight with its attributes such as flight ID, departure airport,
 * arrival airport, departure time, and duration.
 */
public class Flight {

    /** Unique identifier for the flight */
    private String flightID;

    /** Departure airport code */
    private Airport depAirport;

    /** Arrival airport code */
    private Airport arrAirport;

    /** Departure time in hours */
    private double hDeparture;

    /** Duration of the flight in minutes */
    private int duration;

    /** Layer information for the flight */
    private int layer;

    /**
     * Default constructor.
     */
    public Flight() { }

    /**
     * Gets the flight ID.
     * @return The flight ID.
     */
    public String getFlightID() {
        return flightID;
    }

    /**
     * Gets the departure airport.
     * @return The departure airport.
     */
    public Airport getDepAirport() {
        return depAirport;
    }

    /**
     * Gets the departure airport code.
     * @return The departure airport code.
     */
    public String getDepAirportCode() {
        return depAirport.getAirportCode();
    }

    /**
     * Gets the arrival airport.
     * @return The arrival airport.
     */
    public Airport getArrAirport() {
        return arrAirport;
    }

    /**
     * Gets the arrival airport code.
     * @return The arrival airport code.
     */
    public String getArrAirportCode() {
        return arrAirport.getAirportCode();
    }

    /**
     * Gets the departure time in hours.
     * @return The departure time in hours.
     */
    public double gethDeparture() {
        return hDeparture;
    }

    /**
     * Gets the duration of the flight in minutes.
     * @return The duration of the flight in minutes.
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Gets the layer information for the flight.
     * @return The layer information.
     */
    public int getLayer() {
        return layer;
    }

    /**
     * Sets the layer information for the flight.
     * @param newLayer the new layer value
     */
    public void setLayer(int newLayer) {
        layer = newLayer;
    }

    /**
     * Extracts flight information from a line of text.
     * @param line The line of text containing flight information.
     * @param airportsList The list of airports to retrieve airport information.
     */
    public void extractFlightLine(String line, AirportsList airportsList) {
        TimeTools tmpTime = new TimeTools();
        StringTokenizer st = new StringTokenizer(line, ";");
        this.flightID = st.nextToken();
        String depAirportCode = st.nextToken();
        String arrAirportCode = st.nextToken();
        this.depAirport = airportsList.codeGet(depAirportCode);
        this.arrAirport = airportsList.codeGet(arrAirportCode);
        tmpTime.setHours(Integer.parseInt(st.nextToken()));
        tmpTime.setMinutes(Double.parseDouble(st.nextToken()));
        hDeparture = tmpTime.HM_to_M();
        this.duration = Integer.parseInt(st.nextToken());
    }

    /**
     * Returns a string representation of the flight.
     * @return A string representing the flight details.
     */
    public String toString() {
        return String.format("%s: %s->%s", flightID, depAirport, arrAirport);
    }

    /**
     * Prints flight information in a formatted string.
     * @return A string representing detailed flight information.
     */
    public String infoVolsPrint() {
        TimeTools timeTools1 = new TimeTools();
        timeTools1.M_to_HM(hDeparture);
        TimeTools timeTools2 = new TimeTools();
        timeTools2.M_to_HM(hDeparture + duration);
        return String.format("%s | %s | %s | %s | %s | %s | %s | %s",
                flightID, getDepAirportCode(), depAirport.getAirportLocation(),
                getArrAirportCode(), arrAirport.getAirportLocation(),
                timeTools1, timeTools2, duration);
    }
}
