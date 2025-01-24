package logic;

import java.util.ArrayList;
import models.Card;
import org.eclipse.swt.widgets.Display;

/**
* defines what a Pair of Cards is for the program
* and how to handle those.
*/
public class Pair {

  private static final Card[] SELECTED_CARDS = new Card[2];
  private static ArrayList<Pair> allPairs = new ArrayList<>();

  private final Card card1;
  private final Card card2;
  private final Card[] cards;

  /**
  * Creates one Pair of Cards.
  */
  public Pair(String pathBackCover, String pathFrontCover, Display display) {

    // Creates the cards and adds in the array
    this.card1 = new Card(pathBackCover, pathFrontCover, display);
    this.card2 = new Card(pathBackCover, pathFrontCover, display);
    cards = new Card[]{card1, card2};

    // static list to keep track of all pairs
    // adds the new created Pair to it
    addToAllPairs(this);
  }

  /**
   * return the array of Cards.
   */
  public Card[] getCards() {
    return cards;
  }

  /**
   * adds one Pair to a total list of Pairs.
   */
  public static void addToAllPairs(Pair pair) {
    allPairs.add(pair);
  }

  /**
   * returns all Pairs created.
   */
  public static ArrayList<Pair> getAllPairs() {
    return allPairs;
  }

  /**
   * resets the list of created Pairs.
   */
  public static void resetAllPairs() {
    allPairs = new ArrayList<>();
  }

  /**
  * array with two spaces to show which cards are turned up.
  * adds Card to the array if turned up.
  */
  public static void addToSelectedCards(Card card) {
    Card check = SELECTED_CARDS[0];
    if (check != null) {
      SELECTED_CARDS[1] = card;
    } else {
      SELECTED_CARDS[0] = card;
    }
  }

  /**
   * removes Card from the array if turned down.
   */
  public static void removeFromSelectedCards(Card card) {
    if (SELECTED_CARDS[0] != null && SELECTED_CARDS[0].getId() == card.getId()) {
      SELECTED_CARDS[0] = null;
    } else {
      SELECTED_CARDS[1] = null;
    }
  }

  /**
   * Compares two turned up cards.
   */
  public static void compareCards() {
    // Check if selected Cards are a pair
    // by going through all Pairs and comparing them with the ID's
    for (Pair pair : allPairs) {
      if (pair.card1.getId() == SELECTED_CARDS[0].getId()
              && pair.card2.getId() == SELECTED_CARDS[1].getId()) {
        SELECTED_CARDS[0].setCardInvisible();
        SELECTED_CARDS[1].setCardInvisible();
        resetSelectedCards();
        break;
      } else if (pair.card1.getId() == SELECTED_CARDS[1].getId()
              && pair.card2.getId() == SELECTED_CARDS[0].getId()) {
        SELECTED_CARDS[0].setCardInvisible();
        SELECTED_CARDS[1].setCardInvisible();
        resetSelectedCards();
        break;
      }
    }
  }

  // resets the array of Selected Cards
  private static void resetSelectedCards() {
    Card.resetTurnedUpCards();
    SELECTED_CARDS[0] = null;
    SELECTED_CARDS[1] = null;
  }

}