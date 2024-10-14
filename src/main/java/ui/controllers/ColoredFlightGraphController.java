package ui.controllers;

import flights.FlightsList;
import ui.views.ColoredFlightFileView;
import ui.views.ColoredFlightGraphView;
import ui.views.HomeView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Controller class for managing interactions between the ColoredFlightGraphView, ColoredFlightFileView,
 * and related data structures.
 */
public class ColoredFlightGraphController {
    private final ColoredFlightGraphView view;
    private final ColoredFlightFileView mapView;
    private final FlightsList flightsList;

    /**
     * Constructor to initialize the controller with necessary views and data.
     *
     * @param newView    The ColoredFlightGraphView instance.
     * @param newMapView The ColoredFlightFileView instance.
     * @param newFlightsList The FlightsList instance containing flight data.
     */
    public ColoredFlightGraphController(ColoredFlightGraphView newView, ColoredFlightFileView newMapView,
                                        FlightsList newFlightsList) {
        view = newView;
        mapView = newMapView;
        flightsList = newFlightsList;


        // Add action listeners to buttons in view
        view.getHomeButton().addActionListener(homeButtonActionListener());
        view.getRetourCarteButton().addActionListener(retourCarteButtonListener());
        view.getSeeConflitsButton().addActionListener(seeConflictsListener());

    }

    /**
     * ActionListener for the Home button.
     *
     * @return ActionListener instance.
     */
    private ActionListener homeButtonActionListener() {
        return evt -> {
            // Create a new HomeView instance
            HomeView hView = new HomeView();
            // Initialize HomeController with the view
            new HomeController(hView);
            // Set the HomeView visible
            hView.setVisible(true);
            // Dispose the current ColoredFlightGraphView
            view.dispose();
        };
    }

    /**
     * ActionListener for the Retour Carte button.
     *
     * @return ActionListener instance.
     */
    private ActionListener retourCarteButtonListener() {
        return evt -> {
            // Set the mapView visible
            mapView.setVisible(true);
            // Dispose the current ColoredFlightGraphView
            view.dispose();
        };
    }

    /**
     * ActionListener for the See Conflicts button.
     *
     * @return ActionListener instance.
     */
    private ActionListener seeConflictsListener() {
        return evt -> {
            // Define column names for the JTable
            String[] columnNames = {"Vol 1", "Vol 2"};

            // Create a DefaultTableModel with the column names
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);

            ArrayList<int[]> indConflictsList;
            // Populate the model with data from indConflictsList
            if (view.getdSatur() != null) {
                indConflictsList = view.getdSatur().getIndConflictsList();
            } else {
                indConflictsList = view.getWp().getIndConflictsList();
            }
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
}
