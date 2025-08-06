package mess.tech.movieapp.repository;

import mess.tech.movieapp.entites.Favorite;
import mess.tech.movieapp.entites.composite.FavoriteCompositeId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FavoriteRepository  extends JpaRepository<Favorite, FavoriteCompositeId> {
    // permet de récupérer tous les favoris d’un utilisateur donné
    List<Favorite> findByFavoriteCompositeIdUserId(Long userId);
}