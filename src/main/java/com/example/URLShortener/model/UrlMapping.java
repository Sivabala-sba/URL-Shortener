package com.example.URLShortener.model;

public class UrlMapping {
    private String shortCode;
    private String originalUrl;

    // Default constructor
    public UrlMapping() {
    }

    // Parameterized constructor
    public UrlMapping(String shortCode, String originalUrl) {
        this.shortCode = shortCode;
        this.originalUrl = originalUrl;
    }

    // Getters and Setters
    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }
}
