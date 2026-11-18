package cl.javier.starwars.domain;
import jakarta.persistence.*;
@Entity @Table(name="vehicles")
public class Vehicle {
 @Id private Long id; @Column(nullable=false) private String name; private String model; private String manufacturer; private String vehicleClass;
 protected Vehicle(){} public Vehicle(Long id){this.id=id;} public Long getId(){return id;} public String getName(){return name;} public String getModel(){return model;} public String getManufacturer(){return manufacturer;} public String getVehicleClass(){return vehicleClass;}
 public void update(String name,String model,String manufacturer,String vehicleClass){this.name=name;this.model=model;this.manufacturer=manufacturer;this.vehicleClass=vehicleClass;}
}
