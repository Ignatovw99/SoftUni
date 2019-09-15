package productsshop.domain.dto;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "sold-products")
@XmlAccessorType(XmlAccessType.FIELD)
public class SoldProductsDto {

    @XmlAttribute(name = "count")
    private Integer count;

    @XmlElement(name = "product")
    private List<ProductSeedXmlDto> soldProducts;

    public SoldProductsDto() {
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<ProductSeedXmlDto> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(List<ProductSeedXmlDto> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
