package ru.omstu.fitprogwork.db;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional(readOnly = true)
    public Optional<String> get(String key) {
        return repository.findByKey(key).map(CacheEntry::getValue);
    }

    @Override
    @Transactional
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
    @Transactional
    public void removeOldEntries(long secondsAgo) {
        LocalDateTime threshold = LocalDateTime.now().minusSeconds(secondsAgo);
        repository.deleteEntriesOlderThan(threshold);
    }

    @Override
    @Transactional
    public void clearAll() {
        repository.deleteAllEntries();
    }
}
