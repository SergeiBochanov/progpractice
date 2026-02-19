package ru.omstu.fitprogwork.lab2;

public class DataReaderFactory {
    public static DataReader getReader(String fileName) {
        if (fileName.endsWith(".json")) {
            return new JsonDataReader();
        } else if (fileName.endsWith(".xml")) {
            return new XmlDataReader();
        }
        throw new IllegalArgumentException("Неизвестный формат файла");
    }
}
