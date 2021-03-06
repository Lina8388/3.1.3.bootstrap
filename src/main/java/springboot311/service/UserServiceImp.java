package springboot311.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot311.dao.UserDao;
import springboot311.model.User;
import springboot311.model.Role;


import java.util.List;
import java.util.Set;

@Transactional
@Service
public class UserServiceImp implements UserService {


    private UserDao userDao;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImp(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    @Override
    public Set<Role> getListRoles() {
        return userDao.getListRoles();
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getListUsers() {
        return userDao.getListUsers();
    }


    @Override
    public void add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.add(user);
    }


    @Override
    public User update( User user, String[] roleNames) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userDao.update(user, roleNames);
    }

    @Override
    public void removeUser(Long id) {
        userDao.removeUser(id);
    }

    @Transactional(readOnly = true)
    @Override
    public User getUser(Long id) {
        return userDao.getUser(id);
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }
}
