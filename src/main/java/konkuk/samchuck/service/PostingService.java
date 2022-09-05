package konkuk.samchuck.service;

import konkuk.samchuck.domain.Posting;
import konkuk.samchuck.domain.Reply;
import konkuk.samchuck.dto.ResponseReplyDTO;
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

    @Transactional
    public void createPost(Posting posting) {
        posting.setCreateDate(LocalDateTime.now());
        postingRepository.save(posting);
    }

    public List<Posting> getPostings(int pageNum) {
        return postingRepository.findAllBySize(pageNum);
    }

    public Posting getPosting(Long id) {
        return postingRepository.findPostingById(id)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 게시글 아이디!!"));
    }

    public void dfs(List<ResponseReplyDTO> replies, Reply reply) {
        ResponseReplyDTO replyDTO = ResponseReplyDTO.builder()
                .author(reply.getAuthor().getUserId())
                .content(reply.getContent())
                .createDate(reply.getCreateDate())
                .modifiedDate(reply.getModifiedDate())

                .build();
        replies.add(replyDTO);

        for (Reply reReply : reply.getReReplies()) {
            dfs(replyDTO.getReReplies(), reReply);
        }
    }


}
