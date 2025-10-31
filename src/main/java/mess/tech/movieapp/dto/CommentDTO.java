package mess.tech.movieapp.dto;

import java.time.LocalDateTime;

public record CommentDTO(
        Long id,
        Long userId,
        String username,
        Long movieId,
        String movieTitle,
        String content,
        LocalDateTime createdAt,
        String sentimentLabel,
        double sentimentScore
) {}