package ramir.com.schedule.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ramir.com.schedule.api.dto.ScheduleRequest;
import ramir.com.schedule.api.dto.ScheduleResponse;
import ramir.com.schedule.api.mapper.ScheduleMapper;
import ramir.com.schedule.domain.entity.Schedule;
import ramir.com.schedule.domain.service.ScheduleService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final ScheduleMapper mapper;

    @PostMapping
    public ResponseEntity<ScheduleResponse> save(@Valid @RequestBody ScheduleRequest scheduleRequest){
        Schedule schedule = mapper.toSchedule(scheduleRequest);
        Schedule savedSchedule = scheduleService.saveSchedule(schedule);
        ScheduleResponse scheduleResponse = mapper.toScheduleResponse(savedSchedule);
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleResponse);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponse>> findAllSchedules(){
        var scheduleList = scheduleService.getSchedules();
        List<ScheduleResponse> scheduleResponseList = mapper.toScheduleResponseList(scheduleList);
        return ResponseEntity.status(HttpStatus.OK).body(scheduleResponseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponse> findSchedule(UUID id){
        Optional<Schedule> scheduleFound = scheduleService.getSchedule(id);
        if (scheduleFound.isEmpty())
            return ResponseEntity.notFound().build();

        ScheduleResponse scheduleResponse = mapper.toScheduleResponse(scheduleFound.get());
        return ResponseEntity.status(HttpStatus.OK).body(scheduleResponse);
    }
}
