package ui.frameTools;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * A JPanel subclass that displays a background image.
 */
public class BackgroundImagePanel extends JPanel {
    private BufferedImage backgroundImage;

    /**
     * Constructs a BackgroundImagePanel and loads the background image from "/Images/Background.png".
     */
    public BackgroundImagePanel() {
        String imagePath = "/Images/Background.png";
        try {
            backgroundImage = ImageIO.read(Objects.requireNonNull(getClass().getResource(imagePath)));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error, file not found | " + imagePath);

        }
    }

    /**
     * Overrides the paintComponent method to paint the background image if available.
     *
     * @param g the Graphics context in which to paint
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getToolkit().getScreenSize().width, getToolkit().getScreenSize().height, this);
        }
    }
}
