package ru.omstu.fitprogwork.lab3;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

//@Service
public class MyCacheService implements CacheService {
    private final ConcurrentHashMap<String, String> values = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Long> timestamps = new ConcurrentHashMap<>();

    @Override
    public Optional<String> get(String key) {
        return Optional.ofNullable(values.get(key));
    }

    @Override
    public void put(String key, String value) {
        values.put(key, value);
        timestamps.put(key, System.currentTimeMillis());
    }

    @Override
    public void removeOldEntries(long secondsAgo) {
        long threshold = System.currentTimeMillis() - secondsAgo * 1000;
        timestamps.entrySet().removeIf(entry -> {
            if (entry.getValue() < threshold) {
                values.remove(entry.getKey());
                return true;
            }
            return false;
        });
    }

    @Override
    public void clearAll() {
        values.clear();
        timestamps.clear();
    }
}
