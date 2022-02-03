package springboot311.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import springboot311.model.Role;
import springboot311.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public User getUserByName(String name) {
        return entityManager
                .createQuery(" SELECT u FROM User u WHERE u.name =:name", User.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public User getUserByEmail(String email) {
        return entityManager
                .createQuery(" SELECT u FROM User u WHERE u.email =:email", User.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    @Override
    public User getUser(Long id) {
        return entityManager.find(User.class, id);
    }


    @Override
    public Set<Role> getListRoles() {
        List<Role> list = entityManager.createQuery("from Role", Role.class).getResultList();
        Set<Role> role = new HashSet<>(list);
        return role;
    }

    @Override
    public List<User> getListUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }


    @Override
    public Set<Role> getRolesByRoleNames(String[] roleNames) {

        Set<Role> roles = new HashSet<>();
        for (String roleName : roleNames) {
            roles.add((getRoleByName(roleName)));
        }
        return roles;
    }

    @Override
    public Role getRoleByName(String roleName) {
        return entityManager
                .createQuery(" SELECT u FROM Role u WHERE u.role =:role", Role.class)
                .setParameter("role", roleName)
                .getSingleResult();
    }

    @Override
    public void add(User user) {

        entityManager.persist(user);
    }


    @Override
    public User update(User user, String[] roleNames) {
        user.setRoles(getRolesByRoleNames(roleNames));
        return entityManager.merge(user);
    }


    @Override
    public void removeUser(Long id) {
        entityManager.remove(entityManager.find(User.class, id));
    }

}

