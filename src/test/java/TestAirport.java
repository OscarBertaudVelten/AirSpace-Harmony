import airports.Airport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.jxmapviewer.viewer.GeoPosition;

import static org.junit.jupiter.api.Assertions.*;

public class TestAirport {

    private Airport airport;

    @BeforeEach
    void setUp() {
        airport = new Airport();
    }

    @Test
    public void testDefaultConstructor() {
        assertEquals("", airport.getAirportCode());
        assertEquals("", airport.getAirportLocation());
        assertNull(airport.getGeoPosition());
    }

    @Test
    public void testExtractAirportLine() {
        String line = "JFK;New York;40;38;23;N;73;46;44;W";
        airport.extractAirportLine(line);

        assertEquals("JFK", airport.getAirportCode());
        assertEquals("New York", airport.getAirportLocation());

        GeoPosition geoPosition = airport.getGeoPosition();
        assertNotNull(geoPosition);
        assertEquals(40.639722, geoPosition.getLatitude(), 0.000001);
        assertEquals(-73.778889, geoPosition.getLongitude(), 0.000001);
    }

    @Test
    public void testExtractAirportLineWithInvalidData() {
        String line = "JFK;New York;invalid;data;here";
        assertThrows(IllegalArgumentException.class, () -> {airport.extractAirportLine(line);});
    }

    @Test
    public void testExtractAirportLineWithNull() {
       assertThrows(NullPointerException.class, () -> {airport.extractAirportLine(null);});
    }

    @Test
    public void testGetLatitudeLongitudeRadians() {
        airport.extractAirportLine("JFK;New York;40;38;23;N;73;46;44;W");
        double[] latLongRad = airport.getLatitudeLongitudeRadians();

        assertEquals(40.639722 * Math.PI / 180, latLongRad[0], 0.000001);
        assertEquals(-73.778889 * Math.PI / 180, latLongRad[1], 0.000001);
    }

    @Test
    public void testGetXCoord() {
        airport.extractAirportLine("JFK;New York;40;38;23;N;73;46;44;W");
        double xCoord = airport.getXCoord();

        double expectedX = 6371 * Math.cos(40.639722 * Math.PI / 180) * Math.sin(-73.778889 * Math.PI / 180);
        assertEquals(expectedX, xCoord, 0.0001);
    }

    @Test
    public void testGetYCoord() {
        airport.extractAirportLine("JFK;New York;40;38;23;N;73;46;44;W");
        double yCoord = airport.getYCoord();

        double expectedY = 6371 * Math.cos(40.639722 * Math.PI / 180) * Math.cos(-73.778889 * Math.PI / 180);
        assertEquals(expectedY, yCoord, 0.0001);
    }

    @Test
    public void testToString() {
        airport.extractAirportLine("JFK;New York;40;38;23;N;73;46;44;W");
        assertEquals("New York(JFK)", airport.toString());
    }

    @Test
    public void testToStringWithEmptyData() {
        assertEquals("()", airport.toString());
    }
}
