package com.test.task.client.domain.content;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;

public class Login extends Content {
    private TextBox loginBox;
    private PasswordTextBox passwordTextBox;
    private Button submit;
    private Button register;

    public Login() {
        loginBox = new TextBox();
        passwordTextBox = new PasswordTextBox();
        submit = new Button("Submit");
        register = new Button("Register");

        register.addClickHandler(event -> ContentContainer.go(new Registration()));
    }

    @Override
    public String getTitle() {
        return "Login Page";
    }
}
