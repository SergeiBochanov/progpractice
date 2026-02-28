package ru.omstu.fitprogwork.lab3;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExtractionResponse {
    public String value;
    public String error;

    public ExtractionResponse(String value) {
        this.value = value;
    }

    public static ExtractionResponse error(String message) {
        ExtractionResponse response = new ExtractionResponse(null);
        response.error = message;
        return response;
    }
}
