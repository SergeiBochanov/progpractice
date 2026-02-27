package ru.omstu.fitprogwork.lab3;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.springframework.stereotype.Component;

@Component
public class YamlDataReader implements DataReader {
    @Override
    public String getType() { return "yaml"; }

    @Override
    public String getValue(String data, String path) {
        try {
            YAMLMapper mapper = new YAMLMapper();
            JsonNode root = mapper.readTree(data);
            JsonNode node = root.at(path);
            return node.asText();
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
}
