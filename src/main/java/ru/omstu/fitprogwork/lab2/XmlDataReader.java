package ru.omstu.fitprogwork.lab2;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.InputStream;

public class XmlDataReader implements DataReader {
    @Override
    public String getValue(String fileName, String path) {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(fileName)) {
            XmlMapper mapper = new XmlMapper();
            JsonNode root = mapper.readTree(is);
            JsonNode node = root.at(path);
            return node.asText();
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
}
