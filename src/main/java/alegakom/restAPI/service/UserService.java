package alegakom.restAPI.service;

import alegakom.restAPI.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers ();


    User findByUsername(String username);


    void saveUser(User user);


    void removeUser(long id);

    void updateUser(User user);


    User getUserById(long id);


}
