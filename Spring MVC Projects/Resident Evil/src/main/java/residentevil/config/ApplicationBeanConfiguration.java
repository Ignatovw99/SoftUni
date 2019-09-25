package residentevil.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import residentevil.common.factories.UserRoleFactory;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public UserRoleFactory userRoleFactory() {
        return new UserRoleFactory();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
