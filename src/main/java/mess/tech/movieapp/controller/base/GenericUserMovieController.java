package mess.tech.movieapp.controller.base;

import mess.tech.movieapp.dto.UserMovieDTO;
import mess.tech.movieapp.entites.Movie;
import mess.tech.movieapp.service.base.AbstractUserMovieService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.function.Supplier;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

public abstract class GenericUserMovieController<T extends AbstractUserMovieService<?, ?, ?>> {
    protected final T genericUserMovieService;

    protected GenericUserMovieController(T genericUserMovieService) {
        this.genericUserMovieService = genericUserMovieService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addEntity(@RequestBody UserMovieDTO userMoviedto) {
        return tryCatchExecute(() -> {
            genericUserMovieService.addEntity(userMoviedto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        });
    }

    @GetMapping(path = "/users/{userId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getEntityListByUserId(@PathVariable Long userId) {
        return tryCatchExecute(() -> {
            List<Movie> list = genericUserMovieService.getEntityListByUserId(userId);
            if (list == null || list.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(list);
        });
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllEntityList(Pageable pageable) {
        return tryCatchExecute(() -> {
            Page<UserMovieDTO> page = genericUserMovieService.getAllEntityList(pageable);
            if (page == null || page.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(page);
        });
    }

    @DeleteMapping(path = "/users/{userId}/movies/{movieId}")
    public ResponseEntity<?> removeEntity(@PathVariable Long userId, @PathVariable Long movieId) {
        return tryCatchExecute(() -> {
            genericUserMovieService.removeEntity(userId, movieId);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        });
    }

    protected ResponseEntity<?> tryCatchExecute(Supplier<ResponseEntity<?>> bloc) {
        //Supplier sert ici à encapsuler un bloc de code qui va retourner une ResponseEntity<?>
        try {
            return bloc.get();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }
}
