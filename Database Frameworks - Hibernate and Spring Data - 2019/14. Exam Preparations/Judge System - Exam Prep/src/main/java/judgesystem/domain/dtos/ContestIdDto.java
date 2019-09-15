package judgesystem.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "contest")
@XmlAccessorType(XmlAccessType.FIELD)
public class ContestIdDto {

    @XmlElement(name = "id")
    private Long id;

    public ContestIdDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
