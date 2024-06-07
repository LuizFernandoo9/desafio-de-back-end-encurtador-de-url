package encurtador.url.shortener_url;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.databind.ObjectMapper;

import encurtador.url.shortener_url.model.Url;
import encurtador.url.shortener_url.repository.UrlRepository;

import org.junit.jupiter.api.BeforeEach;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UrlShortenerApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UrlRepository urlRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        urlRepository.deleteAll();
    }

    @Test
    public void testShortenUrl() throws Exception {
        Map<String, String> request = new HashMap<>();
        request.put("originalUrl", "https://www.example.com");

        mockMvc.perform(post("/shorten")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.shortUrl").exists());
    }

    @Test
    public void testRedirectToUrl() throws Exception {
        Url url = new Url("https://www.example.com", "abcdef");
        urlRepository.save(url);

        mockMvc.perform(get("/abcdef"))
                .andExpect(status().isFound())
                .andExpect(header().string("Location", "https://www.example.com"));
    }

    @Test
    public void testGetUrlStats() throws Exception {
        Url url = new Url("https://www.example.com", "abcdef");
        urlRepository.save(url);

        mockMvc.perform(get("/stats/abcdef"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.originalUrl").value("https://www.example.com"))
                .andExpect(jsonPath("$.shortUrl").value("abcdef"))
                .andExpect(jsonPath("$.accessCount").value(0));
    }
}