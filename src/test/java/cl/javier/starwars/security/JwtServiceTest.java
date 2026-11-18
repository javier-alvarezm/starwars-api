package cl.javier.starwars.security;
import cl.javier.starwars.domain.*; import org.junit.jupiter.api.Test; import static org.assertj.core.api.Assertions.assertThat;
class JwtServiceTest{
 @Test void createsAndValidatesToken(){var service=new JwtService("0123456789012345678901234567890123456789",3600);var user=new AppUser("leia","hash",Role.ADMIN);String token=service.create(user);assertThat(service.username(token)).isEqualTo("leia");assertThat(service.valid(token,"leia")).isTrue();}
}
