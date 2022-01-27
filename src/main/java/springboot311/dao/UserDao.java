package springboot311.dao;

import springboot311.model.Role;
import springboot311.model.User;
import java.util.List;
import java.util.Set;

public interface UserDao {

    User getUserByName(String name);

    User getUser(Long id);

    List<User> getListUsers();

    void add(User user);

    User update(Long id, User updateUser, String[] roleNames);

    User update(Long id, User updateUser);

    void removeUser(Long id);

    Set<Role> getListRoles();

    Set<Role> getRolesByRoleNames(String[] roleNames);

    Role getRoleByName(String roleName);
}

