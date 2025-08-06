package mess.tech.movieapp.service;

import mess.tech.movieapp.entites.Movie;
import mess.tech.movieapp.entites.User;
import mess.tech.movieapp.entites.WatchedMovie;
import mess.tech.movieapp.entites.composite.WatchedCompositeId;
import mess.tech.movieapp.repository.MovieRepository;
import mess.tech.movieapp.repository.UserRepository;
import mess.tech.movieapp.repository.WatchedRepository;
import mess.tech.movieapp.service.base.AbstractUserMovieService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WatchedMovieService extends AbstractUserMovieService<WatchedMovie, WatchedCompositeId, WatchedRepository> {

    public WatchedMovieService(WatchedRepository watchedRepository,
                               UserRepository userRepository,
                               MovieRepository movieRepository) {
        super(watchedRepository, userRepository, movieRepository);
    }

    @Override
    protected WatchedCompositeId createId(Long userId, Long movieId) {
        return new WatchedCompositeId(userId, movieId);
    }

    @Override
    protected WatchedMovie createEntity(WatchedCompositeId id, User user, Movie movie) {
        return new WatchedMovie(id, user, movie);
    }

    @Override
    public List<Movie> getEntityListByUserId(Long userId) {
        return repository.findByWatchedCompositeIdUserId(userId).stream()
                .map(WatchedMovie::getMovie)
                .toList();
    }

}
