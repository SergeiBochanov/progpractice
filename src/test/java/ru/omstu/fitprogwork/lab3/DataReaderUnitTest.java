package ru.omstu.fitprogwork.lab3;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

public class DataReaderUnitTest {

    private String loadResourceAsString(String fileName) throws Exception {
        try (var inputStream = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (inputStream == null) throw new IllegalArgumentException("Файл не найден: " + fileName);
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

    @Test
    void jsonReaderShouldParseCorrectly() throws Exception {
        JsonDataReader reader = new JsonDataReader();
        String jsonData = loadResourceAsString("data.json");

        String result = reader.getValue(jsonData, "/name");
        assertThat(result).isEqualTo("Иван Иванов");
    }

    @Test
    void xmlReaderShouldParseCorrectly() throws Exception {
        XmlDataReader reader = new XmlDataReader();
        String xmlData = loadResourceAsString("data.xml");

        String result = reader.getValue(xmlData, "/name");
        assertThat(result).isEqualTo("Иван Иванов");
    }

    @Test
    void yamlReaderShouldParseCorrectly() throws Exception {
        YamlDataReader reader = new YamlDataReader();
        String yamlData = loadResourceAsString("data.yaml");

        String result = reader.getValue(yamlData, "/name");
        assertThat(result).isEqualTo("Иван Иванов");
    }

    @Test
    void shouldReturnErrorMessageOnInvalidData() {
        JsonDataReader reader = new JsonDataReader();
        String result = reader.getValue("invalid json {", "/test");

        assertThat(result).contains("Unrecognized token");
    }
}
