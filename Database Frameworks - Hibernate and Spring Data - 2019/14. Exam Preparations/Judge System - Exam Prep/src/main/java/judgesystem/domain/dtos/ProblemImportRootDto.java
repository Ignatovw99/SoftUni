package judgesystem.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "problems")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProblemImportRootDto implements Serializable {

    @XmlElement(name = "problem")
    private List<ProblemImportDto> problemImportDtos;

    public ProblemImportRootDto() {
    }

    public List<ProblemImportDto> getProblemImportDtos() {
        return problemImportDtos;
    }

    public void setProblemImportDtos(List<ProblemImportDto> problemImportDtos) {
        this.problemImportDtos = problemImportDtos;
    }
}
