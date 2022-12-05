package com.test.task.frontend.client.handlers.content;

import com.google.gwt.user.client.ui.*;
import com.test.task.frontend.Constants;
import com.test.task.frontend.client.domain.dto.UserDto;

public class UserDataComposite extends Composite {
    private Label infoLabel;

    private TextBox firstNameBox;

    private TextBox lastNameBox;

    private TextBox fathersNameBox;

    private TextBox loginBox;

    private PasswordTextBox passwordTextBox;

    private PasswordTextBox confirmPasswordTextBox;

    private TextBox dateOfBirthBox;

    private TextBox phoneNumberBox;

    public UserDataComposite() {
        final VerticalPanel panel = new VerticalPanel();
        infoLabel = new Label();
        firstNameBox = new TextBox();
        lastNameBox = new TextBox();
        fathersNameBox = new TextBox();
        loginBox = new TextBox();
        passwordTextBox = new PasswordTextBox();
        confirmPasswordTextBox = new PasswordTextBox();
        dateOfBirthBox = new TextBox();
        phoneNumberBox = new TextBox();
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
        initWidget(panel);
    }

    public void loadUserData(UserDto data) {
        firstNameBox.setText(data.getFirstName());
        lastNameBox.setText(data.getLastName());
        fathersNameBox.setText(data.getFathersName());
        loginBox.setText(data.getEmail());
        passwordTextBox.setText(data.getPassword());
        dateOfBirthBox.setText(data.getDateOfBirth());
        phoneNumberBox.setText(data.getPhoneNumber());
    }

    public boolean validated() {
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

        if (!dateOfBirthBox.getText().matches(Constants.DATE_REGEX)) {
            message("Date of birth should be of pattern yyyy-MM-dd");
            return false;
        }

        if (phoneNumberBox.getText().trim().equals("")) {
            message("Phone field must not be empty");
            return false;
        }

        return true;
    }

    public void refresh() {
        infoLabel.setText("");
        infoLabel.setVisible(false);
    }

    public void message(String message) {
        infoLabel.setText(message);
        infoLabel.getElement().getStyle().setColor("red");
        infoLabel.setVisible(true);
    }

    public Label getInfoLabel() {
        return infoLabel;
    }

    public void setInfoLabel(Label infoLabel) {
        this.infoLabel = infoLabel;
    }

    public TextBox getFirstNameBox() {
        return firstNameBox;
    }

    public void setFirstNameBox(TextBox firstNameBox) {
        this.firstNameBox = firstNameBox;
    }

    public TextBox getLastNameBox() {
        return lastNameBox;
    }

    public void setLastNameBox(TextBox lastNameBox) {
        this.lastNameBox = lastNameBox;
    }

    public TextBox getFathersNameBox() {
        return fathersNameBox;
    }

    public void setFathersNameBox(TextBox fathersNameBox) {
        this.fathersNameBox = fathersNameBox;
    }

    public TextBox getLoginBox() {
        return loginBox;
    }

    public void setLoginBox(TextBox loginBox) {
        this.loginBox = loginBox;
    }

    public PasswordTextBox getPasswordTextBox() {
        return passwordTextBox;
    }

    public void setPasswordTextBox(PasswordTextBox passwordTextBox) {
        this.passwordTextBox = passwordTextBox;
    }

    public PasswordTextBox getConfirmPasswordTextBox() {
        return confirmPasswordTextBox;
    }

    public void setConfirmPasswordTextBox(PasswordTextBox confirmPasswordTextBox) {
        this.confirmPasswordTextBox = confirmPasswordTextBox;
    }

    public TextBox getDateOfBirthBox() {
        return dateOfBirthBox;
    }

    public void setDateOfBirthBox(TextBox dateOfBirthBox) {
        this.dateOfBirthBox = dateOfBirthBox;
    }

    public TextBox getPhoneNumberBox() {
        return phoneNumberBox;
    }

    public void setPhoneNumberBox(TextBox phoneNumberBox) {
        this.phoneNumberBox = phoneNumberBox;
    }
}
