package ramir.com.schedule.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import ramir.com.schedule.domain.entity.UserLogin;

import java.util.UUID;

public interface LoginRepository extends JpaRepository<UserLogin, UUID> {
    UserDetails findByLogin(String login);
}
