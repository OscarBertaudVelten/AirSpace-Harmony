package ui.jXMapViewer;

import airports.Airport;
import org.jxmapviewer.viewer.DefaultWaypoint;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * Extends DefaultWaypoint to represent a waypoint associated with an airport and a clickable button.
 */
public class MyWaypoint extends DefaultWaypoint {

    private final Airport airport;
    private JButton button;

    /**
     * Constructs a MyWaypoint object with the specified airport.
     *
     * @param newAirport the airport associated with this waypoint
     */
    public MyWaypoint(Airport newAirport) {
        super(newAirport.getGeoPosition());
        this.airport = newAirport;
        createButton();
    }

    /**
     * Retrieves the airport associated with this waypoint.
     *
     * @return the airport object
     */
    public Airport getAirport() {
        return airport;
    }

    /**
     * Retrieves the button associated with this waypoint.
     *
     * @return the JButton object representing the button associated with this waypoint
     */
    public JButton getButton() {
        return button;
    }

    /**
     * Creates and configures the button associated with this waypoint.
     */
    private void createButton() {
        button = new JButton();
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/Images/airport_icon.png"))));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setSize(new Dimension(24, 24));
    }
}
