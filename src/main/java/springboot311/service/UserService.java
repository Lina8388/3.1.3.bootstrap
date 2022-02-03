package springboot311.service;

import springboot311.model.Role;
import springboot311.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    List<User> getListUsers();

    void add(User user);

    User update(User updateUser, String[] roleNames);

    void removeUser(Long id);

    User getUser(Long id);

    User getUserByName(String name);

    Set<Role> getListRoles();

}
