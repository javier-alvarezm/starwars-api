package cl.javier.starwars.repository;
import cl.javier.starwars.domain.Vehicle; import org.springframework.data.jpa.repository.JpaRepository;
public interface VehicleRepository extends JpaRepository<Vehicle,Long>{}
