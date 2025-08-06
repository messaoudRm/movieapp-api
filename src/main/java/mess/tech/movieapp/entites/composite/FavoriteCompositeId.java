package mess.tech.movieapp.entites.composite;

import jakarta.persistence.Embeddable;
import mess.tech.movieapp.entites.base.AbstractUserMovieId;

@Embeddable
public class FavoriteCompositeId extends AbstractUserMovieId {

    public FavoriteCompositeId() {
        super();
    }

    public FavoriteCompositeId(Long userId, Long movieId) {
        super(userId, movieId);
    }
}
