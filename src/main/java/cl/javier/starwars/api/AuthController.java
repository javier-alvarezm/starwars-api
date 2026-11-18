package cl.javier.starwars.api;
import cl.javier.starwars.domain.*; import cl.javier.starwars.repository.UserRepository; import cl.javier.starwars.security.JwtService; import jakarta.validation.Valid; import org.springframework.http.*; import org.springframework.security.authentication.*; import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/auth") public class AuthController{
 private final UserRepository users; private final PasswordEncoder encoder; private final AuthenticationManager auth; private final JwtService jwt;
 public AuthController(UserRepository users,PasswordEncoder encoder,AuthenticationManager auth,JwtService jwt){this.users=users;this.encoder=encoder;this.auth=auth;this.jwt=jwt;}
 @PostMapping("/register") @ResponseStatus(HttpStatus.CREATED) public AuthDtos.TokenResponse register(@Valid @RequestBody AuthDtos.RegisterRequest r){if(users.existsByUsernameIgnoreCase(r.username()))throw new IllegalArgumentException("El usuario ya existe");var u=users.save(new AppUser(r.username(),encoder.encode(r.password()),Role.USER));return token(u);}
 @PostMapping("/login") public AuthDtos.TokenResponse login(@Valid @RequestBody AuthDtos.LoginRequest r){auth.authenticate(new UsernamePasswordAuthenticationToken(r.username(),r.password()));return token(users.findByUsernameIgnoreCase(r.username()).orElseThrow());}
 private AuthDtos.TokenResponse token(AppUser u){return new AuthDtos.TokenResponse(jwt.create(u),"Bearer",jwt.expiration(),u.getRole().name());}
}
