package com.test.task.client.handlers;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.test.task.client.handlers.content.PageLoader;
import com.test.task.client.handlers.content.Login;

public class TestTaskEntryPoint implements EntryPoint, ValueChangeHandler<String> {
    @Override
    public void onModuleLoad() {
        PageLoader.go(new Login());
    }

    @Override
    public void onValueChange(ValueChangeEvent<String> event) {
        PageLoader.go(History.getToken());
    }
}
