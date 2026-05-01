package mess.tech.movieapp.decorator;

import mess.tech.movieapp.dto.MovieDetailsDTO;
import mess.tech.movieapp.entites.Movie;

public interface MovieDecorator {
    MovieDetailsDTO decorate(Movie movie);
}
