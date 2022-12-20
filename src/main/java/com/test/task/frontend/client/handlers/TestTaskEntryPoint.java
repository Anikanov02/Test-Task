package com.test.task.frontend.client.handlers;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.test.task.frontend.client.Constants;
import com.test.task.frontend.client.handlers.content.Login;
import com.test.task.frontend.client.handlers.content.PageLoader;
import org.fusesource.restygwt.client.Defaults;

public class TestTaskEntryPoint implements EntryPoint, ValueChangeHandler<String> {
    @Override
    public void onModuleLoad() {
        Defaults.setServiceRoot(Constants.baseUrl);
        PageLoader.go(new Login());
    }

    @Override
    public void onValueChange(ValueChangeEvent<String> event) {
        PageLoader.go(History.getToken());
    }
}
