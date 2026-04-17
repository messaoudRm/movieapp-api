package mess.tech.movieapp.dto;

public record ReviewEvent(Long reviewId, Long movieId, String text) {
}
