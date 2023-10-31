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

    public Optional<User> saveUser(User user) {
        if (isRegisteredEmail(user.getEmail()))
            return Optional.empty();

        return Optional.of(userRepository.save(user));
    }

    private boolean isRegisteredEmail(String email) {
        var emailFound = userRepository.findByEmail(email);
        return emailFound.isPresent();
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(UUID id) {
        return userRepository.findById(id);
    }

    public Optional<User> updateUser(User userReceived, UUID id) {
        Optional<User> userFound = userRepository.findById(id);
        if (userFound.isEmpty())
            return userFound;

        copyAttributes(userReceived, userFound.get());
        var userUpdated = userRepository.save(userFound.get());
        return Optional.of(userUpdated);
    }

    private static void copyAttributes(User userSource, User userDestination) {
        userDestination.setName(userSource.getName());
        userDestination.setLastname(userSource.getLastname());
        userDestination.setPhone(userSource.getPhone());
        userDestination.setEmail(userSource.getEmail());
    }

    public Optional<User> deleteUser(UUID id) {
        Optional<User> userFound = userRepository.findById(id);
        if (userFound.isEmpty())
            return Optional.empty();

        userRepository.deleteById(id);
        return userFound;
    }
}
