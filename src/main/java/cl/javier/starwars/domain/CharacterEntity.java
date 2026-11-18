package cl.javier.starwars.domain;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity @Table(name="characters")
public class CharacterEntity {
 @Id private Long id; @Column(nullable=false) private String name; private String height; private String mass; private String gender; private Integer birthYearOrder;
 @ManyToMany @JoinTable(name="character_films") private Set<Film> films=new HashSet<>();
 @ManyToMany @JoinTable(name="character_vehicles") private Set<Vehicle> vehicles=new HashSet<>();
 @ManyToMany @JoinTable(name="character_starships") private Set<Starship> starships=new HashSet<>();
 protected CharacterEntity(){} public CharacterEntity(Long id){this.id=id;}
 public Long getId(){return id;} public String getName(){return name;} public String getHeight(){return height;} public String getMass(){return mass;} public String getGender(){return gender;} public Integer getBirthYearOrder(){return birthYearOrder;}
 public Set<Film> getFilms(){return films;} public Set<Vehicle> getVehicles(){return vehicles;} public Set<Starship> getStarships(){return starships;}
 public void update(String name,String height,String mass,String gender,Integer birthYearOrder){this.name=name;this.height=height;this.mass=mass;this.gender=gender;this.birthYearOrder=birthYearOrder;}
}
