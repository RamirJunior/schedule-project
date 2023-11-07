package ramir.com.schedule.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthRequestDto {
    private String login;
    private String password;
    private String role;
}
