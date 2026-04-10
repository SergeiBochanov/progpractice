package ru.omstu.fitprogwork.lab3;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataProcessingService {
    final Map<String, DataReader> extractors;

    public DataProcessingService(List<DataReader> extractorList) {
        this.extractors = new HashMap<>();
        for (DataReader reader : extractorList) {
            this.extractors.put(reader.getType(), reader);
        }
    }

    @Cacheable(value = "dataExtractionCache", key = "#request.type + '|' + #request.data + '|' + #request.path")
    public String process(ExtractionRequest request) {
        System.out.println("Обработка для первого вызова запроса");

        DataReader reader = extractors.get(request.type);
        return reader.getValue(request.data, request.path);
    }
}
