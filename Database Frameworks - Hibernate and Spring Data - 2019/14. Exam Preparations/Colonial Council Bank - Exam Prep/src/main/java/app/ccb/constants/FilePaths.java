package app.ccb.constants;

public final class FilePaths {

    private static final String FILES_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\files\\";

    private static final String JSON_FILES_PATH = FILES_PATH.concat("json\\");

    private static final String XML_FILES_PATH = FILES_PATH.concat("xml\\");

    public static final String BRANCHES_JSON_FILE_PATH = JSON_FILES_PATH.concat("branches.json");

    public static final String EMPLOYEES_JSON_FILE_PATH = JSON_FILES_PATH.concat("employees.json");

    public static final String CLIENTS_JSON_FILE_PATH = JSON_FILES_PATH.concat("clients.json");

    public static final String BANK_ACCOUNTS_XML_FILE_PATH = XML_FILES_PATH.concat("bank-accounts.xml");

    public static final String CARDS_XML_FILE_PATH = XML_FILES_PATH.concat("cards.xml");

    private FilePaths() {
    }
}
