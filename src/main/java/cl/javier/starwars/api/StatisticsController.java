package cl.javier.starwars.api;
import cl.javier.starwars.repository.QueryLogRepository; import org.springframework.security.access.prepost.PreAuthorize; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api/admin/statistics") @PreAuthorize("hasRole('ADMIN')") public class StatisticsController{
 private final QueryLogRepository logs; public StatisticsController(QueryLogRepository logs){this.logs=logs;}
 @GetMapping public Map<String,Object> stats(){return Map.of("totalQueries",logs.count(),"byResource",rows(logs.countByResourceType()),"byUser",rows(logs.countByUsername()),"topEndpoints",rows(logs.countByEndpoint()));}
 private List<Map<String,Object>> rows(List<Object[]> rows){return rows.stream().limit(10).map(x->Map.<String,Object>of("value",x[0],"count",x[1])).toList();}
}
