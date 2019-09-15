package judgesystem.domain.dtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CategoryImportDto implements Serializable {

    @Expose
    private Long id;

    @Expose
    private String name;

    @Expose
    @SerializedName("category")
    private CategoryImportDto parentCategory;

    @Expose
    @SerializedName("categories")
    private CategoryImportDto[] subcategories;

    public CategoryImportDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryImportDto getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(CategoryImportDto parentCategory) {
        this.parentCategory = parentCategory;
    }

    public CategoryImportDto[] getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(CategoryImportDto[] subcategories) {
        this.subcategories = subcategories;
    }
}
