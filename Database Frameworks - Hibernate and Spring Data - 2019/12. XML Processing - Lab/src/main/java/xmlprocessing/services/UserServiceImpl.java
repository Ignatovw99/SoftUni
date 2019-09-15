package xmlprocessing.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmlprocessing.domain.dto.UserXmlDto;
import xmlprocessing.domain.entities.User;
import xmlprocessing.repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public List<UserXmlDto> getAllUsers() {
        List<User> all = this.userRepository.findAll();
        return this.userRepository.findAll()
                .stream()
                .map(user -> this.modelMapper.map(user, UserXmlDto.class))
                .collect(Collectors.toList());
    }
}
