package konkuk.samchuck.repository;

import konkuk.samchuck.domain.Reply;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class ReplyRepository {

    private final EntityManager em;

    public ReplyRepository(EntityManager em) {
        this.em = em;
    }

    public void save(Reply reply) {
        em.persist(reply);
    }
}
