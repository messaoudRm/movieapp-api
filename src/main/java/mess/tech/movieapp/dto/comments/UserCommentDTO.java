package mess.tech.movieapp.dto.comments;

import java.time.LocalDateTime;

public record UserCommentDTO(
        Long id,
        Long movieId,
        String movieTitle,
        String content,
        LocalDateTime createdAt
) {}
