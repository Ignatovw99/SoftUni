package app.ccb.config;

import app.ccb.domain.dtos.ClientImportDto;
import app.ccb.domain.dtos.EmployeeImportDto;
import app.ccb.domain.entities.Client;
import app.ccb.domain.entities.Employee;
import app.ccb.util.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public FileUtil fileUtil() {
        return new FileUtilImpl();
    }

    @Bean
    public Gson gson(){
        JsonDeserializer<LocalDate> dateJsonDeserializer = (jsonElement, type, context) -> LocalDate.parse(jsonElement.getAsString(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, dateJsonDeserializer)
                .create();
    }

    @Bean
    public ValidationUtil validationUtil() {
        return new ValidationUtilImpl();
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        Converter<String, String> convertFullToFirstName = new AbstractConverter<String, String>() {
            @Override
            protected String convert(String fullName) {
                return fullName.substring(0, fullName.indexOf(" "));
            }
        };

        Converter<String, String> convertFullToLastName = new AbstractConverter<String, String>() {
            @Override
            protected String convert(String fullName) {
                return fullName.substring(fullName.lastIndexOf(" ") + 1);
            }
        };

        modelMapper.createTypeMap(EmployeeImportDto.class, Employee.class)
                .addMappings(m ->
                        m.using(convertFullToFirstName)
                        .map(EmployeeImportDto::getFullName, Employee::setFirstName)
                )
                .addMappings(m ->
                        m.using(convertFullToLastName)
                        .map(EmployeeImportDto::getFullName, Employee::setLastName)
                );

        modelMapper.createTypeMap(ClientImportDto.class, Client.class)
                .addMappings(m -> m.map(
                        ClientImportDto::getFirstName,
                        Client::setFirstName
                ))
                .addMappings(m -> m.map(
                        ClientImportDto::getLastName,
                        Client::setLastName
                ));

        return modelMapper;
    }

    @Bean
    public XmlParser xmlParser() {
        return new XmlParserImpl();
    }
}
