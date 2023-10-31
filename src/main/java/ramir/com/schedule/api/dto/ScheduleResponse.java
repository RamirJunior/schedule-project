package ramir.com.schedule.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleResponse {

    private UUID id;
    private String description;
    private LocalDateTime dateTime;
    private UserResponse user;
}
