package mess.tech.movieapp.entites;

import jakarta.persistence.*;
import mess.tech.movieapp.entites.base.AbstractUserMovieRelation;
import mess.tech.movieapp.entites.composite.WatchLaterCompositeId;

@Entity
@Table(name = "watch_later")
public class WatchLater extends AbstractUserMovieRelation {

    @EmbeddedId
    private WatchLaterCompositeId watchLaterCompositeId;

    public WatchLater() {
        this.watchLaterCompositeId = new WatchLaterCompositeId();
    }

    public WatchLater(WatchLaterCompositeId watchLaterCompositeId, User user, Movie movie) {
        this.watchLaterCompositeId = watchLaterCompositeId;
        this.user = user;
        this.movie = movie;
    }

    public WatchLaterCompositeId getWatchLaterCompositeId() {
        return watchLaterCompositeId;
    }

    public void setWatchLaterCompositeId(WatchLaterCompositeId watchLaterCompositeId) {
        this.watchLaterCompositeId = watchLaterCompositeId;
    }
}