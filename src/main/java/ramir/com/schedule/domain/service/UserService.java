package ramir.com.schedule.domain.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ramir.com.schedule.api.dto.UserDto;
import ramir.com.schedule.domain.entity.User;
import ramir.com.schedule.domain.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    public Optional<User> saveUser(User user) {
        if (!isRegisteredEmail(user.getEmail()))
            return Optional.of(userRepository.save(user));

        return Optional.empty();
    }

    private boolean isRegisteredEmail(String email) {
        var emailFound = userRepository.findByEmail(email);
        return emailFound.isPresent();
    }

    public List<UserDto> getUsers() {
        var users = userRepository.findAll();

        return users.stream()
                .map(this::convertToUserDTO)
                .collect(Collectors.toList());
    }

    public Optional<UserDto> getUser(UUID id) {
        var userFound = userRepository.findById(id);
        return userFound.map(this::convertToUserDTO);
    }

    public UserDto updateUser(User userReceived, UUID id) {
        Optional<User> userFound = userRepository.findById(id);
        if (userFound.isPresent()) {
            userFound.get().setName(userReceived.getName());
            userFound.get().setLastname(userReceived.getLastname());
            userFound.get().setPhone(userReceived.getPhone());
            userFound.get().setEmail(userReceived.getEmail());

            userRepository.save(userFound.get());
        }
        return convertToUserDTO(userFound.get());
    }

    public Optional<UserDto> deleteUser(UUID id) {
        var userFound = userRepository.findById(id);
        if (userFound.isPresent()) {
            userRepository.deleteById(id);
            return Optional.ofNullable(
                    convertToUserDTO(userFound.get())
            );
        }
        return Optional.empty();
    }

    private UserDto convertToUserDTO(User user) {
        return modelMapper.map(user, UserDto.class);
    }
}
