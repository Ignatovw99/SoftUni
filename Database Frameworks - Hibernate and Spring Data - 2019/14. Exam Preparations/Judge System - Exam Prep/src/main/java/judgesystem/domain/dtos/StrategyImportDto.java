package judgesystem.domain.dtos;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class StrategyImportDto implements Serializable {

    @Expose
    private Long id;

    @Expose
    private String name;

    public StrategyImportDto() {
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
}
