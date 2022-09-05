package konkuk.samchuck.controller;

import konkuk.samchuck.domain.Posting;
import konkuk.samchuck.domain.Reply;
import konkuk.samchuck.domain.User;
import konkuk.samchuck.dto.*;
import konkuk.samchuck.service.PostingService;
import konkuk.samchuck.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/posting")
public class PostingController {

    private final UserService userService;
    private final PostingService postingService;

    public PostingController(UserService userService, PostingService postingService) {
        this.userService = userService;
        this.postingService = postingService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> savePosting(HttpServletRequest request, @RequestBody PostingDTO postingDTO) {
        Posting posting = new Posting();
        String username = (String) request.getAttribute("username");
        User curUser = userService.findUser(username);

        posting.setAuthor(curUser);
        posting.setTitle(postingDTO.getTitle());
        posting.setContent(postingDTO.getContent());
        postingService.createPost(posting);
        return new ResponseEntity<>(
                new ResponseDTO("success", "created new posting"), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ResponseAllPostingsDTO> getAllPostings(@RequestParam("page") int page) {
        ResponseAllPostingsDTO responseAllPostingsDTO =
                new ResponseAllPostingsDTO("success", "loaded all postings");
        List<Posting> postings = postingService.getPostings(page);
        postings.forEach(posting -> responseAllPostingsDTO.getPostings().add(new PostingInfoDTO(posting)));
        return new ResponseEntity<>(responseAllPostingsDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsePostingDTO> getPosting(@PathVariable("id") Long id) {
        Posting posting = postingService.getPosting(id);
        List<ResponseReplyDTO> replies = new ArrayList<>();
        posting.getReplies()
                .forEach(reply -> postingService.dfs(replies, reply));

        ResponsePostingDTO responsePostingDTO = ResponsePostingDTO.builder()
                .author(posting.getAuthor().getUserId())
                .title(posting.getTitle())
                .content(posting.getContent())
                .createDate(posting.getCreateDate())
                .modifiedDate(posting.getModifiedDate())
                .replies(replies)
                .build();

        return new ResponseEntity<>(responsePostingDTO, HttpStatus.OK);
    }


}
