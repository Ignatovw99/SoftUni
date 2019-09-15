package softuni.workshop.domain.dtos.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "projects")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProjectRootXmlDto {

    @XmlElement(name = "project")
    private List<ProjectImportDto> projectImportDtos;

    public ProjectRootXmlDto() {
    }

    public List<ProjectImportDto> getProjectImportDtos() {
        return projectImportDtos;
    }

    public void setProjectImportDtos(List<ProjectImportDto> projectImportDtos) {
        this.projectImportDtos = projectImportDtos;
    }
}
