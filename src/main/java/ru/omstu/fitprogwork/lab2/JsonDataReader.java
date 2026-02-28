package ru.omstu.fitprogwork.lab2;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;

public class JsonDataReader implements DataReader {
    @Override
    public String getValue(String fileName, String path) {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(fileName)) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(is);
            JsonNode node = root.at(path);
            return node.asText();
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
}
