package ui.views;

import graphTools.GraphPlus;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import ui.frameTools.BackgroundImagePanel;
import ui.frameTools.CurrentTimeLabel;

import javax.swing.*;
import java.awt.*;

import static ui.frameTools.Fonts.spaceGrotesk18;
import static ui.frameTools.Fonts.textColor;


/**
 * A JFrame subclass representing a view for displaying a graph from a file.
 */
@SuppressWarnings("FieldCanBeLocal")
public class GraphFileView extends JFrame {

    /**
     * The panel displaying the graph.
     */
    @SuppressWarnings("CanBeFinal")
    GraphStreamPanel graphPanel;

    /**
     * The graph instance to be displayed.
     */
    final GraphPlus graph;

    /**
     * Constructs a GraphFileView object with a specified graph.
     *
     * @param newGraph The graph to be displayed.
     */
    public GraphFileView(GraphPlus newGraph) {
        graph = newGraph;
        graphPanel = new GraphStreamPanel(graph, -1, "");

        initComponents();
    }

    /**
     * Initializes the components of the frame.
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
        colorationPanel = new JPanel();
        algoPanel = new JPanel();
        algoLabel = new JLabel();
        algoComboBox = new JComboBox<>();
        colorierButton = new JButton();
        kmaxLabel = new JLabel();
        kmaxPanel = new JPanel();
        kmaxSpinner = new JSpinner();

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

        colorationPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(textColor, 3),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        colorationPanel.setLayout(new AbsoluteLayout());
        colorationPanel.setBackground(new Color(202, 206, 231));

        algoPanel.setLayout(new BorderLayout(40, 0));
        algoPanel.setBackground(new Color(0, 0, 0, 0));

        algoLabel.setFont(spaceGrotesk18);
        algoLabel.setText("Algorithme : ");
        algoLabel.setForeground(textColor);
        algoPanel.add(algoLabel, BorderLayout.WEST);

        algoComboBox.setModel(new DefaultComboBoxModel<>(new String[] { "Welsh Powell", "DSatur"}));
        algoComboBox.setBackground(new Color(206, 209, 232));
        algoComboBox.setForeground(textColor);
        algoComboBox.setFont(spaceGrotesk18);
        algoComboBox.setSelectedIndex(1);
        algoPanel.add(algoComboBox, BorderLayout.CENTER);

        colorationPanel.add(algoPanel, new AbsoluteConstraints(40, 90, 600, 40));

        colorierButton.setFont(spaceGrotesk18);
        colorierButton.setText("Colorier le graphe");
        colorierButton.setForeground(textColor);
        colorationPanel.add(colorierButton, new AbsoluteConstraints(260, 150, 190, 40));


        assert kmaxPanel != null;
        kmaxPanel.setLayout(new BorderLayout(20, 0));
        kmaxPanel.setBackground(new Color(0,0,0,0));

        assert kmaxLabel != null;
        kmaxLabel.setFont(spaceGrotesk18);
        kmaxLabel.setText("Nombre de couches max : ");
        kmaxLabel.setForeground(textColor);
        kmaxPanel.add(kmaxLabel, BorderLayout.WEST);

        assert kmaxSpinner != null;
        setSpinner(kmaxSpinner, graph.getKMax(), 1.0, 1000.0, new Color(196, 202, 228));
        kmaxPanel.add(kmaxSpinner, BorderLayout.CENTER);

        colorationPanel.add(kmaxPanel, new AbsoluteConstraints(40, 30, 310, 40));

        getContentPane().add(colorationPanel, new AbsoluteConstraints(1050, 610, 700, 210));
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


    public JComboBox<String> getAlgoComboBox() { return algoComboBox; }

    public GraphPlus getGraph() { return graph; }

    public JButton getHomeButton() { return homeButton; }

    public JButton getColorierButton() { return colorierButton; }

    public JSpinner getKmaxSpinner() { return kmaxSpinner; }


    private JComboBox<String> algoComboBox;
    private JLabel algoLabel;
    private JPanel algoPanel;
    private JLabel aretesLabel;
    private JPanel aretesPanel;
    private JLabel ccLabel;
    private JPanel ccPanel;
    private JPanel colorationPanel;
    private JLabel degreeLabel;
    private JPanel degreePanel;
    private JLabel diametreLabel;
    private JPanel diametrePanel;
    private JButton homeButton;
    private CurrentTimeLabel timeLabel;
    private JButton colorierButton;
    private JLabel nbAretesLabel;
    private JLabel nbCcLabel;
    private JLabel nbDegreeLabel;
    private JLabel nbDiametreLabel;
    private JLabel nbNoeudsLabel;
    private JLabel noeudsLabel;
    private JPanel noeudsPanel;
    private JLabel statsLabel;
    private JPanel statsPanel;
    private JPanel kmaxPanel;
    private JLabel kmaxLabel;
    private JSpinner kmaxSpinner;
}
