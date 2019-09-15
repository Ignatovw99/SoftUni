package judgesystem.services.api;

public interface UserService {

    void importUsers(String usersFilePath);

    String enrollInContests(String userParticipationXmlFilePath);

    String submitProblems(String submissionFilePath);
}
