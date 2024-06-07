package encurtador.url.shortener_url.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import encurtador.url.shortener_url.model.Url;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url, Long> {
    Optional<Url> findByShortUrl(String shortUrl);
}