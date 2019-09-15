package judgesystem.constants;

public final class FilesPaths {

    private static final String FILES_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\files\\";

    public static final String CATEGORIES_JSON_FILE_PATH = FILES_PATH.concat("categories.json");

    public static final String STRATEGIES_JSON_FILE_PATH = FILES_PATH.concat("strategies.json");

    public static final String CONTESTS_JSON_FILE_PATH = FILES_PATH.concat("contests.json");

    public static final String PROBLEMS_XML_FILE_PATH = FILES_PATH.concat("problems.xml");

    public static final String USERS_JSON_FILE_PATH = FILES_PATH.concat("users.json");

    public static final String USER_PARTICIPATION_XML_FILE_PATH = FILES_PATH.concat("user_participation.xml");

    public static final String SUBMISSION_XML_FILE_PATH = FILES_PATH.concat("submissions.xml");

    private FilesPaths(){}
}
