package ui.controllers;

import ui.views.ColoredGraphFileView;
import ui.views.GraphFileView;
import ui.views.HomeView;

import java.awt.event.ActionListener;

/**
 * Controller class for managing interactions and actions related to a GraphFileView.
 */
public class GraphFileController {

    private final GraphFileView view;

    /**
     * Constructor for GraphFileController.
     *
     * @param newView The GraphFileView instance to associate with this controller.
     */
    public GraphFileController(GraphFileView newView) {
        view = newView;

        view.getHomeButton().addActionListener(homeButtonActionListener());
        view.getColorierButton().addActionListener(colorierButtonActionListener());
    }

    /**
     * ActionListener for the home button. Creates a new HomeView and disposes the current view.
     *
     * @return ActionListener for the home button.
     */
    private ActionListener homeButtonActionListener() {
        return evt -> {
            HomeView hView = new HomeView();
            new HomeController(hView);
            hView.setVisible(true);

            view.dispose();
        };
    }

    /**
     * ActionListener for the colorier button. Creates a new ColoredGraphFileView based on selected algorithm
     * and disposes the current view.
     *
     * @return ActionListener for the colorier button.
     */
    private ActionListener colorierButtonActionListener() {
        return evt -> {
            String chosenAlgo = (String) view.getAlgoComboBox().getSelectedItem();
            int kMax = ((Double) view.getKmaxSpinner().getValue()).intValue();

            ColoredGraphFileView cgfview = new ColoredGraphFileView(view.getGraph(), kMax, chosenAlgo);
            new ColoredGraphFileController(cgfview);
            cgfview.setVisible(true);

            view.dispose();
        };
    }
}
