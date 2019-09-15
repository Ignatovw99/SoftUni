package softuni.workshop.domain.dtos.view;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class CompanyExportDto implements Serializable {

    @Expose
    private String name;

    public CompanyExportDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
