package mess.tech.movieapp.dto;

public record SentimentEvent(Long reviewId, String sentiment, double score) {
}
