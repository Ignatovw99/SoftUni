package app.services.category;

import app.entities.Category;

import java.util.Set;

public interface CategoryService {

    void addNewCategory(Category category);

    Category getRandomCategory();

    Set<Category> getRandomCategories();
}
