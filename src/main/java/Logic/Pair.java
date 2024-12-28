package Logic;

import Models.Card;
import org.eclipse.swt.widgets.Display;

import java.util.ArrayList;

public class Pair {

    private static Card[] selectedCards;
    private static ArrayList<Pair> allPairs = new ArrayList<Pair>();

    private Card card1;
    private Card card2;
    private Card[] cards;

    public Pair(String pathBackCover, String pathFrontCover, Display display){

        selectedCards = new Card[2];

        this.card1 = new Card(pathBackCover, pathFrontCover, display);
        this.card2 = new Card(pathBackCover, pathFrontCover, display);
        cards = new Card[]{card1, card2};
        addToAllPairs(this);
    }

    public Card[] getCards() {
        return cards;
    }

    public static void addToAllPairs(Pair pair){
        allPairs.add(pair);
    }

    public static ArrayList<Pair> getAllPairs(){
        return allPairs;
    }

    public static void resetAllPairs(){ allPairs = new ArrayList<>(); }

    public static void addToSelectedCards(Card card){

        Card check = selectedCards[0];

        if(check != null) {
            selectedCards[1] = card;
        }

        if(check == null){
            selectedCards[0] = card;
        }
    }

    public static void removeFromSelectedCards(Card card){

        if( selectedCards[0] != null && selectedCards[0].getId() == card.getId()){
            selectedCards[0] = null;
        } else {
            selectedCards[1] = null;
        }
    }

    public static void compareCards(){

        // Check if selected Cards are a pair
        for(Pair pair : allPairs) {
            if (pair.card1.getId() == selectedCards[0].getId() && pair.card2.getId() == selectedCards[1].getId()) {
                selectedCards[0].setCardInvisible();
                selectedCards[1].setCardInvisible();
                resetSelectedCards();
                break;
            } else if (pair.card1.getId() == selectedCards[1].getId() && pair.card2.getId() == selectedCards[0].getId()) {
                selectedCards[0].setCardInvisible();
                selectedCards[1].setCardInvisible();
                resetSelectedCards();
                break;
            }
        }
    }

    private static void resetSelectedCards(){
        Card.resetTurnedUpCards();
        selectedCards[0] = null;
        selectedCards[1] = null;
    }

}