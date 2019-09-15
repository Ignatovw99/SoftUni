package judgesystem.domain.dtos;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

public class ContestImportDto implements Serializable {

    @Expose
    private Long id;

    @Expose
    private String name;

    @Expose
    private CategoryImportDto category;

    @Expose
    private List<StrategyImportDto> allowedStrategies;

    public ContestImportDto() {
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

    public CategoryImportDto getCategory() {
        return category;
    }

    public void setCategory(CategoryImportDto category) {
        this.category = category;
    }

    public List<StrategyImportDto> getAllowedStrategies() {
        return allowedStrategies;
    }

    public void setAllowedStrategies(List<StrategyImportDto> allowedStrategies) {
        this.allowedStrategies = allowedStrategies;
    }
}
