package ui.controllers;

import airports.Airport;
import airports.AirportsList;
import flights.FlightsList;
import graphTools.GraphPlus;
import ui.views.FlightOperationsView;
import ui.views.GraphFileView;
import ui.views.HomeView;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.File;

import static ui.frameTools.Fonts.textColor;

/**
 * Controller class for handling actions and events in the HomeView.
 */
public class HomeController {

    // Directory where data files are located
    final String baseFileDirectory = "C:\\All\\sae_collisions_vols\\Java_SAE_Collisions\\";
    //final String baseFileDirectory = "D:\\sae_collisions_vols\\Java_SAE_Collisions\\Data Test\\";

    // Labels for displaying selected file names
    @SuppressWarnings("CanBeFinal")
    JLabel airportFileLabel;

    @SuppressWarnings("CanBeFinal")
    JLabel flightFileLabel;

    @SuppressWarnings("CanBeFinal")
    JLabel graphFileLabel;

    private final HomeView view;

    private FlightsList flightsList;

    private AirportsList airportsList;

    private GraphPlus graph;

    /**
     * Constructor for HomeController.
     *
     * @param newView The HomeView instance associated with this controller.
     */
    public HomeController(HomeView newView) {
        view = newView;

        airportFileLabel = view.getAirportFileLabel();
        flightFileLabel = view.getFlightFileLabel();
        graphFileLabel = view.getGraphFileLabel();

        // Adding action listeners to buttons in HomeView
        view.getAirportFileButton().addActionListener(airportFileButtonActionListener());
        view.getFlightFileButton().addActionListener(flightFileButtonActionListener());
        view.getFlightAirportValiderButton().addActionListener(flightAirportValiderButtonActionListener());
        view.getGraphFileButton().addActionListener(graphFileButtonActionListener());
        view.getGraphValiderButton().addActionListener(graphValiderButtonActionListener());

        view.getFlightFileButton().setEnabled(false);
    }

    /**
     * ActionListener for airport file selection button.
     *
     * @return ActionListener instance for airport file button.
     */
    private ActionListener airportFileButtonActionListener() {
        return evt -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Choisir un fichier d'aeroports");

            File baseDirectory = new File(baseFileDirectory);
            fileChooser.setCurrentDirectory(baseDirectory);

            int result = fileChooser.showOpenDialog(view);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();

                try {
                    airportsList = new AirportsList();
                    airportsList.loadAirportsFile(selectedFile);

                    airportFileLabel.setText(selectedFile.getName());
                    airportFileLabel.setForeground(textColor);

                    view.getFlightFileButton().setEnabled(true);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(view, "Mauvais type de fichier, Veuillez en choisir un autre", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
    }

    /**
     * ActionListener for flight file selection button.
     *
     * @return ActionListener instance for flight file button.
     */
    private ActionListener flightFileButtonActionListener() {
        return evt -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Choisir un fichier de vols");

            File baseDirectory = new File(baseFileDirectory);
            fileChooser.setCurrentDirectory(baseDirectory);

            int result = fileChooser.showOpenDialog(view);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();

                try {
                    flightsList = new FlightsList();
                    flightsList.loadFlightsFile(selectedFile, airportsList);

                    flightFileLabel.setText(selectedFile.getName());
                    flightFileLabel.setForeground(textColor);

                    view.getFlightAirportValiderButton().setEnabled(true);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(view, "Mauvais type de fichier, Veuillez en choisir un autre", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
    }

    /**
     * ActionListener for validating flight and airport selection.
     *
     * @return ActionListener instance for flight and airport validation button.
     */
    private ActionListener flightAirportValiderButtonActionListener() {
        return evt -> {
            if (airportsList != null && flightsList != null) {
                FlightOperationsView foView = new FlightOperationsView();
                new FlightOperationsController(airportsList, flightsList, foView);
                foView.setVisible(true);

                view.dispose();
            } else {
                JOptionPane.showMessageDialog(view, "Veuillez séléctionner les fichiers", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        };
    }

    /**
     * ActionListener for graph file selection button.
     *
     * @return ActionListener instance for graph file button.
     */
    private ActionListener graphFileButtonActionListener() {
        return evt -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Choisir un fichier de graphe");

            File baseDirectory = new File(baseFileDirectory);
            fileChooser.setCurrentDirectory(baseDirectory);

            int result = fileChooser.showOpenDialog(view);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();

                graph = new GraphPlus("");
                try {
                    graph.loadTestGraph(selectedFile);

                    graphFileLabel.setText(selectedFile.getName());
                    graphFileLabel.setForeground(textColor);

                    view.getGraphValiderButton().setEnabled(true);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(view, "Mauvais type de fichier, Veuillez en choisir un autre", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
    }

    /**
     * ActionListener for validating graph selection.
     *
     * @return ActionListener instance for graph validation button.
     */
    private ActionListener graphValiderButtonActionListener() {
        return e -> {
            GraphFileView gfView = new GraphFileView(graph);
            new GraphFileController(gfView);
            gfView.setVisible(true);

            view.dispose();
        };
    }
}
