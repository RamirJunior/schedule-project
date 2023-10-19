package ramir.com.schedule.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String name;
    private String lastname;
    private String email;
    private String phone;

}
