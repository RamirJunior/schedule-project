package ramir.com.schedule.api.dto;

import jakarta.validation.constraints.NotBlank;
import ramir.com.schedule.domain.entity.UserRole;

public class UserAuthResponseDto {
    @NotBlank
    private String login;
    @NotBlank
    private String password;
    @NotBlank
    private UserRole role;
}
