package com.jamesorban.ecommerceapplicationbackend.dto;


public class ResponseDTO {

    private Object body;
    private String errorMessage;

    public ResponseDTO(Object body, String errorMessage) {
        this.body = body;
        this.errorMessage = errorMessage;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
