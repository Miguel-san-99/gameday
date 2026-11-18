package com.soccerapp.gameday.exceptions;

public class DuplicateResourceException extends RuntimeException {
    private final String redirectUri;
    public DuplicateResourceException(String message, String redirectUri) {
        super(message);
        this.redirectUri = redirectUri;
    }

    public String getRedirectUri() {
        return redirectUri;
    }
}
