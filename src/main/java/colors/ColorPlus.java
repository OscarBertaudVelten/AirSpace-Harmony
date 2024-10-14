package colors;

import java.util.Random;

/**
 * Represents a color with RGB values.
 */
public class ColorPlus {
    private int red;
    private int green;
    private int blue;

    /**
     * Constructs a color with default RGB values (0, 0, 0).
     */
    @SuppressWarnings("unused")
    public ColorPlus() { }

    /**
     * Constructs a color with specified RGB values.
     *
     * @param newRed the red component value
     * @param newGreen the green component value
     * @param newBlue the blue component value
     */
    @SuppressWarnings("unused")
    public ColorPlus(int newRed, int newGreen, int newBlue) {
        red = newRed;
        green = newGreen;
        blue = newBlue;
    }

    /**
     * Gets the red component value of the color.
     *
     * @return the red component value
     */
    public int getRed() {
        return red;
    }

    /**
     * Gets the green component value of the color.
     *
     * @return the green component value
     */
    public int getGreen() {
        return green;
    }

    /**
     * Gets the blue component value of the color.
     *
     * @return the blue component value
     */
    public int getBlue() {
        return blue;
    }

    /**
     * Generates a random color.
     *
     * @return a ColorPlus object with random RGB values
     */
    public ColorPlus generateRandomColor() {
        Random randomGenerator = new Random();
        red = randomGenerator.nextInt(256);
        green = randomGenerator.nextInt(256);
        blue = randomGenerator.nextInt(256);

        return new ColorPlus(red, green, blue);
    }
}
