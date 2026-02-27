package ru.omstu.fitprogwork.lab2;

import java.util.HashMap;
import java.util.Map;

public class DataReaderFactory {
    static final Map<String, DataReader> readers = new HashMap<>();

    static {
        readers.put("json", new JsonDataReader());
        readers.put("xml", new XmlDataReader());
    }

    public static DataReader getReader(String fileName) {
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        DataReader reader = readers.get(extension);
        if (reader == null) {
            throw new IllegalArgumentException("Неизвестный формат файла");
        }
        return reader;
    }
}
