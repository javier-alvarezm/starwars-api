package cl.javier.starwars.service;

import cl.javier.starwars.api.ResourceDtos;
import cl.javier.starwars.domain.Film;
import cl.javier.starwars.repository.FilmRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class FilmQueryService {
    private final FilmRepository films;

    public FilmQueryService(FilmRepository films) { this.films = films; }

    public Page<ResourceDtos.FilmSummary> list(String title, Pageable pageable) {
        return (title.isBlank() ? films.findAll(pageable)
                : films.findByTitleContainingIgnoreCase(title, pageable)).map(ResourceDtos::filmSummary);
    }

    public ResourceDtos.FilmDetail detail(Long id) { return ResourceDtos.filmDetail(find(id)); }

    public List<ResourceDtos.NamedResource> characters(Long id) {
        return find(id).getCharacters().stream().map(c -> new ResourceDtos.NamedResource(c.getId(), c.getName())).toList();
    }

    public List<ResourceDtos.NamedResource> vehicles(Long id) {
        return find(id).getVehicles().stream().map(v -> new ResourceDtos.NamedResource(v.getId(), v.getName())).toList();
    }

    public List<ResourceDtos.NamedResource> starships(Long id) {
        return find(id).getStarships().stream().map(s -> new ResourceDtos.NamedResource(s.getId(), s.getName())).toList();
    }

    private Film find(Long id) {
        return films.findById(id).orElseThrow(() -> new EntityNotFoundException("Película no encontrada: " + id));
    }
}
