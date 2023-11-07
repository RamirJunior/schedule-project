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
import ramir.com.schedule.api.dto.login.LoginResponseDto;
import ramir.com.schedule.api.dto.login.LoginRequestDto;
import ramir.com.schedule.api.dto.auth.UserAuthRequestDto;
import ramir.com.schedule.api.dto.auth.UserAuthResponseDto;
import ramir.com.schedule.api.mapper.UserAuthMapper;
import ramir.com.schedule.domain.entity.UserAuth;
import ramir.com.schedule.domain.repository.UserAuthRepository;
import ramir.com.schedule.domain.service.security.EncryptionService;
import ramir.com.schedule.domain.service.security.TokenService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserAuthRepository userAuthRepository;
    private final EncryptionService encryptionService;
    private final UserAuthMapper userAuthMapper;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        var loginData = new UsernamePasswordAuthenticationToken(
                loginRequestDto.getLogin(), loginRequestDto.getPassword());
        var auth = authenticationManager.authenticate(loginData);

        var token = tokenService.generateToken((UserAuth) auth.getPrincipal());
        var loginResponseDto = new LoginResponseDto(token);
        return ResponseEntity.status(HttpStatus.OK).body(loginResponseDto);
    }

    @PostMapping("/register")
    public ResponseEntity<UserAuthResponseDto> register(@Valid @RequestBody UserAuthRequestDto userAuthRequestDto) {
        var userFound = userAuthRepository.findByLogin(userAuthRequestDto.getLogin());
        if (userFound != null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        userAuthRequestDto.setPassword(
                encryptionService.hashPassword(userAuthRequestDto.getPassword()));

        var userAuth = userAuthMapper.toUserAuth(userAuthRequestDto);
        var savedUserAuth = userAuthRepository.save(userAuth);
        var userAuthResponseDto = userAuthMapper.toUserAuthResponseDto(savedUserAuth);
        return ResponseEntity.status(HttpStatus.CREATED).body(userAuthResponseDto);
    }
}
