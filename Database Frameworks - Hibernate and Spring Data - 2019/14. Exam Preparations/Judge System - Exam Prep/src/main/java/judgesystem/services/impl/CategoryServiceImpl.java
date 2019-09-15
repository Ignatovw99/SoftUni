package judgesystem.services.impl;

import com.google.gson.Gson;
import judgesystem.domain.dtos.CategoryImportDto;
import judgesystem.domain.entities.Category;
import judgesystem.repositories.CategoryRepository;
import judgesystem.services.api.CategoryService;
import judgesystem.utils.FileUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final FileUtil fileUtil;

    private final Gson gson;

    private final ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, FileUtil fileUtil, Gson gson, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }

    @Override
    public void importCategories(String categoriesFilePath) {
        String categoriesFileContent = this.fileUtil.readFile(categoriesFilePath);

        CategoryImportDto[] categoryImportDtos = this.gson.fromJson(categoriesFileContent, CategoryImportDto[].class);

        for (CategoryImportDto categoryImportDto : categoryImportDtos) {
            Category parentCategory = this.modelMapper.map(categoryImportDto, Category.class);
            this.setParentCategory(parentCategory.getSubcategories(), parentCategory);
        }
    }

    private void setParentCategory(Set<Category> subcategories, Category parent) {
        parent.setSubcategories(null);

        this.categoryRepository.saveAndFlush(parent);

        if (subcategories == null) {
            return;
        }

        for (Category subcategory : subcategories) {
            this.setParentCategory(subcategory.getSubcategories(), subcategory);
            subcategory.setParentCategory(parent);
        }
    }
}