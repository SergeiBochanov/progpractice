package ru.omstu.fitprogwork.lab3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataProcessingService {
    final Map<String, DataReader> extractors;

    @Autowired
    public DataProcessingService(List<DataReader> extractorList) {
        this.extractors = new HashMap<>();
        for (DataReader reader : extractorList) {
            this.extractors.put(reader.getType(), reader);
        }
    }

    public String process(ExtractionRequest request) {
        DataReader reader = extractors.get(request.type);
        if (reader == null) {
            return "Формат " + request.type + " не поддерживается";
        }
        return reader.getValue(request.data, request.path);
    }
}
