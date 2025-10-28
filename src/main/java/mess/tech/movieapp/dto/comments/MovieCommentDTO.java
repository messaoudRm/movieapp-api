package mess.tech.movieapp.dto.comments;

import java.time.LocalDateTime;

public record MovieCommentDTO(
        Long id,
        Long userId,
        String username,
        String content,
        LocalDateTime createdAt
) {}
