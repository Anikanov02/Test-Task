package com.test.task.frontend.client.handlers.content;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;

public abstract class Page extends Composite {
    protected void centerPage() {
        int left = Window.getClientWidth() / 2 - this.getOffsetWidth() / 2;
        int top = Window.getClientHeight() / 2 - this.getOffsetHeight() / 2;
        PageLoader.setPagePosition(this, left, top);
    }
}
