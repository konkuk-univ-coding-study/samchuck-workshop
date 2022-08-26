package konkuk.samchuck.controller;

import konkuk.samchuck.domain.User;
import konkuk.samchuck.dto.ResponseDTO;
import konkuk.samchuck.dto.UserDTO;
import konkuk.samchuck.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseEntity<ResponseDTO> validateDuplicate(Map<String, String> userId) {
        try {
            userService.checkDuplicate(userId.get("id"));
            return new ResponseEntity<>(new ResponseDTO("success", "available user id"), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseDTO("fail", "conflict user id"), HttpStatus.CONFLICT);
        }
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> signup(@RequestBody UserDTO userDTO) {
        User newUser = new User(userDTO.getUserid(), userDTO.getPassword());
        try {
            userService.signup(newUser);
            return new ResponseEntity<>(new ResponseDTO("success", "signup ok"), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseDTO("fail", "conflict user id"), HttpStatus.CONFLICT);
        }
    }
}
