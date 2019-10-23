package kayzer.services;

import kayzer.domain.entities.UserRole;
import kayzer.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void initializeUserRoles() {
        UserRole user = new UserRole();
        user.setAuthority("USER");
        user.setPower(1);
        UserRole moderator = new UserRole();
        moderator.setAuthority("MODERATOR");
        moderator.setPower(2);
        UserRole admin = new UserRole();
        admin.setAuthority("ADMIN");
        admin.setPower(3);

        Set<UserRole> roles = new HashSet<>();
        roles.add(user);
        roles.add(moderator);
        roles.add(admin);

        this.userRoleRepository.saveAll(roles);
    }

    @Override
    public UserRole getUserRoleByAuthority(String authority) {
        return this.userRoleRepository.findByAuthority(authority);
    }

    @Override
    public UserRoleRepository getUserRoleRepository() {
        return userRoleRepository;
    }
}
