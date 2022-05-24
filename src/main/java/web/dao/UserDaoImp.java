package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void deleteUser(User user) {
        entityManager.remove(entityManager.find(User.class, user.getId()));
    }

    @Override
    public void updateUser(int id, User user) {
        Query query = entityManager.
                createNativeQuery("UPDATE users SET name = :name , surname = :surname, email = :email , salary = :salary WHERE id = :id");
        query.setParameter("name", user.getName());
        query.setParameter("surname", user.getSurname());
        query.setParameter("email", user.getEmail());
        query.setParameter("id", user.getId());
        query.setParameter("salary", user.getSalary());
        query.executeUpdate();
    }

    @Override
    public List<User> getAllUsers() {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u", User.class);
        return query.getResultList();
    }

    @Override
    public User getUserById(long id) {
        TypedQuery<User> query = entityManager.createQuery("SELECT user FROM User user WHERE user.id = :id", User.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }
}
