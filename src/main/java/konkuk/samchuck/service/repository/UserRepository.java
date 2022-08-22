package konkuk.samchuck.repository;

import konkuk.samchuck.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserRepository {

    private final EntityManager em;

    public UserRepository(EntityManager em) {
        this.em = em;
    }

    public boolean isValid(String id) {
        List<User> result = em.createQuery("select user from User user where user.id = :id", User.class)
                .setParameter("id", id)
                .getResultList();
        return result.size() == 0;
    }

}
