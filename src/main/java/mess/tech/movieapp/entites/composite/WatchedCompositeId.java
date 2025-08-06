package mess.tech.movieapp.entites.composite;

import jakarta.persistence.Embeddable;
import mess.tech.movieapp.entites.base.AbstractUserMovieId;

@Embeddable
public class WatchedCompositeId extends AbstractUserMovieId {

    public WatchedCompositeId() {
        super();
    }

    public WatchedCompositeId(Long userId, Long movieId) {
        super(userId, movieId);
    }
}