package ramir.com.schedule.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ramir.com.schedule.domain.entity.User;
import ramir.com.schedule.domain.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @GetMapping
    public ResponseEntity<List<User>> findAllUsers() {
        List<User> userList = userService.getUsers();
        return ResponseEntity.status(HttpStatus.OK).body(userList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable(value = "id") UUID id,
                                             @RequestBody User user) {
        Optional<User> userOptional = userService.updateUser(user, id);
        if (userOptional.isPresent()) {
            var userUpdated = userOptional.get();
            userService.saveUser(userUpdated);
            return ResponseEntity.status(HttpStatus.OK).body(userUpdated);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
    }

}
