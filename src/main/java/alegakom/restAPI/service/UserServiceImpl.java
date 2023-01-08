package alegakom.restAPI.service;

import alegakom.restAPI.dao.UserDao;
import alegakom.restAPI.model.Role;
import alegakom.restAPI.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
   private final UserDao userDao;



    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public void removeUser(long id) {
        userDao.removeUser(id);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(long id) {
        return userDao.getUserById(id);
    }

    @Override
    @Transactional
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public String encode(CharSequence password) {
        return userDao.encode(password);
    }

    @Override
    public List<Role> getAllRoles() {
        return userDao.getAllRoles();
    }

    @Override
    public Role getRoleByName(String role) {
        return userDao.getRoleByName(role);
    }

    @Override
    public User getPrincipalUser() {
        return userDao.getPrincipalUser();
    }
}
