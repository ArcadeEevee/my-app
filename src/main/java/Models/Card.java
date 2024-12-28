package Models;


import Logic.Pair;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;

public class Card {

    private static int currentIds;
    private static int turnedUpCards;

    private final int id;

    private Image backtCoverIMG;
    private Image frontCoverIMG;
    private boolean selected;
    private Button button;


    public Card(String pathBackCover, String pathFrontCover, Display display){

        this.id = currentIds;

        this.backtCoverIMG = new Image(display, pathBackCover);
        this.frontCoverIMG = new Image(display, pathFrontCover);
        this.selected = false;

        countID();
    }

    public int getId(){
        return id;
    }

    public Button getButton(){
        return button;
    }

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

        button.setImage(backtCoverIMG);
        selected = false;
    }

    public void turnCardAround(){

        // Check if card front is up
        // and if two cards are already open
        if (!selected && turnedUpCards < 2) {
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

        if(turnedUpCards == 2){
            Pair.compareCards();
        }
    }

    // add Event Listeners to the button
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

    // each Card has to have its own ID
    private static void countID(){
        currentIds += 1;
    }

    public static void resetTurnedUpCards(){
        turnedUpCards = 0;
    }

    public static void resetIDs(){currentIds = 0;}

    public static void increaseTurnedUpCards(){

        if(turnedUpCards < 2){
            turnedUpCards += 1;
        }
    }

    public static void decreaseTurnedUpCards(){

        if(turnedUpCards > 0){
            turnedUpCards -= 1;
        }
    }
}
