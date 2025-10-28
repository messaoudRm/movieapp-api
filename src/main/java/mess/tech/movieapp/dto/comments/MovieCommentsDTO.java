package mess.tech.movieapp.dto.comments;

import java.util.List;

public record MovieCommentsDTO(
        Long movieId,
        String movieTitle,
        List<MovieCommentDTO> comments
) {}
