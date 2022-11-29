package com.test.task.client.domain.content;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;

public class Registration extends Content {
    private TextBox loginBox;
    private PasswordTextBox passwordTextBox;
    private PasswordTextBox confirmPasswordTextBox;
    private Button submit;
    private Button login;
    public Registration() {
        loginBox = new TextBox();
        passwordTextBox = new PasswordTextBox();
        confirmPasswordTextBox = new PasswordTextBox();
        submit = new Button("Submit");
        login = new Button("Login");

        login.addClickHandler(event -> ContentContainer.go(new Login()));
    }
    
    @Override
    public String getTitle() {
        return "Registration Page";
    }
}
