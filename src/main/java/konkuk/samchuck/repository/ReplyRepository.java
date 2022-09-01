package konkuk.samchuck.repository;

import konkuk.samchuck.domain.Board;
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

    public List<Reply> findReplyByBoard(Board board) {
        return em.createQuery("select reply from Reply reply where reply.board = :board", Reply.class)
                .setParameter("board", board)
                .getResultList();
    }

    public void save(Reply reply) {
        em.persist(reply);
    }
}
