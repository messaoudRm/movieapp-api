package mess.tech.movieapp.controller;

import mess.tech.movieapp.controller.base.GenericUserMovieController;
import mess.tech.movieapp.service.FavoriteMovieService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/favorites")
public class FavoriteMovieController extends GenericUserMovieController<FavoriteMovieService> {

    public FavoriteMovieController(FavoriteMovieService favoriteMovieService) {
        super(favoriteMovieService);
    }

}
