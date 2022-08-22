package konkuk.samchuck.service;

import konkuk.samchuck.repository.UserRepository;
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
    public boolean isDuplicate(String id) {
        return userRepository.findByUserid(id)
                .isPresent();
    }
}
