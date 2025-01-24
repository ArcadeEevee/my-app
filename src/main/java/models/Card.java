package models;


import logic.Pair;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;

/**
 * Creates a playable card.
 */
public class Card {

  private static final int MAX_NUMBER_OF_TURNED_UP_CARDS = 2;
  private static int currentIds;
  private static int turnedUpCards;


  private final int id;

  private final Image backCoverImg;
  private final Image frontCoverImg;
  private boolean selected;
  private Button button;


  /**
   * Creates a card.
   */
  public Card(String pathBackCover, String pathFrontCover, Display display) {
    // Get latest ID
    this.id = currentIds;

    // defines the look of the cards
    this.backCoverImg = new Image(display, pathBackCover);
    this.frontCoverImg = new Image(display, pathFrontCover);
    this.selected = false;

    // Updates ID by increasing it for the next card
    countId();
  }

  /**
  * returns the ID of the card.
  */
  public int getId() {
    return id;
  }

  /**
  * returns the Button.
  */
  public Button getButton() {
    return button;
  }

  /**
   * Creates a Button and gives it a Selection Listener.
   * this button is the Card you can click on so the back side is shown.
   */
  public void setButton(Button button) {
    this.button = button;
    this.button.addSelectionListener(setUpButtonListener());
    setFrontHidden();
  }

  /**
  * sets Front side visible.
  */
  public void setFrontVisible() {
    button.setImage(frontCoverImg);
    selected = true;
  }

  /**
  * sets back side visible.
  */
  public void setFrontHidden() {
    button.setImage(backCoverImg);
    selected = false;
  }

  /**
  * This function decides which side of the card is shown
  * and keeps track of it.
  */
  public void turnCardAround() {
    // Check if card front is up
    // and if two cards are already open
    if (!selected && turnedUpCards < MAX_NUMBER_OF_TURNED_UP_CARDS) {
      // Show front side of the card and increase the counter for open Cards
      increaseTurnedUpCards();
      setFrontVisible();
      Pair.addToSelectedCards(this);

    // Check if card front is visible
    } else if (selected) {
      // if it is turn the card around and decrease the counter
      decreaseTurnedUpCards();
      setFrontHidden();
      Pair.removeFromSelectedCards(this);
    }

    // if 2 Cards are turned up it compares these two
    if (turnedUpCards == MAX_NUMBER_OF_TURNED_UP_CARDS) {
      Pair.compareCards();
    }
  }

  /**
  * adds Selection Listener to the Button.
  */
  public SelectionListener setUpButtonListener() {
    return new SelectionListener() {
      @Override
      public void widgetSelected(SelectionEvent selectionEvent) {
        turnCardAround();
      }

      @Override
      public void widgetDefaultSelected(SelectionEvent selectionEvent) {
        turnCardAround();
      }
    };
  }

  /**
  * defines a Button to the card.
  */
  public void setCardInvisible() {
    button.setBounds(0, 0, 0, 0);
  }

  /**
  * increases the ID by to a free ID number.
  */
  private static void countId() {
    currentIds += 1;
  }

  /**
  * resets the amount of turned up cards.
  */
  public static void resetTurnedUpCards() {
    turnedUpCards = 0;
  }

  /**
  * resets all the ID counter.
  */
  public static void resetIds() {
    currentIds = 0;
  }

  /**
  * A function to keep to increase the number of currently
  * turned up cards.
  */
  public static void increaseTurnedUpCards() {
    if (turnedUpCards < MAX_NUMBER_OF_TURNED_UP_CARDS) {
      turnedUpCards += 1;
    }
  }

  /**
  * A function to keep to increase the number of currently
  * turned up cards.
  */
  public static void decreaseTurnedUpCards() {
    if (turnedUpCards > 0) {
      turnedUpCards -= 1;
    }
  }

  public static int getUnsignedId() {
    return currentIds;
  }

  public static int getTurnedUpCardsAmount() {
    return turnedUpCards;
  }
}
