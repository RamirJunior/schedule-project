package ramir.com.schedule.api.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserDto {
    private UUID id;
    private String name;
    private String lastname;
    private String email;
    private String phone;
}
