package alegakom.restAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String role;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    @Transient
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "roles_id"),
            inverseJoinColumns = @JoinColumn(name = "users_id")
    )
    private List<User> users = new ArrayList<>();


    public Role(String role) {
        this.role = role;
    }

    public Role() {

    }

    public void addRoleToUser (User user) {
        if (users == null) {
            users = new ArrayList<>();
            users.add(user);
        }
    }


    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", users=" + users +
                '}';
    }
}
