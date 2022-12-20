package com.test.task.frontend.client.handlers.content;

import com.google.gwt.user.client.ui.*;
import com.test.task.frontend.Constants;
import com.test.task.frontend.client.domain.dto.UserDto;

public class UserDataComposite extends Composite {
    private Label infoLabel;

    private Label firstNameLabel;

    private TextBox firstNameBox;


    private boolean firstNameEnabled;

    private Label lastNameLabel;


    private TextBox lastNameBox;

    private boolean lastNameEnabled;

    private Label fathersNameLabel;

    private TextBox fathersNameBox;

    private boolean fathersNameEnabled;

    private Label loginLabel;


    private TextBox loginBox;

    private boolean loginEnabled;

    private Label passwordLabel;

    private PasswordTextBox passwordTextBox;

    private boolean passwordEnabled;

    private Label confirmPasswordLabel;

    private PasswordTextBox confirmPasswordTextBox;

    private boolean confirmPasswordEnabled;

    private Label dateOfBirthLabel;

    private TextBox dateOfBirthBox;

    private boolean dateOfBirthEnabled;

    private Label phoneNumberLabel;

    private TextBox phoneNumberBox;

    private boolean phoneNumberEnabled;

    public UserDataComposite() {
        final VerticalPanel panel = new VerticalPanel();
        firstNameEnabled = true;
        lastNameEnabled = true;
        fathersNameEnabled = true;
        loginEnabled = true;
        passwordEnabled = true;
        confirmPasswordEnabled = true;
        dateOfBirthEnabled = true;
        phoneNumberEnabled = true;

        infoLabel = new Label();
        firstNameLabel = new Label("First name:");
        firstNameBox = new TextBox();
        lastNameLabel = new Label("Last name:");
        lastNameBox = new TextBox();
        fathersNameLabel = new Label("Fathers name");
        fathersNameBox = new TextBox();
        loginLabel = new Label("Login:");
        loginBox = new TextBox();
        passwordLabel = new Label("Password:");
        passwordTextBox = new PasswordTextBox();
        confirmPasswordLabel = new Label("Confirm password:");
        confirmPasswordTextBox = new PasswordTextBox();
        dateOfBirthLabel = new Label("Date of birth(yyyy-MM-dd):");
        dateOfBirthBox = new TextBox();
        phoneNumberLabel = new Label("Phone:");
        phoneNumberBox = new TextBox();
        panel.add(infoLabel);
        panel.add(firstNameLabel);
        panel.add(firstNameBox);
        panel.add(lastNameLabel);
        panel.add(lastNameBox);
        panel.add(fathersNameLabel);
        panel.add(fathersNameBox);
        panel.add(loginLabel);
        panel.add(loginBox);
        panel.add(passwordLabel);
        panel.add(passwordTextBox);
        panel.add(confirmPasswordLabel);
        panel.add(confirmPasswordTextBox);
        panel.add(dateOfBirthLabel);
        panel.add(dateOfBirthBox);
        panel.add(phoneNumberLabel);
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
        if (firstNameEnabled && firstNameBox.getText().trim().equals("")) {
            message("You should specify your first name");
            return false;
        }

        if (lastNameEnabled && lastNameBox.getText().trim().equals("")) {
            message("You should specify your last name");
            return false;
        }

        if (fathersNameEnabled && fathersNameBox.getText().trim().equals("")) {
            message("You should specify your fathers name");
            return false;
        }

        if (loginEnabled && loginBox.getText().trim().equals("")) {
            message("Login field must not be empty");
            return false;
        }

        if (passwordEnabled && passwordTextBox.getText().trim().equals("")) {
            message("Password field must not be empty");
            return false;
        }

        if (confirmPasswordEnabled && confirmPasswordTextBox.getText().trim().equals("")) {
            message("You should confirm password");
            return false;
        }

        if (confirmPasswordEnabled && !passwordTextBox.getText().equals(confirmPasswordTextBox.getText())) {
            message("Password confirmation failed, retry");
            return false;
        }

        if (dateOfBirthEnabled && !dateOfBirthBox.getText().matches(Constants.DATE_REGEX)) {
            message("Date of birth should be of pattern yyyy-MM-dd");
            return false;
        }

        if (phoneNumberEnabled && phoneNumberBox.getText().trim().equals("")) {
            message("Phone field must not be empty");
            return false;
        }

        return true;
    }

    public UserDto getUserData() {
        String firstName = firstNameEnabled ? firstNameBox.getText().trim() : "";
        String lastName = lastNameEnabled ? lastNameBox.getText().trim() : "";
        String fathersName = fathersNameEnabled ? fathersNameBox.getText().trim() : "";
        String email = loginEnabled ? loginBox.getText().trim() : "";
        String password = passwordEnabled ? passwordTextBox.getText().trim() : "";
        String dateOfBirth = dateOfBirthEnabled ? dateOfBirthBox.getText().trim() : "";
        String phoneNumber = phoneNumberEnabled ? phoneNumberBox.getText().trim() : "";
        return new UserDto(firstName, lastName, fathersName, email, password, dateOfBirth, phoneNumber);
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

    public Label getFirstNameLabel() {
        return firstNameLabel;
    }

    public Label getLastNameLabel() {
        return lastNameLabel;
    }

    public Label getFathersNameLabel() {
        return fathersNameLabel;
    }

    public Label getLoginLabel() {
        return loginLabel;
    }

    public Label getPasswordLabel() {
        return passwordLabel;
    }

    public Label getConfirmPasswordLabel() {
        return confirmPasswordLabel;
    }

    public Label getDateOfBirthLabel() {
        return dateOfBirthLabel;
    }

    public Label getPhoneNumberLabel() {
        return phoneNumberLabel;
    }
}
