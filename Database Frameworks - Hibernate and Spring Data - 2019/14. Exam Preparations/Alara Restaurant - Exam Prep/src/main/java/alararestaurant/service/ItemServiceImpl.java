package alararestaurant.service;

import alararestaurant.domain.dtos.ItemImportDto;
import alararestaurant.domain.entities.Category;
import alararestaurant.domain.entities.Item;
import alararestaurant.repository.CategoryRepository;
import alararestaurant.repository.ItemRepository;
import alararestaurant.util.FileUtil;
import alararestaurant.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

import static alararestaurant.constant.FilesPaths.ITEMS_IMPORT_FILE;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    private final CategoryRepository categoryRepository;

    private final FileUtil fileUtil;

    private final Gson gson;

    private final ModelMapper modelMapper;

    private final ValidationUtil validationUtil;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, CategoryRepository categoryRepository, FileUtil fileUtil, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean itemsAreImported() {
        return this.itemRepository.count() > 0;
    }

    @Override
    public String readItemsJsonFile() {
        return this.fileUtil.readFile(ITEMS_IMPORT_FILE);
    }

    @Override
    public String importItems(String items) {
        StringBuilder stringBuilder = new StringBuilder();
        Arrays.stream(this.gson.fromJson(items, ItemImportDto[].class))
                .forEach(itemImportDto -> {
                    Item item = this.modelMapper.map(itemImportDto, Item.class);
                    if (!this.validationUtil.isValid(item)) {
                        stringBuilder.append("Invalid data format.")
                                .append(System.lineSeparator());
                    } else {
                        if (this.itemRepository.existsByName(item.getName())) {
                            stringBuilder.append("Invalid data format.")
                                    .append(System.lineSeparator());
                        } else {
                            Category category = this.categoryRepository.findByName(itemImportDto.getCategory());
                            boolean isCategoryValid = true;
                            if (category == null) {
                                category = new Category();
                                category.setName(itemImportDto.getCategory());
                                isCategoryValid = this.validationUtil.isValid(category);
                                if (!isCategoryValid) {
                                    stringBuilder.append("Invalid data format.")
                                            .append(System.lineSeparator());
                                } else {
                                    category = this.categoryRepository.saveAndFlush(category);
                                }
                            }
                            if (isCategoryValid) {
                                item.setCategory(category);
                                this.itemRepository.saveAndFlush(item);
                                stringBuilder.append(String.format("Record %s successfully imported.", item.getName()))
                                        .append(System.lineSeparator());
                            }
                        }
                    }
                });
        return stringBuilder.toString().trim();
    }
}
