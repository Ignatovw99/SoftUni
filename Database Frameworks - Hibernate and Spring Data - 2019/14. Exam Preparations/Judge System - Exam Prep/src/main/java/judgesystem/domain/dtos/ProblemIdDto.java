package judgesystem.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "problem")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProblemIdDto implements Serializable {

    @XmlElement(name = "id")
    private Long id;

    public ProblemIdDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
