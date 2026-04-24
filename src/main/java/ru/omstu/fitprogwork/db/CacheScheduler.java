package ru.omstu.fitprogwork.db;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.omstu.fitprogwork.lab3.CacheService;

@Component
@EnableScheduling
public class CacheScheduler {
    private final CacheService cacheService;

    public CacheScheduler(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @Scheduled(fixedRate = 30000)
    @Transactional(rollbackFor = Exception.class)
    public void evictOldEntries() {
        try {
            cacheService.removeOldEntries(60);
            System.out.println("Удалены записи кэша, созданные более 60 секунд назад");
        } catch (Exception e) {
            System.out.println("Ошибка при очистке кэша");
            throw e;
        }
    }

    @Scheduled(cron = "0 0 0 * * SUN")
    @Transactional(rollbackFor = Exception.class)
    public void clearAllCache() {
        try {
            cacheService.clearAll();
            System.out.println("Полная очистка таблицы кэша выполнена (еженедельная)");
        } catch (Exception e) {
            System.out.println("Ошибка при полной очистке кэша");
            throw e;
        }
    }
}
