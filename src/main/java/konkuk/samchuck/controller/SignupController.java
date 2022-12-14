package konkuk.samchuck.controller;

import konkuk.samchuck.domain.User;
import konkuk.samchuck.dto.ResponseDTO;
import konkuk.samchuck.dto.UserDTO;
import konkuk.samchuck.exceptions.IllegalPasswordFormatException;
import konkuk.samchuck.exceptions.IllegalUseridConflictException;
import konkuk.samchuck.exceptions.IllegalUseridFormatException;
import konkuk.samchuck.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/signup")
public class SignupController {

    private final UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/validate/duplicate")
    public ResponseEntity<ResponseDTO> validateDuplicate(@RequestParam("userid") String userid) {
        try {
            userService.checkValidUseridForm(userid);
            userService.checkDuplicate(userid);
            return new ResponseEntity<>(
                    new ResponseDTO("success", "available user id"), HttpStatus.OK);
        } catch (IllegalUseridFormatException e) {
            return new ResponseEntity<>(
                    new ResponseDTO("fail", "not supported format"), HttpStatus.BAD_REQUEST);
        } catch (IllegalUseridConflictException e) {
            return new ResponseEntity<>(
                    new ResponseDTO("fail", "conflict user id"), HttpStatus.CONFLICT);
        }
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> signup(@RequestBody UserDTO userDTO) {
        User newUser = new User(userDTO.getUserid(), userDTO.getPassword());
        try {
            userService.checkValidUseridForm(userDTO.getUserid());
            userService.checkValidPasswordForm(userDTO.getPassword());
            userService.signup(newUser);
            return new ResponseEntity<>(
                    new ResponseDTO("success", "signup ok"), HttpStatus.OK);
        } catch (IllegalUseridFormatException e) {
            return new ResponseEntity<>(
                    new ResponseDTO("fail", "not supported id format"), HttpStatus.BAD_REQUEST);
        } catch (IllegalPasswordFormatException e) {
            return new ResponseEntity<>(
                    new ResponseDTO("fail", "not supported pw format"), HttpStatus.BAD_REQUEST);
        } catch (IllegalUseridConflictException e) {
            return new ResponseEntity<>(
                    new ResponseDTO("fail", "conflict user id"), HttpStatus.CONFLICT);
        }
    }

    //test push
}
