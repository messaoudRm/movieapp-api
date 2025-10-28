package mess.tech.movieapp.repository;

import mess.tech.movieapp.entites.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    // commentaires d’un film
    List<Comment> findByMovieId(Long movieId);

    // commentaires d’un utilisateur
    List<Comment> findByUserId(Long userId);
}
