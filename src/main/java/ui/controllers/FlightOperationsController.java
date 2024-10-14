package ui.controllers;

import ui.views.ColoredFlightFileView;
import ui.views.FlightFileView;
import ui.views.FlightOperationsView;
import ui.views.HomeView;
import airports.AirportsList;
import flights.FlightsList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Controller class for managing flight operations view interactions.
 */
public class FlightOperationsController {

    private final FlightOperationsView view;
    private final AirportsList airportsList;
    private final FlightsList flightsList;

    /**
     * Constructor to initialize the controller with necessary dependencies.
     *
     * @param newAirportsList The list of airports.
     * @param newFlightsList The list of flights.
     * @param newView The view associated with flight operations.
     */
    public FlightOperationsController(AirportsList newAirportsList, FlightsList newFlightsList, FlightOperationsView newView) {
        view = newView;
        airportsList = newAirportsList;
        flightsList = newFlightsList;

        view.getHomeButton().addActionListener(homeButtonListener());
        view.getCarteButton().addActionListener(carteButtonListener());
        view.getInfoVolButton().addActionListener(infoVolButtonListener());
        view.getCollisionsButton().addActionListener(collisionsButtonListener());
    }

    /**
     * ActionListener for the home button.
     *
     * @return ActionListener instance for home button.
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
     * ActionListener for the carte button.
     *
     * @return ActionListener instance for carte button.
     */
    private ActionListener carteButtonListener() {
        return evt -> {
            FlightFileView ffView = new FlightFileView();
            new FlightFileController(airportsList, flightsList, ffView);
            ffView.setVisible(true);

            view.dispose();
        };
    }

    /**
     * ActionListener for the infoVol button.
     *
     * @return ActionListener instance for infoVol button.
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
     * ActionListener for the collisions button.
     *
     * @return ActionListener instance for collisions button.
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
}
