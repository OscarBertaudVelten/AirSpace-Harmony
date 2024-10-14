package ui.views;

import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import ui.frameTools.BackgroundImagePanel;

import javax.swing.*;
import java.awt.*;

import static ui.frameTools.Fonts.*;



/**
 * The HomeView class represents the main view of the application.
 * It extends JFrame and provides a user interface with various components.
 */
@SuppressWarnings("FieldCanBeLocal")
public class HomeView extends JFrame {

    /**
     * Constructs a new HomeView object.
     */
    @SuppressWarnings("unused")
    public HomeView() {
        initComponents();
    }

    /**
     * Initializes and configures all components of the HomeView.
     */
    private void initComponents() {
        GridBagConstraints gridBagConstraints;

        graphPanel = new JPanel();
        graphFileButton = new JButton();
        graphLabelPanel = new JPanel();
        graphFileLabel = new JLabel();
        graphWeightLabel = new JLabel();
        graphValiderButton = new JButton();
        titleLabel = new JLabel();
        airportFlightPanel = new JPanel();
        flightFileButton = new JButton();
        flightLabelPanel = new JPanel();
        flightFileLabel = new JLabel();
        flightWeightLabel = new JLabel();
        airportFileButton = new JButton();
        airportLabelPanel = new JPanel();
        airportFileLabel = new JLabel();
        airportWeightLabel = new JLabel();
        flightAirportValiderButton = new JButton();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel bgImagePanel = new BackgroundImagePanel();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(getToolkit().getScreenSize());
        setResizable(false);
        setContentPane(bgImagePanel);
        getContentPane().setLayout(new AbsoluteLayout());

        graphPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(textColor, 3),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        graphPanel.setLayout(new GridBagLayout());
        graphPanel.setBackground(new Color(0, 0, 0, 0));

        graphFileButton.setText("Choisir un fichier de graphes");
        graphFileButton.setForeground(textColor);
        graphFileButton.setFont(spaceGrotesk18);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(10, 0, 10, 0);
        graphPanel.add(graphFileButton, gridBagConstraints);

        graphLabelPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 25, 119), 3),
                BorderFactory.createEmptyBorder(2, 2, 2, 2)));
        graphLabelPanel.setLayout(new BorderLayout());
        graphLabelPanel.setBackground(new Color(223, 221, 240));

        graphFileLabel.setFont(spaceGrotesk18);
        graphFileLabel.setForeground(new Color(0, 0, 0, 60));
        graphLabelPanel.add(graphFileLabel, BorderLayout.WEST);
        graphFileLabel.setText("Fichier de graphes");
        graphFileLabel.getAccessibleContext().setAccessibleDescription("");

        graphWeightLabel.setFont(spaceGrotesk18);
        graphLabelPanel.add(graphWeightLabel, BorderLayout.EAST);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(0, 0, 10, 0);
        graphPanel.add(graphLabelPanel, gridBagConstraints);

        graphValiderButton.setFont(spaceGrotesk18);
        graphValiderButton.setText("Valider");
        graphValiderButton.setForeground(textColor);
        graphValiderButton.setEnabled(false);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.insets = new Insets(10, 0, 10, 0);
        graphPanel.add(graphValiderButton, gridBagConstraints);

        getContentPane().add(graphPanel, new AbsoluteConstraints(1050, 430, 330, -1));

        titleLabel.setFont(chakraPetch48);
        titleLabel.setForeground(textColor);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setText("AirSpace Harmony");
        getContentPane().add(titleLabel, new AbsoluteConstraints(739, 200, -1, -1));

        airportFlightPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(textColor, 3),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        airportFlightPanel.setLayout(new GridBagLayout());
        airportFlightPanel.setBackground(new Color(0, 0, 0, 0));




        airportFileButton.setFont(spaceGrotesk18);
        airportFileButton.setText("Choisir un fichier d'aeroports");
        airportFileButton.setForeground(textColor);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(10, 0, 10, 0);
        airportFlightPanel.add(airportFileButton, gridBagConstraints);

        airportLabelPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(textColor, 3),
                BorderFactory.createEmptyBorder(2, 2, 2, 2)));
        airportLabelPanel.setLayout(new java.awt.BorderLayout());
        airportLabelPanel.setBackground(new Color(228, 224, 242));

        airportFileLabel.setFont(spaceGrotesk18);
        airportFileLabel.setText("Fichier d'aeroports");
        airportFileLabel.setForeground(new Color(0, 0, 0, 60));
        airportLabelPanel.add(airportFileLabel, java.awt.BorderLayout.WEST);

        airportWeightLabel.setFont(spaceGrotesk18);
        airportLabelPanel.add(airportWeightLabel, java.awt.BorderLayout.EAST);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        airportFlightPanel.add(airportLabelPanel, gridBagConstraints);


        flightFileButton.setFont(spaceGrotesk18);
        flightFileButton.setText("Choisir un fichier de vols");
        flightFileButton.setForeground(textColor);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 20);
        airportFlightPanel.add(flightFileButton, gridBagConstraints);

        flightLabelPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(textColor, 3),
                BorderFactory.createEmptyBorder(2, 2, 2, 2)));
        flightLabelPanel.setLayout(new java.awt.BorderLayout());
        flightLabelPanel.setBackground(new Color(217, 217, 237));

        flightFileLabel.setFont(spaceGrotesk18);
        flightFileLabel.setText("Fichier de vols");
        flightFileLabel.setForeground(new Color(0, 0, 0, 60));
        flightLabelPanel.add(flightFileLabel, java.awt.BorderLayout.WEST);

        flightWeightLabel.setFont(spaceGrotesk18);
        airportLabelPanel.add(flightWeightLabel, java.awt.BorderLayout.LINE_END);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        airportFlightPanel.add(flightLabelPanel, gridBagConstraints);




        flightAirportValiderButton.setFont(spaceGrotesk18);
        flightAirportValiderButton.setText("Valider");
        flightAirportValiderButton.setForeground(textColor);
        flightAirportValiderButton.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        airportFlightPanel.add(flightAirportValiderButton, gridBagConstraints);

        getContentPane().add(airportFlightPanel, new AbsoluteConstraints(550, 380, 350, -1));
    }


    public JButton getGraphFileButton() { return graphFileButton; }

    public JLabel getAirportFileLabel() { return airportFileLabel; }

    public JButton getFlightAirportValiderButton() { return flightAirportValiderButton; }

    public JButton getGraphValiderButton() { return graphValiderButton; }

    public JLabel getFlightFileLabel() { return flightFileLabel; }

    public JLabel getGraphFileLabel() { return graphFileLabel; }

    public JButton getAirportFileButton() { return airportFileButton; }

    public JButton getFlightFileButton() { return flightFileButton; }


    private JButton airportFileButton;
    private JLabel airportFileLabel;
    private JPanel airportFlightPanel;
    private JPanel airportLabelPanel;
    private JLabel airportWeightLabel;
    private JButton flightFileButton;
    private JLabel flightFileLabel;
    private JPanel flightLabelPanel;
    private JLabel flightWeightLabel;
    private JButton graphFileButton;
    private JLabel graphFileLabel;
    private JPanel graphLabelPanel;
    private JPanel graphPanel;
    private JLabel graphWeightLabel;
    private JLabel titleLabel;
    private JButton graphValiderButton;
    private JButton flightAirportValiderButton;
}
