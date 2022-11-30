package com.test.task.client.domain.content;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.*;
import com.test.task.client.domain.GlobalSessionInfo;
import com.test.task.client.domain.dto.UserDto;
import com.test.task.client.service.UserRequestService;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

public class Registration extends Composite {
    private Label infoLabel;

    private TextBox firstNameBox;

    private TextBox lastNameBox;

    private TextBox fathersNameBox;

    private TextBox loginBox;

    private PasswordTextBox passwordTextBox;

    private PasswordTextBox confirmPasswordTextBox;

    private TextBox dateOfBirthBox;

    private TextBox phoneNumberBox;

    private Button submit;

    private Button login;

    private UserRequestService userService;

    public Registration() {
        userService = GWT.create(UserRequestService.class);

        VerticalPanel panel = new VerticalPanel();
        infoLabel = new Label();
        firstNameBox = new TextBox();
        lastNameBox = new TextBox();
        fathersNameBox = new TextBox();
        loginBox = new TextBox();
        passwordTextBox = new PasswordTextBox();
        confirmPasswordTextBox = new PasswordTextBox();
        dateOfBirthBox = new TextBox();
        phoneNumberBox = new TextBox();
        submit = new Button("Submit");
        login = new Button("Login");
        refresh();
        panel.add(infoLabel);
        panel.add(new Label("First name:"));
        panel.add(firstNameBox);
        panel.add(new Label("Last name:"));
        panel.add(lastNameBox);
        panel.add(new Label("Fathers name"));
        panel.add(fathersNameBox);
        panel.add(new Label("Login:"));
        panel.add(loginBox);
        panel.add(new Label("Password:"));
        panel.add(passwordTextBox);
        panel.add(new Label("Confirm password:"));
        panel.add(confirmPasswordTextBox);
        panel.add(new Label("Date of birth(yyyy-MM-dd):"));
        panel.add(dateOfBirthBox);
        panel.add(new Label("Phone:"));
        panel.add(phoneNumberBox);
        panel.add(submit);
        panel.add(login);
        initWidget(panel);

        submit.addClickHandler(event -> {
            refresh();
            if (validated()) {
                UserDto userDto = new UserDto(firstNameBox.getText().trim(),
                        lastNameBox.getText().trim(),
                        fathersNameBox.getText().trim(),
                        loginBox.getText().trim(),
                        passwordTextBox.getText().trim(),
                        dateOfBirthBox.getText(),
                        phoneNumberBox.getText().trim());
                userService.newUser(userDto, new MethodCallback<UserDto>() {
                    @Override
                    public void onFailure(Method method, Throwable throwable) {
                        message("creating user failed with error: " + throwable.getMessage());
                    }

                    @Override
                    public void onSuccess(Method method, UserDto userDto) {
                        GlobalSessionInfo.userDto = userDto;
                        ContentContainer.go(new Home());
                    }
                });
            }
        });

        login.addClickHandler(event -> ContentContainer.go(new Login()));
    }

    private boolean validated() {
        if (firstNameBox.getText().trim().equals("")) {
            message("You should specify your first name");
            return false;
        }

        if (lastNameBox.getText().trim().equals("")) {
            message("You should specify your last name");
            return false;
        }

        if (fathersNameBox.getText().trim().equals("")) {
            message("You should specify your fathers name");
            return false;
        }

        if (loginBox.getText().trim().equals("")) {
            message("Login field must not be empty");
            return false;
        }

        if (passwordTextBox.getText().trim().equals("")) {
            message("Password field must not be empty");
            return false;
        }

        if (confirmPasswordTextBox.getText().trim().equals("")) {
            message("You should confirm password");
            return false;
        }

        if (!passwordTextBox.getText().equals(confirmPasswordTextBox.getText())) {
            message("Password confirmation failed, retry");
            return false;
        }

        if (dateOfBirthBox.getText().equals("")) {
            message("You should specify your date of birth");
            return false;
        }

        if (!dateOfBirthBox.getText().matches("\\d{4}-\\d{2}-\\d{2}")) {
            message("Date of birth should be of pattern yyyy-MM-dd");
            return false;
        }

        if (phoneNumberBox.getText().trim().equals("")) {
            message("Phone field must not be empty");
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
        return "Registration Page";
    }
}
