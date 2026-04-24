package ru.omstu.fitprogwork.db;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cache_entry")
public class CacheEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
