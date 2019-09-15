package productsshop.domain.dto;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserSeedRootDto {

    @XmlElement(name = "user")
    private List<UserSeedXmlDto> userSeedXmlDtos;

    public UserSeedRootDto() {
    }

    public List<UserSeedXmlDto> getUserSeedXmlDtos() {
        return userSeedXmlDtos;
    }

    public void setUserSeedXmlDtos(List<UserSeedXmlDto> userSeedXmlDtos) {
        this.userSeedXmlDtos = userSeedXmlDtos;
    }
}
