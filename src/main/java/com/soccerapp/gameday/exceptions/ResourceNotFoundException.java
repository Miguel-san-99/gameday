package com.soccerapp.gameday.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    private final String redirectUri;
    public ResourceNotFoundException(String message, String redirectUri) {
        super(message);
        this.redirectUri = redirectUri;
    }

    public String getRedirectUri() {
        return redirectUri;
    }
}
