package frontend;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

/**
* Menu for the program with two available Options.
*/
public class Menu {

  private final ToolItem optionOne;
  private final ToolItem optionTwo;
  private final ToolBar menuBar;

  /**
  * Creates the menu for the program.
  */
  public Menu(Shell shell, int width) {

    // Creates and Places Menu Toolbar
    menuBar = new ToolBar(shell, SWT.HORIZONTAL);
    GridData gridMenu = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
    gridMenu.horizontalSpan = width;

    menuBar.setLayoutData(gridMenu);

    // Creates and names Menu Options
    optionOne = createOption(menuBar);
    optionOne.setText("new game");

    optionTwo = createOption(menuBar);
    optionTwo.setText("new game with new cards");

  }

  // function to Create a menu option
  private ToolItem createOption(ToolBar menu) {
    return new ToolItem(menu, SWT.PUSH);
  }

  // Getter methods for the Two option Buttons
  public ToolItem getOptionOne() {
    return optionOne;
  }

  public ToolItem getOptionTwo() {
    return optionTwo;
  }
}
