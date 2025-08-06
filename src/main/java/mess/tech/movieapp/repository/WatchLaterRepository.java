package mess.tech.movieapp.repository;

import mess.tech.movieapp.entites.WatchLater;
import mess.tech.movieapp.entites.composite.WatchLaterCompositeId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WatchLaterRepository  extends JpaRepository<WatchLater, WatchLaterCompositeId> {
    // permet de récupérer tous les watch-later d’un utilisateur donné
    List<WatchLater> findByWatchLaterCompositeIdUserId(Long userId);
}