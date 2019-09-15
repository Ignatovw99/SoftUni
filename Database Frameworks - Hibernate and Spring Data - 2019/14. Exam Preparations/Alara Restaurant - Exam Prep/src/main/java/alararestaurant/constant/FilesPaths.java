package alararestaurant.constant;

public final class FilesPaths {

    private static final String IMPORT_FILES_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\files\\";

    public static final String EMPLOYEES_IMPORT_FILE = IMPORT_FILES_PATH.concat("employees.json");

    public static final String ITEMS_IMPORT_FILE = IMPORT_FILES_PATH.concat("items.json");

    public static final String ORDERS_IMPORT_FILE = IMPORT_FILES_PATH.concat("orders.xml");

    private FilesPaths(){}
}
