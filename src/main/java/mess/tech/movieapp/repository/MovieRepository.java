package mess.tech.movieapp.repository;

import mess.tech.movieapp.entites.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
