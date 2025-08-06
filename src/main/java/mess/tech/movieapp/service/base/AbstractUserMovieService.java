package mess.tech.movieapp.service.base;

import jakarta.persistence.EntityNotFoundException;
import mess.tech.movieapp.dto.UserMovieDTO;
import mess.tech.movieapp.entites.Movie;
import mess.tech.movieapp.entites.User;
import mess.tech.movieapp.entites.base.AbstractUserMovieRelation;
import mess.tech.movieapp.repository.MovieRepository;
import mess.tech.movieapp.repository.UserRepository;
import mess.tech.movieapp.service.contract.InterfaceUserMovie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public abstract class AbstractUserMovieService<
        TYPE extends AbstractUserMovieRelation,
        COMPOSITE_ID,
        REPOSITORY extends JpaRepository<TYPE, COMPOSITE_ID>> implements InterfaceUserMovie {

    protected final REPOSITORY repository;
    protected final UserRepository userRepository;
    protected final MovieRepository movieRepository;

    protected AbstractUserMovieService(REPOSITORY repository, UserRepository userRepository, MovieRepository movieRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public void addEntity(UserMovieDTO dto) {
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Movie movie = movieRepository.findById(dto.movieId())
                .orElseThrow(() -> new EntityNotFoundException("Movie not found"));

        COMPOSITE_ID id = createId(dto.userId(), dto.movieId());
        TYPE entity = createEntity(id, user, movie);
        repository.save(entity);
    }

    protected abstract COMPOSITE_ID createId(Long userId, Long movieId);

    protected abstract TYPE createEntity(COMPOSITE_ID id, User user, Movie movie);

    @Override
    public abstract List<Movie> getEntityListByUserId(Long userId);

    @Override
    public Page<UserMovieDTO> getAllEntityList(Pageable pageable) {
        return repository.findAll(pageable)
                .map(entity -> new UserMovieDTO(
                        entity.getUser().getId(),
                        entity.getUser().getUsername(),
                        entity.getMovie().getId(),
                        entity.getMovie().getTitle()
                ));
    }

    @Override
    public void removeEntity(Long userId, Long movieId) {
        COMPOSITE_ID id = createId(userId, movieId);
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Relation not found for the user " + userId + " and Movie " + movieId);
        }
    }
}