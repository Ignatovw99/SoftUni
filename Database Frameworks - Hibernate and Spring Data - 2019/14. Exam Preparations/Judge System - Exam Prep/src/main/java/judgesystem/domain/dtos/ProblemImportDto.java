package judgesystem.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "problem")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProblemImportDto {

    @XmlElement
    private Long id;

    @XmlElement
    private String name;

    @XmlElement
    private ContestIdDto contest;

    public ProblemImportDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ContestIdDto getContest() {
        return contest;
    }

    public void setContest(ContestIdDto contest) {
        this.contest = contest;
    }
}
