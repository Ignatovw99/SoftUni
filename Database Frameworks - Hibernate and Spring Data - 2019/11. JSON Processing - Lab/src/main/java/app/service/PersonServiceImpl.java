package app.service;

import app.domain.dto.PersonJsonDto;
import app.domain.model.Person;
import app.repository.PersonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;

    private ModelMapper modelMapper;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository, ModelMapper modelMapper) {
        this.personRepository = personRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PersonJsonDto getById(long id) {
        Person person = this.personRepository.findById(id)
                .orElse(null);

        if (person == null) {
            return null;
        }

        return this.modelMapper.map(person, PersonJsonDto.class);
    }

    @Override
    public void save(PersonJsonDto personJsonDto) {
        Person person = this.modelMapper.map(personJsonDto, Person.class);
        person.getPhoneNumbers()
                .forEach(phoneNumber -> phoneNumber.setPerson(person));
        this.personRepository.saveAndFlush(person);
    }
}
