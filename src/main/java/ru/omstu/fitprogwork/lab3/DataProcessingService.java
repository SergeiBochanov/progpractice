package ru.omstu.fitprogwork.lab3;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataProcessingService {
    final Map<String, DataReader> extractors;
    private final CacheService cacheService;

    public DataProcessingService(List<DataReader> extractorList, CacheService cacheService) {
        this.extractors = new HashMap<>();
        for (DataReader reader : extractorList) {
            this.extractors.put(reader.getType(), reader);
        }
        this.cacheService = cacheService;
    }

    public String process(ExtractionRequest request) {
        String key = request.type + "|" + request.data + "|" + request.path;
        return cacheService.get(key).orElseGet(() -> {
            System.out.println("Обработка для первого вызова запроса");
            String result = extractors.get(request.type).getValue(request.data, request.path);
            cacheService.put(key, result);
            return result;
        });
    }
}
