package xmlprocessing.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xmlprocessing.domain.dto.UserXmlDto;
import xmlprocessing.domain.entities.User;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(User.class, UserXmlDto.class)
                .addMappings(m -> m.map(
                        User::getCars,
                        UserXmlDto::setCarDtos
                ));
        return modelMapper;
    }
}
