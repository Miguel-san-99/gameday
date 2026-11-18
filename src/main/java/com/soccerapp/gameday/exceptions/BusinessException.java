package com.soccerapp.gameday.exceptions;

public class BusinessException extends RuntimeException {
    private final String redirectUri;
    public BusinessException(String message, String redirectUri) {
        super(message);
        this.redirectUri = redirectUri;
    }

    public String getRedirectUri() {
        return redirectUri;
    }
}
