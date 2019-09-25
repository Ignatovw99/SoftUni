package residentevil.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import residentevil.domain.models.binding.UserRegisterBindingModel;
import residentevil.domain.models.binding.UserRoleEditBindingModel;
import residentevil.domain.models.service.UserServiceModel;
import residentevil.domain.models.view.UserViewModel;

import java.util.List;

public interface UserService extends UserDetailsService {

    boolean registerUser(UserRegisterBindingModel userRegisterModel);

    UserServiceModel getUserByUsername(String username);

    List<UserViewModel> getAllUsers();

    UserViewModel getUserById(String id);

    void editUserRole(String id, UserRoleEditBindingModel userRoleEditBindingModel, String editor);
}
