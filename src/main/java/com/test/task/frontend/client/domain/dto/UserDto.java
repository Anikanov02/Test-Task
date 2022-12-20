package com.test.task.frontend.client.domain.dto;

public class UserDto {
    private Long id;

    private String firstName;

    private String lastName;

    private String fathersName;

    private String email;

    private String password;

    private String dateOfBirth;

    private String phoneNumber;

    public UserDto(Long id, String firstName, String lastName, String fathersName, String email, String password, String dateOfBirth, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fathersName = fathersName;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
    }

    public UserDto(String firstName, String lastName, String fathersName, String email, String password, String dateOfBirth, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fathersName = fathersName;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
    }

    public UserDto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFathersName() {
        return fathersName;
    }

    public void setFathersName(String fathersName) {
        this.fathersName = fathersName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
