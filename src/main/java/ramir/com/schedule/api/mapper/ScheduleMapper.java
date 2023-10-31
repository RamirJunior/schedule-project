package ramir.com.schedule.api.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ramir.com.schedule.api.dto.ScheduleRequest;
import ramir.com.schedule.api.dto.ScheduleResponse;
import ramir.com.schedule.domain.entity.Schedule;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ScheduleMapper {

    private final ModelMapper mapper;

    public Schedule toSchedule(ScheduleRequest request) {
        return mapper.map(request, Schedule.class);
    }

    public ScheduleResponse toScheduleResponse(Schedule schedule) {
        return mapper.map(schedule, ScheduleResponse.class);
    }

    public List<ScheduleResponse> toScheduleResponseList(List<Schedule> schedules) {
        return schedules.stream()
                .map(this::toScheduleResponse)
                .collect(Collectors.toList());
    }
}
