package app.services.category;

import app.constants.Messages;
import app.entities.Category;
import app.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void addNewCategory(Category category) {
        this.categoryRepository.saveAndFlush(category);
    }

    @Override
    public Category getRandomCategory() {
        long categoriesCount = this.categoryRepository.count();
        if (categoriesCount == 0L) {
            throw new IllegalArgumentException(Messages.NO_CATEGORIES_IN_DATABASE);
        }

        Random random = new Random();
        long categoryId = random.nextInt((int) categoriesCount) + 1;
        return this.categoryRepository.getOne(categoryId);
    }

    @Override
    public Set<Category> getRandomCategories() {
        Set<Category> categories = new HashSet<>();

        Random random = new Random();
        int randomCategoriesCount = random.nextInt((int) this.categoryRepository.count()) + 1;
        for (int i = 0; i < randomCategoriesCount; i++) {
            categories.add(getRandomCategory());
        }

        return categories;
    }
}
