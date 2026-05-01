package mess.tech.movieapp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@Service
public class TmdbService {

    @Value("${tmdb.api.base-url}")
    private String baseUrl;

    @Value("${tmdb.api.token}")
    private String token;

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    public Long getTmdbMovieId(String title) {
        try {
            String url = baseUrl + "/search/movie?query=" + URLEncoder.encode(title, StandardCharsets.UTF_8);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                    .GET()
                    .build();

            HttpResponse<String> response =
                    httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            var json = mapper.readTree(response.body());

            if (json.get("results").isEmpty()) return null;

            return json.get("results").get(0).get("id").asLong();

        } catch (Exception e) {
            return null;
        }
    }

    public String getTmdbTrailerUrl(Long tmdbId) {
        try {
            String url = baseUrl + "/movie/" + tmdbId + "/videos";

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                    .GET()
                    .build();

            HttpResponse<String> response =
                    httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            var json = mapper.readTree(response.body());

            for (var v : json.get("results")) {
                if ("YouTube".equals(v.get("site").asText())
                        && "Trailer".equals(v.get("type").asText())) {

                    return v.get("key").asText();
                }
            }

            return null;

        } catch (Exception e) {
            return null;
        }
    }

    public String getTmdbTrailerUrlByTitle(String title) {
        Long id = getTmdbMovieId(title);
        return getTmdbTrailerUrl(id);
    }
}