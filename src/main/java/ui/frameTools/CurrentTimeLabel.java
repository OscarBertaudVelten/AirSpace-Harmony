package ui.frameTools;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a JLabel that displays the current time.
 */
public class CurrentTimeLabel extends JLabel {

    /**
     * Constructs a CurrentTimeLabel instance.
     * Initializes the label with the current time and starts a timer to update it every second.
     */
    public CurrentTimeLabel() {
        updateHeure();
        Timer timer = new Timer(1000, e -> updateHeure());
        timer.start();
    }

    /**
     * Updates the label with the current time in "HH:mm" format.
     */
    private void updateHeure() {
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        setText("Heure : ".concat(time));
    }
}
