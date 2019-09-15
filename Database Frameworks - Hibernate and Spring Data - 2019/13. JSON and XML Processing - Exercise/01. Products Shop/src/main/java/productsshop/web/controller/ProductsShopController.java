package productsshop.web.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import productsshop.domain.dto.*;
import productsshop.service.CategoryService;
import productsshop.service.ProductService;
import productsshop.service.UserService;
import productsshop.util.FileUtil;
import productsshop.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.math.BigDecimal;
import java.util.*;

import static productsshop.constant.JsonFilesPaths.*;

@Controller
public class ProductsShopController implements CommandLineRunner {

    private final UserService userService;

    private final ProductService productService;

    private final CategoryService categoryService;

    private final Gson gson;

    private final XmlParser xmlParser;

    private Random random;

    @Autowired
    public ProductsShopController(UserService userService, ProductService productService, CategoryService categoryService, Gson gson, XmlParser xmlParser, Random random) {
        this.userService = userService;
        this.productService = productService;
        this.categoryService = categoryService;
        this.gson = gson;
        this.xmlParser = xmlParser;
        this.random = random;
    }

    @Override
    public void run(String... args) throws Exception {
        // When you first run the application, comment all methods except methods from 1 to 6.
        // These methods(1-6) seed the database with records, which are necessary for queries later.
        // Before executing the queries you should comment 'seed database methods'.

        this.seedUsersFromJson();  // Method 1
        this.seedUsersFromXml();
        this.seedCategoriesFromJson(); // Method 2
        this.seedCategoriesFromXml();
        this.seedProductsFromJson(); // Method 3
        this.seedProductsFromXml();
        this.addCategoriesToProducts(); // Method 4
        this.addSellerAndBuyerToProducts(); // Method 5
        this.addFriendsToUsers(); // Method 6
        this.exportToJsonAllProductsInGivenRange(); // Method 7
        this.exportToXmlAllProductsInGivenRange();
        this.exportToJsonAllUsersWhoSoldAtLeastOneProduct(); // Method 8
        this.exportToXmlAllUsersWhoSoldAtLeastOneProduct();
        this.exportToJsonCategoriesByProductsCount(); // Method 9
        this.exportToXmlCategoriesByProductsCount();
        this.exportToJsonUsersAndTheirSoldProducts(); // Method 10
        this.exportToXmlUsersAndTheirSoldProducts();
    }

    private void seedUsersFromJson() {
        StringBuilder seedUsersResult = new StringBuilder();
        if (!this.userService.isUsersTableEmpty()) {
            seedUsersResult.append("Users table is not empty.");
        } else {
            String usersJson = FileUtil.readFile(JSON_RESOURCES_PATH.concat(JSON_USERS));
            UserSeedJsonDto[] userSeedJsonDtos = this.gson.fromJson(usersJson, UserSeedJsonDto[].class);
            Arrays.stream(userSeedJsonDtos)
                    .forEach(userSeedJsonDto ->
                            seedUsersResult.append(this.userService.registerFromJson(userSeedJsonDto))
                                    .append(System.lineSeparator())
                    );
        }
        System.out.println(seedUsersResult.toString().trim());
    }

    private void seedUsersFromXml() {
        if (!this.userService.isUsersTableEmpty()) {
            return;
        }

        String usersXml = FileUtil.readFile(XML_USERS);
        try {
            UserSeedRootDto userSeedRootDto = this.xmlParser.convertObjectFromXml(usersXml, UserSeedRootDto.class);
            userSeedRootDto.getUserSeedXmlDtos()
                    .forEach(this.userService::registerFromXml);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private void seedCategoriesFromJson() {
        StringBuilder seedCategoriesResult = new StringBuilder();
        if (!this.categoryService.isCategoriesTableEmpty()) {
            seedCategoriesResult.append("Categories table is not empty.");
        } else {
            String categoriesJson = FileUtil.readFile(JSON_RESOURCES_PATH.concat(JSON_CATEGORIES));
            CategorySeedJsonDto[] categorySeedJsonDtos = this.gson.fromJson(categoriesJson, CategorySeedJsonDto[].class);
            Arrays.stream(categorySeedJsonDtos)
                    .forEach(categorySeedJsonDto ->
                            seedCategoriesResult.append(this.categoryService.addCategoryFromJson(categorySeedJsonDto))
                                    .append(System.lineSeparator())
                    );
        }

        System.out.println(seedCategoriesResult.toString().trim());
    }

    private void seedCategoriesFromXml() {
        if (!this.categoryService.isCategoriesTableEmpty()) {
            return;
        }

        String categoriesXml = FileUtil.readFile(XML_CATEGORIES);
        try {
            CategorySeedRootDto categorySeedRootDto = this.xmlParser.convertObjectFromXml(categoriesXml, CategorySeedRootDto.class);
            categorySeedRootDto.getCategorySeedXmlDtos()
                    .forEach(this.categoryService::addCategoryFromXml);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private void seedProductsFromJson() {
        StringBuilder seedProductsResult = new StringBuilder();
        if (!this.productService.isProductsTableEmpty()) {
            seedProductsResult.append("Products table is not empty.");
        } else {
            String productsJson = FileUtil.readFile(JSON_RESOURCES_PATH.concat(JSON_PRODUCTS));
            ProductSeedDto[] productSeedDtos = this.gson.fromJson(productsJson, ProductSeedDto[].class);
            Arrays.stream(productSeedDtos)
                    .forEach(productSeedDto ->
                            seedProductsResult.append(this.productService.addProductFromJson(productSeedDto))
                                    .append(System.lineSeparator())
                    );
        }
        System.out.println(seedProductsResult.toString().trim());
    }

    private void seedProductsFromXml() {
        if (!this.productService.isProductsTableEmpty()) {
            return;
        }

        String productsXml = FileUtil.readFile(XML_PRODUCTS);
        try {
            ProductSeedRootDto productSeedRootDto = this.xmlParser.convertObjectFromXml(productsXml, ProductSeedRootDto.class);
            productSeedRootDto.getProductSeedXmlDtos()
                    .forEach(this.productService::addProductFromXml);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private void addCategoriesToProducts() {
        this.productService.addCategoriesToAllProducts(this.categoryService, this.random);
    }

    private void addSellerAndBuyerToProducts() {
        this.productService.addSellerAndBuyerToProducts(this.userService, this.random);
    }

    private void addFriendsToUsers() {
        this.userService.addFriendsToAllUsers(random);
    }

    private void exportToJsonAllProductsInGivenRange() {
        FileUtil.writeToFile(
                this.gson.toJson(this.productService.getAllProductsInGivenPriceRangeForJsonExport(BigDecimal.valueOf(500), BigDecimal.valueOf(1000)), new TypeToken<ArrayList<ProductsShopController>>(){}.getType()),
                JSON_RESOURCES_PATH.concat(JSON_PRODUCTS_IN_RANGE)
        );
    }

    private void exportToXmlAllProductsInGivenRange() throws JAXBException {
        FileUtil.writeToFile(
                this.xmlParser.convertObjectToXml(this.productService.getAllProductsInGivenPriceRangeForXmlExport(BigDecimal.valueOf(500), BigDecimal.valueOf(1000))),
                XML_PRODUCTS_IN_RANGE
        );
    }

    private void exportToJsonAllUsersWhoSoldAtLeastOneProduct() {
        FileUtil.writeToFile(
                this.gson.toJson(this.userService.getAllUsersWithAtLeastOneSoldProductForJsonExport()),
                JSON_RESOURCES_PATH.concat(JSON_USERS_SOLD_PRODUCTS)
        );
    }

    private void exportToXmlAllUsersWhoSoldAtLeastOneProduct() throws JAXBException {
        FileUtil.writeToFile(
                this.xmlParser.convertObjectToXml(this.userService.getAllUsersWithAtLeastOneSoldProductForXmlExport()),
                XML_USERS_SOLD_PRODUCTS
        );
    }

    private void exportToJsonCategoriesByProductsCount() {
        FileUtil.writeToFile(
                this.gson.toJson(this.categoryService.getCategoriesByProductsCountAndPriceForJsonExport()),
                JSON_RESOURCES_PATH.concat(JSON_CATEGORIES_BY_PRODUCTS)
        );
    }

    private void exportToXmlCategoriesByProductsCount() throws JAXBException {
        FileUtil.writeToFile(
                this.xmlParser.convertObjectToXml(this.categoryService.getCategoriesByProductsCountAndPriceForXmlExport()),
                XML_CATEGORIES_BY_PRODUCTS
        );
    }

    private void exportToJsonUsersAndTheirSoldProducts() {
        FileUtil.writeToFile(
                this.gson.toJson(this.userService.getUsersWithSalesForJsonExport()),
                JSON_RESOURCES_PATH.concat(JSON_USERS_AND_PRODUCTS)
        );
    }

    private void exportToXmlUsersAndTheirSoldProducts() throws JAXBException {
        FileUtil.writeToFile(
                this.xmlParser.convertObjectToXml(this.userService.getUsersWithSalesForXmlExport()),
                XML_USERS_AND_PRODUCTS
        );
    }
}