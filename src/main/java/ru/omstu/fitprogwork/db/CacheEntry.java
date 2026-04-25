package ru.omstu.fitprogwork.db;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cache_entry")
public class CacheEntry {

    @Id
    @Column(name = "cache_key", nullable = false, unique = true, length = 1024)
    private String key;

    @Column(name = "value", nullable = false, length = 4096)
    private String value;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public CacheEntry() {
    }

    public CacheEntry(String key, String value) {
        this.key = key;
        this.value = value;
        this.createdAt = LocalDateTime.now();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
