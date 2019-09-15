package productsshop.domain.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoriesReportRootDto {

    @XmlElement(name = "category")
    private List<CategoryReportDto> categoryReportDtos;

    public CategoriesReportRootDto() {
    }

    public List<CategoryReportDto> getCategoryReportDtos() {
        return categoryReportDtos;
    }

    public void setCategoryReportDtos(List<CategoryReportDto> categoryReportDtos) {
        this.categoryReportDtos = categoryReportDtos;
    }
}
