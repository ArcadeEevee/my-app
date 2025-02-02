package models;

import java.util.ArrayList;
import logic.Pair;
import logic.Shuffle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;

/**
 * Responsible for the laying out the cards for each round.
 */
public class CardField {

  private final int[] shuffledCardsPosition;

  /**
   * Creates the playable area and lays the cards down.
   */
  public CardField(ArrayList<String> coverPaths, Display display) {
    // Creating Paired Cards
    // coverPath[0] -> backCover
    // coverPath[] i > 0 -> frontCover
    for (int i = 1; i < coverPaths.size(); i++) {
      new Pair(coverPaths.get(0), coverPaths.get(i), display);
    }

    // Shuffle Cards
    Shuffle shuffle = new Shuffle(Pair.getAllPairs());
    shuffledCardsPosition = shuffle.shuffleCards();
  }

  /**
   * Placing the cards according to the shuffled order.
   */
  public void placeCards(Group groupCards, GridData gridData) {
    // Places Cards equals to their shuffled positions
    for (int i : shuffledCardsPosition) {
      for (Pair pair : Pair.getAllPairs()) {
        for (Card card : pair.getCards()) {
          if (card.getId() == i) {
            card.setButton(new Button(groupCards, SWT.TOGGLE));
            card.getButton().setLayoutData(gridData);
          }
        }
      }
    }
  }

  /**
   * Deletes all Cards and rests the game.
   */
  public static void clearField() {
    for (Pair pair : Pair.getAllPairs()) {
      for (Card card : pair.getCards()) {
        card.getButton().dispose();
      }
    }
    Pair.resetAllPairs();
    Card.resetIds();
  }

}
