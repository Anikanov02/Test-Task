package com.test.task.frontend.client.handlers.content;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;

import java.util.Iterator;

public class Home extends Page {
    interface HomeUiBinder extends UiBinder<HTMLPanel, Home> {}
    private static HomeUiBinder uiBinder = GWT.create(HomeUiBinder.class);
    @UiField
    TabPanel panel;

    public Home() {
        initWidget(uiBinder.createAndBindUi(this));

        final VerticalPanel accountContentPanel = new VerticalPanel();
        final VerticalPanel contractsContentPanel = new VerticalPanel();
        panel.add(accountContentPanel, "Account");
        panel.add(contractsContentPanel, "Contracts");

        panel.addSelectionHandler(event -> {
            if (panel.getTabBar().getSelectedTab() == 0) {
                setupAccountTab(accountContentPanel);
            } else if (panel.getTabBar().getSelectedTab() == 1) {
                setupContractsTab(contractsContentPanel);
            }
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
