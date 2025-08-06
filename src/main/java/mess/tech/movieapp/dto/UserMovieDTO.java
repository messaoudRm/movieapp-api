package mess.tech.movieapp.dto;

public record UserMovieDTO(
        Long userId,
        String username,
        Long movieId,
        String title
){}
