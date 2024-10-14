package ui.views;

import colorationAlgorithms.DSatur;
import colorationAlgorithms.WelshPowell;
import graphTools.GraphPlus;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.swing_viewer.ViewPanel;
import org.graphstream.ui.view.Viewer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static ui.frameTools.Fonts.textColor;

/**
 * Panel for displaying a graph using GraphStream library with coloring algorithms.
 */
public class GraphStreamPanel extends JPanel {

    private int nbColors;

    private int kMax;

    public DSatur dSatur;

    public WelshPowell wp;

    private Point lastPoint;


    /**
     * Constructs a new GraphStreamPanel with the specified graph and chosen coloring algorithm.
     * @param graph The graph to be displayed.
     */
    public GraphStreamPanel(GraphPlus graph, int newKMax, WelshPowell newWp) {
        System.setProperty("org.graphstream.ui", "swing"); // Required setting

        kMax = newKMax;
        wp = newWp;

        colorGraphWelshPowellWithWP();


        Viewer viewer = new SwingViewer(graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        ViewPanel viewPanel = (ViewPanel) viewer.addDefaultView(false);
        viewer.enableAutoLayout();
        viewPanel.setPreferredSize(new Dimension(900, 895));

        viewPanel.setBorder(javax.swing.BorderFactory.createLineBorder(textColor, 3));

        addZoomAndDrag(viewPanel);

        add(viewPanel);
    }


    /**
     * Constructs a new GraphStreamPanel with the specified graph and chosen coloring algorithm.
     * @param graph The graph to be displayed.
     */
    public GraphStreamPanel(GraphPlus graph, int newKMax, DSatur newDSatur) {
        System.setProperty("org.graphstream.ui", "swing"); // Required setting

        kMax = newKMax;
        dSatur = newDSatur;

        colorGraphDSaturWithDsatur();


        Viewer viewer = new SwingViewer(graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        ViewPanel viewPanel = (ViewPanel) viewer.addDefaultView(false);
        viewer.enableAutoLayout();
        viewPanel.setPreferredSize(new Dimension(900, 895));

        viewPanel.setBorder(javax.swing.BorderFactory.createLineBorder(textColor, 3));

        addZoomAndDrag(viewPanel);

        add(viewPanel);
    }


    /**
     * Constructs a new GraphStreamPanel with the specified graph and chosen coloring algorithm.
     * @param graph The graph to be displayed.
     */
    public GraphStreamPanel(GraphPlus graph, int newKMax, String chosenAlgo) {
        System.setProperty("org.graphstream.ui", "swing"); // Required setting

        kMax = newKMax;

        if (chosenAlgo.equals("DSatur"))
            colorGraphDSatur(graph);
        else if (chosenAlgo.equals("Welsh Powell"))
            colorGraphWelshPowell(graph);


        Viewer viewer = new SwingViewer(graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        ViewPanel viewPanel = (ViewPanel) viewer.addDefaultView(false);
        viewer.enableAutoLayout();
        viewPanel.setPreferredSize(new Dimension(900, 895));

        viewPanel.setBorder(javax.swing.BorderFactory.createLineBorder(textColor, 3));

        addZoomAndDrag(viewPanel);

        add(viewPanel);
    }


    /**
     * Retrieves the number of colors used to color the graph.
     * @return The number of colors used.
     */
    public int getNbColors() {
        return nbColors;
    }


    public DSatur getDSatur() { return dSatur; }

    public WelshPowell getWp() { return wp; }

    /**
     * Colors the graph using the DSatur coloring algorithm.
     * @param graph The graph to be colored.
     */
    public void colorGraphDSatur(GraphPlus graph) {
        dSatur = new DSatur(graph, false);
        dSatur.setKMax(kMax);
        dSatur.dSaturAlgo();
        dSatur.getNodeList().colorGraphGraphStream(dSatur.getNodeColors());
        nbColors = dSatur.getNbColors();
    }


    public void colorGraphDSaturWithDsatur() {
        dSatur.getNodeList().colorGraphGraphStream(dSatur.getNodeColors());
        nbColors = dSatur.getNbColors();
    }


    public void colorGraphWelshPowell(GraphPlus graph) {
        wp = new WelshPowell(graph);
        wp.setKMax(kMax);
        wp.welshPowellAlgo();
        wp.getNodeList().colorGraphGraphStream(wp.getNodeColors());
        nbColors = wp.getNbColors();
    }


    public void colorGraphWelshPowellWithWP() {
        wp.getNodeList().colorGraphGraphStream(wp.getNodeColors());
        nbColors = wp.getNbColors();
    }



    private void addZoomAndDrag(ViewPanel viewPanel) {
        viewPanel.addMouseWheelListener(e -> {
            if (e.getWheelRotation() < 0) {
                viewPanel.getCamera().setViewPercent(viewPanel.getCamera().getViewPercent() * 0.9);
            } else {
                viewPanel.getCamera().setViewPercent(viewPanel.getCamera().getViewPercent() * 1.1);
            }
        });

        viewPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    lastPoint = e.getPoint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                lastPoint = null;
            }
        });

        viewPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    Point currentPoint = e.getPoint();
                    if (lastPoint != null) {
                        int dx = currentPoint.x - lastPoint.x;
                        int dy = currentPoint.y - lastPoint.y;

                        double viewPercent = viewPanel.getCamera().getViewPercent();
                        double factor = 0.005;

                        viewPanel.getCamera().setViewCenter(
                                viewPanel.getCamera().getViewCenter().x - dx * viewPercent * factor,
                                viewPanel.getCamera().getViewCenter().y + dy * viewPercent * factor,
                                0);
                    }
                    lastPoint = currentPoint;
                }
            }
        });
    }
}
