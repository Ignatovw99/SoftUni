package usersystem.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usersystem.entities.Album;
import usersystem.entities.User;
import usersystem.repositories.UserRepository;
import usersystem.services.interfaces.AlbumService;
import usersystem.services.interfaces.TownService;
import usersystem.services.interfaces.UserService;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;

import static usersystem.constants.Constants.EMPTY_USERS_TABLE;
import static usersystem.constants.UserValues.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Long getUsersCount() {
        return this.userRepository.count();
    }

    @Override
    public void seedUsersTable(TownService townService, AlbumService albumService) {
        for (int i = 0; i < USERS_COUNT; i++) {
            User user = new User();
            user.setUsername(USERNAME + i);
            user.setPassword(PASSWORD + i);
            user.setEmail(EMAIL_USER_PART + i + AT_SIGN + EMAIL_HOST_PARTS[i % EMAIL_HOST_PARTS.length]);
            user.setRegistrationDate(UNIVERSAL_VALUE_FOR_DATE_TYPE);
            user.setLastTimeLoggedIn(UNIVERSAL_VALUE_FOR_DATE_TYPE);
            user.setAge(i + 1);
            user.setDeleted(IS_PROFILE_DELETED_FALSE_VALUE);
            user.setBornTown(townService.getRandomTown());
            user.setCurrentlyLivingTown(townService.getRandomTown());
            user.setFirstName(FIRST_NAME + i);
            user.setLastName(LAST_NAME + i);
            user.setFriends(new HashSet<>());
            if (i != 0) {
                this.becomeFriendWithOtherUsers(user);
            }
            Set<Album> albums = albumService.getRandomAlbums();
            user.setAlbums(albums);
            albums.forEach(album -> album.setUser(user));
            this.registerNewUser(user);
        }
    }

    @Override
    public User getRandomUser() {
        long usersCount = this.userRepository.count();
        if (usersCount == 0L) {
            throw new IllegalArgumentException(EMPTY_USERS_TABLE);
        }

        Random random = new Random();
        List<User> users = this.userRepository.findAll();
        return users.get(random.nextInt((int) usersCount));
    }

    @Override
    public void becomeFriendWithOtherUsers(User user) {
        Set<User> newFriends = new HashSet<>();

        for (int i = 0; i < INITIAL_FRIENDS_COUNT; i++) {
            User randomUser = this.getRandomUser();
            newFriends.add(randomUser);
            randomUser.getFriends()
                    .add(user);
        }

        user.getFriends()
                .addAll(newFriends);
    }

    @Override
    public void registerNewUser(User user) {
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public List<User> getUsersByEmailProvider(String emailProvider) {
        return this.userRepository.findAllByEmailEndingWith(emailProvider);
    }

    @Override
    public List<User> getInactiveUsers(LocalDate date) {
        return this.userRepository.findAllByLastTimeLoggedInBefore(date);
    }

    @Override
    public void markUserAsDeleted(User user) {
        user.setDeleted(IS_PROFILE_DELETED_TRUE_VALUE);
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public void deleteUserFromDatabase(User user) {
        this.userRepository.deleteById(user.getId());
        this.userRepository.flush();
    }

    @Override
    public void deleteUserFromHisFriends(User user) {
        List<User> friends = new ArrayList<>(user.getFriends());
        for (int j = 0; j < friends.size(); j++) {
            User currentFriend = friends.get(j);
            List<User> users = new ArrayList<>(currentFriend.getFriends());
            for (int k = 0; k < users.size(); k++) {
                User currentUser = users.get(k);
                if (currentUser == user) {
                    currentFriend.getFriends().remove(user);
                    this.userRepository.save(currentFriend);
                    break;
                }
            }
        }
        user.setFriends(new HashSet<>());
        this.userRepository.saveAndFlush(user);
    }
}
