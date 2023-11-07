package ramir.com.schedule.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ramir.com.schedule.api.dto.UserAuthRequestDto;
import ramir.com.schedule.api.dto.UserAuthResponseDto;
import ramir.com.schedule.api.mapper.UserAuthMapper;
import ramir.com.schedule.domain.entity.UserAuth;
import ramir.com.schedule.domain.repository.UserAuthRepository;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserAuthRepository userAuthRepository;
    private final UserAuthMapper userAuthMapper;

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody UserAuthRequestDto requestAuth) {
        var loginData = new UsernamePasswordAuthenticationToken(requestAuth.getLogin(), requestAuth.getPassword());
        var auth = authenticationManager.authenticate(loginData);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/register")
    public ResponseEntity<UserAuthResponseDto> register(@Valid @RequestBody UserAuthRequestDto userAuthRequestDto) {
        var userFound = userAuthRepository.findByLogin(userAuthRequestDto.getLogin());
        if (userFound != null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        UserAuth userAuth = userAuthMapper.toUserAuth(userAuthRequestDto);
        var savedUserAuth = userAuthRepository.save(userAuth);
        var userAuthResponseDto = userAuthMapper.userAuthResponseDto(savedUserAuth);
        return ResponseEntity.status(HttpStatus.CREATED).body(userAuthResponseDto);
    }
}
