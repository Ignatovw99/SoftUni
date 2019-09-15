package productsshop.domain.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductsInRangeRootExportDto {

    @XmlElement(name = "product")
    private List<ProductInRangeDto> productInRangeDtos;

    public ProductsInRangeRootExportDto() {
    }

    public List<ProductInRangeDto> getProductInRangeDtos() {
        return productInRangeDtos;
    }

    public void setProductInRangeDtos(List<ProductInRangeDto> productInRangeDtos) {
        this.productInRangeDtos = productInRangeDtos;
    }
}
