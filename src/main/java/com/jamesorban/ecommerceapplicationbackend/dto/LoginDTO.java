package com.jamesorban.ecommerceapplicationbackend.dto;

import com.jamesorban.ecommerceapplicationbackend.models.User;

public class LoginDTO {

    private String accessToken;

    private User user;

    private boolean error;

    private String errorMessage;

    public LoginDTO() {
    }

    public LoginDTO(String accessToken, User user) {
        this.accessToken = accessToken;
        this.user = user;
    }

    public LoginDTO(boolean error, String errorMessage) {
        this.error = error;
        this.errorMessage = errorMessage;
    }


    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
