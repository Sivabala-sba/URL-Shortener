package com.example.URLShortener.service;

import com.example.URLShortener.model.UrlMapping;
import com.example.URLShortener.util.FileStorageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UrlShortenerService {

    @Autowired
    private FileStorageUtil fileStorageUtil;

    // Method to generate a short URL for a given original URL
    public String shortenUrl(String originalUrl) {
        // Check if the URL is already shortened
        List<UrlMapping> urlMappings = fileStorageUtil.readUrlMappings();
        Optional<UrlMapping> existingMapping = urlMappings.stream()
                .filter(mapping -> mapping.getOriginalUrl().equals(originalUrl))
                .findFirst();

        if (existingMapping.isPresent()) {
            return existingMapping.get().getShortCode();
        }

        // Generate a new short code
        String shortCode = UUID.randomUUID().toString().substring(0, 6);
        UrlMapping newMapping = new UrlMapping(shortCode, originalUrl);

        // Save the new mapping
        urlMappings.add(newMapping);
        fileStorageUtil.writeUrlMappings(urlMappings);

        return shortCode;
    }

    // Method to retrieve the original URL from a short code
    public String getOriginalUrl(String shortCode) {
        List<UrlMapping> urlMappings = fileStorageUtil.readUrlMappings();
        Optional<UrlMapping> mapping = urlMappings.stream()
                .filter(urlMapping -> urlMapping.getShortCode().equals(shortCode))
                .findFirst();

        return mapping.map(UrlMapping::getOriginalUrl).orElse(null);
    }
}
