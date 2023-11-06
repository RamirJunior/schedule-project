package ramir.com.schedule.api.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ramir.com.schedule.api.dto.ScheduleRequestDto;
import ramir.com.schedule.api.dto.ScheduleResponseDto;
import ramir.com.schedule.domain.entity.Schedule;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ScheduleMapper {

    private final ModelMapper mapper;

    public Schedule toSchedule(ScheduleRequestDto request) {
        return mapper.map(request, Schedule.class);
    }

    public ScheduleResponseDto toScheduleResponse(Schedule schedule) {
        return mapper.map(schedule, ScheduleResponseDto.class);
    }

    public List<ScheduleResponseDto> toScheduleResponseList(List<Schedule> schedules) {
        return schedules.stream()
                .map(this::toScheduleResponse)
                .collect(Collectors.toList());
    }
}
