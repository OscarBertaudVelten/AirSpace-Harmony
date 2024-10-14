import airports.Airport;
import airports.AirportsList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class TestAirportsList {

    private AirportsList airportsList;
    private Airport airport1;
    private Airport airport2;

    @BeforeEach
    void setUp() {
        airportsList = new AirportsList();
        airport1 = new Airport();
        airport1.extractAirportLine("JFK;New York;40;38;23;N;73;46;44;W");
        airport2 = new Airport();
        airport2.extractAirportLine("LAX;Los Angeles;33;56;33;N;118;24;29;W");
    }

    @Test
    void testAdd() {
        airportsList.add(airport1);
        assertEquals(1, airportsList.size());
        assertEquals(airport1, airportsList.indexGet(0));

        airportsList.add(airport2);
        assertEquals(2, airportsList.size());
        assertEquals(airport2, airportsList.indexGet(1));
    }

    @Test
    void testDoesntContain() {
        assertTrue(airportsList.doesntContain(airport1));
        airportsList.add(airport1);
        assertFalse(airportsList.doesntContain(airport1));
    }

    @Test
    void testToArray() {
        airportsList.add(airport1);
        airportsList.add(airport2);
        Airport[] airportsArray = airportsList.toArray();
        assertEquals(2, airportsArray.length);
        assertEquals(airport1, airportsArray[0]);
        assertEquals(airport2, airportsArray[1]);
    }

    @Test
    void testIndexGet() {
        airportsList.add(airport1);
        airportsList.add(airport2);
        assertEquals(airport1, airportsList.indexGet(0));
        assertEquals(airport2, airportsList.indexGet(1));
    }

    @Test
    void testCodeGet() {
        airportsList.add(airport1);
        airportsList.add(airport2);
        assertEquals(airport1, airportsList.codeGet("JFK"));
        assertEquals(airport2, airportsList.codeGet("LAX"));
        assertNull(airportsList.codeGet("ORD")); // Non-existent code
    }

    @Test
    void testLoadAirportsFile() throws Exception {
        File tempFile = createTempFileWithContent(
                "JFK;New York;40;38;23;N;73;46;44;W\n" +
                        "LAX;Los Angeles;33;56;33;N;118;24;29;W"
        );

        airportsList.loadAirportsFile(tempFile);

        assertEquals(2, airportsList.size());
        assertEquals("JFK", airportsList.indexGet(0).getAirportCode());
        assertEquals("LAX", airportsList.indexGet(1).getAirportCode());
    }

    @Test
    void testLoadAirportsFile_EmptyFile() {
        File emptyFile = new File("emptyFile.txt");
        try {
            emptyFile.createNewFile();
            Exception exception = assertThrows(Exception.class, () -> airportsList.loadAirportsFile(emptyFile));
            assertEquals("java.lang.Exception", exception.getClass().getName());
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    void testLoadAirportsFile_FileNotFound() {
        File nonExistentFile = new File("nonExistentFile.txt");
        assertThrows(Exception.class, () -> airportsList.loadAirportsFile(nonExistentFile));
    }

    private File createTempFileWithContent(String content) throws IOException {
        File tempFile = File.createTempFile("airports", ".txt");
        FileWriter writer = new FileWriter(tempFile);
        writer.write(content);
        writer.close();
        return tempFile;
    }

    @Test
    void testToString() {
        airportsList.add(airport1);
        airportsList.add(airport2);
        String expected = "New York(JFK)\nLos Angeles(LAX)\n";
        assertEquals(expected, airportsList.toString());
    }
}
