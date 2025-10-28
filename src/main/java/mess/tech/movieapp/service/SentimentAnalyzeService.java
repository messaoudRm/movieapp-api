package mess.tech.movieapp.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import mess.tech.movieapp.dto.SentimentResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Service
public class SentimentAnalyzeService {

    @Value("${sentiment.api.url}")
    private String sentimentApiUrl;
    private final ObjectMapper objectMapper;

    private final HttpClient httpClient;

    public SentimentAnalyzeService() {
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .build();
        this.objectMapper = new ObjectMapper();
    }

    public SentimentResult analyzeSentiment(String text) {
        try {
            HttpRequest request = buildRequest(text);
            HttpResponse<String> response = sendRequest(request);
            return parseResponse(response);
        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt(); // si interruption
            throw new RuntimeException("Error calling Sentiment API", e);
        }
    }

    /** Construit la requête HTTP POST pour le microservice FastAPI */
    private HttpRequest buildRequest(String text) {
        String jsonBody = String.format("{\"text\": \"%s\"}", text.replace("\"", "\\\""));
        return HttpRequest.newBuilder()
                .uri(URI.create(sentimentApiUrl))
                .timeout(Duration.ofSeconds(5))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();
    }

    /** Envoie la requête HTTP et retourne la réponse */
    private HttpResponse<String> sendRequest(HttpRequest request)
            throws IOException, InterruptedException {
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    /** Analyse la réponse JSON et retourne un objet SentimentResult */
    private SentimentResult parseResponse(HttpResponse<String> response) throws IOException {
        if (response.statusCode() != 200) {
            throw new RuntimeException("Sentiment API returned status: " + response.statusCode());
        }

        JsonNode jsonNode = objectMapper.readTree(response.body());
        String label = jsonNode.path("label").asText("UNKNOWN");
        double score = jsonNode.path("score").asDouble(0.0);
        return new SentimentResult(label, score);
    }

}

