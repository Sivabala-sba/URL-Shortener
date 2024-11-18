package com.example.URLShortener.util;

import com.example.URLShortener.model.UrlMapping;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Component
public class FileStorageUtil {

    private static final String FILE_PATH = "src/main/resources/urls.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    // Reads all URL mappings from the JSON file
    public List<UrlMapping> readUrlMappings() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.createNewFile();
                return new ArrayList<>();
            }
            return objectMapper.readValue(file, new TypeReference<>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // Writes the updated list of URL mappings to the JSON file
    public void writeUrlMappings(List<UrlMapping> urlMappings) {
        try {
            objectMapper.writeValue(new File(FILE_PATH), urlMappings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
