package ru.omstu.fitprogwork.lab3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/data")
public class DataExtractorController {
    final DataProcessingService processingService;

    public DataExtractorController(DataProcessingService processingService) {
        this.processingService = processingService;
    }

    @PostMapping("/extract")
    public ExtractionResponse extract(@RequestBody ExtractionRequest request) {
        try {
            String result = processingService.process(request);
            return new ExtractionResponse(result);
        } catch (Exception e) {
            return ExtractionResponse.error(e.getMessage());
        }
    }
}
