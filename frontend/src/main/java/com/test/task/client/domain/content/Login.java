package com.test.task.client.domain.content;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.*;
import com.test.task.client.domain.GlobalSessionInfo;
import com.test.task.client.domain.dto.LoginDto;
import com.test.task.client.domain.dto.UserDto;
import com.test.task.client.service.UserRequestService;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

public class Login extends Composite {
    private Label infoLabel;
    private TextBox loginBox;
    private PasswordTextBox passwordTextBox;
    private Button submit;
    private Button register;
    private UserRequestService userService;

    public Login() {
        userService = GWT.create(UserRequestService.class);

        VerticalPanel panel = new VerticalPanel();
        infoLabel = new Label();
        loginBox = new TextBox();
        passwordTextBox = new PasswordTextBox();
        submit = new Button("Submit");
        register = new Button("Register");
        refresh();
        panel.add(infoLabel);
        panel.add(new Label("Login:"));
        panel.add(loginBox);
        panel.add(new Label("Password:"));
        panel.add(passwordTextBox);
        panel.add(submit);
        panel.add(register);
        initWidget(panel);

        submit.addClickHandler(event -> {
            refresh();
            if(validated()) {
                LoginDto loginDto = new LoginDto(loginBox.getText().trim(), passwordTextBox.getText().trim());
                userService.login(loginDto, new MethodCallback<UserDto>() {
                    @Override
                    public void onFailure(Method method, Throwable throwable) {
                        infoLabel.setText("creating user failed with error: " + throwable.getMessage());
                    }

                    @Override
                    public void onSuccess(Method method, UserDto userDto) {
                        GlobalSessionInfo.userDto = userDto;
                        ContentContainer.go(new Home());
                    }
                });
            }
        });

        register.addClickHandler(event -> ContentContainer.go(new Registration()));
    }

    private boolean validated() {
        if (passwordTextBox.getText().trim().equals("")) {
            message("Password field must not be empty");
            return false;
        }

        if(loginBox.getText().trim().equals("")) {
            message("Login field must not be empty");
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
