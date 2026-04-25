package ru.omstu.fitprogwork.db;

import org.springframework.stereotype.Service;
import ru.omstu.fitprogwork.lab3.CacheService;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class DatabaseCacheService implements CacheService {
    private final CacheEntryRepository repository;

    public DatabaseCacheService(CacheEntryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<String> get(String key) {
        return repository.findByKey(key).map(CacheEntry::getValue);
    }

    @Override
    public void put(String key, String value) {
        repository.findByKey(key).ifPresentOrElse(
                existing -> {
                    existing.setValue(value);
                    existing.setCreatedAt(LocalDateTime.now());
                    repository.save(existing);
                },
                () -> repository.save(new CacheEntry(key, value))
        );
    }

    @Override
    public void removeOldEntries(long secondsAgo) {
        LocalDateTime threshold = LocalDateTime.now().minusSeconds(secondsAgo);
        repository.deleteEntriesOlderThan(threshold);
    }

    @Override
    public void clearAll() {
        repository.deleteAllEntries();
    }
}
