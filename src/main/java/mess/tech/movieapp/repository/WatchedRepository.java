package mess.tech.movieapp.repository;

import mess.tech.movieapp.entites.WatchedMovie;
import mess.tech.movieapp.entites.composite.WatchedCompositeId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WatchedRepository  extends JpaRepository<WatchedMovie, WatchedCompositeId> {
    // permet de récupérer tous les films vus d’un utilisateur donné
    List<WatchedMovie> findByWatchedCompositeIdUserId(Long userId);
}