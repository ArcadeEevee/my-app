package frontend;

import logic.Pair;
import models.Card;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GuiTest {

  @Test
  public void getFileTest(){
    // Tests if the Cards can be selected through a new window

    Gui gui = new Gui();
    assertNotNull(gui.getPathsToAllCovers());
  }

  @Test
  public void calculateColumnSizeTest(){
    // Tests if the number of needed is Calculated correctly

    Gui gui = new Gui();

    // Example with a total amount of 20 cards -> 10 Front covers
    int rowsCount = gui.calculateColumnSize(20);

    assertEquals(5, rowsCount, "With 20 Cards (21 Total Covers) there should be 5 rows since it's smaller then 2 of the power of 5");
  }

  @Test
  public void resetGameTest() {
    //test if the game fully resets

    Gui gui = new Gui();

    gui.resetGame();

    assertEquals(12, Card.getUnsignedId(), "Unsigned ID should be 11, with the standard set of cards");
  }

  @Test
  public void test2() {
    // tests if the card gets turned around

    Display display = new Display();

    Pair pair = new Pair("src/main/Images/Karte_1.png", "src/main/Images/blank.png", display);
    Shell shell = new Shell();
    Group groupCards = new Group(shell, SWT.NO_RADIO_GROUP | SWT.SHADOW_ETCHED_IN);
    Button button = new Button(groupCards, SWT.TOGGLE);
    pair.getCards()[0].setButton(button);

    pair.getCards()[0].turnCardAround();

    assertEquals(1, Card.getTurnedUpCardsAmount(), "Amount of Cards turned around should be one");
  }
}