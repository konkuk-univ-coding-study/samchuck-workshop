package konkuk.samchuck.repository;

import konkuk.samchuck.domain.Posting;
import konkuk.samchuck.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class PostingRepository {

    private final EntityManager em;

    public PostingRepository(EntityManager em) {
        this.em = em;
    }

    public List<Posting> findPostingByAuthor(User author) {
        return em.createQuery("select posting from Posting posting where posting.author = :author", Posting.class)
                .setParameter("author", author)
                .getResultList();
    }

    public Optional<Posting> findPostingById(Long id) {
        return Optional.ofNullable(em.find(Posting.class, id));
    }

    public List<Posting> findAllBySize(int start) {
        return em.createQuery("select board from Posting board order by board.id desc", Posting.class)
                .setFirstResult(start)
                .setMaxResults(20)
                .getResultList();
    }

    public void save(Posting posting) {
        em.persist(posting);
    }
}
