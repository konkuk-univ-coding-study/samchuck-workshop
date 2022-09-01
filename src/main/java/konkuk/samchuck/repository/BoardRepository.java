package konkuk.samchuck.repository;

import konkuk.samchuck.domain.Board;
import konkuk.samchuck.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class BoardRepository {

    private final EntityManager em;

    public BoardRepository(EntityManager em) {
        this.em = em;
    }

    public List<Board> findBoardByAuthor(User author) {
        return em.createQuery("select board from Board board where board.author = :author", Board.class)
                .setParameter("author", author)
                .getResultList();
    }

    public List<Board> findAllBySize(int start) {
        return em.createQuery("select board from Board board order by board.id desc", Board.class)
                .setFirstResult(start)
                .setMaxResults(20)
                .getResultList();
    }

    public void save(Board board) {
        em.persist(board);
    }
}
