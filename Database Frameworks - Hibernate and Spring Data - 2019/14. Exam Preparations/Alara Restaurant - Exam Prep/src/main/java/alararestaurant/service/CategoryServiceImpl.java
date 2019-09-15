package alararestaurant.service;

import alararestaurant.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public String exportCategoriesByCountOfItems() {
        StringBuilder stringBuilder = new StringBuilder();

        this.categoryRepository.findAllOrderByItemsCount()
                .forEach(category -> {
                    stringBuilder.append(String.format("Category: %s", category.getName())).append(System.lineSeparator());
                    category.getItems()
                            .forEach(item ->
                                    stringBuilder.append(String.format("---Item Name: %s", item.getName())).append(System.lineSeparator())
                                    .append(String.format("---Item Price: %.2f", item.getPrice())).append(System.lineSeparator())
                                    .append(System.lineSeparator())
                            );
                });

        return stringBuilder.toString().trim();
    }
}
