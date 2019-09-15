package xmlprocessing.domain.dto;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "user_app")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserRootDto implements Serializable {

    @XmlElementWrapper(name = "users")
    @XmlElement(name = "user")
    private List<UserXmlDto> userXmlDtos;

    public UserRootDto() {
    }

    public List<UserXmlDto> getUserXmlDtos() {
        return userXmlDtos;
    }

    public void setUserXmlDtos(List<UserXmlDto> userXmlDtos) {
        this.userXmlDtos = userXmlDtos;
    }

    @Override
    public String toString() {
        return "UserRootDto{" +
                "userXmlDtos=" + userXmlDtos +
                '}';
    }
}
