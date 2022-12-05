package com.test.task.frontend.client.handlers.content;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.*;
import com.test.task.frontend.client.service.UserRequestService;

import java.util.Iterator;

public class Home extends Page {

    private final VerticalPanel accountContentPanel;
    private final VerticalPanel contractsContentPanel;
    private final UserRequestService userService;

    public Home() {
        userService = GWT.create(UserRequestService.class);

        TabPanel panel = new TabPanel();
        accountContentPanel = new VerticalPanel();
        contractsContentPanel = new VerticalPanel();
        panel.add(accountContentPanel, "Account");
        panel.add(contractsContentPanel, "Contracts");
        initWidget(panel);

        panel.addSelectionHandler(event -> {
            if (panel.getTabBar().getSelectedTab() == 0) {
                setupAccountTab(accountContentPanel);
            } else if (panel.getTabBar().getSelectedTab() == 1) {
                setupContractsTab(contractsContentPanel);
            }
            centerPage();
        });
    }

    private void setupAccountTab(CellPanel panel) {
        clearPanel(panel);
        panel.add(new AccountMenuComposite());
    }

    private void setupContractsTab(CellPanel panel) {
        clearPanel(panel);
        final ContractsMenuComposite contractsMenu = new ContractsMenuComposite();
        panel.add(contractsMenu);
        contractsMenu.loadContracts();
    }

    private void clearPanel(CellPanel panel) {
        Iterator<Widget> itr = panel.iterator();
        while(itr.hasNext()) {
            itr.next();
            itr.remove();
        }
    }

    @Override
    public String getTitle() {
        return "Home Page";
    }
}
