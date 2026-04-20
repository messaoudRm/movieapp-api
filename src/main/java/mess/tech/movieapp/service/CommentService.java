package mess.tech.movieapp.service;

import jakarta.persistence.EntityNotFoundException;
import mess.tech.movieapp.dto.CommentDTO;
import mess.tech.movieapp.entites.Comment;
import mess.tech.movieapp.entites.Movie;
import mess.tech.movieapp.entites.User;
import mess.tech.movieapp.repository.CommentRepository;
import mess.tech.movieapp.repository.MovieRepository;
import mess.tech.movieapp.repository.UserRepository;
import mess.tech.movieapp.service.kafka.ReviewProducer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final ReviewProducer reviewProducer;

    public CommentService(CommentRepository commentRepository,
                          UserRepository userRepository,
                          MovieRepository movieRepository,
                          ReviewProducer reviewProducer) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
        this.reviewProducer = reviewProducer;
    }

    public void addComment(Comment comment) {
        commentEntities(comment);
        Comment saved = commentRepository.save(comment);
        commentWithSentiment(saved);
    }

    private void commentEntities(Comment comment) {
        Long userId = comment.getUser().getId();
        Long movieId = comment.getMovie().getId();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new EntityNotFoundException("Movie not found with id: " + movieId));

        comment.setUser(user);
        comment.setMovie(movie);
    }

    private void commentWithSentiment(Comment comment) {
        reviewProducer.sendReview(comment.getId(), comment.getMovie().getId(), comment.getContent());
    }

    public void removeCommentById(Long id) {
        this.commentRepository.deleteById(id);
    }

    public List<CommentDTO> getCommentsByMovieId(Long movieId) {
        List<Comment> comments = commentRepository.findByMovieId(movieId);
        if (comments.isEmpty()) return List.of();

        return comments.stream()
                .map(this::toCommentDTO)
                .toList();
    }

    public List<CommentDTO> getCommentsByUserId(Long userId) {
        List<Comment> comments = commentRepository.findByUserId(userId);
        if (comments.isEmpty()) return List.of();

        return comments.stream()
                .map(this::toCommentDTO)
                .toList();
    }

    public CommentDTO toCommentDTO(Comment comment) {
        return new CommentDTO(
                comment.getId(),
                comment.getUser().getId(),
                comment.getUser().getUsername(),
                comment.getMovie().getId(),
                comment.getMovie().getTitle(),
                comment.getContent(),
                comment.getCreatedAt(),
                comment.getSentimentLabel(),
                comment.getSentimentScore()
        );
    }

}
