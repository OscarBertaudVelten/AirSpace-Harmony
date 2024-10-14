package colors;

/**
 * Represents a list of colors.
 */
public class ColorList {

    /** An array to hold the colors. */
    private final ColorPlus[] colorList;

    /** The number of colors in the list. */
    private final int nbColors;

    /**
     * Constructs a ColorList object with the specified number of colors.
     *
     * @param newNbColors the number of colors in the list
     */
    @SuppressWarnings("unused")
    public ColorList(int newNbColors) {
        nbColors = newNbColors;
        colorList = new ColorPlus[nbColors];
    }

    /**
     * Generates a list of random colors and assigns them to the colorList array.
     *
     * @return the array of random colors
     */
    public ColorPlus[] generateRandomColorList() {
        ColorPlus tmpColor;
        for (int ii = 0; ii < nbColors; ii++) {
            tmpColor = new ColorPlus().generateRandomColor();
            colorList[ii] = tmpColor;
        }
        return colorList;
    }
}
