package ramir.com.schedule.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ramir.com.schedule.domain.entity.User;
import ramir.com.schedule.domain.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User saveUser(User person) {

        // TODO: validate uuid existent on db

        return userRepository.save(person);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(UUID id) {
        return userRepository.findById(id);
    }

    public void delete(UUID id) {
        userRepository.deleteById(id);
    }

}
