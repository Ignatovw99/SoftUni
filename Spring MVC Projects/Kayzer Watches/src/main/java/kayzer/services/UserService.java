package kayzer.services;

import kayzer.domain.models.service.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Set;

public interface UserService extends UserDetailsService {

    boolean registerUser(UserServiceModel userServiceModel);

    Set<UserServiceModel> getAllUsers();

    boolean promoteUser(String id);

    boolean demoteUser(String id);
}
