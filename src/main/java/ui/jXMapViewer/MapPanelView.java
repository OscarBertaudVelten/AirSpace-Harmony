package ui.jXMapViewer;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;

/**
 * A customized map panel view based on JXMapViewer that initializes with OpenStreetMap tiles.
 */
public class MapPanelView extends JXMapViewer {

    /**
     * Constructs a new MapPanelView with default settings.
     * Initializes the map with OpenStreetMap tiles centered at a default location.
     */
    public MapPanelView() {
        // Create a TileFactoryInfo for OpenStreetMap
        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        setTileFactory(tileFactory);

        // Set the default zoom level and center location
        setZoom(13);
        setAddressLocation(new GeoPosition(46.5, 2));
    }
}
