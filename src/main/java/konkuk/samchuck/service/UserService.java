package konkuk.samchuck.service;

import konkuk.samchuck.domain.User;
import konkuk.samchuck.exceptions.IllegalPasswordFormatException;
import konkuk.samchuck.exceptions.IllegalUseridFormatException;
import konkuk.samchuck.exceptions.IllegalUseridConflictException;
import konkuk.samchuck.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.regex.Pattern;

@Service
@Transactional
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void checkDuplicate(String id) {
        userRepository.findByUserid(id)
                .ifPresent(user -> {
                    throw new IllegalUseridConflictException("입력된 사용자 아이디가 중복됩니다.");
                });
    }

    public void checkValidUseridForm(String id) {
        String pattern = "^[a-zA-Z0-9!@#$%^&*(){}<>?]{5,20}$";
        if (!Pattern.matches(pattern, id)) {
            throw new IllegalUseridFormatException("입력된 사용자 아이디가 형식에 맞지 않습니다.");
        }
    }

    @Transactional
    public void signup(User user) {
        checkDuplicate(user.getUserId());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        userRepository.save(user);
    }

    public void checkValidPasswordForm(String password) {
        String pattern = "^[a-zA-Z0-9!@#$%^&*(){}<>?]{8,20}$";
        if (!Pattern.matches(pattern, password)) {
            throw new IllegalPasswordFormatException("입력된 비밀번호가 형식에 맞지 않습니다.");
        }
    }

    public User findUser(String userid) {
        return userRepository.findByUserid(userid)
                .orElseThrow(() -> new UsernameNotFoundException(userid + " -> 존재하지 않는 유저!!"));
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
        return userRepository.findByUserid(userid)
                .map(user -> createUser(userid, user))
                .orElseThrow(() -> new UsernameNotFoundException(userid + " -> 존재하지 않는 유저!!"));
    }

    private org.springframework.security.core.userdetails.User createUser(String userid, User user) {
        if (!user.isActive()) {
            throw new RuntimeException(userid + " -> 활성화되지 않은 유저!!");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUserId(),
                user.getPassword(),
                new ArrayList<>());
    }
}
