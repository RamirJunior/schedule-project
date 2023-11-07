package ramir.com.schedule.api.dto.schedule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ramir.com.schedule.api.dto.user.UserResponseDto;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleResponseDto {

    private UUID id;
    private String description;
    private LocalDateTime dateTime;
    private UserResponseDto user;
}
