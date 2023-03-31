package dao;

import jakarta.persistence.EntityManagerFactory;
import model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
    private final EntityManagerFactory em;

    public UserDaoImp(EntityManagerFactory em) {
        this.em = em;
    }

    @Override
    public void add(User user) {
        em.createEntityManager().persist(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> index() {
        return em.createEntityManager().createQuery("from User").getResultList();
    }

    @Override
    public User show(int id) {
        return em.createEntityManager().find(User.class, id);
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
        em.createEntityManager().remove(show(id));
    }
}
