package ramir.com.schedule.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ramir.com.schedule.domain.entity.Schedule;
import ramir.com.schedule.domain.service.ScheduleService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

//    @PostMapping
//    public ResponseEntity<Schedule> save(@RequestBody Schedule schedule){
//        var scheduleSaved = scheduleService.saveSchedule(schedule);
//        if (scheduleSaved != )
//    }

    @GetMapping
    public ResponseEntity<List<Schedule>> findAllSchedules(){
        var schedules = scheduleService.getSchedules();
        return ResponseEntity.status(HttpStatus.OK).body(schedules);
    }

    @GetMapping("{id}")
    public ResponseEntity<Schedule> findSchedule(UUID id){
        var scheduleFound = scheduleService.getSchedule(id);
        if (scheduleFound.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.status(HttpStatus.OK).body(scheduleFound.get());
    }

}
