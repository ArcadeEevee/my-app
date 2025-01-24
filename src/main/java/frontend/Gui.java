package frontend;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import models.CardField;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;

/**
* Creates the GUI for the program.
*/
public class Gui {

  private Display display;
  private Shell shell;
  private Group groupCards;
  private ArrayList<String> currentCards;
  private int currentNumberOfColumns;

  /**
  * Creates an GUI object.
  */
  public Gui() {

    // standard set of cards
    currentCards = new ArrayList<>(Arrays.asList("src/main/Images/blank.png",
            "src/main/Images/Karte_1.png", "src/main/Images/Karte_2.png",
            "src/main/Images/Karte_3.png", "src/main/Images/Karte_4.png",
            "src/main/Images/Karte_5.png", "src/main/Images/Karte_6.png"));
    // calculates the number of rows needed
    currentNumberOfColumns = calculateColumnSize((currentCards.size() - 1) * 2);
    // Creates the Shell, Menu and the Card Area
    createShell();
  }

  private void createShell() {

    // Create Shell
    display = new Display();
    shell = new Shell();
    shell.setLayout(new GridLayout(3, true));
    shell.setMinimumSize(750, 500);

    // Create Menu
    Menu menu = new Menu(shell, 3);
    menu.getOptionOne().addSelectionListener(startNewGameListener());
    menu.getOptionTwo().addSelectionListener(startNewGameWithNewCardsListener());

    // Creates a space for the cards and fills it with cards
    createCardArea();
  }

  /**
  * Creates a card area, where the game is actually played on.
  */
  public void createCardArea() {

    // Create and set Card Area
    groupCards = new Group(shell, SWT.NO_RADIO_GROUP | SWT.SHADOW_ETCHED_IN);
    GridData gridCardData = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
    GridData gridGroupCardsData = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1);
    GridLayout cardGrid = new GridLayout(currentNumberOfColumns, true);

    groupCards.setLayout(cardGrid);
    groupCards.setLayoutData(gridGroupCardsData);

    // Creating cards, Shuffle those, and place them on the Grid
    CardField cardField = new CardField(currentCards, display);
    cardField.placeCards(groupCards, gridCardData);
  }

  /**
  * Opens a new window to Select one file.
  * returns the path to the selected file.
  */
  public String getPath() {

    JFileChooser chooser = new JFileChooser();
    chooser.setDialogTitle("Select a Back Cover");
    FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg & png", "jpg", "png");
    chooser.setFileFilter(filter);
    int returnVal = chooser.showOpenDialog(null);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      return chooser.getSelectedFile().getPath();
    } else {
      throw new IllegalArgumentException("No File Selected");
    }
  }

  /**
  * Opens a new window to Select multiple files.
  * returns a list of multiple file paths.
  */
  public ArrayList<String> getPaths() {

    JFileChooser chooser = new JFileChooser();
    chooser.setDialogTitle("Select Front Covers for all Pairs (Multiple are Selectable here)");
    chooser.setMultiSelectionEnabled(true);

    FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg & png", "jpg", "png");
    chooser.setFileFilter(filter);

    ArrayList<String> paths = new ArrayList<>();
    int returnVal = chooser.showOpenDialog(null);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      for (File file : chooser.getSelectedFiles()) {
        paths.add(file.getPath());
      }
      return paths;
    } else {
      throw new IllegalArgumentException("No File Selected");
    }
  }

  /**
  * this function returns a list of file paths (Strings) for the cards.
  */
  public ArrayList<String> getPathsToAllCovers() {

    // Creates an ArrayList and lets the user Choose a Back Cover
    ArrayList<String> paths = new ArrayList<>();
    String pathBackCover = getPath();
    paths.add(pathBackCover);

    // Adds Front Covers until at least 4 are chosen
    while (paths.size() < 5) {
      paths.addAll(getPaths());
    }
    return paths;
  }

  /**
   * resets the game.
   */
  public void resetGame() {
    CardField.clearField();
    groupCards.dispose();
    createCardArea();
    shell.layout();
  }

  /**
  * Listener for the Menü Buttons.
  * Menu Button 1.
  */
  public SelectionListener startNewGameListener() {

    return new SelectionListener() {
        @Override
        public void widgetSelected(SelectionEvent selectionEvent) {
          resetGame();
        }

        @Override
        public void widgetDefaultSelected(SelectionEvent selectionEvent) {
          resetGame();
        }
    };
  }

  /**
  * Listener for the Menü Buttons.
  * Menu Button 2.
  */
  public SelectionListener startNewGameWithNewCardsListener() {
    return new SelectionListener() {
      @Override
      public void widgetSelected(SelectionEvent selectionEvent) {
        CardField.clearField();
        groupCards.dispose();
        currentCards = getPathsToAllCovers();
        currentNumberOfColumns = calculateColumnSize((currentCards.size() - 1) * 2);
        createCardArea();
        shell.layout();
      }

      @Override
      public void widgetDefaultSelected(SelectionEvent selectionEvent) {
        CardField.clearField();
        groupCards.dispose();
        currentCards = getPathsToAllCovers();
        createCardArea();
        shell.layout();
      }
    };
  }

  /**
  * Calculates the number of Rows needed.
  * The number of rows is the exponent from 2^x if the number of cards is smaller
  * the square number and the smallest exponent.
  */
  public int calculateColumnSize(int amountOfCards) {
    int counter = 0;
    while (amountOfCards > Math.pow(2, counter)) {
      counter += 1;
    }
    return counter;
  }

  /**
  * Opens the Window.
  */
  public void open() {
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
  }
}