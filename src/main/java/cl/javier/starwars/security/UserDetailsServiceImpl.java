package cl.javier.starwars.security;
import cl.javier.starwars.repository.UserRepository; import org.springframework.security.core.userdetails.*; import org.springframework.stereotype.Service;
@Service public class UserDetailsServiceImpl implements UserDetailsService{
 private final UserRepository users; public UserDetailsServiceImpl(UserRepository users){this.users=users;}
 public UserDetails loadUserByUsername(String username){var u=users.findByUsernameIgnoreCase(username).orElseThrow(()->new UsernameNotFoundException(username));return User.withUsername(u.getUsername()).password(u.getPassword()).roles(u.getRole().name()).build();}
}
