package web.dao;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import web.model.User;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void add(User user) {
        em.persist(user);
    }

    @Override
    public List<User> index() {
        return em.createQuery("from User", User.class).getResultList();
    }

    @Override
    public User show(int id) {
        return em.find(User.class, id);
    }

    @Override
    public void update(int id, User updatedUser) {
        User userToBeUpdated = show(id);
        userToBeUpdated.setName(updatedUser.getName());
        userToBeUpdated.setAge(updatedUser.getAge());
        userToBeUpdated.setEmail(updatedUser.getEmail());
    }

    @Override
    public void remove(int id) {
        em.remove(show(id));
    }
}
