package konkuk.samchuck.repository;

import konkuk.samchuck.domain.Posting;
import konkuk.samchuck.domain.Reply;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ReplyRepository {

    private final EntityManager em;

    public ReplyRepository(EntityManager em) {
        this.em = em;
    }

    public List<Reply> findReplyByBoard(Posting posting) {
        return em.createQuery("select reply from Reply reply where reply.posting = :board", Reply.class)
                .setParameter("board", posting)
                .getResultList();
    }

    public void save(Reply reply) {
        em.persist(reply);
    }
}
