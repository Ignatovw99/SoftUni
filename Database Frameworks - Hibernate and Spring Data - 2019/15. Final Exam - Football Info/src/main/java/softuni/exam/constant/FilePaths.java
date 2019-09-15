package softuni.exam.constant;

public final class FilePaths {

    private static final String FILES_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\files\\";

    private static final String XML_FILES_PATH = FILES_PATH.concat("xml\\");

    private static final String JSON_FILES_PATH = FILES_PATH.concat("json\\");

    public static final String PICTURES_FILE_PATH = XML_FILES_PATH.concat("pictures.xml");

    public static final String TEAMS_FILE_PATH = XML_FILES_PATH.concat("teams.xml");

    public static final String PLAYERS_FILE_PATH = JSON_FILES_PATH.concat("players.json");

    private FilePaths() {
    }
}
