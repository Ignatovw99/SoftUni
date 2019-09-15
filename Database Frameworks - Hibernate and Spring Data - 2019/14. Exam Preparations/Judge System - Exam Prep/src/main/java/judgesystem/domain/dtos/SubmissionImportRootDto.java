package judgesystem.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "submissions")
@XmlAccessorType(XmlAccessType.FIELD)
public class SubmissionImportRootDto {

    @XmlElement(name = "submission")
    private List<SubmissionImportDto> submissionImportDtos;

    public SubmissionImportRootDto() {
    }

    public List<SubmissionImportDto> getSubmissionImportDtos() {
        return submissionImportDtos;
    }

    public void setSubmissionImportDtos(List<SubmissionImportDto> submissionImportDtos) {
        this.submissionImportDtos = submissionImportDtos;
    }
}
