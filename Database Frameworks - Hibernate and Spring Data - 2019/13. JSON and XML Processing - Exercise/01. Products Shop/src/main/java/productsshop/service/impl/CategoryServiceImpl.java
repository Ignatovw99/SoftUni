package productsshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import productsshop.domain.dto.*;
import productsshop.domain.entity.Category;
import productsshop.domain.entity.Product;
import productsshop.repository.CategoryRepository;
import productsshop.service.CategoryService;
import productsshop.util.EntityMapper;
import productsshop.util.ValidatorUtil;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Transactional
@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public boolean isCategoriesTableEmpty() {
        return this.categoryRepository.count() == 0;
    }

    @Override
    public String addCategoryFromJson(CategorySeedJsonDto categorySeedJsonDto) {
        if (!ValidatorUtil.isValid(categorySeedJsonDto)) {
            return ValidatorUtil.getErrorConstraintMessages(categorySeedJsonDto)
                    .stream()
                    .collect(Collectors.joining(System.lineSeparator()));
        }

        Category category = EntityMapper.map(categorySeedJsonDto, Category.class);
        this.categoryRepository.saveAndFlush(category);
        return "Category was added successfully.";
    }

    @Override
    public void addCategoryFromXml(CategorySeedXmlDto categorySeedXmlDto) {
        Category category = EntityMapper.map(categorySeedXmlDto, Category.class);
        this.categoryRepository.saveAndFlush(category);
    }

    private Category getCategoryById(long id) {
        return this.categoryRepository.findById(id)
                .orElse(null);
    }

    @Override
    public Set<Category> getRandomCategories(Random random) {
        long categoriesCount = this.categoryRepository.count();
        int size = random.nextInt((int) categoriesCount) + 1;

        Set<Category> randomCategories = new HashSet<>();

        for (int i = 0; i < size; i++) {
            int id = random.nextInt((int) categoriesCount) + 1;
            randomCategories.add(this.getCategoryById(id));
        }

        return randomCategories;
    }

    @Override
    public List<CategoryProductsDto> getCategoriesByProductsCountAndPriceForJsonExport() {
        return this.categoryRepository.findAllByProductsCountAverageAndTotalPrice();
    }

    @Override
    public CategoriesReportRootDto getCategoriesByProductsCountAndPriceForXmlExport() {
        CategoriesReportRootDto categoriesReportRootDto = new CategoriesReportRootDto();
        List<CategoryReportDto> categoryReportDtos = this.categoryRepository.findAll()
                .stream()
                .map(category -> {
                    CategoryReportDto categoryReportDto = new CategoryReportDto();
                    categoryReportDto.setName(category.getName());
                    categoryReportDto.setProductsCount(category.getProducts().size());
                    BigDecimal totalPriceSum = category.getProducts()
                            .stream()
                            .map(Product::getPrice)
                            .reduce(BigDecimal.ZERO, BigDecimal::add);
                    BigDecimal averagePrice = totalPriceSum
                            .divide(BigDecimal.valueOf(categoryReportDto.getProductsCount()), RoundingMode.FLOOR);
                    categoryReportDto.setAveragePrice(averagePrice);
                    categoryReportDto.setTotalRevenue(totalPriceSum);
                    return categoryReportDto;
                })
                .collect(Collectors.toList());
        categoriesReportRootDto.setCategoryReportDtos(categoryReportDtos);
        return categoriesReportRootDto;
    }
}
