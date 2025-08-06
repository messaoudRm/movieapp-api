package mess.tech.movieapp.service;

import mess.tech.movieapp.entites.Favorite;
import mess.tech.movieapp.entites.Movie;
import mess.tech.movieapp.entites.User;
import mess.tech.movieapp.entites.composite.FavoriteCompositeId;
import mess.tech.movieapp.repository.FavoriteRepository;
import mess.tech.movieapp.repository.MovieRepository;
import mess.tech.movieapp.repository.UserRepository;
import mess.tech.movieapp.service.base.AbstractUserMovieService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FavoriteMovieService extends AbstractUserMovieService<Favorite, FavoriteCompositeId, FavoriteRepository> {

    public FavoriteMovieService(FavoriteRepository favoriteRepository,
                                UserRepository userRepository,
                                MovieRepository movieRepository) {
        super(favoriteRepository, userRepository, movieRepository);
    }

    @Override
    protected FavoriteCompositeId createId(Long userId, Long movieId) {
        return new FavoriteCompositeId(userId, movieId);
    }

    @Override
    protected Favorite createEntity(FavoriteCompositeId id, User user, Movie movie) {
        return new Favorite(id, user, movie);
    }

    @Override
    public List<Movie> getEntityListByUserId(Long userId) {
        return repository.findByFavoriteCompositeIdUserId(userId).stream()
                .map(Favorite::getMovie)
                .toList();
    }

}