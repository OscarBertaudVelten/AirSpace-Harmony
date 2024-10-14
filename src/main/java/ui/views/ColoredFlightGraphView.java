package ui.views;

import colorationAlgorithms.DSatur;
import colorationAlgorithms.WelshPowell;
import graphTools.GraphPlus;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import ui.frameTools.BackgroundImagePanel;
import ui.frameTools.CurrentTimeLabel;

import javax.swing.*;
import java.awt.*;

import static ui.frameTools.Fonts.spaceGrotesk18;
import static ui.frameTools.Fonts.textColor;


import javax.swing.JFrame;

/**
 * ColoredFlightGraphView extends JFrame to display a graph visualization with statistics.
 */
@SuppressWarnings("FieldCanBeLocal")
public class ColoredFlightGraphView extends JFrame {

    @SuppressWarnings("CanBeFinal")
    GraphStreamPanel graphPanel;

    @SuppressWarnings("CanBeFinal")
    GraphPlus graph;

    int nbConflicts;

    DSatur dSatur;

    WelshPowell wp;


    /**
     * Constructor for ColoredFlightGraphView.
     * @param newGraph The graph to be displayed.
     */
    public ColoredFlightGraphView(GraphPlus newGraph, WelshPowell newWp) {
        graph = newGraph;

        wp = newWp;
        graphPanel = new GraphStreamPanel(graph, -1, newWp);

        nbConflicts = newWp.getNbConflicts();

        initComponents();
    }

    public ColoredFlightGraphView(GraphPlus newGraph, DSatur newDSatur) {
        graph = newGraph;


        dSatur = newDSatur;
        graphPanel = new GraphStreamPanel(graph, -1, newDSatur);

        nbConflicts = newDSatur.getNbConflicts();


        initComponents();
    }


    /**
     * Initializes the components of the JFrame.
     */
    private void initComponents() {

        timeLabel = new CurrentTimeLabel();
        homeButton = new JButton();
        statsPanel = new JPanel();
        statsLabel = new JLabel();
        diametrePanel = new JPanel();
        diametreLabel = new JLabel();
        nbDiametreLabel = new JLabel();
        degreePanel = new JPanel();
        degreeLabel = new JLabel();
        nbDegreeLabel = new JLabel();
        ccPanel = new JPanel();
        ccLabel = new JLabel();
        nbCcLabel = new JLabel();
        noeudsPanel = new JPanel();
        noeudsLabel = new JLabel();
        nbNoeudsLabel = new JLabel();
        aretesPanel = new JPanel();
        aretesLabel = new JLabel();
        nbAretesLabel = new JLabel();
        retourCarteButton = new JButton();
        nbConflictsLabel = new JLabel();
        seeConflitsButton = new JButton();
        nbCouleursPanel = new JPanel();
        nbCouleursLabel = new JLabel();
        nbCouleursNBLabel = new JLabel();

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

        getContentPane().add(graphPanel, new AbsoluteConstraints(50, 100, 900, 900));

        statsPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(textColor, 3),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        statsPanel.setLayout(new AbsoluteLayout());
        statsPanel.setBackground(new Color(0, 0, 0, 0));

        statsLabel.setFont(spaceGrotesk18);
        statsLabel.setText("Statistiques sur le graphe");
        statsLabel.setForeground(textColor);
        statsPanel.add(statsLabel, new AbsoluteConstraints(250, 30, -1, -1));

        diametrePanel.setLayout(new BorderLayout());
        diametrePanel.setBackground(new Color(0, 0, 0, 0));

        diametreLabel.setFont(spaceGrotesk18);
        diametreLabel.setText("Diamètre");
        diametreLabel.setForeground(textColor);
        diametrePanel.add(diametreLabel, BorderLayout.WEST);

        nbDiametreLabel.setFont(spaceGrotesk18);
        nbDiametreLabel.setText(String.valueOf(graph.getDiameter()));
        nbDiametreLabel.setForeground(textColor);
        diametrePanel.add(nbDiametreLabel, BorderLayout.EAST);

        statsPanel.add(diametrePanel, new AbsoluteConstraints(20, 310, 660, 40));

        degreePanel.setLayout(new BorderLayout());
        degreePanel.setBackground(new Color(0, 0, 0, 0));

        degreeLabel.setFont(spaceGrotesk18);
        degreeLabel.setText("Degré Moyen");
        degreeLabel.setForeground(textColor);
        degreePanel.add(degreeLabel, BorderLayout.WEST);

        nbDegreeLabel.setFont(spaceGrotesk18);
        nbDegreeLabel.setText(String.valueOf(graph.getAvgDegree()));
        nbDegreeLabel.setForeground(textColor);
        degreePanel.add(nbDegreeLabel, BorderLayout.EAST);

        statsPanel.add(degreePanel, new AbsoluteConstraints(20, 70, 660, 40));

        ccPanel.setLayout(new BorderLayout());
        ccPanel.setBackground(new Color(0, 0, 0, 0));

        ccLabel.setFont(spaceGrotesk18);
        ccLabel.setText("Composantes Connexes");
        ccLabel.setForeground(textColor);
        ccPanel.add(ccLabel, BorderLayout.WEST);

        nbCcLabel.setFont(spaceGrotesk18);
        nbCcLabel.setText(String.valueOf(graph.getConnectedComponentsCount()));
        nbCcLabel.setForeground(textColor);
        ccPanel.add(nbCcLabel, BorderLayout.EAST);

        statsPanel.add(ccPanel, new AbsoluteConstraints(20, 130, 660, 40));

        noeudsPanel.setLayout(new BorderLayout());
        noeudsPanel.setBackground(new Color(0, 0, 0, 0));

        noeudsLabel.setFont(spaceGrotesk18);
        noeudsLabel.setText("Noeuds");
        noeudsLabel.setForeground(textColor);
        noeudsPanel.add(noeudsLabel, BorderLayout.WEST);

        nbNoeudsLabel.setFont(spaceGrotesk18);
        nbNoeudsLabel.setText(String.valueOf(graph.getNodeCount()));
        nbNoeudsLabel.setForeground(textColor);
        noeudsPanel.add(nbNoeudsLabel, BorderLayout.EAST);

        statsPanel.add(noeudsPanel, new AbsoluteConstraints(20, 190, 660, 40));

        aretesPanel.setLayout(new BorderLayout());
        aretesPanel.setBackground(new Color(0, 0, 0, 0));

        aretesLabel.setFont(spaceGrotesk18);
        aretesLabel.setText("Arêtes");
        aretesLabel.setForeground(textColor);
        aretesPanel.add(aretesLabel, BorderLayout.WEST);

        nbAretesLabel.setFont(spaceGrotesk18);
        nbAretesLabel.setText(String.valueOf(graph.getEdgeCount()));
        nbAretesLabel.setForeground(textColor);
        aretesPanel.add(nbAretesLabel, BorderLayout.EAST);

        statsPanel.add(aretesPanel, new AbsoluteConstraints(20, 250, 660, 40));
        statsPanel.setBackground(new Color(0, 0, 0, 0));

        getContentPane().add(statsPanel, new AbsoluteConstraints(1050, 180, 700, 380));

        retourCarteButton.setFont(spaceGrotesk18);
        retourCarteButton.setForeground(textColor);
        retourCarteButton.setText("Retour à la carte");
        getContentPane().add(retourCarteButton, new AbsoluteConstraints(1190, 800, 400, 50));

        assert nbConflictsLabel != null;
        nbConflictsLabel.setFont(spaceGrotesk18);
        nbConflictsLabel.setText("Nombre de conflits : ");
        nbConflictsLabel.setForeground(textColor);
        getContentPane().add(nbConflictsLabel, new AbsoluteConstraints(1200, 610));

        assert seeConflitsButton != null;
        seeConflitsButton.setFont(spaceGrotesk18);
        seeConflitsButton.setText("Voir les conflits");
        seeConflitsButton.setForeground(textColor);
        getContentPane().add(seeConflitsButton, new AbsoluteConstraints(1450, 605));

        nbConflictsLabel.setText("Nombre de conflits : " + nbConflicts);

        nbCouleursPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(textColor, 3),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        nbCouleursPanel.setLayout(new BorderLayout());
        nbCouleursPanel.setBackground(new Color(197, 202, 228));

        nbCouleursLabel.setFont(spaceGrotesk18);
        nbCouleursLabel.setForeground(textColor);
        nbCouleursLabel.setText("Nombre de Couleurs : ");
        nbCouleursPanel.add(nbCouleursLabel, BorderLayout.WEST);

        nbCouleursNBLabel.setFont(spaceGrotesk18);
        nbCouleursNBLabel.setForeground(textColor);
        nbCouleursNBLabel.setText(String.valueOf(graphPanel.getNbColors()));
        nbCouleursPanel.add(nbCouleursNBLabel, BorderLayout.LINE_END);

        getContentPane().add(nbCouleursPanel, new AbsoluteConstraints(1050, 700, 700, 60));
    }


    public GraphPlus getGraph() { return graph; }

    public JButton getHomeButton() { return homeButton; }

    public JButton getRetourCarteButton() { return retourCarteButton; }

    public JButton getSeeConflitsButton() { return seeConflitsButton; }

    public DSatur getdSatur() { return dSatur; }

    public WelshPowell getWp() { return wp; }


    private JLabel aretesLabel;
    private JPanel aretesPanel;
    private JLabel ccLabel;
    private JPanel ccPanel;
    private JLabel degreeLabel;
    private JPanel degreePanel;
    private JLabel diametreLabel;
    private JPanel diametrePanel;
    private JButton homeButton;
    private CurrentTimeLabel timeLabel;
    private JLabel nbAretesLabel;
    private JLabel nbCcLabel;
    private JLabel nbDegreeLabel;
    private JLabel nbDiametreLabel;
    private JLabel nbNoeudsLabel;
    private JLabel noeudsLabel;
    private JPanel noeudsPanel;
    private JLabel statsLabel;
    private JPanel statsPanel;
    private JButton retourCarteButton;
    private JLabel nbConflictsLabel;
    private JButton seeConflitsButton;
    private JPanel nbCouleursPanel;
    private JLabel nbCouleursLabel;
    private JLabel nbCouleursNBLabel;
}
