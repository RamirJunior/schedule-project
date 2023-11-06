package ramir.com.schedule.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ramir.com.schedule.api.dto.UserAuthResponseDto;
import ramir.com.schedule.api.dto.UserAuthRequestDto;
import ramir.com.schedule.api.mapper.AuthMapper;
import ramir.com.schedule.domain.entity.UserAuth;
import ramir.com.schedule.domain.repository.UserAuthRepository;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    final private AuthenticationManager authenticationManager;
    final private UserAuthRepository userAuthRepository;
    final private AuthMapper authMapper;

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody UserAuthRequestDto requestAuth) {
        var loginData = new UsernamePasswordAuthenticationToken(requestAuth.getLogin(), requestAuth.getPassword());
        var auth = authenticationManager.authenticate(loginData);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/register")
    public ResponseEntity<UserAuthResponseDto> register(@Valid @RequestBody UserAuthRequestDto userAuthRequestDto) {

        if (userAuthRepository.findByLogin(userAuthRequestDto.getLogin()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        // TODO: Aplicar lógica e transferir pra classe de serviço
        var encryptedPassword = new BCryptPasswordEncoder().encode(userAuthRequestDto.getPassword());
        var newUser = new UserAuth(userAuthRequestDto.getLogin(), encryptedPassword, userAuthRequestDto.getRole());

        userAuthRepository.save(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
}
