package cl.javier.starwars.config;
import cl.javier.starwars.domain.*; import cl.javier.starwars.repository.UserRepository; import org.springframework.beans.factory.annotation.Value; import org.springframework.boot.CommandLineRunner; import org.springframework.context.annotation.*; import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration public class BootstrapConfig{
 @Bean CommandLineRunner admin(UserRepository users,PasswordEncoder encoder,@Value("${app.bootstrap-admin.username}") String name,@Value("${app.bootstrap-admin.password}") String pass){return args->{if(!users.existsByUsernameIgnoreCase(name))users.save(new AppUser(name,encoder.encode(pass),cl.javier.starwars.domain.Role.ADMIN));};}
}
