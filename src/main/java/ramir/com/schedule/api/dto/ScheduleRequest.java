package ramir.com.schedule.api.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleRequest {

    @NotBlank
    private String description;

    @NotNull
    @Future
    private LocalDateTime dateTime;
    @NotNull
    private UUID userId;
}
