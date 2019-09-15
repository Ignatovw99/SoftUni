package productsshop.domain.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductSeedRootDto {

    @XmlElement(name = "product")
    private List<ProductSeedXmlDto> productSeedXmlDtos;

    public ProductSeedRootDto() {
    }

    public List<ProductSeedXmlDto> getProductSeedXmlDtos() {
        return productSeedXmlDtos;
    }

    public void setProductSeedXmlDtos(List<ProductSeedXmlDto> productSeedXmlDtos) {
        this.productSeedXmlDtos = productSeedXmlDtos;
    }
}
