package ru.omstu.fitprogwork.lab3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DataExtractorIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private DataProcessingService dataProcessingService;

    private ExtractionRequest jsonRequest;

    private String loadResourceAsString(String fileName) throws Exception {
        try (var inputStream = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (inputStream == null) throw new IllegalArgumentException("Файл не найден: " + fileName);
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

    @BeforeEach
    void setUp() throws Exception {
        String jsonData = loadResourceAsString("data.json");
        jsonRequest = new ExtractionRequest("json", jsonData, "/name");
    }

    @Test
    void shouldReturnCorrectValueForJsonType_viaController() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<ExtractionRequest> entity = new HttpEntity<>(jsonRequest, headers);

        ResponseEntity<ExtractionResponse> response = restTemplate.exchange(
                "/api/data/extract",
                HttpMethod.POST,
                entity,
                ExtractionResponse.class
        );

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().value).isEqualTo("Иван Иванов");
    }

    @Test
    void shouldSelectCorrectReaderByTypeJson() {
        String result = dataProcessingService.process(jsonRequest);
        assertThat(result).isEqualTo("Иван Иванов");
    }

    @Test
    void shouldSelectCorrectReaderByTypeXml() throws Exception {
        String xmlData = loadResourceAsString("data.xml");
        ExtractionRequest xmlReq = new ExtractionRequest("xml", xmlData, "/name");

        String result = dataProcessingService.process(xmlReq);
        assertThat(result).isEqualTo("Иван Иванов");
    }

    @Test
    void shouldSelectCorrectReaderByTypeYaml() throws Exception {
        String yamlData = loadResourceAsString("data.yaml");
        ExtractionRequest yamlReq = new ExtractionRequest("yaml", yamlData, "/name");

        String result = dataProcessingService.process(yamlReq);
        assertThat(result).isEqualTo("Иван Иванов");
    }

    @Test
    void shouldUseCacheOnSecondIdenticalRequest() {
        long start1 = System.currentTimeMillis();
        String result1 = dataProcessingService.process(jsonRequest);
        long time1 = System.currentTimeMillis() - start1;

        long start2 = System.currentTimeMillis();
        String result2 = dataProcessingService.process(jsonRequest);
        long time2 = System.currentTimeMillis() - start2;

        assertThat(result1).isEqualTo(result2);
        assertThat(result1).isEqualTo("Иван Иванов");

        System.out.println("Время 1-го вызова: " + time1 + " ms");
        System.out.println("Время 2-го вызова:  " + time2 + " ms");
    }
}
