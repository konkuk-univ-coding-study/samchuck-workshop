package konkuk.samchuck.controller;

import konkuk.samchuck.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController("/api")
public class SignupController {

    private final UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup/validate/duplicate")
    public String validateDuplicate(@RequestBody Map<String, String> userId) {

    }
}
