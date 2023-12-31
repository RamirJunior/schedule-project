package ramir.com.schedule.api.dto.auth;

import lombok.Data;
import ramir.com.schedule.domain.entity.UserRole;

@Data
public class UserAuthResponseDto {
    private String login;
    private String password;
    private UserRole role;
}
