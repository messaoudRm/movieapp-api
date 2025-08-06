package mess.tech.movieapp.service;

import jakarta.persistence.EntityNotFoundException;
import mess.tech.movieapp.entites.Movie;
import mess.tech.movieapp.repository.MovieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void addMovie(Movie movie) {
        this.movieRepository.save(movie);
    }

    public Page<Movie> getAllMovies(Pageable pageable) {
        return this.movieRepository.findAll(pageable);
    }

    public Movie getMovieById(Long id) {
        Optional<Movie> optionalUser = this.movieRepository.findById(id);
        return optionalUser.orElseThrow(
                () -> new EntityNotFoundException("No movie exists with this id")
        );
    }

    public void removeMovieById(Long id) {
        this.movieRepository.deleteById(id);
    }

    public void editMovieById(Long id, Movie movie) {
        Movie movieById = this.getMovieById(id);
        Movie movieEdited = this.setEditMovieById(movieById,movie);
        this.movieRepository.save(movieEdited);
    }

    public Movie setEditMovieById(Movie movieById, Movie movie){
        movieById.setReleaseDate(movie.getReleaseDate());
        movieById.setTitle(movie.getTitle());
        movieById.setOverview(movie.getOverview());
        movieById.setPopularity(movie.getPopularity());
        movieById.setVoteCount(movie.getVoteCount());
        movieById.setVoteAverage(movie.getVoteAverage());
        movieById.setOriginalLanguage(movie.getOriginalLanguage());
        movieById.setGenre(movie.getGenre());
        movieById.setPosterUrl(movie.getPosterUrl());

        return movieById;
    }
}