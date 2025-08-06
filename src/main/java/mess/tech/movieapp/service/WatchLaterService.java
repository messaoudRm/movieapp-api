package mess.tech.movieapp.service;

import mess.tech.movieapp.entites.Movie;
import mess.tech.movieapp.entites.User;
import mess.tech.movieapp.entites.WatchLater;
import mess.tech.movieapp.entites.composite.WatchLaterCompositeId;
import mess.tech.movieapp.repository.MovieRepository;
import mess.tech.movieapp.repository.UserRepository;
import mess.tech.movieapp.repository.WatchLaterRepository;
import mess.tech.movieapp.service.base.AbstractUserMovieService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WatchLaterService extends AbstractUserMovieService<WatchLater, WatchLaterCompositeId, WatchLaterRepository> {

    public WatchLaterService(WatchLaterRepository watchLaterRepository,
                             UserRepository userRepository,
                             MovieRepository movieRepository) {
        super(watchLaterRepository, userRepository, movieRepository);
    }

    @Override
    protected WatchLaterCompositeId createId(Long userId, Long movieId) {
        return new WatchLaterCompositeId(userId, movieId);
    }

    @Override
    protected WatchLater createEntity(WatchLaterCompositeId id, User user, Movie movie) {
        return new WatchLater(id, user, movie);
    }

    @Override
    public List<Movie> getEntityListByUserId(Long userId) {
        return repository.findByWatchLaterCompositeIdUserId(userId).stream()
                .map(WatchLater::getMovie)
                .toList();
    }

}