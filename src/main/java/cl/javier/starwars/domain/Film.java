package cl.javier.starwars.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity @Table(name="films")
public class Film {
    @Id private Long id;
    @Column(nullable=false) private String title;
    private Integer episodeId; private String director; private String producer; private LocalDate releaseDate;
    @ManyToMany(mappedBy="films") private Set<CharacterEntity> characters=new HashSet<>();
    @ManyToMany private Set<Vehicle> vehicles=new HashSet<>();
    @ManyToMany private Set<Starship> starships=new HashSet<>();
    protected Film() {}
    public Film(Long id){this.id=id;} public Long getId(){return id;} public String getTitle(){return title;} public Integer getEpisodeId(){return episodeId;} public String getDirector(){return director;} public String getProducer(){return producer;} public LocalDate getReleaseDate(){return releaseDate;}
    public Set<CharacterEntity> getCharacters(){return characters;} public Set<Vehicle> getVehicles(){return vehicles;} public Set<Starship> getStarships(){return starships;}
    public void update(String title,Integer episodeId,String director,String producer,LocalDate releaseDate){this.title=title;this.episodeId=episodeId;this.director=director;this.producer=producer;this.releaseDate=releaseDate;}
}
