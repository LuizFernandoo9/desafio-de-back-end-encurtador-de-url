package encurtador.url.shortener_url.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import encurtador.url.shortener_url.model.Url;
import encurtador.url.shortener_url.repository.UrlRepository;

import java.util.Optional;
import java.util.Random;

@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;

    private final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private final int SHORT_URL_LENGTH = 5;
    private final Random random = new Random();

    public Url createShortUrl(String originalUrl) {
        String shortUrl = generateShortUrl();
        while (urlRepository.findByShortUrl(shortUrl).isPresent()) {
            shortUrl = generateShortUrl();
        }
        Url url = new Url(originalUrl, shortUrl);
        return urlRepository.save(url);
    }

    public Optional<Url> getOriginalUrl(String shortUrl) {
        return urlRepository.findByShortUrl(shortUrl);
    }

    public Url incrementAccessCount(Url url) {
        url.setAccessCount(url.getAccessCount() + 1);
        return urlRepository.save(url);
    }

    private String generateShortUrl() {
        StringBuilder sb = new StringBuilder(SHORT_URL_LENGTH);
        for (int i = 0; i < SHORT_URL_LENGTH; i++) {
            sb.append(ALPHANUMERIC.charAt(random.nextInt(ALPHANUMERIC.length())));
        }
        return sb.toString();
    }
}
