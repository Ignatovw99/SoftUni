package app.utils;

import app.domain.dto.PersonJsonDto;
import app.domain.model.Person;
import app.domain.model.PhoneNumber;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public final class EntityMapper {

    private static ModelMapper MODEL_MAPPER;

    private EntityMapper() {
    }

    @Bean
    public static ModelMapper getModelMapper() {
        EntityMapper.MODEL_MAPPER = new ModelMapper();

        Converter<Set<PhoneNumber>, Set<String>> phoneNumberToNumberConverter = new AbstractConverter<>() {

            @Override
            protected Set<String> convert(Set<PhoneNumber> phoneNumbers) {
                return phoneNumbers == null
                        ? new HashSet<>()
                        : phoneNumbers.stream()
                        .map(PhoneNumber::getNumber)
                        .collect(Collectors.toSet());
            }
        };

        Converter<Set<String>, Set<PhoneNumber>> numberToPhoneNumberConverter = new AbstractConverter<Set<String>, Set<PhoneNumber>>() {

            @Override
            protected Set<PhoneNumber> convert(Set<String> numbers) {
                return numbers == null
                        ? new HashSet<>()
                        : numbers.stream()
                        .map(number -> {
                            PhoneNumber phoneNumber = new PhoneNumber();
                            phoneNumber.setNumber(number);
                            return phoneNumber;
                        })
                        .collect(Collectors.toSet());
            }
        };

        EntityMapper.MODEL_MAPPER.createTypeMap(Person.class, PersonJsonDto.class)
                .addMappings(
                        m -> m.using(phoneNumberToNumberConverter)
                                .map(
                                        Person::getPhoneNumbers,
                                        PersonJsonDto::setPhoneNumbers
                                )
                );

        EntityMapper.MODEL_MAPPER.createTypeMap(PersonJsonDto.class, Person.class)
                .addMappings(
                        m -> m.using(numberToPhoneNumberConverter)
                                .map(
                                        PersonJsonDto::getPhoneNumbers,
                                        Person::setPhoneNumbers
                                )
                );

        return EntityMapper.MODEL_MAPPER;
    }
}
