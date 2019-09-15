package productsshop.domain.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategorySeedRootDto {

    @XmlElement(name = "category")
    private List<CategorySeedXmlDto> categorySeedXmlDtos;

    public CategorySeedRootDto() {
    }

    public List<CategorySeedXmlDto> getCategorySeedXmlDtos() {
        return categorySeedXmlDtos;
    }

    public void setCategorySeedXmlDtos(List<CategorySeedXmlDto> categorySeedXmlDtos) {
        this.categorySeedXmlDtos = categorySeedXmlDtos;
    }
}
