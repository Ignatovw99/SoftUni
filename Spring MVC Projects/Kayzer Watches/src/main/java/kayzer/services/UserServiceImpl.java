package kayzer.services;

import kayzer.domain.entities.User;
import kayzer.domain.entities.UserRole;
import kayzer.domain.models.service.UserServiceModel;
import kayzer.repositories.UserRepository;
import kayzer.utils.UserRoleIterator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserRoleService userRoleService;

    private final ModelMapper modelMapper;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserRoleService userRoleService, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.userRoleService = userRoleService;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    private Set<UserRole> getAuthorities(String authority) {
        Set<UserRole> authorities = new HashSet<>();

        if ("ADMIN".equals(authority)) {
            authorities.add(this.userRoleService.getUserRoleByAuthority("ADMIN"));
        }

        if ("ADMIN".equals(authority) || "MODERATOR".equals(authority)) {
            authorities.add(this.userRoleService.getUserRoleByAuthority("MODERATOR"));
        }

        authorities.add(this.userRoleService.getUserRoleByAuthority("USER"));

        return authorities;
    }

    private void moveIteratorToCurrentUserRole(UserRoleIterator userRoleIterator, User user) {
        UserRole currentUserRole = user.getAuthorities()
                .stream()
                .findFirst()
                .orElse(null);

        while (userRoleIterator.hasNext() && currentUserRole != null) {
            if (userRoleIterator.next().getAuthority().equals(currentUserRole.getAuthority())) {
                return;
            }
        }
    }

    @Override
    public boolean registerUser(UserServiceModel userServiceModel) {
        User userEntity = this.modelMapper.map(userServiceModel, User.class);
        userEntity.setPassword(this.bCryptPasswordEncoder.encode(userEntity.getPassword()));
        if (this.userRepository.findAll().isEmpty()) {
            this.userRoleService.initializeUserRoles();
            userEntity.setAuthorities(this.getAuthorities("ADMIN"));
        } else {
            userEntity.setAuthorities(this.getAuthorities("USER"));
        }

        return this.userRepository.saveAndFlush(userEntity) != null;
    }

    @Override
    public Set<UserServiceModel> getAllUsers() {
        return this.userRepository.findAll()
                .stream()
                .map(user -> this.modelMapper.map(user, UserServiceModel.class))
                .collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userCandidate = this.userRepository.findByUsername(username);

        if (userCandidate == null) {
            throw new UsernameNotFoundException("No such user.");
        }

        return userCandidate;
    }

    @Override
    public boolean promoteUser(String id) {
        User user = this.userRepository.findById(id).orElse(null);

        if (user == null) {
            return false;
        }

        UserRoleIterator userRoleIterator = new UserRoleIterator(this.userRoleService.getUserRoleRepository());

        this.moveIteratorToCurrentUserRole(userRoleIterator, user);

        if (!userRoleIterator.hasNext()) {
            return false;
        }

        Set<UserRole> newRole = new HashSet<>();
        newRole.add(userRoleIterator.next());
        user.setAuthorities(newRole);

        this.userRepository.saveAndFlush(user);

        return true;
    }

    @Override
    public boolean demoteUser(String id) {
        User user = this.userRepository.findById(id).orElse(null);

        if (user == null) {
            return false;
        }

        UserRoleIterator userRoleIterator = new UserRoleIterator(this.userRoleService.getUserRoleRepository());

        this.moveIteratorToCurrentUserRole(userRoleIterator, user);

        userRoleIterator.previous();

        if (!userRoleIterator.hasPrevious()) {
            return false;
        }

        Set<UserRole> newRole = new HashSet<>();
        newRole.add(userRoleIterator.previous());
        user.setAuthorities(newRole);

        this.userRepository.saveAndFlush(user);

        return true;
    }
}
