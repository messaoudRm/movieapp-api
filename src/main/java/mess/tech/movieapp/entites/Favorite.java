package mess.tech.movieapp.entites;

import jakarta.persistence.*;
import mess.tech.movieapp.entites.base.AbstractUserMovieRelation;
import mess.tech.movieapp.entites.composite.FavoriteCompositeId;

@Entity
@Table(name = "favorites")
public class Favorite extends AbstractUserMovieRelation {

    @EmbeddedId
    private FavoriteCompositeId favoriteCompositeId;

    public Favorite() {
        this.favoriteCompositeId = new FavoriteCompositeId();
    }

    public Favorite(FavoriteCompositeId favoriteCompositeId, User user, Movie movie) {
        this.favoriteCompositeId = favoriteCompositeId;
        this.user = user;
        this.movie = movie;
    }

    public FavoriteCompositeId getFavoriteCompositeId() {
        return favoriteCompositeId;
    }

    public void setFavoriteCompositeId(FavoriteCompositeId favoriteCompositeId) {
        this.favoriteCompositeId = favoriteCompositeId;
    }
}