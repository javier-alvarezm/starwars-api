package cl.javier.starwars.api;
import jakarta.validation.constraints.*;
public final class AuthDtos { private AuthDtos(){}
 public record RegisterRequest(@NotBlank @Size(min=3,max=80) String username,@NotBlank @Size(min=8,max=100) String password){}
 public record LoginRequest(@NotBlank String username,@NotBlank String password){}
 public record TokenResponse(String token,String type,long expiresIn,String role){}
}
