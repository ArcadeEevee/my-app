package Models;


import Logic.Pair;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;

public class Card {

    private static final int MAX_NUMBER_OF_TURNED_UP_CARDS = 2;
    private static int currentIds;
    private static int turnedUpCards;


    private final int id;

    private final Image backCoverIMG;
    private final Image frontCoverIMG;
    private boolean selected;
    private Button button;


    // Creates a card
    public Card(String pathBackCover, String pathFrontCover, Display display){

        // Get latest ID
        this.id = currentIds;

        // defines the look of the cards
        this.backCoverIMG = new Image(display, pathBackCover);
        this.frontCoverIMG = new Image(display, pathFrontCover);
        this.selected = false;

        // Updates ID by increasing it for the next card
        countID();
    }

    // returns the ID of the card
    public int getId(){
        return id;
    }

    // returns the Button
    public Button getButton(){
        return button;
    }

    // Creates a Button and gives it a Selection Listener
    // this button is the Card you can click on so the back side is shown
    public void setButton(Button button){

        this.button = button;
        this.button.addSelectionListener(setUpButtonListener());
        setFrontHidden();
    }

    // sets Front side visible
    public void setFrontVisible(){

        button.setImage(frontCoverIMG);
        selected = true;
    }

    // sets back side visible
    public void setFrontHidden(){

        button.setImage(backCoverIMG);
        selected = false;
    }

    public void turnCardAround(){

        // Check if card front is up
        // and if two cards are already open
        if (!selected && turnedUpCards < MAX_NUMBER_OF_TURNED_UP_CARDS) {
            // Show front side of the card and increase the counter for open Cards
            increaseTurnedUpCards();
            setFrontVisible();
            Pair.addToSelectedCards(this);

            // Check if card front is visible
        } else if(selected) {
            // if it is turn the card around and decrease the counter
            decreaseTurnedUpCards();
            setFrontHidden();
            Pair.removeFromSelectedCards(this);
        }

        // if 2 Cards are turned up it compares these two
        if(turnedUpCards == MAX_NUMBER_OF_TURNED_UP_CARDS){
            Pair.compareCards();
        }
    }

    // adds Selection Listener to the Button
    public SelectionListener setUpButtonListener(){

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

    public void setCardInvisible(){
        button.setBounds(0,0,0,0);
    }

    // increases the ID by to a free ID number
    private static void countID(){
        currentIds += 1;
    }

    // resets the amount of turned up cards
    public static void resetTurnedUpCards(){
        turnedUpCards = 0;
    }

    // resets all the ID counter
    public static void resetIDs(){currentIds = 0;}

    // Two functions to keep track of the number off currently turned up cards

    // one increases the number
    public static void increaseTurnedUpCards(){

        if(turnedUpCards < MAX_NUMBER_OF_TURNED_UP_CARDS){
            turnedUpCards += 1;
        }
    }

    // and the second decreases the number
    public static void decreaseTurnedUpCards(){

        if(turnedUpCards > 0){
            turnedUpCards -= 1;
        }
    }
}
