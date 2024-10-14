package ui.frameTools;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * The Fonts class provides static methods and constants for loading custom fonts
 * from file and registering them with the local GraphicsEnvironment.
 */
public class Fonts {

    /**
     * The custom font ChakraPetch with size 48 and semi-bold italic style.
     */
    public static final Font chakraPetch48;

    /**
     * The custom font SpaceGrotesk with size 18 and bold style.
     */
    public static final Font spaceGrotesk18;

    /**
     * The default text color used in UI components, decoded from hexadecimal code.
     */
    public static final Color textColor = Color.decode("#001977");

    static {
        chakraPetch48 = initFont("src/main/resources/Fonts/ChakraPetch-SemiBoldItalic.ttf", 48f);
        spaceGrotesk18 = initFont("src/main/resources/Fonts/SpaceGrotesk-Bold.ttf", 18f);
    }

    /**
     * Initializes a Font object from the provided font file and style.
     *
     * @param fileName the path to the font file.
     * @param style    the style of the font.
     * @return the Font object initialized from the file and style.
     */
    public static Font initFont(String fileName, float style) {
        Font newFont = null;
        try {
            File fontFile = new File(fileName);
            newFont = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(style);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(newFont);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error, file not found | File : " + fileName);
        }
        return newFont;
    }
}
