package com.test.task.frontend.client.handlers.content;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.test.task.frontend.client.GlobalSessionInfo;
import com.test.task.frontend.client.domain.dto.UserDto;
import com.test.task.frontend.client.service.UserRequestService;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

public class Registration extends Page {
    private UserDataComposite userData;

    private Button submit;

    private Button login;

    private UserRequestService userService;

    public Registration() {
        userService = GWT.create(UserRequestService.class);

        final VerticalPanel panel = new VerticalPanel();
        userData = new UserDataComposite();
        submit = new Button("Submit");
        login = new Button("Login");
        userData.refresh();
        panel.add(userData);
        panel.add(submit);
        panel.add(login);
        initWidget(panel);

        submit.addClickHandler(event -> {
            userData.refresh();
            if (userData.validated()) {
                final UserDto userDto = new UserDto(userData.getFirstNameBox().getText().trim(),
                        userData.getLastNameBox().getText().trim(),
                        userData.getFathersNameBox().getText().trim(),
                        userData.getLoginBox().getText().trim(),
                        userData.getPasswordTextBox().getText().trim(),
                        userData.getDateOfBirthBox().getText(),
                        userData.getPhoneNumberBox().getText().trim());
                userService.newUser(userDto, new MethodCallback<UserDto>() {
                    @Override
                    public void onFailure(Method method, Throwable throwable) {
                        userData.message("creating user failed with error: " + throwable.getMessage());
                    }

                    @Override
                    public void onSuccess(Method method, UserDto userDto) {
                        GlobalSessionInfo.userDto = userDto;
                        PageLoader.go(new Home());
                    }
                });
            }
        });

        login.addClickHandler(event -> PageLoader.go(new Login()));
    }

    @Override
    public String getTitle() {
        return "Registration Page";
    }
}
