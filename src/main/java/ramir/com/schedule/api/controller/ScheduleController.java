package ramir.com.schedule.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ramir.com.schedule.api.dto.ScheduleRequestDto;
import ramir.com.schedule.api.dto.ScheduleResponseDto;
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
    private final ScheduleMapper scheduleMapper;

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> save(@Valid @RequestBody ScheduleRequestDto scheduleRequest){
        var schedule = scheduleMapper.toSchedule(scheduleRequest);
        var savedSchedule = scheduleService.saveSchedule(schedule);
        var scheduleResponse = scheduleMapper.toScheduleResponse(savedSchedule);
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleResponse);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAllSchedules(){
        var scheduleList = scheduleService.getSchedules();
        var scheduleResponseList = scheduleMapper.toScheduleResponseList(scheduleList);
        return ResponseEntity.status(HttpStatus.OK).body(scheduleResponseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findSchedule(UUID id){
        Optional<Schedule> scheduleFound = scheduleService.getSchedule(id);
        if (scheduleFound.isEmpty())
            return ResponseEntity.notFound().build();

        var scheduleResponse = scheduleMapper.toScheduleResponse(scheduleFound.get());
        return ResponseEntity.status(HttpStatus.OK).body(scheduleResponse);
    }
}
