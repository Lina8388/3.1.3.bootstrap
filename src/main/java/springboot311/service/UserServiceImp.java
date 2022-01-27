package springboot311.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot311.dao.UserDao;
import springboot311.model.User;
import springboot311.model.Role;


import java.util.List;
import java.util.Set;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;


   // @Transactional(readOnly = true)
    @Override
    public Set<Role> getListRoles() {
        return userDao.getListRoles();
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getListUsers() {
        return userDao.getListUsers();
    }

    @Transactional
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Transactional
    @Override
    public User update(Long id, User updateUser, String[] roleNames) {
        return userDao.update(id, updateUser, roleNames);
    }

    @Transactional
    @Override
    public User update(Long id, User updateUser) {
        return userDao.update(id, updateUser);
    }

    @Transactional
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
