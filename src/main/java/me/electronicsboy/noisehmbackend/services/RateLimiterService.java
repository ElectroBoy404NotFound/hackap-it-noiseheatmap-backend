package me.electronicsboy.noisehmbackend.services;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class RateLimiterService {

    private final Cache<String, AtomicInteger> userRequestCounts = CacheBuilder.newBuilder()
            .expireAfterWrite(1, TimeUnit.MINUTES)
            .build();

    public boolean isAllowed(String username, int limit) {
        AtomicInteger requests = userRequestCounts.getIfPresent(username);
        if (requests == null) {
            requests = new AtomicInteger(0);
            userRequestCounts.put(username, requests);
        }
        return requests.incrementAndGet() <= limit;
    }
}
