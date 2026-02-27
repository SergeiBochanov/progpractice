package ru.omstu.fitprogwork.lab3;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class JsonDataReader implements DataReader {
    @Override
    public String getType() { return "json"; }

    @Override
    public String getValue(String data, String path) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(data);
            JsonNode node = root.at(path);
            return node.asText();
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
}
