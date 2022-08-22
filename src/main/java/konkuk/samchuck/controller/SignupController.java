package konkuk.samchuck.controller;

import konkuk.samchuck.domain.User;
import konkuk.samchuck.response.Response;
import konkuk.samchuck.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/signup")
public class SignupController {

    private final UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/validate/duplicate")
    public Response validateDuplicate(Map<String, String> userId) {
        try {
            userService.checkDuplicate(userId.get("id"));
            return new Response("200", "ok");
        } catch (IllegalArgumentException e) {
            return new Response("409", "conflict user id");
        }
    }

    @PostMapping("")
    public Response signup(Map<String, String> signupForm) {
        User newUser = new User(signupForm.get("id"), signupForm.get("password"));
        try {
            userService.signup(newUser);
            return new Response("200", "ok");
        } catch (Exception e) {
            return new Response("409", "invalid password");
        }
    }
}
