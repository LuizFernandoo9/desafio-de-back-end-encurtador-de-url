package encurtador.url.shortener_url.model;



import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String originalUrl;

    @Column(nullable = false, unique = true)
    private String shortUrl;

     @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private int accessCount;
   
    public Url() {
    }
    public Url(String originalUrl, String shortUrl) {
        this.originalUrl = originalUrl;
        this.shortUrl = shortUrl;
        this.createdAt = LocalDateTime.now();
        this.accessCount = 0;
    }

    public long getId() {
        return id;
    }
    public String getOriginalUrl() {
        return originalUrl;
    }
    public String getShortUrl() {
        return shortUrl;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public int getAccessCount() {
        return accessCount;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }
    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public void setAccessCount(int accessCount) {
        this.accessCount = accessCount;
    }
}
