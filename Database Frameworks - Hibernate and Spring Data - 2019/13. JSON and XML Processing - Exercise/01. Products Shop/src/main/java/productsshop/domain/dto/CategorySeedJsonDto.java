package productsshop.domain.dto;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class CategorySeedJsonDto implements Serializable {

    @Expose
    private String name;

    public CategorySeedJsonDto() {
    }

    @NotNull(message = "Category can not be null.")
    @Length(min = 3, max = 15, message = "Category length should be between 3 and 15 symbols.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
