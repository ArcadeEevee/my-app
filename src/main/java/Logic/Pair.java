package Logic;

import Models.Card;
import org.eclipse.swt.widgets.Display;

public class Pair {

    private Card card1;
    private Card card2;

    public Pair(String pathBackCover, String pathFrontCover, Display display){

        this.card1 = new Card(pathBackCover, pathFrontCover, display);
        this.card2 = new Card(pathBackCover, pathFrontCover, display);

    }

    public Card getCard1(){
        return card1;
    }

    public Card getCard2(){
        return card2;
    }

    public boolean compareCards(){

        return true;
    }

}
