package mess.tech.movieapp.controller;

import mess.tech.movieapp.controller.base.GenericUserMovieController;
import mess.tech.movieapp.service.WatchedMovieService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/watched")
public class WatchedMovieController extends GenericUserMovieController<WatchedMovieService> {

    public WatchedMovieController(WatchedMovieService watchedMovieService) {
        super(watchedMovieService);
    }

}
