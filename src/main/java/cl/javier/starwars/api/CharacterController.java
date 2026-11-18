package cl.javier.starwars.api;

import cl.javier.starwars.service.CharacterQueryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/characters")
public class CharacterController {
    private final CharacterQueryService characters;

    public CharacterController(CharacterQueryService characters) { this.characters = characters; }

    @GetMapping
    public Page<ResourceDtos.CharacterSummary> list(@RequestParam(defaultValue = "") String name,
                                                     @PageableDefault(size = 20, sort = "name") Pageable pageable) {
        return characters.list(name, pageable);
    }

    @GetMapping("/{id}")
    public ResourceDtos.CharacterDetail detail(@PathVariable Long id) { return characters.detail(id); }

    @GetMapping("/{id}/films")
    public List<ResourceDtos.NamedResource> films(@PathVariable Long id) { return characters.films(id); }

    @GetMapping("/{id}/vehicles")
    public List<ResourceDtos.NamedResource> vehicles(@PathVariable Long id) { return characters.vehicles(id); }

    @GetMapping("/{id}/starships")
    public List<ResourceDtos.NamedResource> starships(@PathVariable Long id) { return characters.starships(id); }
}
