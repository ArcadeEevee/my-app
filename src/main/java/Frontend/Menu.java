package Frontend;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public class Menu {

    private final ToolItem optionOne;
    private final ToolItem optionTwo;

    public Menu(Shell shell, int width){

        // Creates and Places Menu Toolbar
        ToolBar menu = new ToolBar(shell, SWT.HORIZONTAL);
        GridData gridMenu = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gridMenu.horizontalSpan = width;

        menu.setLayoutData(gridMenu);

        // Creates and names Menu Options
        optionOne = createOption(menu);
        optionOne.setText("new game");

        optionTwo = createOption(menu);
        optionTwo.setText("new game with new cards");

    }

    // function to Create a menu option
    private ToolItem createOption(ToolBar menu){
        return new ToolItem(menu, SWT.PUSH);
    }

    // Getter methods for the Two option Buttons
    public ToolItem getOption_One(){
        return optionOne;
    }

    public ToolItem getOptionTwo(){
        return optionTwo;
    }
}
