package app.service;

import app.domain.dto.PersonJsonDto;

public interface PersonService {

    PersonJsonDto getById(long id);

    void save(PersonJsonDto personJsonDto);
}
