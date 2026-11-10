package com.soccerapp.gameday.dtos;

public class RegisterUserRequest {
    private String name;
    private String password;

    public RegisterUserRequest(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public RegisterUserRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
