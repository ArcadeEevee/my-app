package Models;

import Logic.Pair;
import Logic.Shuffle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;

import java.util.ArrayList;

public class CardField {

    private ArrayList<Pair> pairs;
    private int[] shuffledCardsPosition;

    public CardField(ArrayList<String> coverPaths, Display display) {

        // Creating a List for pairs
        pairs = new ArrayList<Pair>();

        // Creating Paired Cards
        // coverPath[0] -> backCover
        // coverPath[] i > 0 -> frontCover
        for(int i = 1; i < coverPaths.size(); i++){
            Pair pair = new Pair(coverPaths.get(0), coverPaths.get(i), display);
            pairs.add(pair);
        }

        // Shuffle Cards
        Shuffle shuffle = new Shuffle(pairs);
        shuffledCardsPosition = shuffle.shuffleCards();
        System.out.println(shuffledCardsPosition.length);
    }

    public void placeCards(Group groupCards, GridData gridData){

        for(Pair pair : pairs){

            pair.getCard1().setButton(new Button(groupCards, SWT.TOGGLE));
            pair.getCard1().getButton().setLayoutData(gridData);

            pair.getCard2().setButton(new Button(groupCards, SWT.TOGGLE));
            pair.getCard2().getButton().setLayoutData(gridData);

        }
    }

}
