package konkuk.samchuck.controller;

import konkuk.samchuck.domain.Posting;
import konkuk.samchuck.domain.User;
import konkuk.samchuck.dto.PostingDTO;
import konkuk.samchuck.dto.ResponseDTO;
import konkuk.samchuck.service.PostingService;
import konkuk.samchuck.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/board")
public class PostingController {

    private final UserService userService;
    private final PostingService postingService;

    public PostingController(UserService userService, PostingService postingService) {
        this.userService = userService;
        this.postingService = postingService;
    }

    public ResponseEntity<ResponseDTO> saveBoard(HttpServletRequest request, @RequestBody PostingDTO postingDTO) {
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
}
