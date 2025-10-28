package mess.tech.movieapp.service;

import jakarta.persistence.EntityNotFoundException;
import mess.tech.movieapp.dto.comments.MovieCommentDTO;
import mess.tech.movieapp.dto.comments.MovieCommentsDTO;
import mess.tech.movieapp.dto.comments.UserCommentDTO;
import mess.tech.movieapp.dto.comments.UserCommentsDTO;
import mess.tech.movieapp.entites.Comment;
import mess.tech.movieapp.entites.Movie;
import mess.tech.movieapp.entites.User;
import mess.tech.movieapp.repository.CommentRepository;
import mess.tech.movieapp.repository.MovieRepository;
import mess.tech.movieapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    public CommentService(CommentRepository commentRepository,
                          UserRepository userRepository,
                          MovieRepository movieRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
    }

    public void addComment(Comment comment) {

        Long userId = comment.getUser().getId();
        Long movieId = comment.getMovie().getId();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new EntityNotFoundException("Movie not found with id: " + movieId));

        comment.setUser(user);
        comment.setMovie(movie);

        commentRepository.save(comment);
    }

    public void removeCommentById(Long id) {
        this.commentRepository.deleteById(id);
    }

    public MovieCommentsDTO getCommentsByMovieId(Long movieId) {
        List<Comment> comments = commentRepository.findByMovieId(movieId);
        if (comments.isEmpty()) return null;

        var movie = comments.get(0).getMovie();

        return new MovieCommentsDTO(
                movie.getId(),
                movie.getTitle(),
                toMovieCommentDTOList(comments)
        );
    }

    public UserCommentsDTO getCommentsByUserId(Long userId) {
        List<Comment> comments = commentRepository.findByUserId(userId);
        if (comments.isEmpty()) return null;

        var user = comments.get(0).getUser();

        return new UserCommentsDTO(
                user.getId(),
                user.getUsername(),
                toUserCommentDTOList(comments)
        );
    }

    private List<UserCommentDTO> toUserCommentDTOList(List<Comment> comments) {
        return comments.stream()
                .map(c -> new UserCommentDTO(
                        c.getId(),
                        c.getMovie().getId(),
                        c.getMovie().getTitle(),
                        c.getContent(),
                        c.getCreatedAt()
                ))
                .toList();
    }

    private List<MovieCommentDTO> toMovieCommentDTOList(List<Comment> comments) {
        return comments.stream()
                .map(c -> new MovieCommentDTO(
                        c.getId(),
                        c.getUser().getId(),
                        c.getUser().getUsername(),
                        c.getContent(),
                        c.getCreatedAt()
                ))
                .toList();
    }

}
