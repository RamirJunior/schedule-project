package ramir.com.schedule.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ramir.com.schedule.domain.entity.Schedule;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {
    Optional<Schedule> findByDateTime(LocalDateTime dateTime);
}
