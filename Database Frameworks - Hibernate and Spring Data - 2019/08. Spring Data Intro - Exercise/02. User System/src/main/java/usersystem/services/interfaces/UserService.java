package usersystem.services.interfaces;

import usersystem.entities.User;

import java.time.LocalDate;
import java.util.List;

public interface UserService {

    Long getUsersCount();

    void seedUsersTable(TownService townService, AlbumService albumService);

    User getRandomUser();

    void becomeFriendWithOtherUsers(User user);

    void registerNewUser(User user);

    List<User> getUsersByEmailProvider(String emailProvider);

    List<User> getInactiveUsers(LocalDate date);

    void markUserAsDeleted(User user);

    void deleteUserFromDatabase(User user);

    void deleteUserFromHisFriends(User user);
}
