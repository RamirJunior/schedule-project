package ramir.com.schedule.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ramir.com.schedule.api.dto.UserResponse;
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
    public ResponseEntity<Object> save(@Valid @RequestBody User user) {
        Optional<User> savedUser = userService.saveUser(user);
        if (savedUser.isEmpty())
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already registered.");

        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAllUsers() {
        List<UserResponse> userList = userService.getUsers();
        return ResponseEntity.status(HttpStatus.OK).body(userList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUser(@PathVariable(name = "id") UUID id) {
        Optional<User> user = userService.getUser(id);
        if (user.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.status(HttpStatus.OK).body(user.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable(value = "id") UUID id,
            @RequestBody User user
    ) {
        var response = userService.updateUser(user, id);
        if (response.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(response.get());

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(name = "id") UUID id) {
        Optional<UserResponse> deletedUser = userService.deleteUser(id);
        if (deletedUser.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.status(HttpStatus.OK).body(deletedUser.get());
    }

}
