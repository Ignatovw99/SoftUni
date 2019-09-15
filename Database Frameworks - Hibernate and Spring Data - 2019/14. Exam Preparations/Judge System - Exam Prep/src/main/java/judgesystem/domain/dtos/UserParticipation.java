package judgesystem.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "participation")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserParticipation implements Serializable {

    @XmlElement(name = "contest")
    private ContestIdDto contestIdDto;

    @XmlElement(name = "user")
    private UserIdDto userIdDto;

    public UserParticipation() {
    }

    public ContestIdDto getContestIdDto() {
        return contestIdDto;
    }

    public void setContestIdDto(ContestIdDto contestIdDto) {
        this.contestIdDto = contestIdDto;
    }

    public UserIdDto getUserIdDto() {
        return userIdDto;
    }

    public void setUserIdDto(UserIdDto userIdDto) {
        this.userIdDto = userIdDto;
    }
}
