package mess.tech.movieapp.dto;

import mess.tech.movieapp.entites.Movie;

public record MovieDetailsDTO(
        Movie movie,
        String trailerUrl
) {}