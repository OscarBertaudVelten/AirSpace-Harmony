package ui.views;

import airports.Airport;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import ui.frameTools.BackgroundImagePanel;
import ui.frameTools.CurrentTimeLabel;
import ui.jXMapViewer.MapPanelView;

import javax.swing.*;
import java.awt.*;

import static ui.frameTools.Fonts.spaceGrotesk18;
import static ui.frameTools.Fonts.textColor;

/**
 * JFrame subclass representing the main view for flight files.
 */
@SuppressWarnings("FieldCanBeLocal")
public class FlightFileView extends JFrame {

    /**
     * The map panel view for displaying airport locations.
     */
    @SuppressWarnings("unused")
    MapPanelView mapView;

    /**
     * Initializes the FlightFileView by calling initComponents().
     */
    public FlightFileView() {
        initComponents();
    }

    /**
     * Initializes all components used in the FlightFileView.
     * Includes setting up button groups, labels, and panels.
     */
    private void initComponents() {

        voirVolsParHButtonGroup = new ButtonGroup();
        voirVolsParAerButtonGroup = new ButtonGroup();
        timeLabel = new CurrentTimeLabel();
        homeButton = new JButton();
        airportPanel = new JPanel();
        selecAeroportPanel = new JPanel();
        airportLabel = new JLabel();
        aerSouhaiteLabel = new JLabel();
        voirTousVolsParAerRadio = new JRadioButton();
        voirVolCetAerRadio = new JRadioButton();
        traiterCollisionsPanel = new JPanel();
        margeSecuritePanel = new JPanel();
        margeSecuriteLabel = new JLabel();
        margeSecuriteSpinner = new JSpinner();
        kmaxPanel = new JPanel();
        kmaxLabel = new JLabel();
        kmaxSpinner = new JSpinner();
        algoPanel = new JPanel();
        algoLabel = new JLabel();
        algoComboBox = new JComboBox<>();
        collisionsButton = new JButton();
        voirVolsPanel = new JPanel();
        voirTousVolsParHRadio = new JRadioButton();
        voirVolsCetHPanel = new JPanel();
        voirVolsCetHRadio = new JRadioButton();
        voirVolsCetHSpinner = new JSpinner();
        hLabel = new JLabel();
        separatorPanel = new JPanel();
        infoVolButton = new JButton();
        mapView = new MapPanelView();
        minLabel = new JLabel();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel bgImagePanel = new BackgroundImagePanel();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(getToolkit().getScreenSize());
        setResizable(false);
        setContentPane(bgImagePanel);
        getContentPane().setLayout(new AbsoluteLayout());

        timeLabel.setFont(spaceGrotesk18);
        timeLabel.setForeground(textColor);
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        timeLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(textColor, 3),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        getContentPane().add(timeLabel, new AbsoluteConstraints(50, 40, 140, 40));

        homeButton.setFont(spaceGrotesk18);
        homeButton.setForeground(textColor);
        homeButton.setText("Accueil");
        getContentPane().add(homeButton, new AbsoluteConstraints(1730, 40, 140, 40));

        assert mapView != null;
        mapView.setBorder(BorderFactory.createLineBorder(textColor, 3));
        getContentPane().add(mapView, new AbsoluteConstraints(50, 100, 900, 800));

        airportPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(textColor, 3),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        airportPanel.setLayout(new AbsoluteLayout());
        airportPanel.setBackground(new Color(238, 231, 246));

        selecAeroportPanel.setLayout(new BorderLayout(40, 0));

        airportLabel.setFont(spaceGrotesk18);
        airportLabel.setText("Selectionner un aéroport : ");
        airportLabel.setForeground(textColor);
        selecAeroportPanel.add(airportLabel, BorderLayout.WEST);
        selecAeroportPanel.setBackground(new Color(0,0,0,0));

        airportComboBox = new JComboBox<>();
        airportComboBox.setFont(spaceGrotesk18);
        airportComboBox.setForeground(textColor);
        airportComboBox.setBackground(new Color(238, 231, 246));
        selecAeroportPanel.add(airportComboBox, BorderLayout.CENTER);

        airportPanel.add(selecAeroportPanel, new AbsoluteConstraints(40, 20, 600, 40));
        aerSouhaiteLabel.setFont(spaceGrotesk18);
        aerSouhaiteLabel.setText("(Ou cliquer sur l'aeroport souhaité)");
        aerSouhaiteLabel.setForeground(textColor);
        airportPanel.add(aerSouhaiteLabel, new AbsoluteConstraints(220, 70, -1, -1));

        voirVolsParAerButtonGroup.add(voirTousVolsParAerRadio);
        voirVolsParAerButtonGroup.setSelected(voirTousVolsParAerRadio.getModel(), true);

        voirTousVolsParAerRadio.setFont(spaceGrotesk18);
        voirTousVolsParAerRadio.setForeground(textColor);
        voirTousVolsParAerRadio.setText("Voir tous les vols");
        voirTousVolsParAerRadio.setBackground(new Color(238, 231, 246));
        assert voirTousVolsParAerRadio != null;
        assert airportPanel != null;
        airportPanel.add(voirTousVolsParAerRadio, new AbsoluteConstraints(40, 150, -1, 30));

        assert voirVolsParAerButtonGroup != null;
        voirVolsParAerButtonGroup.add(voirVolCetAerRadio);
        assert voirVolCetAerRadio != null;
        voirVolCetAerRadio.setFont(spaceGrotesk18);
        voirVolCetAerRadio.setForeground(textColor);
        voirVolCetAerRadio.setText("Voir les vols de cet aéroport");
        voirVolCetAerRadio.setBackground(new Color(238, 231, 246));
        assert airportPanel != null;
        assert voirVolCetAerRadio != null;
        airportPanel.add(voirVolCetAerRadio, new AbsoluteConstraints(40, 110, -1, 30));

        getContentPane().add(airportPanel, new AbsoluteConstraints(1050, 320, 700, 200));

        assert traiterCollisionsPanel != null;
        traiterCollisionsPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(textColor, 3),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        traiterCollisionsPanel.setLayout(new AbsoluteLayout());
        traiterCollisionsPanel.setBackground(new Color(196, 202, 228));

        assert margeSecuritePanel != null;
        margeSecuritePanel.setLayout(new BorderLayout(20, 0));
        margeSecuritePanel.setBackground(new Color(196, 202, 228));

        assert margeSecuriteLabel != null;
        margeSecuriteLabel.setFont(spaceGrotesk18);
        margeSecuriteLabel.setText("Marge de sécurité : ");
        margeSecuriteLabel.setForeground(textColor);
        margeSecuritePanel.add(margeSecuriteLabel, BorderLayout.WEST);

        setSpinner(margeSecuriteSpinner, 15.0, 1.0, 1000.0, new Color(196, 202, 228));

        margeSecuritePanel.add(margeSecuriteSpinner, BorderLayout.CENTER);

        traiterCollisionsPanel.add(margeSecuritePanel, new AbsoluteConstraints(40, 90, 260, 40));

        assert minLabel != null;
        minLabel.setFont(spaceGrotesk18);
        minLabel.setText("min");
        minLabel.setForeground(textColor);
        traiterCollisionsPanel.add(minLabel, new AbsoluteConstraints(310, 90, 255, 40));

        assert kmaxPanel != null;
        kmaxPanel.setLayout(new BorderLayout(20, 0));
        kmaxPanel.setBackground(new Color(0,0,0,0));

        assert kmaxLabel != null;
        kmaxLabel.setFont(spaceGrotesk18);
        kmaxLabel.setText("Nombre de couches max : ");
        kmaxLabel.setForeground(textColor);
        kmaxPanel.add(kmaxLabel, BorderLayout.WEST);

        assert kmaxSpinner != null;

        setSpinner(kmaxSpinner, 5.0, 1.0, 1000.0, new Color(196, 202, 228));

        kmaxPanel.add(kmaxSpinner, BorderLayout.CENTER);

        traiterCollisionsPanel.add(kmaxPanel, new AbsoluteConstraints(40, 30, 310, 40));

        assert algoPanel != null;
        algoPanel.setLayout(new BorderLayout(40, 0));
        algoPanel.setBackground(new Color(0,0,0,0));

        assert algoLabel != null;
        algoLabel.setFont(spaceGrotesk18);
        algoLabel.setText("Algorithme : ");
        algoLabel.setForeground(textColor);
        algoPanel.add(algoLabel, BorderLayout.WEST);

        assert algoComboBox != null;
        algoComboBox.setFont(spaceGrotesk18);
        algoComboBox.setForeground(textColor);
        algoComboBox.setModel(new DefaultComboBoxModel<>(new String[] {"Welsh Powell", "DSatur"}));
        algoComboBox.setBackground(new Color(196, 202, 228));
        algoPanel.add(algoComboBox, BorderLayout.CENTER);

        traiterCollisionsPanel.add(algoPanel, new AbsoluteConstraints(40, 150, 600, 40));

        assert collisionsButton != null;
        collisionsButton.setFont(spaceGrotesk18);
        collisionsButton.setForeground(textColor);
        collisionsButton.setText("Traiter les collisions");
        traiterCollisionsPanel.add(collisionsButton, new AbsoluteConstraints(250, 220, 210, 40));

        getContentPane().add(traiterCollisionsPanel, new AbsoluteConstraints(1050, 610, 700, 290));

        assert voirVolsPanel != null;
        voirVolsPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(textColor, 3),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        voirVolsPanel.setLayout(new AbsoluteLayout());
        voirVolsPanel.setBackground(new Color(0,0,0,0));

        assert voirVolsParHButtonGroup != null;
        voirVolsParHButtonGroup.add(voirTousVolsParHRadio);
        voirVolsParHButtonGroup.setSelected(voirTousVolsParHRadio.getModel(), true);

        assert voirTousVolsParHRadio != null;
        voirTousVolsParHRadio.setFont(spaceGrotesk18);
        voirTousVolsParHRadio.setForeground(textColor);
        voirTousVolsParHRadio.setText("Voir tous les vols");
        voirTousVolsParHRadio.setBackground(new Color(173, 186, 218));
        assert voirVolsPanel != null;
        assert voirTousVolsParHRadio != null;
        voirVolsPanel.add(voirTousVolsParHRadio, new AbsoluteConstraints(260, 10, 180, 60));


        assert voirVolsCetHPanel != null;
        voirVolsCetHPanel.setLayout(new BoxLayout(voirVolsCetHPanel, BoxLayout.LINE_AXIS));
        voirVolsCetHPanel.setBackground(new Color(0,0,0,0));

        assert voirVolsParHButtonGroup != null;
        voirVolsParHButtonGroup.add(voirVolsCetHRadio);
        assert voirVolsCetHRadio != null;
        voirVolsCetHRadio.setFont(spaceGrotesk18);
        voirVolsCetHRadio.setForeground(textColor);
        voirVolsCetHRadio.setText("Voir les vols a : ");
        voirVolsCetHRadio.setBackground(new Color(173, 186, 218));
        assert voirVolsCetHPanel != null;
        voirVolsCetHPanel.add(voirVolsCetHRadio);

        assert voirVolsCetHSpinner != null;

        setSpinner(voirVolsCetHSpinner, 11.0, -1.0, 24.0, new Color(173, 186, 218));

        voirVolsCetHPanel.add(voirVolsCetHSpinner);

        assert hLabel != null;
        hLabel.setFont(spaceGrotesk18);
        hLabel.setText(" h");
        hLabel.setForeground(textColor);
        voirVolsCetHPanel.add(hLabel);

        assert voirVolsPanel != null;
        voirVolsPanel.add(voirVolsCetHPanel, new AbsoluteConstraints(10, 10, 230, 60));

        assert separatorPanel != null;
        separatorPanel.setBackground(textColor);
        separatorPanel.setMaximumSize(new Dimension(3, 32767));
        separatorPanel.setMinimumSize(new Dimension(3, 10));
        separatorPanel.setPreferredSize(new Dimension(3, 100));
        voirVolsPanel.add(separatorPanel, new AbsoluteConstraints(253, 0, 3, 80));

        getContentPane().add(voirVolsPanel, new AbsoluteConstraints(240, 920, 460, 80));

        assert infoVolButton != null;
        infoVolButton.setFont(spaceGrotesk18);
        infoVolButton.setForeground(textColor);
        infoVolButton.setText("Informations des vols");
        getContentPane().add(infoVolButton, new AbsoluteConstraints(1190, 175, 400, 50));
    }


    public void setSpinner(JSpinner spinner, double value, double min, double max, Color color) {
        assert spinner != null;

        spinner.setModel(new SpinnerNumberModel(value, min, max, 1));
        spinner.setFont(spaceGrotesk18);
        spinner.setForeground(textColor);
        spinner.getEditor().getComponent(0).setBackground(color);
        spinner.getEditor().getComponent(0).setForeground(textColor);
        spinner.getComponent(0).setBackground(color);
        spinner.getComponent(0).setForeground(textColor);
        spinner.getComponent(1).setBackground(color);
        spinner.getComponent(1).setForeground(textColor);
    }


    public MapPanelView getMapView() { return mapView; }

    public JComboBox<Airport> getAirportComboBox() { return airportComboBox; }

    public JRadioButton getVoirTousVolsParHRadio() { return voirTousVolsParHRadio; }

    public JSpinner getVoirVolsCetHSpinner() { return voirVolsCetHSpinner; }

    public JRadioButton getVoirTousVolsParAerRadio() { return voirTousVolsParAerRadio; }

    public ButtonGroup getVoirVolsParAerButtonGroup() { return voirVolsParAerButtonGroup; }

    public ButtonGroup getVoirVolsParHButtonGroup() { return voirVolsParHButtonGroup; }

    public JRadioButton getVoirVolCetAerRadio() { return voirVolCetAerRadio; }

    public JRadioButton getVoirVolsCetHRadio() { return voirVolsCetHRadio; }

    public JSpinner getKMaxSpinner() { return kmaxSpinner; }

    public JSpinner getMargeSecuriteSpinner() { return margeSecuriteSpinner; }

    public JComboBox<String> getAlgoComboBox() { return algoComboBox; }

    public JButton getCollisionsButton() { return collisionsButton; }

    public JButton getInfoVolButton() { return infoVolButton; }

    public JButton getHomeButton() { return homeButton; }


    private JLabel aerSouhaiteLabel;
    private JComboBox<Airport> airportComboBox;
    private JLabel airportLabel;
    private JPanel airportPanel;
    private JComboBox<String> algoComboBox;
    private JLabel algoLabel;
    private JPanel algoPanel;
    private JButton collisionsButton;
    private JLabel hLabel;
    private JButton homeButton;
    private CurrentTimeLabel timeLabel;
    private JButton infoVolButton;
    private JLabel kmaxLabel;
    private JPanel kmaxPanel;
    private JSpinner kmaxSpinner;
    private JLabel margeSecuriteLabel;
    private JPanel margeSecuritePanel;
    private JSpinner margeSecuriteSpinner;
    private JPanel traiterCollisionsPanel;
    private JPanel selecAeroportPanel;
    private JPanel separatorPanel;
    private JRadioButton voirTousVolsParAerRadio;
    private JRadioButton voirTousVolsParHRadio;
    private JRadioButton voirVolCetAerRadio;
    private JPanel voirVolsCetHPanel;
    private JRadioButton voirVolsCetHRadio;
    private JSpinner voirVolsCetHSpinner;
    private ButtonGroup voirVolsParAerButtonGroup;
    private ButtonGroup voirVolsParHButtonGroup;
    private JPanel voirVolsPanel;
    private JLabel minLabel;
}
