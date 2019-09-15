package productsshop.service;

import productsshop.domain.dto.CategoriesReportRootDto;
import productsshop.domain.dto.CategoryProductsDto;
import productsshop.domain.dto.CategorySeedJsonDto;
import productsshop.domain.dto.CategorySeedXmlDto;
import productsshop.domain.entity.Category;

import java.util.List;
import java.util.Random;
import java.util.Set;

public interface CategoryService {

    boolean isCategoriesTableEmpty();

    String addCategoryFromJson(CategorySeedJsonDto categorySeedJsonDto);

    void addCategoryFromXml(CategorySeedXmlDto categorySeedXmlDto);

    Set<Category> getRandomCategories(Random random);

    List<CategoryProductsDto> getCategoriesByProductsCountAndPriceForJsonExport();

    CategoriesReportRootDto getCategoriesByProductsCountAndPriceForXmlExport();
}
