package cl.javier.starwars.api;
import cl.javier.starwars.domain.*; import java.time.LocalDate; import java.util.List;
public final class ResourceDtos {private ResourceDtos(){}
 public record NamedResource(Long id,String name){}
 public record CharacterSummary(Long id,String name,String height,String mass,String gender){}
 public record CharacterDetail(Long id,String name,String height,String mass,String gender,List<NamedResource> films,List<NamedResource> vehicles,List<NamedResource> starships){}
 public record FilmSummary(Long id,String title,Integer episodeId,String director,LocalDate releaseDate){}
 public record FilmDetail(Long id,String title,Integer episodeId,String director,String producer,LocalDate releaseDate,List<NamedResource> characters,List<NamedResource> vehicles,List<NamedResource> starships){}
 public static CharacterSummary characterSummary(CharacterEntity c){return new CharacterSummary(c.getId(),c.getName(),c.getHeight(),c.getMass(),c.getGender());}
 public static CharacterDetail characterDetail(CharacterEntity c){return new CharacterDetail(c.getId(),c.getName(),c.getHeight(),c.getMass(),c.getGender(),c.getFilms().stream().map(f->new NamedResource(f.getId(),f.getTitle())).toList(),c.getVehicles().stream().map(v->new NamedResource(v.getId(),v.getName())).toList(),c.getStarships().stream().map(s->new NamedResource(s.getId(),s.getName())).toList());}
 public static FilmSummary filmSummary(Film f){return new FilmSummary(f.getId(),f.getTitle(),f.getEpisodeId(),f.getDirector(),f.getReleaseDate());}
 public static FilmDetail filmDetail(Film f){return new FilmDetail(f.getId(),f.getTitle(),f.getEpisodeId(),f.getDirector(),f.getProducer(),f.getReleaseDate(),f.getCharacters().stream().map(c->new NamedResource(c.getId(),c.getName())).toList(),f.getVehicles().stream().map(v->new NamedResource(v.getId(),v.getName())).toList(),f.getStarships().stream().map(s->new NamedResource(s.getId(),s.getName())).toList());}
}
