package gamestore.domain;

import gamestore.constants.CommandMessages;
import gamestore.domain.dtos.view.LoggedInUserDto;
import gamestore.domain.entities.enums.Role;

public class LoggedInUser {

    private static LoggedInUserDto currentLoggedInUser;

    public static String login(LoggedInUserDto loggedInUserDto) {
        StringBuilder builder = new StringBuilder();

        if (hasCurrentlyLoggedInUser() && currentLoggedInUser.getEmail().equals(loggedInUserDto.getEmail())) {
            return String.format(CommandMessages.CURRENTLY_LOGGED_IN_USER, currentLoggedInUser.getEmail());
        }

        if (hasCurrentlyLoggedInUser()) {
            builder.append(logout())
                    .append(System.lineSeparator());
        }

        currentLoggedInUser = loggedInUserDto;

        builder.append(String.format(CommandMessages.USER_LOGGED_IN_SUCCESSFULLY, currentLoggedInUser.getFullName()));

        return builder.toString();
    }

    public static String logout() {
        if (currentLoggedInUser == null) {
            return CommandMessages.NO_LOGGED_IN_USER_TO_LOGOUT;
        }

        String loggedOutUserFullName = currentLoggedInUser.getFullName();
        currentLoggedInUser = null;
        return String.format(CommandMessages.USER_LOGGED_OUT_SUCCESSFULLY, loggedOutUserFullName);
    }

    public static boolean hasCurrentlyLoggedInUser() {
        return currentLoggedInUser != null;
    }

    public static boolean isAdmin() {
        if (currentLoggedInUser == null) {
            return false;
        }
        return currentLoggedInUser.getRole().equals(Role.ADMIN);
    }

    public static String getFullName() {
        if (currentLoggedInUser == null) {
            return null;
        }
        return currentLoggedInUser.getFullName();
    }

    public static Long getUserId() {
        if (currentLoggedInUser == null) {
            return null;
        }
        return currentLoggedInUser.getId();
    }
}
