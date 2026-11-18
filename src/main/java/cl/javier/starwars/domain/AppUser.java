package cl.javier.starwars.domain;

import jakarta.persistence.*;

@Entity @Table(name="app_users")
public class AppUser {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false, unique=true, length=80) private String username;
    @Column(nullable=false) private String password;
    @Enumerated(EnumType.STRING) @Column(nullable=false) private Role role = Role.USER;
    protected AppUser() {}
    public AppUser(String username, String password, Role role) { this.username=username; this.password=password; this.role=role; }
    public Long getId(){return id;} public String getUsername(){return username;} public String getPassword(){return password;} public Role getRole(){return role;}
}
