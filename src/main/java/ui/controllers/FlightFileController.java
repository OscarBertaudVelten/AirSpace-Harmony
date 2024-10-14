package ui.controllers;

import airports.Airport;
import airports.AirportsList;
import flights.FlightsList;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import ui.jXMapViewer.MapPanelController;
import ui.jXMapViewer.MapPanelView;
import ui.jXMapViewer.MyWaypoint;
import ui.views.ColoredFlightFileView;
import ui.views.FlightFileView;
import ui.views.HomeView;

import javax.swing.*;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Set;

import static ui.frameTools.Fonts.textColor;

/**
 * Controller class for managing interactions with FlightFileView.
 */
public class FlightFileController {

    final AirportsList airportsList;
    final FlightsList flightsList;

    @SuppressWarnings("CanBeFinal")
    double nbCouches;

    MapPanelView mapView;
    MapPanelController mapController;

    final JComboBox<Airport> airportComboBox;
    final JRadioButton voirTousVolsParHRadio;
    final JSpinner voirVolsCetHSpinner;
    final JRadioButton voirTousVolsParAerRadio;
    final ButtonGroup voirVolsParAerButtonGroup;
    final ButtonGroup voirVolsParHButtonGroup;

    private final FlightFileView view;

    /**
     * Constructor to initialize FlightFileController.
     *
     * @param newAirportsList AirportsList object containing the list of airports.
     * @param newFlightsList  FlightsList object containing the list of flights.
     * @param newView         FlightFileView object representing the view associated with this controller.
     */
    public FlightFileController(AirportsList newAirportsList, FlightsList newFlightsList, FlightFileView newView) {
        view = newView;
        airportsList = newAirportsList;
        flightsList = newFlightsList;
        nbCouches = 12;

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

        voirTousVolsParAerRadio.addActionListener(voirTousVolsRadioListener());
        view.getVoirVolCetAerRadio().addActionListener(voirVolCetAerRadioListener());
        voirTousVolsParHRadio.addActionListener(voirTousVolsRadioListener());
        view.getVoirVolsCetHRadio().addActionListener(voirVolsCetHRadioListener());
        voirVolsCetHSpinner.addChangeListener(voirVolsCetHSpinnerListener());
        view.getCollisionsButton().addActionListener(collisionsButtonListener());
        view.getInfoVolButton().addActionListener(infoVolButtonListener());
        view.getHomeButton().addActionListener(homeButtonListener());
    }

    /**
     * ActionListener for the homeButton in the view.
     *
     * @return ActionListener for homeButton.
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
     * ActionListener for voirVolCetAerRadio in the view.
     *
     * @return ActionListener for voirVolCetAerRadio.
     */
    private ActionListener voirVolCetAerRadioListener() {
        return evt -> {
            view.getContentPane().remove(mapView);
            Airport selectedAirport = (Airport) airportComboBox.getSelectedItem();
            mapView = new MapPanelView();
            mapController = new MapPanelController(airportsList, flightsList, -2, selectedAirport, -2, mapView);
            voirTousVolsParHRadio.setSelected(true);
            reloadMapViewer();
        };
    }

    /**
     * ActionListener for voirVolsCetHRadio in the view.
     *
     * @return ActionListener for voirVolsCetHRadio.
     */
    private ActionListener voirVolsCetHRadioListener() {
        return evt -> {
            view.getContentPane().remove(mapView);
            int selectedHour = ((Double) voirVolsCetHSpinner.getValue()).intValue();
            mapView = new MapPanelView();
            mapController = new MapPanelController(airportsList, flightsList, selectedHour, null, -2, mapView);
            voirTousVolsParAerRadio.setSelected(true);
            reloadMapViewer();
        };
    }

    /**
     * ActionListener for voirTousVolsParHRadio and voirTousVolsParAerRadio in the view.
     *
     * @return ActionListener for voirTousVolsRadio.
     */
    private ActionListener voirTousVolsRadioListener() {
        return evt -> {
            view.getContentPane().remove(mapView);
            mapView = new MapPanelView();
            mapController = new MapPanelController(airportsList, flightsList, -2, null, -2, mapView);
            voirTousVolsParAerRadio.setSelected(true);
            voirTousVolsParHRadio.setSelected(true);
            reloadMapViewer();
        };
    }

    /**
     * Reloads the map viewer with updated data and settings.
     */
    private void reloadMapViewer() {
        mapView.setBorder(javax.swing.BorderFactory.createLineBorder(textColor, 3));
        view.getContentPane().add(mapView, new AbsoluteConstraints(50, 100, 900, 800));
        addMapViewerWaypointsListeners();
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
     * @param myWaypoint The MyWaypoint object associated with the action.
     * @return ActionListener for map viewer waypoints.
     */
    private ActionListener mapViewerWaypointsListener(MyWaypoint myWaypoint) {
        return evt -> {
            view.getContentPane().remove(mapView);
            Airport selectedAirport = myWaypoint.getAirport();
            mapView = new MapPanelView();
            mapController = new MapPanelController(airportsList, flightsList, -2, selectedAirport, -2, mapView);
            voirVolsParAerButtonGroup.clearSelection();
            voirVolsParHButtonGroup.clearSelection();
            reloadMapViewer();
        };
    }

    /**
     * ChangeListener for voirVolsCetHSpinner in the view.
     *
     * @return ChangeListener for voirVolsCetHSpinner.
     */
    private ChangeListener voirVolsCetHSpinnerListener() {
        return evt -> {
            int value = (int) voirVolsCetHSpinner.getValue();
            if (value >= 24)
                voirVolsCetHSpinner.setValue(0);
            if (value <= -1)
                voirVolsCetHSpinner.setValue(23);
        };
    }

    /**
     * ActionListener for collisionsButton in the view.
     *
     * @return ActionListener for collisionsButton.
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
     * ActionListener for infoVolButton in the view.
     *
     * @return ActionListener for infoVolButton.
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
}
