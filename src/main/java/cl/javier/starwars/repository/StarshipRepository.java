package cl.javier.starwars.repository;
import cl.javier.starwars.domain.Starship; import org.springframework.data.jpa.repository.JpaRepository;
public interface StarshipRepository extends JpaRepository<Starship,Long>{}
