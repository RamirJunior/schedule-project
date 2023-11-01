package ramir.com.schedule.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ramir.com.schedule.api.dto.UserRequest;
import ramir.com.schedule.api.dto.UserResponse;
import ramir.com.schedule.api.mapper.UserMapper;
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
    private final UserMapper mapper;

    @PostMapping
    public ResponseEntity<UserResponse> save(@Valid @RequestBody UserRequest userRequest) {
        User user = mapper.toUser(userRequest);
        Optional<User> savedUser = userService.saveUser(user);
        if (savedUser.isEmpty())
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        var userResponse = mapper.toUserResponse(savedUser.get());
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAllUsers() {
        var userList = userService.getUsers();
        var userResponseList = mapper.toUserResponseList(userList);
        return ResponseEntity.status(HttpStatus.OK).body(userResponseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findUser(@PathVariable(name = "id") UUID id) {
        Optional<User> user = userService.getUser(id);
        if (user.isEmpty())
            return ResponseEntity.notFound().build();

        var userResponse = mapper.toUserResponse(user.get());
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable(value = "id") UUID id,
            @RequestBody UserRequest userRequest
    ) {
        User user = mapper.toUser(userRequest);
        Optional<User> userUpdated = userService.updateUser(user, id);
        if (userUpdated.isEmpty())
            return ResponseEntity.notFound().build();

        var userResponse = mapper.toUserResponse(userUpdated.get());
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable(name = "id") UUID id) {
        Optional<User> deletedUser = userService.deleteUser(id);
        if (deletedUser.isEmpty())
            return ResponseEntity.notFound().build();

        var userResponse = mapper.toUserResponse(deletedUser.get());
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }
}
