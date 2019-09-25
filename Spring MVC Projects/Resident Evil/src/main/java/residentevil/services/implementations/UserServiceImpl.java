package residentevil.services.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import residentevil.domain.entities.User;
import residentevil.domain.entities.UserRole;
import residentevil.domain.models.binding.UserRegisterBindingModel;
import residentevil.domain.models.binding.UserRoleEditBindingModel;
import residentevil.domain.models.service.UserServiceModel;
import residentevil.domain.models.view.UserViewModel;
import residentevil.repositories.UserRepository;
import residentevil.repositories.UserRoleRepository;
import residentevil.services.UserService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserRoleRepository userRoleRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, BCryptPasswordEncoder bCryptPasswordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.modelMapper = modelMapper;
    }

    private void initializeUserRolesIfEmpty() {
        if (this.userRoleRepository.findAll().isEmpty()) {
            UserRole root = new UserRole();
            root.setAuthority("ROOT");
            UserRole admin = new UserRole();
            admin.setAuthority("ADMIN");
            UserRole moderator = new UserRole();
            moderator.setAuthority("MODERATOR");
            UserRole user = new UserRole();
            user.setAuthority("USER");

            List<UserRole> roles = new ArrayList<>();
            roles.add(root);
            roles.add(admin);
            roles.add(moderator);
            roles.add(user);

            this.userRoleRepository.saveAll(roles);
        }
    }

    @Override
    public boolean registerUser(UserRegisterBindingModel userRegisterModel) {
        if (!userRegisterModel.getPassword().equals(userRegisterModel.getConfirmPassword())) {
            return false;
        }
        User userEntity = this.modelMapper.map(userRegisterModel, User.class);

        userEntity.setPassword(this.bCryptPasswordEncoder.encode(userEntity.getPassword()));

        this.initializeUserRolesIfEmpty();

        Set<UserRole> userAuthorities = new HashSet<>();

        if (this.userRepository.count() == 0) {
            userAuthorities.add(this.userRoleRepository.findByAuthority("ROOT"));
            userAuthorities.add(this.userRoleRepository.findByAuthority("ADMIN"));
            userAuthorities.add(this.userRoleRepository.findByAuthority("MODERATOR"));
            userAuthorities.add(this.userRoleRepository.findByAuthority("USER"));
        } else {
            userAuthorities.add(this.userRoleRepository.findByAuthority("USER"));
        }

        userEntity.setAuthorities(userAuthorities);

        this.userRepository.saveAndFlush(userEntity);
        return true;
    }

    @Override
    public UserServiceModel getUserByUsername(String username) {
        return this.modelMapper.map(this.userRepository.findByUsername(username).get(), UserServiceModel.class);
    }

    @Override
    public UserViewModel getUserById(String id) {
        Optional<User> user = this.userRepository.findById(id);

        if (user.isEmpty()) {
            return null;
        }

        UserViewModel viewModel = this.modelMapper.map(user.get(), UserViewModel.class);
        viewModel.setAuthorities(user.get().getAuthorities().stream().map(UserRole::getAuthority).collect(Collectors.toSet()));
        return viewModel;
    }

    @Override
    public List<UserViewModel> getAllUsers() {
        return this.userRepository.findAll()
                .stream()
                .map(user -> {
                    UserViewModel viewModel = this.modelMapper.map(user, UserViewModel.class);
                    viewModel.setAuthorities(user.getAuthorities()
                            .stream()
                            .map(UserRole::getAuthority)
                            .collect(Collectors.toSet())
                    );
                    return viewModel;
                })
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public void editUserRole(String id, UserRoleEditBindingModel userRoleEditBindingModel, String editor) {
        Optional<User> userCandidate = this.userRepository.findById(id);
        if (userCandidate.isEmpty()) {
            return;
        }

        User user = userCandidate.get();

        boolean isUserRootAdmin = user.getAuthorities()
                .stream()
                .map(UserRole::getAuthority)
                .collect(Collectors.toList()).contains("ROOT");

        boolean isEditedUserCurrentlyLoggedInUser = user.getUsername().equals(editor);

        boolean isRootAuthorityAdded = userRoleEditBindingModel.getAuthorities().contains("ROOT");

        if (isUserRootAdmin
                || isEditedUserCurrentlyLoggedInUser
                || isRootAuthorityAdded) {
            return;
        }
        Set<UserRole> authorities = new HashSet<>();

        if (userRoleEditBindingModel.getAuthorities().contains("ADMIN")) {
            authorities.add(this.userRoleRepository.findByAuthority("ADMIN"));
        } if (userRoleEditBindingModel.getAuthorities().contains("MODERATOR")) {
            authorities.add(this.userRoleRepository.findByAuthority("MODERATOR"));
        }

        authorities.add(this.userRoleRepository.findByAuthority("USER"));

        user.setAuthorities(authorities);
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> foundUser = this.userRepository.findByUsername(username);

        if (foundUser.isEmpty()) {
            throw new UsernameNotFoundException("Username not found!");
        }

        return foundUser.get();
    }
}
