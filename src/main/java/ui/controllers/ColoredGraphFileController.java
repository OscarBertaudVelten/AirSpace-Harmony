package ui.controllers;

import ui.views.*;

import java.awt.event.ActionListener;

/**
 * The controller class for handling actions in the ColoredGraphFileView.
 */
public class ColoredGraphFileController {
    private final ColoredGraphFileView view;

    /**
     * Constructs a ColoredGraphFileController with the specified view.
     *
     * @param newView The ColoredGraphFileView associated with this controller.
     */
    public ColoredGraphFileController(ColoredGraphFileView newView) {
        view = newView;



        view.getHomeButton().addActionListener(homeButtonActionListener());
        view.getRecolorierButton().addActionListener(recolorierButtonActionListener());
    }

    /**
     * Returns an ActionListener for the home button in the view.
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
     * Returns an ActionListener for the recolorier button in the view.
     *
     * @return ActionListener for the recolorier button.
     */
    private ActionListener recolorierButtonActionListener() {
        return evt -> {
            String chosenAlgo = (String) view.getAlgoComboBox().getSelectedItem();

            int kMax = ((Double) view.getKmaxSpinner().getValue()).intValue();

            ColoredGraphFileView cgfView = new ColoredGraphFileView(view.getGraph(), kMax, chosenAlgo);
            new ColoredGraphFileController(cgfView);
            cgfView.setVisible(true);

            view.dispose();
        };
    }
}
