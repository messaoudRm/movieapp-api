package mess.tech.movieapp.decorator;


import mess.tech.movieapp.dto.MovieDetailsDTO;
import mess.tech.movieapp.entites.Movie;
import mess.tech.movieapp.service.TmdbService;
import org.springframework.stereotype.Component;

@Component
public class MovieTrailerDecorator implements MovieDecorator{

    private final TmdbService tmdbService;

    public MovieTrailerDecorator(TmdbService tmdbService) {
        this.tmdbService = tmdbService;
    }

    @Override
    public MovieDetailsDTO decorate(Movie movie) {
        String trailerUrl = tmdbService.getTmdbTrailerUrlByTitle(movie.getTitle());
        return new MovieDetailsDTO(movie, trailerUrl);
    }
}
