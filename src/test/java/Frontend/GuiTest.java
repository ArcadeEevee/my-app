package Frontend;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GuiTest {

    @Test
    public void getFileTest(){

        Gui gui = new Gui();

        assertNotNull(gui.getPathsToAllCovers());
    }

    @Test
    public void calculateColumnSizeTest(){

        Gui gui = new Gui();

        // Example with a total amount of 20 cards -> 10 Front covers
        int rowsCount = gui.calculateColumnSize(20);

        assertEquals(5, rowsCount, "With 20 Cards (21 Total Covers) there should be 5 rows since it's smaller then 2 of the power of 5");
    }
}