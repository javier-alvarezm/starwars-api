package cl.javier.starwars.service;

import cl.javier.starwars.api.ResourceDtos;
import cl.javier.starwars.domain.CharacterEntity;
import cl.javier.starwars.repository.CharacterRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CharacterQueryService {
    private final CharacterRepository characters;

    public CharacterQueryService(CharacterRepository characters) { this.characters = characters; }

    public Page<ResourceDtos.CharacterSummary> list(String name, Pageable pageable) {
        return (name.isBlank() ? characters.findAll(pageable)
                : characters.findByNameContainingIgnoreCase(name, pageable)).map(ResourceDtos::characterSummary);
    }

    public ResourceDtos.CharacterDetail detail(Long id) { return ResourceDtos.characterDetail(find(id)); }

    public List<ResourceDtos.NamedResource> films(Long id) {
        return find(id).getFilms().stream().map(f -> new ResourceDtos.NamedResource(f.getId(), f.getTitle())).toList();
    }

    public List<ResourceDtos.NamedResource> vehicles(Long id) {
        return find(id).getVehicles().stream().map(v -> new ResourceDtos.NamedResource(v.getId(), v.getName())).toList();
    }

    public List<ResourceDtos.NamedResource> starships(Long id) {
        return find(id).getStarships().stream().map(s -> new ResourceDtos.NamedResource(s.getId(), s.getName())).toList();
    }

    private CharacterEntity find(Long id) {
        return characters.findById(id).orElseThrow(() -> new EntityNotFoundException("Personaje no encontrado: " + id));
    }
}
