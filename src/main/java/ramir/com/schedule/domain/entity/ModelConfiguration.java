package ramir.com.schedule.domain.entity;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelConfiguration {

    @Bean
    public ModelMapper getModel(){
        return new ModelMapper();
    }
}
