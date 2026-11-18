package cl.javier.starwars.api;

import cl.javier.starwars.service.FilmQueryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/films")
public class FilmController {
    private final FilmQueryService films;

    public FilmController(FilmQueryService films) { this.films = films; }

    @GetMapping
    public Page<ResourceDtos.FilmSummary> list(@RequestParam(defaultValue = "") String title,
                                               @PageableDefault(size = 20, sort = "episodeId") Pageable pageable) {
        return films.list(title, pageable);
    }

    @GetMapping("/{id}")
    public ResourceDtos.FilmDetail detail(@PathVariable Long id) { return films.detail(id); }

    @GetMapping("/{id}/characters")
    public List<ResourceDtos.NamedResource> characters(@PathVariable Long id) { return films.characters(id); }

    @GetMapping("/{id}/vehicles")
    public List<ResourceDtos.NamedResource> vehicles(@PathVariable Long id) { return films.vehicles(id); }

    @GetMapping("/{id}/starships")
    public List<ResourceDtos.NamedResource> starships(@PathVariable Long id) { return films.starships(id); }
}
