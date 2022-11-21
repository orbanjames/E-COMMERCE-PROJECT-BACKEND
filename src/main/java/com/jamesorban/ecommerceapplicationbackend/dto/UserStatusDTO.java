package com.jamesorban.ecommerceapplicationbackend.dto;


public class UserStatusDTO {

    private boolean isUserRegisterToSynod;
    private String status;


    public UserStatusDTO(boolean isUserRegister, String status) {
        this.isUserRegisterToSynod = isUserRegister;
        this.status = status;
    }

    public UserStatusDTO(boolean isUserRegister) {
        this.isUserRegisterToSynod = isUserRegister;
    }

    public UserStatusDTO(String status) {
        this.status = status;
    }

    public boolean isUserRegisterToSynod() {
        return isUserRegisterToSynod;
    }

    public void setUserRegisterToSynod(boolean userRegisterToSynod) {
        isUserRegisterToSynod = userRegisterToSynod;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}








