package Models;


import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Card {

    private static int currentIds;

    private int id;
    private Image backtCoverIMG;
    private Image frontCoverIMG;
    private boolean frontVisible;
    private Button button;


    public Card(String pathBackCover, String pathFrontCover, Display display){

        this.id = currentIds;

        this.backtCoverIMG = new Image(display, pathBackCover);
        this.frontCoverIMG = new Image(display, pathFrontCover);
        this.frontVisible = false;

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

    public void setFrontVisible(){

        button.setImage(frontCoverIMG);
        frontVisible = true;
    }

    public void setFrontHidden(){

        button.setImage(backtCoverIMG);
        frontVisible = false;
    }

    public void turnCardAround(){

        if(!frontVisible){
            setFrontVisible();
        } else {
            setFrontHidden();
        }
    }

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

    private static void countID(){
        currentIds += 1;
    }
}
