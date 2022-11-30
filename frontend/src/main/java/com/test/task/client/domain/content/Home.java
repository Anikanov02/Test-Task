package com.test.task.client.domain.content;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Home extends Composite {
    private MenuBar manuBar;
    private HorizontalPanel contentPanel;

    public Home() {
        VerticalPanel panel = new VerticalPanel();
        manuBar = new MenuBar();
        contentPanel = new HorizontalPanel();

        panel.add(manuBar);
        panel.add(contentPanel);
        initWidget(panel);
    }

    @Override
    public String getTitle() {
        return "Home Page";
    }
}
