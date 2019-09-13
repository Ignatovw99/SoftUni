package usersystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import usersystem.entities.*;
import usersystem.services.interfaces.*;

import java.time.LocalDate;
import java.util.*;

import static usersystem.constants.Constants.*;

@Controller
public class UserSystemController implements CommandLineRunner {

    private UserService userService;

    private CountryService countryService;

    private TownService townService;

    private PictureService pictureService;

    private AlbumService albumService;

    @Autowired
    public UserSystemController(
            UserService userService,
            CountryService countryService,
            TownService townService,
            PictureService pictureService,
            AlbumService albumService) {
        this.userService = userService;
        this.countryService = countryService;
        this.townService = townService;
        this.pictureService = pictureService;
        this.albumService = albumService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (this.countryService.getCountriesCount() == 0L) {
            this.countryService.seedCountriesTable();
        }

        if (this.townService.getTownsCount() == 0L) {
            this.townService.seedTownsTable(this.countryService);
        }

        if (this.pictureService.getPicturesCount() == 0L) {
            this.pictureService.seedPicturesTable();
        }

        if (this.albumService.getAlbumsCount() == 0L) {
            this.albumService.seedAlbumsTable(this.pictureService);
        }

        if (this.userService.getUsersCount() == 0L) {
            this.userService.seedUsersTable(this.townService, this.albumService);
        }


        printAllUsersWithGivenEmailProvider(EMAIL_PROVIDER);

        removeInactiveUsers(DATE);
    }

    private void printAllUsersWithGivenEmailProvider(String emailProvider) {
        List<User> users = this.userService.getUsersByEmailProvider(emailProvider);

        StringBuilder stringBuilder = new StringBuilder();
        if (users.size() == 0) {
            stringBuilder.append(NO_USERS_WITH_SUCH_EMAIL_PROVIDER)
                    .append(emailProvider)
                    .append(System.lineSeparator());
        } else {
            users.forEach(user -> stringBuilder.append(user.getUsername())
                    .append(DELIMITER)
                    .append(user.getEmail())
                    .append(System.lineSeparator())
            );
        }

        System.out.println(stringBuilder.toString());
    }

    private void removeInactiveUsers(LocalDate date) {
        List<User> inactiveUsers = this.userService.getInactiveUsers(date);

        String result;
        if (inactiveUsers.size() == 0) {
            result = NO_USERS_DELETED;
        } else {
            for (int i = inactiveUsers.size() - 1; i >= 0; i--) {
                User inactiveUser = inactiveUsers.get(i);
                this.userService.markUserAsDeleted(inactiveUser);
                this.userService.deleteUserFromHisFriends(inactiveUser);
            }
            inactiveUsers.forEach(inactiveUser -> this.userService.deleteUserFromDatabase(inactiveUser));
            result = String.valueOf(inactiveUsers.size()).concat(
                    inactiveUsers.size() == 1 ? SUCCESSFUL_ONE_USER_REMOVAL : SUCCESSFUL_MANY_USER_REMOVAL
            );
        }
        System.out.println(result);
    }
}
