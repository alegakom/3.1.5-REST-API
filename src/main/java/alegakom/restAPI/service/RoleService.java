package alegakom.restAPI.service;

import alegakom.restAPI.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> getAllRoles();


    Role getRoleByName(String role);
}
