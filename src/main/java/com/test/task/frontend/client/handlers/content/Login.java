package com.test.task.frontend.client.handlers.content;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;
import com.test.task.frontend.client.GlobalSessionInfo;
import com.test.task.frontend.client.domain.dto.LoginDto;
import com.test.task.frontend.client.domain.dto.UserDto;
import com.test.task.frontend.client.service.UserRequestService;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

public class Login extends Page {
    interface LoginUiBinder extends UiBinder<HTMLPanel, Login> {}
    private static Login.LoginUiBinder uiBinder = GWT.create(Login.LoginUiBinder.class);

    @UiField
    VerticalPanel panel;
    private final Label infoLabel;
    private final Label loginLabel;
    private final TextBox loginBox;
    private final Label passwordLabel;
    private final PasswordTextBox passwordTextBox;
    private final Button submit;
    private final Button register;
    private final UserRequestService userService;

    public Login() {
        initWidget(uiBinder.createAndBindUi(this));
        userService = GWT.create(UserRequestService.class);

        infoLabel = new Label();
        loginLabel = new Label("Login:");
        loginBox = new TextBox();
        passwordLabel = new Label("Password:");
        passwordTextBox = new PasswordTextBox();
        submit = new Button("Submit");
        register = new Button("Register");
        refresh();
        panel.add(infoLabel);
        panel.add(loginLabel);
        panel.add(loginBox);
        panel.add(passwordLabel);
        panel.add(passwordTextBox);
        panel.add(submit);
        panel.add(register);

        submit.addClickHandler(event -> {
            refresh();
            if(validated()) {
                final LoginDto loginDto = new LoginDto(loginBox.getText().trim(), passwordTextBox.getText().trim());
                userService.login(loginDto, new MethodCallback<UserDto>() {
                    @Override
                    public void onFailure(Method method, Throwable throwable) {
                        message("logging in failed with error: " + throwable.getMessage());
                    }

                    @Override
                    public void onSuccess(Method method, UserDto userDto) {
                        GlobalSessionInfo.userDto = userDto;
                        PageLoader.go(new Home());
                    }
                });
            }
        });

        register.addClickHandler(event -> PageLoader.go(new Registration()));
    }

    private boolean validated() {
        if(loginBox.getText().trim().equals("")) {
            message("Login field must not be empty");
            return false;
        }

        if (passwordTextBox.getText().trim().equals("")) {
            message("Password field must not be empty");
            return false;
        }

        return true;
    }

    private void refresh() {
        infoLabel.setText("");
        infoLabel.setVisible(false);
    }

    public void message(String message) {
        infoLabel.setText(message);
        infoLabel.getElement().getStyle().setColor("red");
        infoLabel.setVisible(true);
    }

    @Override
    public String getTitle() {
        return "Login Page";
    }
}
