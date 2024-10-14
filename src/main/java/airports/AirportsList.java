package airports;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a list of airports.
 */
public class AirportsList {
    private final ArrayList<Airport> airportsList;

    /**
     * Constructs an AirportsList object with an empty ArrayList of airports.
     */
    public AirportsList() {
        airportsList = new ArrayList<>();
    }

    /**
     * Gets the number of airports in the list.
     * @return The number of airports in the list.
     */
    public int size() {
        return airportsList.size();
    }

    /**
     * Gets the list of airports.
     * @return The list of airports.
     */
    public ArrayList<Airport> getAirportsList() {
        return airportsList;
    }

    /**
     * Checks if the list does not contain the specified airport.
     * @param airport The airport to check.
     * @return True if the list does not contain the airport, false otherwise.
     */
    public boolean doesntContain(Airport airport) {
        if (airport != null)
            return !airportsList.contains(airport);
        return true;
    }

    /**
     * Converts the list of airports to an array.
     * @return An array of airports.
     */
    public Airport[] toArray() {
        return airportsList.toArray(new Airport[0]);
    }

    /**
     * Retrieves the airport at the specified index.
     * @param index The index of the airport to retrieve.
     * @return The airport at the specified index.
     */
    public Airport indexGet(int index) {
        return airportsList.get(index);
    }

    /**
     * Adds a new airport to the list.
     * @param newAirport The new airport to add.
     */
    public void add(Airport newAirport) {
        airportsList.add(newAirport);
    }

    /**
     * Retrieves the airport with the specified code.
     * @param code The code of the airport to retrieve.
     * @return The airport with the specified code, or null if not found.
     */
    public Airport codeGet(String code) {
        Airport tmpAirport = null;
        int ii = 0;
        while (tmpAirport == null && ii < airportsList.size()) {
            if (code.equals(airportsList.get(ii).getAirportCode())) {
                tmpAirport = airportsList.get(ii);
            }
            ii++;
        }
        return tmpAirport;
    }

    /**
     * Loads airports from a file.
     * @param file The file containing airport data.
     * @throws Exception If the file is empty or an error occurs during reading.
     */
    public void loadAirportsFile(File file) throws Exception {
        Scanner scanner = new Scanner(file);
        Airport airportTmp;

        while (scanner.hasNextLine()) {
            airportTmp = new Airport();
            airportTmp.extractAirportLine(scanner.nextLine());
            if (!airportsList.contains(airportTmp)) {
                airportsList.add(airportTmp);
            }
        }
        scanner.close();

        if (airportsList.isEmpty()) {
            throw new Exception();
        }
    }

    /**
     * Returns a string representation of the airports list.
     * @return A string representation of the airports list.
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Airport airport : airportsList)
            builder.append(airport.toString()).append("\n");
        return builder.toString();
    }
}
