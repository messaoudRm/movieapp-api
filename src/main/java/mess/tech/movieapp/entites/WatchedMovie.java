package mess.tech.movieapp.entites;

import jakarta.persistence.*;
import mess.tech.movieapp.entites.base.AbstractUserMovieRelation;
import mess.tech.movieapp.entites.composite.WatchedCompositeId;

@Entity
@Table(name = "watched")
public class WatchedMovie extends AbstractUserMovieRelation {

    @EmbeddedId
    private WatchedCompositeId watchedCompositeId;

    public WatchedMovie() {
        this.watchedCompositeId = new WatchedCompositeId();
    }

    public WatchedMovie(WatchedCompositeId watchedCompositeId, User user, Movie movie) {
        this.watchedCompositeId = watchedCompositeId;
        this.user = user;
        this.movie = movie;
    }

    public WatchedCompositeId getWatchedCompositeId() {
        return watchedCompositeId;
    }

    public void setWatchedCompositeId(WatchedCompositeId watchedCompositeId) {
        this.watchedCompositeId = watchedCompositeId;
    }
}