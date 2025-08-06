package mess.tech.movieapp.controller;

import mess.tech.movieapp.controller.base.GenericUserMovieController;
import mess.tech.movieapp.service.WatchLaterService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/watch-later")
public class WatchLaterController extends GenericUserMovieController<WatchLaterService> {

    public WatchLaterController(WatchLaterService watchLaterService) {
        super(watchLaterService);
    }

}
