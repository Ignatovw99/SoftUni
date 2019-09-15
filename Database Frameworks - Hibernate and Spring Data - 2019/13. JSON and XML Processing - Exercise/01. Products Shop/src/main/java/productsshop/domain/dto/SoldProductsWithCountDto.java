package productsshop.domain.dto;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.Set;

public class SoldProductsWithCountDto implements Serializable {

    @Expose
    private Integer count;

    @Expose
    private Set<ProductDto> products;

    public SoldProductsWithCountDto() {
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Set<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductDto> products) {
        this.products = products;
    }
}
