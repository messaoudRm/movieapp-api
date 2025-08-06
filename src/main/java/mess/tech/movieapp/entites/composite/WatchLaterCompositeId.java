package mess.tech.movieapp.entites.composite;

import jakarta.persistence.Embeddable;
import mess.tech.movieapp.entites.base.AbstractUserMovieId;

@Embeddable
public class WatchLaterCompositeId extends AbstractUserMovieId {

    public WatchLaterCompositeId() {
        super();
    }

    public WatchLaterCompositeId(Long userId, Long movieId) {
        super(userId, movieId);
    }
}