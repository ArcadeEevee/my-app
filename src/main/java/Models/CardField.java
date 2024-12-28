package Models;

import Logic.Pair;
import Logic.Shuffle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;

import java.util.ArrayList;

public class CardField {

    private int[] shuffledCardsPosition;

    public CardField(ArrayList<String> coverPaths, Display display) {

        // Creating Paired Cards
        // coverPath[0] -> backCover
        // coverPath[] i > 0 -> frontCover
        for(int i = 1; i < coverPaths.size(); i++){
            new Pair(coverPaths.get(0), coverPaths.get(i), display);
        }

        // Shuffle Cards
        Shuffle shuffle = new Shuffle(Pair.getAllPairs());
        shuffledCardsPosition = shuffle.shuffleCards();
    }

    public void placeCards(Group groupCards, GridData gridData){

        // Places Cards equals to their shuffled positions
        for(int i : shuffledCardsPosition) {
            for (Pair pair : Pair.getAllPairs()) {
                for (Card card : pair.getCards()) {
                    if(card.getId() == i){
                        card.setButton(new Button(groupCards, SWT.TOGGLE));
                        card.getButton().setLayoutData(gridData);
                    }
                }
            }
        }
    }

    public static void clearField(){
        for (Pair pair : Pair.getAllPairs()) {
            for (Card card : pair.getCards()) {
                card.getButton().dispose();
            }
        }
        Pair.resetAllPairs();
        Card.resetIDs();
    }


}
