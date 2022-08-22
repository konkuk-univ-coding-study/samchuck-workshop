package konkuk.samchuck.controller;

import konkuk.samchuck.response.Response;
import konkuk.samchuck.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class SignupController {

    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(getClass());


    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/signup/validate/duplicate")
    public Response validateDuplicate(Map<String, String> userId) {

        logger.info("enter point: " + userId.get("id"));
        if (userService.isDuplicate(userId.get("id"))) {
            return new Response("409", "conflict user id");
        }
        return new Response("200", "ok");
    }
}
