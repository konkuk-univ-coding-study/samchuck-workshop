package konkuk.samchuck.repository;

import konkuk.samchuck.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public class UserRepository {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final EntityManager em;

    public UserRepository(EntityManager em) {
        this.em = em;
    }

    public Optional<User> findByUserid(String userId) {
        logger.info("LOGGER: findByUserid, id: " + userId);
        return em.createQuery("select user from User user where user.userId = :userId", User.class)
                .setParameter("userId", userId)
                .getResultStream()
                .findAny();
    }

    public void save(User user) {
        em.persist(user);
    }

}
