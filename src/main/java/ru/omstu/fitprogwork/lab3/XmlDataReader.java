package ru.omstu.fitprogwork.lab3;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Component;

@Component
public class XmlDataReader implements DataReader {
    @Override
    public String getType() { return "xml"; }

    @Override
    public String getValue(String data, String path) {
        try {
            XmlMapper mapper = new XmlMapper();
            JsonNode root = mapper.readTree(data);
            JsonNode node = root.at(path);
            return node.asText();
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
}
