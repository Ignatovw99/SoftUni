package xmlprocessing.services;

import xmlprocessing.domain.dto.UserXmlDto;

import java.util.List;

public interface UserService {

    List<UserXmlDto> getAllUsers();
}
