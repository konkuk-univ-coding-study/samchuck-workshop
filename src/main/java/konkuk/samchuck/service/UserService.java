package konkuk.samchuck.service;

import konkuk.samchuck.domain.User;
import konkuk.samchuck.repository.UserRepository;
import konkuk.samchuck.util.SHA256Util;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void checkDuplicate(String id) {
        userRepository.findByUserid(id)
                .ifPresent(user -> {
                    throw new IllegalArgumentException();
                });
    }

    @Transactional
    public void signup(User user) {
        String original = user.getPassword();
        String salt = SHA256Util.generateSalt();
        String encoded = SHA256Util.getEncrypt(original, salt);
        user.setPassword(encoded);
        userRepository.save(user);
    }
}
