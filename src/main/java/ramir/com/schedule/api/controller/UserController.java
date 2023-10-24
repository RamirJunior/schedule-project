package ramir.com.schedule.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ramir.com.schedule.domain.entity.User;
import ramir.com.schedule.domain.entity.UserDto;
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
    public ResponseEntity<Object> save(@RequestBody User user) {
        Optional<User> savedUser = userService.saveUser(user);
        if(savedUser.isEmpty())
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already registered.");

        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> findAllUsers() {
        List<UserDto> userList = userService.getUsers();
        return ResponseEntity.status(HttpStatus.OK).body(userList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findUser(@PathVariable(name = "id") UUID id){
        Optional<UserDto> user = userService.getUser(id);
        if(user.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(user);

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(
            @PathVariable(value = "id") UUID id,
            @RequestBody User user)
    {
        var updatedUser = userService.updateUser(user, id);
        if (updatedUser != null) {
            return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(name = "id") UUID id){
        Optional<UserDto> deletedUser = userService.delete(id);
        if (deletedUser.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.status(HttpStatus.OK).body(deletedUser.get());
    }

    // TODO: Create UserResponse class and UserRequest class

}
