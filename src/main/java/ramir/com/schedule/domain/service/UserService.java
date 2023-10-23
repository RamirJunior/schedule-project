package ramir.com.schedule.domain.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ramir.com.schedule.domain.entity.User;
import ramir.com.schedule.domain.entity.UserDto;
import ramir.com.schedule.domain.repository.UserRepository;

import javax.swing.text.html.Option;
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
        if(!isRegisteredEmail(user.getEmail())){
            return Optional.of(userRepository.save(user));
        }
    return Optional.empty();
    }

    private boolean isRegisteredEmail(String email){
        var emailFound = userRepository.findByEmail(email);
        return emailFound.isPresent();
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(UUID id) {
        return userRepository.findById(id);
    }

    public UserDto updateUser(User user, UUID id) {
        Optional<User> userFound = userRepository.findById(id);
        if (userFound.isPresent()) {
            userFound.get().setName(user.getName());
            userFound.get().setLastname(user.getLastname());
            userFound.get().setPhone(user.getPhone());
            userFound.get().setEmail(user.getEmail());

            userRepository.save(userFound.get());
        }
        return modelMapper.map(userFound, UserDto.class);
    }

    public void delete(UUID id) {
        userRepository.deleteById(id);
    }

}
