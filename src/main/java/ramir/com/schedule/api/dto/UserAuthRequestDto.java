package ramir.com.schedule.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ramir.com.schedule.domain.entity.UserRole;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthRequestDto {
    @NotBlank
    private String login;
    @NotBlank
    private String password;
    @NotBlank
    private UserRole role;
}
