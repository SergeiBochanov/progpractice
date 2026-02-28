package ru.omstu.fitprogwork.lab3;

public class ExtractionRequest {
    public String type;
    public String data;
    public String path;

    public ExtractionRequest(String type, String data, String path) {
        this.type = type;
        this.data = data;
        this.path = path;
    }
}
