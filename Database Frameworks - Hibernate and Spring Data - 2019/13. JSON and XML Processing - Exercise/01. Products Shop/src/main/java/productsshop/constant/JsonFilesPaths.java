package productsshop.constant;

public final class JsonFilesPaths {

    public static final String JSON_RESOURCES_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\json\\";

    private static final String XML_RESOURCES_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\xml\\";

    public static final String JSON_USERS = "users.json";

    public static final String JSON_CATEGORIES = "categories.json";

    public static final String JSON_PRODUCTS = "products.json";

    public static final String JSON_PRODUCTS_IN_RANGE = "products-in-range.json";

    public static final String JSON_USERS_SOLD_PRODUCTS = "users-sold-products.json";

    public static final String JSON_CATEGORIES_BY_PRODUCTS = "categories-by-products.json";

    public static final String JSON_USERS_AND_PRODUCTS = "users-and-products.json";

    public static final String XML_USERS = XML_RESOURCES_PATH.concat("users.xml");

    public static final String XML_CATEGORIES = XML_RESOURCES_PATH.concat("categories.xml");

    public static final String XML_PRODUCTS = XML_RESOURCES_PATH.concat("products.xml");

    public static final String XML_PRODUCTS_IN_RANGE = XML_RESOURCES_PATH.concat("products-in-range.xml");

    public static final String XML_USERS_SOLD_PRODUCTS = XML_RESOURCES_PATH.concat("users-sold-products.xml");

    public static final String XML_CATEGORIES_BY_PRODUCTS = XML_RESOURCES_PATH.concat("categories-by-products.xml");

    public static final String XML_USERS_AND_PRODUCTS = XML_RESOURCES_PATH.concat("users-and-products.xml");

    private JsonFilesPaths() {
    }
}
