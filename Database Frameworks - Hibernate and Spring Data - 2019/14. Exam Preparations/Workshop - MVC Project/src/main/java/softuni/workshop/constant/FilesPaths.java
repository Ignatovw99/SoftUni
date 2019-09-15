package softuni.workshop.constant;

public final class FilesPaths {

    private static final String XML_FILES_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\files\\xmls\\";

    private static final String JSON_FILES_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\files\\jsons\\";

    public static final String COMPANIES_XML_PATH = XML_FILES_PATH.concat("companies.xml");

    public static final String PROJECTS_XML_PATH = XML_FILES_PATH.concat("projects.xml");

    public static final String EMPLOYEES_XML_PATH = XML_FILES_PATH.concat("employees.xml");

    public static final String EXPORTED_COMPANIES_XML_PATH = XML_FILES_PATH.concat("exported-companies.xml");

    public static final String COMPANIES_JSON_PATH = JSON_FILES_PATH.concat("export-companies.json");

    public static final String PROJECTS_JSON_PATH = JSON_FILES_PATH.concat("export-projects.json");

    public static final String EMPLOYEES_JSON_PATH = JSON_FILES_PATH.concat("export-employees.json");

    private FilesPaths(){}
}
