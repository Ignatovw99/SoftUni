package productsshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import productsshop.domain.dto.*;
import productsshop.domain.entity.Product;
import productsshop.repository.ProductRepository;
import productsshop.service.CategoryService;
import productsshop.service.ProductService;
import productsshop.service.UserService;
import productsshop.util.EntityMapper;
import productsshop.util.ValidatorUtil;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Transactional
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public boolean isProductsTableEmpty() {
        return this.productRepository.count() == 0L;
    }

    @Override
    public String addProductFromJson(ProductSeedDto productSeedDto) {
        if (!ValidatorUtil.isValid(productSeedDto)) {
            return ValidatorUtil.getErrorConstraintMessages(productSeedDto)
                    .stream()
                    .collect(Collectors.joining(System.lineSeparator()));
        }

        Product product = EntityMapper.map(productSeedDto, Product.class);
        this.productRepository.saveAndFlush(product);

        return "Product was added successfully";
    }

    @Override
    public void addProductFromXml(ProductSeedXmlDto productSeedXmlDto) {
        Product product = EntityMapper.map(productSeedXmlDto, Product.class);
        this.productRepository.saveAndFlush(product);
    }

    @Override
    public void addCategoriesToAllProducts(CategoryService categoryService, Random random) {
        this.productRepository.findAll()
                .forEach(product ->
                    categoryService.getRandomCategories(random)
                            .forEach(category -> category.getProducts().add(product))
                );
    }

    @Override
    public void addSellerAndBuyerToProducts(UserService userService, Random random) {
        this.productRepository.findAll()
                .forEach(product -> {
                    product.setSeller(userService.getRandomUser(random));
                    // If length of product name is divisible by 3, a buyer should be added to it
                    if (product.getName().length() % 3 == 0) {
                        product.setBuyer(userService.getRandomUser(random));
                    }
                    this.productRepository.saveAndFlush(product);
                });
    }

    @Override
    public List<ProductInPriceRangeViewDto> getAllProductsInGivenPriceRangeForJsonExport(BigDecimal lowerBound, BigDecimal upperBound) {
        return this.productRepository.findAllByPriceBetweenAndBuyerIsNullOrderByPriceAsc(lowerBound, upperBound)
                .stream()
                .map(product -> EntityMapper.map(product, ProductInPriceRangeViewDto.class))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public ProductsInRangeRootExportDto getAllProductsInGivenPriceRangeForXmlExport(BigDecimal lowerBound, BigDecimal higherBound) {
        ProductsInRangeRootExportDto productsInRangeRootExportDto = new ProductsInRangeRootExportDto();
        List<ProductInRangeDto> productInRangeDtos = this.productRepository.findAllByPriceBetweenAndBuyerIsNullOrderByPriceAsc(lowerBound, higherBound)
                .stream()
                .map(product -> EntityMapper.map(product, ProductInRangeDto.class))
                .collect(Collectors.toList());
        productsInRangeRootExportDto.setProductInRangeDtos(productInRangeDtos);
        return productsInRangeRootExportDto;
    }
}