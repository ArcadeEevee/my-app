package Frontend;

import org.eclipse.swt.SWT;
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
        option_two = createOption(menu);

    }

    private ToolItem createOption(ToolBar menu){
        option = new ToolItem(menu, SWT.PUSH);
        return option;
    }
}
