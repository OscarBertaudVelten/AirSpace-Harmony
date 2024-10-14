package ui.frameTools;

import ui.controllers.HomeController;
import ui.views.HomeView;


public class FrameTest {
    public static void main(String[] args) {
        HomeView hView = new HomeView();
        new HomeController(hView);
        hView.setVisible(true);
    }
}
