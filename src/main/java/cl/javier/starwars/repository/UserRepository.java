package cl.javier.starwars.repository;
import cl.javier.starwars.domain.AppUser; import org.springframework.data.jpa.repository.JpaRepository; import java.util.Optional;
public interface UserRepository extends JpaRepository<AppUser,Long>{Optional<AppUser> findByUsernameIgnoreCase(String username); boolean existsByUsernameIgnoreCase(String username);}
