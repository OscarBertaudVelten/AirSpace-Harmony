package ui.jXMapViewer;

import airports.Airport;
import airports.AirportsList;
import flights.Flight;
import flights.FlightsList;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCursor;
import org.jxmapviewer.painter.CompoundPainter;
import org.jxmapviewer.painter.Painter;
import org.jxmapviewer.viewer.WaypointPainter;
import tools.TimeTools;

import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Controller class for managing map interactions and displaying flights and airports.
 */
public class MapPanelController {

    /**
     * Color for painting flights on the map.
     */
    final Color flightColor = Color.black;

    /**
     * Flag indicating whether anti-aliasing is enabled.
     */
    final boolean hasAntiAlias = true;

    /**
     * List of flights currently displayed.
     */
    FlightsList flightsList;

    /**
     * Full list of flights (used for filtering).
     */
    final FlightsList fullFlightsList;

    /**
     * List of airports.
     */
    AirportsList airportsList;

    /**
     * Set of waypoints representing airports on the map.
     */
    private Set<MyWaypoint> airportWaypoints;

    /**
     * Reference to the view associated with this controller.
     */
    private final MapPanelView view;

    /**
     * Constructs a MapPanelController with specified initial settings.
     *
     * @param newAirportsList The list of airports to display.
     * @param newFlightsList The list of flights to display.
     * @param selectedHour The selected hour to filter flights (use -1 for all flights).
     * @param selectedAirport The selected airport to filter flights (use null for all airports).
     * @param selectedLayer The selected layer to filter flights (use -1 for all layers).
     * @param newView The view associated with this controller.
     */
    public MapPanelController(AirportsList newAirportsList, FlightsList newFlightsList, int selectedHour, Airport selectedAirport, int selectedLayer, MapPanelView newView) {
        view = newView;

        airportsList = newAirportsList;
        flightsList = newFlightsList;

        fullFlightsList = newFlightsList;

        updateFlightsAirportsWithSelectedAirport(selectedAirport);
        updateFlightsAirportsWithSelectedHour(selectedHour);
        updateFlightsAirportsWithSelectedLayer(selectedLayer);

        // Add interactions
        MouseInputListener mouseListener = new PanMouseInputListener(view);
        view.addMouseListener(mouseListener);
        view.addMouseMotionListener(mouseListener);
        view.addMouseWheelListener(new ZoomMouseWheelListenerCursor(view));

        List<FlightPainter> flightPaintersList = createFlightPaintersList();
        createAirportWaypoints();

        WaypointPainter<MyWaypoint> waypointPainter = createAirportWaypointPainter();

        List<Painter<JXMapViewer>> painters = new ArrayList<>(flightPaintersList);
        painters.add(waypointPainter);

        CompoundPainter<JXMapViewer> painter = new CompoundPainter<>(painters);
        view.setOverlayPainter(painter);
    }

    /**
     * Retrieves the set of airport waypoints.
     *
     * @return The set of airport waypoints.
     */
    public Set<MyWaypoint> getAirportWaypoints() {
        return airportWaypoints;
    }

    /**
     * Updates flights and airports based on the selected airport.
     *
     * @param selectedAirport The selected airport to filter flights.
     */
    public void updateFlightsAirportsWithSelectedAirport(Airport selectedAirport) {
        if (selectedAirport != null) {
            ArrayList<Flight> filteredFlights = new ArrayList<>();

            for (Flight flight : flightsList.getFlightsList()) {
                if (flight.getDepAirport().equals(selectedAirport) ||
                        flight.getArrAirport().equals(selectedAirport)) {
                    filteredFlights.add(flight);
                }
            }
            flightsList = new FlightsList(filteredFlights);
        }

        airportsList = getAllAirportsWithFlights();
    }

    /**
     * Updates flights and airports based on the selected hour.
     *
     * @param selectedHour The selected hour to filter flights (use -1 for all flights).
     */
    public void updateFlightsAirportsWithSelectedHour(int selectedHour) {
        if (selectedHour == -1)
            flightsList = fullFlightsList;
        else if (selectedHour != -2) {
            ArrayList<Flight> filteredFlights = new ArrayList<>();

            for (Flight flight : flightsList.getFlightsList()) {
                if (currentFlightIsSelectedByHour(flight, selectedHour))
                    filteredFlights.add(flight);
            }

            flightsList = new FlightsList(filteredFlights);
        }

        airportsList = getAllAirportsWithFlights();
    }

    /**
     * Checks if a flight matches the selected hour criteria.
     *
     * @param flight The flight to check.
     * @param selectedHour The selected hour.
     * @return True if the flight matches the selected hour criteria, false otherwise.
     */
    private boolean currentFlightIsSelectedByHour(Flight flight, int selectedHour) {
        TimeTools hDeparture = new TimeTools();
        hDeparture.M_to_HM(flight.gethDeparture());

        int duration = flight.getDuration();

        TimeTools hArrival = new TimeTools();
        hArrival.M_to_HM(hDeparture.HM_to_M() + duration);

        return (hDeparture.getHours() <= selectedHour && selectedHour <= hArrival.getHours());
    }

    /**
     * Updates flights and airports based on the selected layer.
     *
     * @param selectedLayer The selected layer to filter flights (use -1 for all layers).
     */
    public void updateFlightsAirportsWithSelectedLayer(int selectedLayer) {
        if (selectedLayer == -1)
            flightsList = fullFlightsList;
        else if (selectedLayer != -2) {
            ArrayList<Flight> filteredFlights = new ArrayList<>();

            for (Flight flight : flightsList.getFlightsList()) {
                if (flight.getLayer() == selectedLayer)
                    filteredFlights.add(flight);
            }

            flightsList = new FlightsList(filteredFlights);
        }

        airportsList = getAllAirportsWithFlights();
    }

    /**
     * Retrieves a list of all airports with associated flights.
     *
     * @return The list of airports with associated flights.
     */
    public AirportsList getAllAirportsWithFlights() {
        AirportsList allAirportsWithFlight = new AirportsList();
        for (Flight flight : flightsList.getFlightsList()) {
            if (allAirportsWithFlight.doesntContain(flight.getDepAirport()))
                allAirportsWithFlight.add(flight.getDepAirport());

            if (allAirportsWithFlight.doesntContain(flight.getArrAirport()))
                allAirportsWithFlight.add(flight.getArrAirport());
        }
        return allAirportsWithFlight;
    }

    /**
     * Creates a list of flight painters based on the current flights list.
     *
     * @return The list of flight painters.
     */
    @SuppressWarnings("SameParameterValue")
    private List<FlightPainter> createFlightPaintersList() {
        List<FlightPainter> flightPaintersList = new ArrayList<>();
        Flight flight;
        for (int ii=0; ii < flightsList.size(); ii++) {
            flight = flightsList.get(ii);
            flightPaintersList.add(new FlightPainter(flightColor, hasAntiAlias, flight));
        }
        return flightPaintersList;
    }

    /**
     * Creates airport waypoints based on the current airports list.
     */
    private void createAirportWaypoints() {
        airportWaypoints = new HashSet<>();
        for (Airport airport : airportsList.getAirportsList()) {
            airportWaypoints.add(new MyWaypoint(airport));
        }
    }

    /**
     * Creates a waypoint painter for airports based on the current airport waypoints.
     *
     * @return The waypoint painter for airports.
     */
    private WaypointPainter<MyWaypoint> createAirportWaypointPainter() {
        WaypointPainter<MyWaypoint> waypointPainter = new WaypointRender();
        waypointPainter.setWaypoints(airportWaypoints);

        for (MyWaypoint waypoint : airportWaypoints) {
            view.add(waypoint.getButton());
        }

        return waypointPainter;
    }
}
