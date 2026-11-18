package cl.javier.starwars.domain;
import jakarta.persistence.*;
@Entity @Table(name="starships")
public class Starship {
 @Id private Long id; @Column(nullable=false) private String name; private String model; private String manufacturer; private String starshipClass;
 protected Starship(){} public Starship(Long id){this.id=id;} public Long getId(){return id;} public String getName(){return name;} public String getModel(){return model;} public String getManufacturer(){return manufacturer;} public String getStarshipClass(){return starshipClass;}
 public void update(String name,String model,String manufacturer,String starshipClass){this.name=name;this.model=model;this.manufacturer=manufacturer;this.starshipClass=starshipClass;}
}
