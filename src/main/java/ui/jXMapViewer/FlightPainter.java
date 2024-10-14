package ui.jXMapViewer;

import flights.Flight;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.painter.Painter;
import org.jxmapviewer.viewer.GeoPosition;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * A custom painter implementation for drawing a flight path on a JXMapViewer component.
 */
public class FlightPainter implements Painter<JXMapViewer> {
    private final Color color;
    private final boolean hasAntiAlias;
    private final GeoPosition depGeoPosition;
    private final GeoPosition arrGeoPosition;

    /**
     * Constructs a FlightPainter with specified color, anti-aliasing setting, and flight details.
     *
     * @param newColor      The color to use for drawing the flight path.
     * @param newHasAntiAlias True if anti-aliasing should be enabled, false otherwise.
     * @param flight        The flight object containing departure and arrival airport information.
     */
    public FlightPainter(Color newColor, boolean newHasAntiAlias, Flight flight) {
        this.color = newColor;
        this.hasAntiAlias = newHasAntiAlias;
        this.depGeoPosition = flight.getDepAirport().getGeoPosition();
        this.arrGeoPosition = flight.getArrAirport().getGeoPosition();
    }

    /**
     * Paints the flight path on the map.
     *
     * @param graphics The graphics context for painting.
     * @param map      The JXMapViewer component to paint on.
     * @param w        The width of the painting area.
     * @param h        The height of the painting area.
     */
    @Override
    public void paint(Graphics2D graphics, JXMapViewer map, int w, int h) {
        graphics = (Graphics2D) graphics.create();

        Rectangle rect = map.getViewportBounds();
        graphics.translate(-rect.x, -rect.y);

        if (hasAntiAlias)
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics.setColor(color);
        graphics.setStroke(new BasicStroke(1));

        drawFlight(graphics, map);

        graphics.dispose();
    }

    /**
     * Draws the flight path between departure and arrival airports on the map.
     *
     * @param graphics The graphics context for drawing.
     * @param map      The JXMapViewer component to draw on.
     */
    private void drawFlight(Graphics2D graphics, JXMapViewer map) {
        Point2D depPoint = map.getTileFactory().geoToPixel(depGeoPosition, map.getZoom());
        Point2D arrPoint = map.getTileFactory().geoToPixel(arrGeoPosition, map.getZoom());

        graphics.drawLine((int) depPoint.getX(), (int) depPoint.getY(), (int) arrPoint.getX(), (int) arrPoint.getY());
    }
}
