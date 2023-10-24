package ramir.com.schedule.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ramir.com.schedule.domain.entity.Schedule;
import ramir.com.schedule.domain.entity.User;
import ramir.com.schedule.domain.repository.ScheduleRepository;
import ramir.com.schedule.exception.BusinessException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserService userService;

    public Schedule saveSchedule(Schedule schedule) {

        //Validate if user exists
        var userFound = findCurrentUser(schedule.getUser().getId());
        if (userFound.isEmpty())
            throw new BusinessException("User not found.");

        //Validate schedule available
        var scheduleFound = checkScheduleAvailable(schedule.getDateTime());
        if (scheduleFound.isPresent())
            throw new BusinessException("Date/time unavailable.");

        schedule.setUser(userFound.get());
        schedule.setDateTime(LocalDateTime.now());
        return scheduleRepository.save(schedule);
    }

    private Optional<User> findCurrentUser(UUID userId) {
        return userService.getUser(userId);
    }

    private Optional<Schedule> checkScheduleAvailable(LocalDateTime dateTime) {
        return scheduleRepository.findByDateTime(dateTime);
    }

    public List<Schedule> getSchedules() {
        return scheduleRepository.findAll();
    }

    public Optional<Schedule> getSchedule(UUID id) {
        return scheduleRepository.findById(id);
    }
}
