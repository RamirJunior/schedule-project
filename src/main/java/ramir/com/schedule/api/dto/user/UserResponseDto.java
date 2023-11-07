package ramir.com.schedule.api.dto.user;

import lombok.Data;

import java.util.UUID;

@Data
public class UserResponseDto {
    private UUID id;
    private String name;
    private String lastname;
    private String email;
    private String phone;
}
