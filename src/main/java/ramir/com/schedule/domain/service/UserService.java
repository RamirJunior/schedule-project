package ramir.com.schedule.domain.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ramir.com.schedule.domain.entity.User;
import ramir.com.schedule.domain.entity.UserDto;
import ramir.com.schedule.domain.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        List<User> userList = userRepository.findAll();
        List<UserDto> dtoList = new ArrayList<UserDto>();

        userList.forEach(user -> {
            dtoList.add(convertToUserDTO(user));
        });
        return dtoList;
    }

    public Optional<UserDto> getUser(UUID id) {
        var userFound = userRepository.findById(id);
        return Optional.ofNullable(convertToUserDTO(userFound.get()));
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

    public Optional<UserDto> delete(UUID id) {
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
