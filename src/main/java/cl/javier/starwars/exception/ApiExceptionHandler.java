package cl.javier.starwars.exception;
import jakarta.persistence.EntityNotFoundException; import org.slf4j.*; import org.springframework.http.*; import org.springframework.security.authentication.BadCredentialsException; import org.springframework.web.bind.MethodArgumentNotValidException; import org.springframework.web.bind.annotation.*; import org.springframework.web.context.request.WebRequest; import java.time.Instant; import java.util.*;
@RestControllerAdvice public class ApiExceptionHandler{
 private static final Logger log=LoggerFactory.getLogger(ApiExceptionHandler.class);
 @ExceptionHandler(EntityNotFoundException.class) ResponseEntity<ErrorResponse> notFound(Exception e,WebRequest r){return response(HttpStatus.NOT_FOUND,e.getMessage(),r);}
 @ExceptionHandler({IllegalArgumentException.class,MethodArgumentNotValidException.class}) ResponseEntity<ErrorResponse> badRequest(Exception e,WebRequest r){String m=e instanceof MethodArgumentNotValidException v?v.getBindingResult().getFieldErrors().stream().map(x->x.getField()+": "+x.getDefaultMessage()).findFirst().orElse("Solicitud inválida"):e.getMessage();return response(HttpStatus.BAD_REQUEST,m,r);}
 @ExceptionHandler(BadCredentialsException.class) ResponseEntity<ErrorResponse> unauthorized(Exception e,WebRequest r){return response(HttpStatus.UNAUTHORIZED,"Credenciales inválidas",r);}
 @ExceptionHandler(Exception.class) ResponseEntity<ErrorResponse> internal(Exception e,WebRequest r){log.error("Error no controlado en {}",r.getDescription(false),e);return response(HttpStatus.INTERNAL_SERVER_ERROR,"Error interno",r);}
 private ResponseEntity<ErrorResponse> response(HttpStatus s,String m,WebRequest r){return ResponseEntity.status(s).body(new ErrorResponse(Instant.now(),s.value(),s.getReasonPhrase(),m,r.getDescription(false).replace("uri=","")));}
 public record ErrorResponse(Instant timestamp,int status,String error,String message,String path){}
}
