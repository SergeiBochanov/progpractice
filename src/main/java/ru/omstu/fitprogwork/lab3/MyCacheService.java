package ru.omstu.fitprogwork.lab3;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MyCacheService implements CacheService {
    private final ConcurrentHashMap<String, String> cache = new ConcurrentHashMap<>();

    @Override
    public Optional<String> get(String key) {
        return Optional.ofNullable(cache.get(key));
    }

    @Override
    public void put(String key, String value) {
        cache.put(key, value);
    }
}
