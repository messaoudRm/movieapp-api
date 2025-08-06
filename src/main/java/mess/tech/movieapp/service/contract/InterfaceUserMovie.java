package mess.tech.movieapp.service.contract;

import mess.tech.movieapp.dto.UserMovieDTO;
import mess.tech.movieapp.entites.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InterfaceUserMovie {

    void addEntity(UserMovieDTO dto);

    List<Movie> getEntityListByUserId(Long userId);

    void removeEntity(Long userId, Long movieId);

    Page<UserMovieDTO> getAllEntityList(Pageable pageable);

}
