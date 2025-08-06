package mess.tech.movieapp.entites.base;

import jakarta.persistence.*;
import mess.tech.movieapp.entites.Movie;
import mess.tech.movieapp.entites.User;
import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.CascadeType.PERSIST;

@MappedSuperclass
public abstract class AbstractUserMovieRelation {

    @ManyToOne(cascade = {PERSIST, MERGE})
    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = false)
    protected User user;

    @ManyToOne(cascade = {PERSIST, MERGE})
    @MapsId("movieId")
    @JoinColumn(name = "movie_id", nullable = false)
    protected Movie movie;

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Movie getMovie() {
        return this.movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
