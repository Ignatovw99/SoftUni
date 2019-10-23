package kayzer.services;

import kayzer.domain.entities.UserRole;
import kayzer.repositories.UserRoleRepository;

public interface UserRoleService {

    void initializeUserRoles();

    UserRole getUserRoleByAuthority(String authority);

    UserRoleRepository getUserRoleRepository();
}
