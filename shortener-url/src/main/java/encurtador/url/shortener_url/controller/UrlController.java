package encurtador.url.shortener_url.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import encurtador.url.shortener_url.model.Url;
import encurtador.url.shortener_url.service.UrlService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UrlController {

    @Autowired
    private UrlService urlService;

    @PostMapping("/shorten")
    public ResponseEntity<?> shortenUrl(@RequestBody Map<String, String> request) {
        String originalUrl = request.get("originalUrl");
        Url url = urlService.createShortUrl(originalUrl);
        return ResponseEntity.ok(url);
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<?> redirectToUrl(@PathVariable String shortUrl) {
        return urlService.getOriginalUrl(shortUrl)
                .map(url -> {
                    urlService.incrementAccessCount(url);
                    return ResponseEntity.status(302).header("Location", url.getOriginalUrl()).build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/stats/{shortUrl}")
    public ResponseEntity<?> getUrlStats(@PathVariable String shortUrl) {
        return urlService.getOriginalUrl(shortUrl)
                .map(url -> {
                    long daysActive = ChronoUnit.DAYS.between(url.getCreatedAt(), LocalDateTime.now()) + 1;
                    double averageAccessPerDay = (double) url.getAccessCount() / daysActive;

                    Map<String, Object> stats = new HashMap<>();
                    stats.put("originalUrl", url.getOriginalUrl());
                    stats.put("shortUrl", url.getShortUrl());
                    stats.put("createdAt", url.getCreatedAt());
                    stats.put("accessCount", url.getAccessCount());
                    stats.put("averageAccessPerDay", averageAccessPerDay);

                    return ResponseEntity.ok(stats);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
