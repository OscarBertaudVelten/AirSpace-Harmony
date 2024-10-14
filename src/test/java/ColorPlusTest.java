import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import colors.ColorPlus;


public class ColorPlusTest {

    @Test
    public void testConstructorWithValidValues() {
        ColorPlus color = new ColorPlus(100, 150, 200);

        assertEquals(100, color.getRed());
        assertEquals(150, color.getGreen());
        assertEquals(200, color.getBlue());
    }

    @Test
    public void testGenerateRandomColor() {
        ColorPlus color = new ColorPlus().generateRandomColor();

        assertTrue(color.getRed() >= 0 && color.getRed() <= 255);
        assertTrue(color.getGreen() >= 0 && color.getGreen() <= 255);
        assertTrue(color.getBlue() >= 0 && color.getBlue() <= 255);
    }

    @Test
    public void testConstructorWithExtremeValues() {
        // Testing with maximum valid values for RGB (255)
        ColorPlus color = new ColorPlus(255, 255, 255);

        assertEquals(255, color.getRed());
        assertEquals(255, color.getGreen());
        assertEquals(255, color.getBlue());
    }

    @Test
    public void testGenerateRandomColorMultipleTimes() {
        // Test if generated colors are different on subsequent calls
        ColorPlus color1 = new ColorPlus().generateRandomColor();
        ColorPlus color2 = new ColorPlus().generateRandomColor();

        assertNotEquals(color1.getRed(), color2.getRed());
        assertNotEquals(color1.getGreen(), color2.getGreen());
        assertNotEquals(color1.getBlue(), color2.getBlue());
    }
}
