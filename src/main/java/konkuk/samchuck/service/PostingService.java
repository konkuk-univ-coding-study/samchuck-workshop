package konkuk.samchuck.service;

import konkuk.samchuck.domain.Posting;
import konkuk.samchuck.repository.PostingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class PostingService {

    private final PostingRepository postingRepository;

    public PostingService(PostingRepository postingRepository) {
        this.postingRepository = postingRepository;
    }

    public void createPost(Posting posting) {
        posting.setCreateDate(LocalDateTime.now());
        postingRepository.save(posting);
    }

    public List<Posting> getPostings(int pageNum) {
        return postingRepository.findAllBySize(pageNum);
    }
}
