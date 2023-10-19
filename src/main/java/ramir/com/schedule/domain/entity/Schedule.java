package ramir.com.schedule.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "schedule")
public class Schedule {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String description;
    private LocalDateTime dateTime;
    private LocalDateTime creationDate;

    @ManyToOne
    private Person person;

}
