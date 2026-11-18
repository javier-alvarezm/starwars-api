package cl.javier.starwars.api;
import cl.javier.starwars.service.SyncService; import org.springframework.security.access.prepost.PreAuthorize; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/admin") @PreAuthorize("hasRole('ADMIN')") public class AdminController{private final SyncService sync;public AdminController(SyncService sync){this.sync=sync;}@PostMapping("/sync") public SyncService.SyncResult sync(){return sync.sync();}}
