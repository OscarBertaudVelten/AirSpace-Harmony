package ui.jXMapViewer;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.WaypointPainter;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

/**
 * WaypointRenderer is a custom waypoint painter for rendering MyWaypoint objects on a JXMapViewer component.
 */
public class WaypointRender extends WaypointPainter<MyWaypoint> {

    /**
     * Renders each waypoint on the map.
     *
     * @param g      the Graphics2D object to paint on
     * @param map    the JXMapViewer component to render waypoints on
     * @param width  the width of the painting area
     * @param height the height of the painting area
     */
    @Override
    protected void doPaint(Graphics2D g, JXMapViewer map, int width, int height) {
        for (MyWaypoint waypoint : getWaypoints()) {

            // Convert geographic coordinates to pixel coordinates
            Point2D point = map.getTileFactory().geoToPixel(waypoint.getPosition(), map.getZoom());

            // Get the viewport bounds of the map
            Rectangle rec = map.getViewportBounds();

            // Calculate the on-screen position of the waypoint
            int x = (int) (point.getX() - rec.getX());
            int y = (int) (point.getY() - rec.getY());

            // Get the button associated with the waypoint
            JButton button = waypoint.getButton();

            // Set the location of the button relative to the waypoint position
            button.setLocation(x - button.getWidth() / 2, y - button.getHeight());
        }
    }
}
