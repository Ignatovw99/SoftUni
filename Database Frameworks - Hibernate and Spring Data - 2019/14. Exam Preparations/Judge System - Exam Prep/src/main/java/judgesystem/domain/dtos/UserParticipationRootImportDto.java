package judgesystem.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "participations")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserParticipationRootImportDto implements Serializable {

    @XmlElement(name = "participation")
    private List<UserParticipation> userParticipations;

    public UserParticipationRootImportDto() {
    }

    public List<UserParticipation> getUserParticipations() {
        return userParticipations;
    }

    public void setUserParticipations(List<UserParticipation> userParticipations) {
        this.userParticipations = userParticipations;
    }
}
