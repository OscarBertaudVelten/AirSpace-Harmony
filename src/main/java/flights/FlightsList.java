package flights;

import airports.AirportsList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a list of flights.
 */
public class FlightsList {

    /** The list of flights. */
    private final ArrayList<Flight> flightsList;

    /**
     * Constructs a new FlightsList object.
     */
    public FlightsList(){
        flightsList = new ArrayList<>();
    }

    /**
     * Constructs a new FlightsList object with a specified list of flights.
     *
     * @param newFlightsList the list of flights
     */
    public FlightsList(ArrayList<Flight> newFlightsList) {
        this.flightsList = newFlightsList;
    }

    /**
     * Gets the number of flights in the list.
     *
     * @return the number of flights in the list
     */
    public int size() {
        return flightsList.size();
    }

    /**
     * Gets the flight at the specified index.
     *
     * @param index the index of the flight to retrieve
     * @return the flight at the specified index
     */
    public Flight get(int index) {
        return flightsList.get(index);
    }

    /**
     * Gets the list of flights.
     *
     * @return the list of flights
     */
    public ArrayList<Flight> getFlightsList() {
        return flightsList;
    }

    /**
     * Loads flight data from a file and populates the list of flights.
     *
     * @param file the file containing flight data
     * @param airportsList the list of airports to use for flight information
     * @throws Exception if an error occurs while loading the file 
     * or if the list is empty
     */
    public void loadFlightsFile(File file, AirportsList airportsList) throws Exception {
        Scanner scanner = new Scanner(file);
        Flight tmpFlight;

        while (scanner.hasNextLine()) {
            tmpFlight = new Flight();
            tmpFlight.extractFlightLine(scanner.nextLine(), airportsList);
            flightsList.add(tmpFlight);
        }
        scanner.close();

        if (flightsList.isEmpty()) {
            throw new Exception();
        }
    }

    /**
     * Returns a string representation of the list of flights.
     *
     * @return a string representation of the list of flights
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Flight flight : flightsList)
            builder.append(flight).append("\n");
        return builder.toString();
    }

    /**
     * Prints detailed flight information for the list of flights.
     *
     * @return a string representing detailed flight information
     */
    public String infoVolsPrint() {
        StringBuilder builder = new StringBuilder();
        for (Flight flight : flightsList)
            builder.append(flight.infoVolsPrint()).append("\n");
        return builder.toString();
    }
}
