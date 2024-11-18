package com.example.URLShortener.controller;

import com.example.URLShortener.service.UrlShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UrlShortenerController {

    @Autowired
    private UrlShortenerService urlShortenerService;

    // Endpoint to shorten a URL
    @PostMapping("/shorten")
    public ResponseEntity<Map<String, String>> shortenUrl(@RequestBody Map<String, String> request) {
        String originalUrl = request.get("originalUrl");

        if (originalUrl == null || originalUrl.isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Original URL is required."));
        }

        String shortCode = urlShortenerService.shortenUrl(originalUrl);
        Map<String, String> response = new HashMap<>();
        response.put("originalUrl", originalUrl);
        response.put("shortUrl", "http://localhost:8080/api/" + shortCode);
        return ResponseEntity.ok(response);
    }

    // Endpoint to retrieve the original URL from a short code
    @GetMapping("/{shortCode}")
    public ResponseEntity<?> getOriginalUrl(@PathVariable String shortCode) {
        String originalUrl = urlShortenerService.getOriginalUrl(shortCode);

        if (originalUrl == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Short URL not found."));
        }

        // Redirect to the original URL
        return ResponseEntity.status(HttpStatus.FOUND).header("Location", originalUrl).build();
    }
}
