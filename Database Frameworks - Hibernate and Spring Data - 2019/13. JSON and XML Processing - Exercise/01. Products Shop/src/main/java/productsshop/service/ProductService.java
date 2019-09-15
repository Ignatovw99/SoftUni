package productsshop.service;

import productsshop.domain.dto.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

public interface ProductService {

    boolean isProductsTableEmpty();

    String addProductFromJson(ProductSeedDto productSeedDto);

    void addProductFromXml(ProductSeedXmlDto productSeedXmlDto);

    void addCategoriesToAllProducts(CategoryService categoryService, Random random);

    void addSellerAndBuyerToProducts(UserService userService, Random random);

    List<ProductInPriceRangeViewDto> getAllProductsInGivenPriceRangeForJsonExport(BigDecimal lowerBound, BigDecimal higherBound);

    ProductsInRangeRootExportDto getAllProductsInGivenPriceRangeForXmlExport(BigDecimal lowerBound, BigDecimal higherBound);
}
