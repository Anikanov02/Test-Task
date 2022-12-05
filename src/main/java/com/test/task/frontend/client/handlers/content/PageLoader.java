package com.test.task.frontend.client.handlers.content;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;

public class PageLoader {

    public static void go(Page c) {
        RootPanel.get("application").clear();
        RootPanel.get("application").getElement().getStyle().setPosition(Style.Position.RELATIVE);

        RootPanel.get("application").add(c);
        // find center
        int left = Window.getClientWidth() / 2 - c.getOffsetWidth() / 2;
        int top = Window.getClientHeight() / 2 - c.getOffsetHeight() / 2;
        setPagePosition(c, left, top);
        History.newItem(c.getTitle());
    }

    public static void go(String pageName) {
        switch (pageName) {
            case "Login":
                go(new Login());
                break;
            case "Home":
                go(new Home());
                break;
            case "Registration":
                go(new Registration());
                break;
            default:
                throw new IllegalArgumentException("Unsupported Page Name: " + pageName);
        }
    }

    public static void setPagePosition(Page page, int left, int top) {
        RootPanel.get("application").setWidgetPosition(page, left, top);
    }
}