package alegakom.restAPI.dao;

import alegakom.restAPI.model.Role;

import java.util.List;

public interface RoleDao {
    Role getRoleByName(String role);
    List<Role> getAllRoles();
}
