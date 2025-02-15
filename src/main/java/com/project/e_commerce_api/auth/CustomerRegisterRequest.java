package com.project.e_commerce_api.auth;

public class CustomerRegisterRequest {

    private String firstName;

    private String lastName;

    private String email;

    private String address;

    private String phoneNumber;

    private Character gender;

    private String password;

    public CustomerRegisterRequest(){

    }

    public CustomerRegisterRequest(String firstName, String email, String lastName, String password,
                                   String address, String phoneNumber, Character gender) {
        this.firstName = firstName;
        this.email = email;
        this.lastName = lastName;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }
}
