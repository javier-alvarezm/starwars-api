package cl.javier.starwars.domain;

import jakarta.persistence.*;
import java.time.Instant;

@Entity @Table(name="query_logs", indexes={@Index(name="idx_log_resource",columnList="resourceType,resourceId"),@Index(name="idx_log_user",columnList="username")})
public class QueryLog {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private String username; @Column(nullable=false) private String endpoint; @Column(nullable=false) private String resourceType; private Long resourceId;
 @Column(length=1000) private String parameters; private int statusCode; @Column(nullable=false) private Instant createdAt;
 protected QueryLog(){} public QueryLog(String username,String endpoint,String resourceType,Long resourceId,String parameters,int statusCode){this.username=username;this.endpoint=endpoint;this.resourceType=resourceType;this.resourceId=resourceId;this.parameters=parameters;this.statusCode=statusCode;this.createdAt=Instant.now();}
 public Long getId(){return id;} public String getUsername(){return username;} public String getEndpoint(){return endpoint;} public String getResourceType(){return resourceType;} public Long getResourceId(){return resourceId;} public String getParameters(){return parameters;} public int getStatusCode(){return statusCode;} public Instant getCreatedAt(){return createdAt;}
}
