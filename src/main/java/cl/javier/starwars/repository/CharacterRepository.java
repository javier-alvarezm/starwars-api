package cl.javier.starwars.repository;
import cl.javier.starwars.domain.CharacterEntity; import org.springframework.data.domain.Page; import org.springframework.data.domain.Pageable; import org.springframework.data.jpa.repository.JpaRepository;
public interface CharacterRepository extends JpaRepository<CharacterEntity,Long>{Page<CharacterEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);}
