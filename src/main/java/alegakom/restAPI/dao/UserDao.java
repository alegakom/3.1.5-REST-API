package alegakom.restAPI.dao;

import alegakom.restAPI.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers ();

    void saveUser(User user);
    void removeUser(long id);
    void updateUser(User user);
    User getUserById(long id);
    User findByUsername (String username);
}
