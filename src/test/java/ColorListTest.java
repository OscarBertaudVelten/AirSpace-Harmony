import colors.ColorList;
import colors.ColorPlus;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ColorListTest {

    @Test
    void testColorListWithZeroColors() {
        ColorList colorList = new ColorList(0);
        ColorPlus[] colors = colorList.generateRandomColorList();
        assertNotNull(colors, "The color array should not be null");
        assertEquals(0, colors.length, "The color array should have zero length");
    }

    @Test
    void testColorListWithOneColor() {
        ColorList colorList = new ColorList(1);
        ColorPlus[] colors = colorList.generateRandomColorList();
        assertNotNull(colors, "The color array should not be null");
        assertEquals(1, colors.length, "The color array should have one element");
        assertNotNull(colors[0], "The single color in the array should not be null");
    }

    @Test
    void testColorListWithNegativeColors() {
        assertThrows(NegativeArraySizeException.class, () -> {
            new ColorList(-1);
        }, "Creating a ColorList with negative number of colors should throw NegativeArraySizeException");
    }

    @Test
    void testGenerateRandomColorList() {
        int numberOfColors = 100;
        ColorList colorList = new ColorList(numberOfColors);
        ColorPlus[] colors = colorList.generateRandomColorList();
        assertNotNull(colors, "The color array should not be null");
        assertEquals(numberOfColors, colors.length, "The color array should have the specified number of elements");

        for (ColorPlus color : colors) {
            assertNotNull(color, "Each color in the array should not be null");
        }
    }
}
