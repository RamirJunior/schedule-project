package ramir.com.schedule.domain.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EncryptionService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public String hashPassword(String password){
        return bCryptPasswordEncoder.encode(password);
    }

    public boolean passwordsMatch(String password, String hashedPassword){
        return bCryptPasswordEncoder.matches(password, hashedPassword);
    }
}
