package Frontend;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public class Menu {

    private ToolBar menu;
    private ToolItem option;
    private ToolItem option_one;
    private ToolItem option_two;

    public Menu(Shell shell, int width){

        menu = new ToolBar(shell, SWT.HORIZONTAL);
        GridData gridMenu = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gridMenu.horizontalSpan = width;

        menu.setLayoutData(gridMenu);

        option_one = createOption(menu);
        option_one.setText("new game");

        option_two = createOption(menu);
        option_two.setText("new game with new cards");

    }

    private ToolItem createOption(ToolBar menu){
        option = new ToolItem(menu, SWT.PUSH);
        return option;
    }

    public ToolItem getOption_One(){
        return option_one;
    }

    public ToolItem getOption_two(){
        return option_two;
    }

    // add Event Listeners to the button
}
