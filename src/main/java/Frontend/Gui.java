package Frontend;

import Models.CardField;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;

import java.util.ArrayList;
import java.util.Arrays;

public class Gui {

    private Shell shell;
    private Display display;
    private Menu menu;


    public Gui(int width, int height){

        createShell(width, height);
    }

    private void createShell(int width, int height){
        //Create Shell
        display = new Display();
        shell = new Shell();
        shell.setLayout(new GridLayout(width, true));
        shell.setMinimumSize(750, 500);

        //Create Menu
        menu = new Menu(shell, width);

        // Get Images
        //replace
        ArrayList<String> paths = new ArrayList<String>(Arrays.asList("src/main/Images/blank.png", "src/main/Images/Karte_1.png", "src/main/Images/Karte_2.png", "src/main/Images/Karte_3.png", "src/main/Images/Karte_4.png", "src/main/Images/Karte_5.png", "src/main/Images/Karte_6.png"));

        //Create Card Area
        Group groupCards = new Group(shell, SWT.NO_RADIO_GROUP | SWT.SHADOW_ETCHED_IN);
        GridData gridCardData = new GridData(SWT.FILL, SWT.FILL, true, true, 1,1);
        GridData gridGroupCardsData = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1);
        GridLayout cardGrid = new GridLayout(4, true);

        groupCards.setLayout(cardGrid);
        groupCards.setLayoutData(gridGroupCardsData);

        // Creating cards, Shuffle those, and place them on the Grid
        CardField cardField = new CardField(paths, display);
        cardField.placeCards(groupCards, gridCardData);


    }

    public void open(){

        shell.open();
        while(! shell.isDisposed()){
            if(! display.readAndDispatch()){
                display.sleep();
            }
        }
    }

}
