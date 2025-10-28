package mess.tech.movieapp.dto.comments;


import java.util.List;

public record UserCommentsDTO(
        Long userId,
        String username,
        List<UserCommentDTO> comments
) {}