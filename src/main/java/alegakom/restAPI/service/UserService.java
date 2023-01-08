package alegakom.restAPI.service;

import alegakom.restAPI.dao.UserDao;
import alegakom.restAPI.model.Role;
import alegakom.restAPI.model.User;

import java.util.List;

public interface UserService extends UserDao {
    List<User> getAllUsers ();

    @Override
    User findByUsername(String username);

    @Override
    void saveUser(User user);

    @Override
    void removeUser(long id);

    @Override
    void updateUser(User user);

    @Override
    User getUserById(long id);

    @Override
    String encode(CharSequence password);

    @Override
    List<Role> getAllRoles();
    @Override
    Role getRoleByName(String role);

    @Override
    User getPrincipalUser();
}
