package com.test.task.frontend.client.handlers.content;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.RootPanel;

public class PageLoader {

    public static void go(Page c) {
        RootPanel.get().clear();
        RootPanel.get().getElement().getStyle().setPosition(Style.Position.RELATIVE);
        RootPanel.get().add(c);
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
        RootPanel.get().setWidgetPosition(page, left, top);
    }
}