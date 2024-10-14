package ui.views;

import airports.Airport;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import ui.frameTools.BackgroundImagePanel;
import ui.frameTools.CurrentTimeLabel;
import ui.jXMapViewer.MapPanelView;

import javax.swing.*;
import java.awt.*;

import static ui.frameTools.Fonts.*;


/**
 * A JFrame subclass representing the view for flight operations.
 */
@SuppressWarnings("FieldCanBeLocal")
public class FlightOperationsView extends JFrame {

    /**
     * Constructor for FlightOperationsView.
     */
    public FlightOperationsView() {
        initComponents();
    }

    /**
     * Initialize components of the JFrame.
     */
    private void initComponents() {
        timeLabel = new CurrentTimeLabel();
        titleLabel = new JLabel();
        homeButton = new JButton();
        carteButton = new JButton();
        infoVolButton = new JButton();
        traiterCollisionsPanel = new JPanel();
        margeSecuritePanel = new JPanel();
        margeSecuriteLabel = new JLabel();
        margeSecuriteSpinner = new JSpinner();
        minLabel = new JLabel();
        kmaxPanel = new JPanel();
        kmaxLabel = new JLabel();
        kmaxSpinner = new JSpinner();
        algoPanel = new JPanel();
        algoLabel = new JLabel();
        algoComboBox = new JComboBox<>();
        collisionsButton = new JButton();

        JPanel bgImagePanel = new BackgroundImagePanel();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(getToolkit().getScreenSize());
        setResizable(false);
        setContentPane(bgImagePanel);
        getContentPane().setLayout(new AbsoluteLayout());


        timeLabel.setFont(spaceGrotesk18);
        timeLabel.setForeground(textColor);
        timeLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(textColor, 3),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(timeLabel, new AbsoluteConstraints(50, 40, 140, 40));

        titleLabel.setFont(chakraPetch48);
        titleLabel.setForeground(textColor);
        titleLabel.setText("Traitement des vols");
        getContentPane().add(titleLabel, new AbsoluteConstraints(737, 170, -1, -1));

        homeButton.setFont(new Font("Space Grotesk Light", Font.BOLD, 18));
        homeButton.setForeground(textColor);
        homeButton.setText("Accueil");
        getContentPane().add(homeButton, new AbsoluteConstraints(1730, 40, 140, 40));

        carteButton.setFont(spaceGrotesk18);
        carteButton.setForeground(textColor);
        carteButton.setText("Carte");
        getContentPane().add(carteButton, new AbsoluteConstraints(890, 350, 140, 60));

        infoVolButton.setFont(spaceGrotesk18);
        infoVolButton.setForeground(textColor);
        infoVolButton.setText("Informations des vols");
        getContentPane().add(infoVolButton, new AbsoluteConstraints(810, 470, 310, 60));

        assert traiterCollisionsPanel != null;
        traiterCollisionsPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(textColor, 3), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
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

        getContentPane().add(traiterCollisionsPanel, new AbsoluteConstraints(610, 610, 700, 290));
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

        collisionsButton.addActionListener(e -> {

        });
    }


    public JButton getHomeButton() { return homeButton; }

    public JButton getCarteButton() { return carteButton; }

    public JSpinner getKMaxSpinner() { return kmaxSpinner; }

    public JSpinner getMargeSecuriteSpinner() { return margeSecuriteSpinner; }

    public JComboBox<String> getAlgoComboBox() { return algoComboBox; }

    public JButton getCollisionsButton() { return collisionsButton; }

    public JButton getInfoVolButton() { return infoVolButton; }


    private JButton carteButton;
    private JButton homeButton;
    private CurrentTimeLabel timeLabel;
    private JButton infoVolButton;
    private JLabel titleLabel;
    private JPanel traiterCollisionsPanel;
    private JPanel margeSecuritePanel;
    private JLabel margeSecuriteLabel;
    private JSpinner margeSecuriteSpinner;
    private JLabel minLabel;
    private JPanel kmaxPanel;
    private JLabel kmaxLabel;
    private JSpinner kmaxSpinner;
    private JPanel algoPanel;
    private JLabel algoLabel;
    private JComboBox<String> algoComboBox;
    private JButton collisionsButton;
}
