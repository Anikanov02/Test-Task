package com.test.task.client.handlers;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.test.task.client.domain.content.ContentContainer;
import com.test.task.client.domain.content.Login;
import com.test.task.client.service.ContractRequestService;
import com.test.task.client.service.UserRequestService;

public class TestTaskEntryPoint implements EntryPoint, ValueChangeHandler<String> {
    final UserRequestService userService = GWT.create(UserRequestService.class);
    final ContractRequestService contractService = GWT.create(ContractRequestService.class);

    @Override
    public void onModuleLoad() {
        ContentContainer.go(new Login());

    }

    @Override
    public void onValueChange(ValueChangeEvent<String> event) {
        ContentContainer.go(History.getToken());
    }
}
