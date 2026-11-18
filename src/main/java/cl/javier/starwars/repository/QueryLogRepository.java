package cl.javier.starwars.repository;
import cl.javier.starwars.domain.QueryLog; import org.springframework.data.jpa.repository.*; import java.util.List;
public interface QueryLogRepository extends JpaRepository<QueryLog,Long>{
 @Query("select q.resourceType, count(q) from QueryLog q group by q.resourceType order by count(q) desc") List<Object[]> countByResourceType();
 @Query("select q.username, count(q) from QueryLog q group by q.username order by count(q) desc") List<Object[]> countByUsername();
 @Query("select q.endpoint, count(q) from QueryLog q group by q.endpoint order by count(q) desc") List<Object[]> countByEndpoint();
}
