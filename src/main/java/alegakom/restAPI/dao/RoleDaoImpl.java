package alegakom.restAPI.dao;

import alegakom.restAPI.model.Role;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Role> getAllRoles() {
        String sql = "select r from Role r";
        return entityManager.createQuery(sql, Role.class).getResultList();
    }
    @Override
    public Role getRoleByName(String role) {
        try {
            TypedQuery<Role> query = entityManager.createQuery("SELECT r FROM Role r WHERE r.role = :role", Role.class);
            return query.setParameter("role", role)
                    .getSingleResult();
        } catch (Exception e) {
            throw new UsernameNotFoundException(String.format("Role '%s' not found", role));
        }
    }
}
