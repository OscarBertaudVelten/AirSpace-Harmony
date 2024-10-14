package airports;

import org.jxmapviewer.viewer.GeoPosition;

import java.util.StringTokenizer;

import static java.lang.Math.*;

/**
 * The Airport class represents an airport with its 
 * code, location, and coordinates.
 */
public class Airport {
    private String airportCode;
    private String airportLocation;
    private GeoPosition geoPosition;

    /**
     * Constructs an Airport object with empty code and location.
     */
    public Airport() {
        airportCode = "";
        airportLocation = "";
    }

    /**
     * Gets the airport code.
     * @return The airport code.
     */
    public String getAirportCode() {
        return airportCode;
    }

    /**
     * Gets the airport location.
     * @return The airport location.
     */
    public String getAirportLocation() {
        return airportLocation;
    }

    /**
     * Gets the geographical position of the airport.
     * @return The geographical position of the airport.
     */
    public GeoPosition getGeoPosition() { 
        return geoPosition; 
    }

    /**
     * Gets the latitude of the airport.
     * @return The latitude of the airport.
     */
    public double getLatitude() { 
        return geoPosition.getLatitude(); 
    }

    /**
     * Gets the longitude of the airport.
     * @return The longitude of the airport.
     */
    public double getLongitude() { 
        return geoPosition.getLongitude(); 
    }

    /**
     * Gets the latitude and longitude in radians.
     * @return An array containing the latitude and longitude in radians.
     */
    public double[] getLatitudeLongitudeRadians() {
        double latitudeRad = getLatitude() * PI / 180;
        double longitudeRad = getLongitude() * PI / 180;
        return new double[]{latitudeRad, longitudeRad};
    }

    /**
     * Gets the X coordinate.
     * @return The X coordinate.
     */
    public double getXCoord() {
        double[] latLongRad = getLatitudeLongitudeRadians();
        return 6371 * cos(latLongRad[0]) * sin(latLongRad[1]);
    }

    /**
     * Gets the Y coordinate.
     * @return The Y coordinate.
     */
    public double getYCoord() {
        double[] latLongRad = getLatitudeLongitudeRadians();
        return 6371 * cos(latLongRad[0]) * cos(latLongRad[1]);
    }

    /**
     * Calculates the coordinates of the airport using latitude and longitude.
     * @param latDegree The latitude degree.
     * @param latMinute The latitude minute.
     * @param latSecond The latitude second.
     * @param latOrientation The latitude orientation (N or S).
     * @param longDegree The longitude degree.
     * @param longMinute The longitude minute.
     * @param longSecond The longitude second.
     * @param longOrientation The longitude orientation (E or W).
     */
    private void calcCoord(int latDegree, int latMinute, int latSecond, 
                           String latOrientation,
                           int longDegree, int longMinute, int longSecond, 
                           String longOrientation) {
        int coef;
        if (latOrientation.equals("N") || latOrientation.equals("E"))
            coef = 1;
        else
            coef = -1;
        double latitude = coef * (latDegree + (double) latMinute / 
                60 + (double) latSecond / 3600);
        if (longOrientation.equals("N") || longOrientation.equals("E"))
            coef = 1;
        else
            coef = -1;
        double longitude = coef * (longDegree + (double) longMinute / 
                60 + (double) longSecond / 3600);
        geoPosition = new GeoPosition(latitude, longitude);
    }

    /**
     * Extracts airport information from a line of data.
     * @param line The line containing airport information in the format: 
     * code;location;latDegree;latMinute;latSecond;latOrientation;longDegree;
     * longMinute;longSecond;longOrientation
     */
    public void extractAirportLine(String line) {
        StringTokenizer st = new StringTokenizer(line, ";");

        this.airportCode = st.nextToken();
        this.airportLocation = st.nextToken();

        int latDegree = Integer.parseInt(st.nextToken());
        int latMinute = Integer.parseInt(st.nextToken());
        int latSecond = Integer.parseInt(st.nextToken());
        String latOrientation = st.nextToken();

        int longDegree = Integer.parseInt(st.nextToken());
        int longMinute = Integer.parseInt(st.nextToken());
        int longSecond = Integer.parseInt(st.nextToken());
        String longOrientation = st.nextToken();

        this.calcCoord(latDegree, latMinute, latSecond, latOrientation,
                longDegree, longMinute, longSecond, longOrientation);
    }

    /**
     * Returns a string representation of the airport.
     * @return A string representation of the airport.
     */
    public String toString() {
        return airportLocation.concat("(").concat(airportCode).concat(")");
    }
}
