package residentevil.services.implementations;

import org.springframework.stereotype.Service;
import residentevil.domain.entities.UserRole;
import residentevil.repositories.UserRoleRepository;
import residentevil.services.UserRoleService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public List<String> getAllUserRoles() {
        return this.userRoleRepository.findAll()
                .stream()
                .map(UserRole::getAuthority)
                .collect(Collectors.toList());
    }
}
