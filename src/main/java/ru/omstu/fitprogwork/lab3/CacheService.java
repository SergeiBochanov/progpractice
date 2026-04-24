package ru.omstu.fitprogwork.lab3;

import java.util.Optional;

public interface CacheService {
    Optional<String> get(String key);
    void put(String key, String value);
}
