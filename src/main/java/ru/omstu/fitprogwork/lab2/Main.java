package ru.omstu.fitprogwork.lab2;

public class Main {
    public static void main(String[] args) {
        String jsonFile = "data.json";
        DataReader jsonReader = DataReaderFactory.getReader(jsonFile);
        System.out.println("JSON Name: " + jsonReader.getValue(jsonFile, "/name"));
        System.out.println("JSON Relation: " + jsonReader.getValue(jsonFile, "/relation/1/name"));

        String xmlFile = "data.xml";
        DataReader xmlReader = DataReaderFactory.getReader(xmlFile);
        System.out.println("XML Result: " + xmlReader.getValue(xmlFile, "/name"));
        System.out.println("XML Relation: " + xmlReader.getValue(xmlFile, "/relation/1/name"));
    }
}
