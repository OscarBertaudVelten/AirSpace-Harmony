package ui.controllers;

import airports.Airport;
import airports.AirportsList;
import colorationAlgorithms.DSatur;
import flights.Flight;
import flights.FlightsList;
import graphTools.GraphPlus;
import graphTools.IntersectionGraph;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import ui.jXMapViewer.MapPanelController;
import ui.jXMapViewer.MapPanelView;
import ui.jXMapViewer.MyWaypoint;
import ui.views.ColoredFlightFileView;
import ui.views.ColoredFlightGraphView;
import ui.views.HomeView;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Set;

import static ui.frameTools.Fonts.textColor;

/**
 * Controller class for managing interactions with the Colored Flight File view.
 */
public class ColoredFlightFileController {

    /**
     * List of airports available.
     */
    final AirportsList airportsList;

    /**
     * List of flights available.
     */
    final FlightsList flightsList;

    /**
     * Instance of DSaturKMax algorithm for coloring flights.
     */
    DSatur dSatur;

    /**
     * Intersection graph of airports and flights.
     */
    IntersectionGraph intersectGraph;

    /**
     * Maximum number of colors (layers) for coloring flights.
     */
    @SuppressWarnings("CanBeFinal")
    int kMax;

    /**
     * Safety margin used in constructing the intersection graph.
     */
    double safetyMargin;

    /**
     * Number of layers determined by the coloring algorithm.
     */
    int nbLayers;

    /**
     * Chosen algorithm for coloring flights.
     */
    String chosenAlgo;

    /**
     * View component for the map panel.
     */
    MapPanelView mapView;

    /**
     * Controller for the map panel.
     */
    MapPanelController mapController;

    /**
     * Combo box for selecting airports.
     */
    JComboBox<Airport> airportComboBox;

    /**
     * Radio button for viewing all flights at a specific hour.
     */
    JRadioButton voirTousVolsParHRadio;

    /**
     * Spinner for selecting a specific hour to view flights.
     */
    JSpinner voirVolsCetHSpinner;

    /**
     * Radio button for viewing all flights for a specific airport.
     */
    JRadioButton voirTousVolsParAerRadio;

    /**
     * Button group for selecting flights by airport.
     */
    ButtonGroup voirVolsParAerButtonGroup;

    /**
     * Button group for selecting flights by hour.
     */
    ButtonGroup voirVolsParHButtonGroup;

    /**
     * Button group for selecting flights by layer.
     */
    ButtonGroup voirVolsLayerButtonGroup;

    /**
     * Radio button for viewing all flights in a specific layer.
     */
    JRadioButton voirTousVolsLayerRadio;

    /**
     * Spinner for selecting a specific layer to view flights.
     */
    JSpinner layerSpinner;

    /**
     * Reference to the associated view.
     */
    private final ColoredFlightFileView view;

    /**
     * Constructor for initializing the controller with necessary data.
     *
     * @param newAirportsList List of airports
     * @param newFlightsList  List of flights
     * @param newKMax         Maximum number of colors (layers)
     * @param newSafetyMargin Safety margin for constructing intersection graph
     * @param newChosenAlgo   Chosen algorithm for coloring flights
     * @param newView         Associated view
     */
    public ColoredFlightFileController(AirportsList newAirportsList, FlightsList newFlightsList, int newKMax,
                                       double newSafetyMargin, String newChosenAlgo,
                                       ColoredFlightFileView newView) {
        view = newView;

        airportsList = newAirportsList;
        flightsList = newFlightsList;

        kMax = newKMax;
        safetyMargin = newSafetyMargin;
        chosenAlgo = newChosenAlgo;

        mapView = view.getMapView();
        mapController = new MapPanelController(airportsList, flightsList, -1, null, -1, mapView);

        addMapViewerWaypointsListeners();

        airportComboBox = view.getAirportComboBox();
        airportComboBox.setModel(new DefaultComboBoxModel<>(mapController.getAllAirportsWithFlights().toArray()));

        voirTousVolsParHRadio = view.getVoirTousVolsParHRadio();
        voirVolsCetHSpinner = view.getVoirVolsCetHSpinner();
        voirTousVolsParAerRadio = view.getVoirTousVolsParAerRadio();
        voirVolsParAerButtonGroup = view.getVoirVolsParAerButtonGroup();
        voirVolsParHButtonGroup = view.getVoirVolsParHButtonGroup();
        voirVolsLayerButtonGroup = view.getVoirVolsLayerButtonGroup();
        layerSpinner = view.getLayerSpinner();
        voirTousVolsLayerRadio = view.getVoirTousVolsLayerRadio();

        voirTousVolsParAerRadio.addActionListener(voirTousVolsRadioListener());
        view.getVoirVolCetAerRadio().addActionListener(voirVolCetAerRadioListener());
        voirTousVolsParHRadio.addActionListener(voirTousVolsRadioListener());
        view.getVoirVolsCetHRadio().addActionListener(voirVolsCetHRadioListener());
        voirVolsCetHSpinner.addChangeListener(setVoirVolsCetHSpinnerListener());
        view.getCollisionsButton().addActionListener(collisionsButtonListener());
        view.getVoirVolsCetteLayerRadio().addActionListener(voirVolsCetteLayerRadioListener());
        voirTousVolsLayerRadio.addActionListener(voirTousVolsRadioListener());
        view.getSeeConflitsButton().addActionListener(seeConflictsListener());
        view.getInfoVolButton().addActionListener(infoVolButtonListener());
        view.getHomeButton().addActionListener(homeButtonListener());
        view.getVoirGrapheButton().addActionListener(voirGrapheListener());

        GraphPlus graph = new GraphPlus("tempGraph").loadFlightsAirportsList(airportsList, flightsList);
        intersectGraph = graph.constructIntersectGraph(safetyMargin);

        dSatur = new DSatur(intersectGraph, false);
        dSatur.setKMax(kMax);
        dSatur.dSaturAlgo();

        nbLayers = dSatur.getNbColors();

        view.getNbLayerLabel().setText("Nombre de couches : " + nbLayers);
        layerSpinner.setValue(nbLayers / 2);

        view.setSpinner(layerSpinner, (double) (nbLayers / 2) + 1, 1.0, nbLayers, new Color(227, 223, 241));

        view.getNbConflictsLabel().setText("Nombre de conflits : " + dSatur.getNbConflicts());

        setFlightLayers();
    }

    /**
     * ActionListener for home button, which navigates back to the home view.
     *
     * @return ActionListener for home button
     */
    private ActionListener homeButtonListener() {
        return evt -> {
            HomeView hView = new HomeView();
            new HomeController(hView);
            hView.setVisible(true);

            view.dispose();
        };
    }

    /**
     * Sets layers for each flight based on the DSatur coloring.
     */
    private void setFlightLayers() {
        int[] nodeColors = dSatur.getNodeColors();
        Flight flight;
        for (int ii = 0; ii < nodeColors.length; ii++) {
            flight = flightsList.get(ii);
            flight.setLayer(nodeColors[ii] + 1);
        }
    }

    /**
     * ActionListener for radio button to view flights for a specific airport.
     *
     * @return ActionListener for airport radio button
     */
    private ActionListener voirVolCetAerRadioListener() {
        return evt -> {
            view.getContentPane().remove(mapView);

            Airport selectedAirport = (Airport) airportComboBox.getSelectedItem();

            mapView = new MapPanelView();
            mapController = new MapPanelController(airportsList, flightsList, -2, selectedAirport, -2, mapView);

            voirTousVolsParHRadio.setSelected(true);
            voirTousVolsLayerRadio.setSelected(true);

            reloadMapViewer();
        };
    }

    /**
     * ActionListener for radio button to view flights for a specific hour.
     *
     * @return ActionListener for hour radio button
     */
    private ActionListener voirVolsCetHRadioListener() {
        return evt -> {
            view.getContentPane().remove(mapView);

            int selectedHour = ((Double) view.getVoirVolsCetHSpinner().getValue()).intValue();

            mapView = new MapPanelView();
            mapController = new MapPanelController(airportsList, flightsList, selectedHour, null, -2, mapView);

            voirTousVolsParAerRadio.setSelected(true);
            voirTousVolsLayerRadio.setSelected(true);

            reloadMapViewer();
        };
    }

    /**
     * ActionListener for radio button to view flights for a specific layer.
     *
     * @return ActionListener for layer radio button
     */
    private ActionListener voirVolsCetteLayerRadioListener() {
        return evt -> {
            view.getContentPane().remove(mapView);

            int selectedLayer = ((Double) view.getLayerSpinner().getValue()).intValue();

            mapView = new MapPanelView();
            mapController = new MapPanelController(airportsList, flightsList, -2, null, selectedLayer, mapView);

            voirTousVolsParHRadio.setSelected(true);
            voirTousVolsParAerRadio.setSelected(true);
            reloadMapViewer();
        };
    }

    /**
     * ActionListener for radio buttons to view all flights.
     *
     * @return ActionListener for radio button
     */
    private ActionListener voirTousVolsRadioListener() {
        return evt -> {
            view.getContentPane().remove(mapView);

            mapView = new MapPanelView();
            mapController = new MapPanelController(airportsList, flightsList, -1, null, -1, mapView);

            voirTousVolsParAerRadio.setSelected(true);
            voirTousVolsParHRadio.setSelected(true);
            voirTousVolsLayerRadio.setSelected(true);

            reloadMapViewer();
        };
    }

    /**
     * Reloads the map viewer component.
     */
    private void reloadMapViewer() {
        mapView.setBorder(javax.swing.BorderFactory.createLineBorder(textColor, 3));
        view.getContentPane().add(mapView, new AbsoluteConstraints(50, 100, 900, 800));
        addMapViewerWaypointsListeners();

        // Reload the Frame
        view.getContentPane().revalidate();
        view.getContentPane().repaint();
    }

    /**
     * Adds action listeners to map viewer waypoints.
     */
    public void addMapViewerWaypointsListeners() {
        Set<MyWaypoint> airportWaypoints = mapController.getAirportWaypoints();

        for (MyWaypoint myWaypoint : airportWaypoints) {
            myWaypoint.getButton().addActionListener(mapViewerWaypointsListener(myWaypoint));
        }
    }

    /**
     * ActionListener for map viewer waypoints.
     *
     * @param myWaypoint Waypoint associated with the action
     * @return ActionListener for map viewer waypoints
     */
    private ActionListener mapViewerWaypointsListener(MyWaypoint myWaypoint) {
        return evt -> {
            view.getContentPane().remove(mapView);

            Airport selectedAirport = myWaypoint.getAirport();

            mapView = new MapPanelView();
            mapController = new MapPanelController(airportsList, flightsList, -2, selectedAirport, -2, mapView);

            voirVolsParAerButtonGroup.clearSelection();
            voirVolsParHButtonGroup.clearSelection();
            voirVolsLayerButtonGroup.clearSelection();

            reloadMapViewer();
        };
    }

    /**
     * ChangeListener for spinner to select specific hour to view flights.
     *
     * @return ChangeListener for hour spinner
     */
    private ChangeListener setVoirVolsCetHSpinnerListener() {
        return e -> {
            int hour = ((Double) view.getVoirVolsCetHSpinner().getValue()).intValue();
            if (24 <= hour)
                voirVolsCetHSpinner.setValue(0);

            if (hour <= -1)
                voirVolsCetHSpinner.setValue(23);
        };
    }

    /**
     * ActionListener for button to view flight collisions.
     *
     * @return ActionListener for collisions button
     */
    private ActionListener collisionsButtonListener() {
        return evt -> {
            int kMax = ((Double) view.getKMaxSpinner().getValue()).intValue();
            double safetyMargin = (double) view.getMargeSecuriteSpinner().getValue();
            String chosenAlgo = (String) view.getAlgoComboBox().getSelectedItem();

            ColoredFlightFileView cffView = new ColoredFlightFileView();
            new ColoredFlightFileController(airportsList, flightsList, kMax, safetyMargin, chosenAlgo, cffView);
            cffView.setVisible(true);

            view.dispose();
        };
    }

    /**
     * ActionListener for button to view flight conflicts.
     *
     * @return ActionListener for conflicts button
     */
    private ActionListener seeConflictsListener() {
        return evt -> {
            // Define column names for the JTable
            String[] columnNames = {"Vol 1", "Vol 2"};

            // Create a DefaultTableModel with the column names
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);

            // Populate the model with data from indConflictsList
            ArrayList<int[]> indConflictsList = dSatur.getIndConflictsList();
            for (int[] indConflicts : indConflictsList) {
                Object[] row = {flightsList.get(indConflicts[0]), flightsList.get(indConflicts[1])};
                model.addRow(row);
            }

            // Create a JTable with the model
            JTable table = new JTable(model);

            // Create a JDialog and add the JTable inside a JScrollPane
            JDialog dialog = new JDialog();
            dialog.add(new JScrollPane(table));
            dialog.setSize(1000, 500);
            dialog.setVisible(true);
        };
    }

    /**
     * ActionListener for button to view flight information.
     *
     * @return ActionListener for info button
     */
    private ActionListener infoVolButtonListener() {
        return evt -> {
            JDialog dialog = new JDialog(view, "Informations des vols", true);
            InfoVolsTableModel flightTableModel = new InfoVolsTableModel(flightsList);
            JTable table = new JTable(flightTableModel);

            table.setBackground(new Color(224, 221, 240));
            table.getTableHeader().setBackground(new Color(174, 187, 219));

            JScrollPane scrollPane = new JScrollPane(table);
            dialog.add(scrollPane);
            dialog.setSize(1000, 600); // Adjusted size for better display
            dialog.setVisible(true);
        };
    }

    /**
     * ActionListener for button to view flight graph.
     *
     * @return ActionListener for graph button
     */
    private ActionListener voirGrapheListener() {
        return evt -> {
            ColoredFlightGraphView cfgView = new ColoredFlightGraphView(intersectGraph, dSatur);
            new ColoredFlightGraphController(cfgView, view, flightsList);
            cfgView.setVisible(true);

            view.setVisible(false);
        };
    }
}
