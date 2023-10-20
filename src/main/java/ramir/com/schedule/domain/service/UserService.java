package ramir.com.schedule.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ramir.com.schedule.domain.entity.User;
import ramir.com.schedule.exception.BusinessException;
import ramir.com.schedule.domain.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User saveUser(User user) {

        //TODO: Duplicated emails sould not be saved

//        Optional<User> userFounded = userRepository.findByEmail(user.getEmail());
//        if(userFounded.isPresent()){
//            throw new BusinessException("Email already registered.");
//        }
        return userRepository.save(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(UUID id) {
        return userRepository.findById(id);
    }

    public Optional<User> updateUser(User user, UUID id){
        Optional<User> userFound = userRepository.findById(id);
        if(userFound.isPresent()){
            BeanUtils.copyProperties(user, userFound);
            userRepository.save(userFound.get());
        }
        return userFound;
    }

    public void delete(UUID id) {
        userRepository.deleteById(id);
    }

}
